package it.unipv.insfw23.TicketWave.test.user;
import it.unipv.insfw23.TicketWave.modelController.factory.payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.payment.MasterPayPayment;
import it.unipv.insfw23.TicketWave.modelDomain.payment.PayPolPayment;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerTest {
    private Customer customer;
    private Customer customer2;
    private ArrayList<Ticket> tickets;
    private IPaymentAdapter paymentpaypal;
    private IPaymentAdapter paymentmastercard;

    private Manager mg;
    private ArrayList<Event> events = new ArrayList<>();
    private Festival fs;
    
    private final int MAX_EVENTS_FOR_FREE_SUB = 1;
	private final int MAX_EVENTS_FOR_BASE_SUB = 5;
	private final int MAX_EVENTS_FOR_PREMIUM_SUB = Short.MAX_VALUE;


    private double points;
   // private Genre[] favoriteGenre ;

    @Before
    public void setUp(){


        Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
        tickets= new ArrayList<>();
        customer=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.BARI,favoriteGenre, 100,tickets);

        mg = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", Province.CAMPOBASSO, "1234567890123456",
                events, MAX_EVENTS_FOR_BASE_SUB, 1, LocalDate.now(), 0);
        int [] a = {20};
        int [] b = {2080};
        double [] p = {125};
        Image bl = null;
        fs = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2024,4,20), LocalTime.parse("14:04:00"), Province.COMO, Genre.EDM, 3000,
                1, a, b, p, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3, bl);

    }



    @Test
    public void buyWithPointsTest() throws Exception  {
        MasterPayPayment mastercard= new MasterPayPayment();
        paymentmastercard = PaymentFactory.getMasterPayAdapter(mastercard);
        customer.buyticket(paymentmastercard,fs,TicketType.BASE,1);
        assertEquals(1,customer.getTicketsList().size());
        assertEquals(100.0, customer.getTicketsList().getFirst().getPrice(), 0);
        assertEquals(10, customer.getPoints(), 0);
    }

    @Test
    public void buyWithoutPointsTest()   {
        try {
            PayPolPayment paypal = new PayPolPayment();
            paymentpaypal = PaymentFactory.getPaypolAdapter(paypal);
            customer.buyticket(paymentpaypal, fs, TicketType.BASE, 0);
            assertEquals(1, customer.getTicketsList().size());
            assertEquals(125.0, customer.getTicketsList().getFirst().getPrice(), 0);
            assertEquals(112, customer.getPoints(), 0);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void ticketTest(){
        Ticket ticket= new Ticket("12345",50.00,TicketType.BASE,12,"lalaprovaprova provolone");
        customer.addTickets(ticket);
        assertEquals(1,customer.getTicketsList().size());
    }

    @Test
    public void isCustomerTest(){
        assertTrue(customer.isCustomer());
    }

    @Test
    public void maxPointsTest() {
        try{

        customer.setPoints(1000);
        PayPolPayment paypal= new PayPolPayment();
        paymentpaypal= PaymentFactory.getPaypolAdapter(paypal);
        customer.buyticket(paymentpaypal,fs,TicketType.BASE,1);
        assertEquals(1,customer.getTicketsList().size());
        assertEquals(0, customer.getTicketsList().getFirst().getPrice(), 0);
        assertEquals(500, customer.getPoints(), 0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void zeroPointsTest() {
        try {
            customer.setPoints(0);
            PayPolPayment paypal = new PayPolPayment();
            paymentpaypal = PaymentFactory.getPaypolAdapter(paypal);
            customer.buyticket(paymentpaypal, fs, TicketType.BASE, 1);
            assertEquals(1, customer.getTicketsList().size());
            assertEquals(125, customer.getTicketsList().getFirst().getPrice(), 0);
            assertEquals(12.0, customer.getPoints(), 0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void pointsExceptionTest(){
        try {
            customer.setPoints(0);
            PayPolPayment paypal = new PayPolPayment();
            paymentpaypal = PaymentFactory.getPaypolAdapter(paypal);
            customer.buyticket(paymentpaypal, fs, TicketType.BASE, 2);
            assertEquals(0, customer.getTicketsList().size());
            assertEquals(0, customer.getPoints(), 0);
        } catch(Exception e){
            assertEquals("Selezionare un numero valido", e.getMessage());
        }
    }
    @Test
    public void favoriteGenreLimitTest(){
        try {
        Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP,Genre.ROCK,Genre.INDIE,Genre.RAP};
        customer2=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.CREMONA,favoriteGenre, 100,tickets);
        customer2.setFavoriteGenre(favoriteGenre);
        } catch(Exception e){
            assertEquals("Impossibile selezionere più di 5 generi", e.getMessage());
        }
    }


}
