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
        /*LocalDate date;

        // Inizializzazione della variabile LocalDate successivamente
        date = LocalDate.now();
        Time time;

        // Inizializzazione della variabile Time successivamente
        time = Time.valueOf("22:30:00");
        Event concert = new Concert(1,"Cocacola event","Milano","Magazzini Generali",date,time, Province.MILANO,100,1,);*/
    }



    @Test
    public void testBuy() throws Exception  {
        customer.buyticket(pay,event,TicketType.BASE,0);
        assertEquals(1,customer.getTicketsList().size());
    }

    @Test
    public void testIsCustomer(){
        assertTrue(customer.isCustomer());
    }



}
