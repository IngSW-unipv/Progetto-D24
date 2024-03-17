package it.unipv.insfw23.TicketWave.Test.user;
import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
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


    private double points;
   // private Genre[] favoriteGenre ;

    @Before
    public void setUp(){


        Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
        customer=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",01,favoriteGenre);

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
