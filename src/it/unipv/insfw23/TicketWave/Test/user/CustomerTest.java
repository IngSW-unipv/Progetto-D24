package it.unipv.insfw23.TicketWave.Test.user;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerTest {
    private Customer customer;
    private ArrayList<Ticket> ticketsList= new ArrayList<>();
    private IPaymentAdapter pay;
    private Event event;
    private Manager mg;


    private double points;
   // private Genre[] favoriteGenre ;

    @Before
    public void setUp(){


        Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
        customer=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",01,favoriteGenre);

        mg = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456",
                events, 5, 1, LocalDate.now(), 0);
        int [] a = {20};
        int [] b = {2080};
        double [] p = {150};
        fs = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2024,4,20), Time.valueOf("14:04:00"), Province.COMO, Genre.EDM, Type.FESTIVAL, 3000,
                1, a, b, p, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3);
    }



    @Test
    public void testBuy() throws Exception  {
        customer.buyticket(pay,event,TicketType.BASE,0);
        assertEquals(1,customer.getTicketsList().size());
    }
    // DA SISTEMARE BUY TICKET
    @Test
    public void testTicket(){
        Ticket ticket= new Ticket("12345",50.00,TicketType.BASE);
        customer.addTickets(ticket);
        assertEquals(1,customer.getTicketsList().size());
    }

    @Test
    public void testIsCustomer(){
        assertTrue(customer.isCustomer());
    }



}
