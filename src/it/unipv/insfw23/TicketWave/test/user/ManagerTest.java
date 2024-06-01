package it.unipv.insfw23.TicketWave.test.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.Test;

import java.sql.Blob;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ManagerTest {

    private Manager manager, manager1,manager2,manager3;
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Event> events1 = new ArrayList<>();
    private ArrayList<Event> events2 = new ArrayList<>();
    private int[] seatsremainedfortypecorrectevent;
    int[] ticketsoldfortypecorrectevent;
    double[] pricecorrectevent;
    private Image bl;
    IPaymentAdapter paymentAdapter ;
    @Before
    public void setUp() {
        // Preparazione dei dati per i test
        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", Province.COMO, "1234567890123456", events, 5, 1, LocalDate.now(), 0);
        manager1 = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "Cicalone", Province.PAVIA, "1111111111000000", events1, 5, -1, LocalDate.now(), 0);
        manager2 = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "Cicalone", Province.BARLETTA_ANDRIA_TRANI, "1111111111000000", events2, 5, 1, LocalDate.now(), 8);
        manager3 = new Manager("Mario", "Rossi", "1998-07-06", "mario@gmail.com", "Mario", Province.MILANO, "1111111111222222", events2, 5, 1, LocalDate.of(2024,4,12), 2);
        seatsremainedfortypecorrectevent = new int[]{60};
        ticketsoldfortypecorrectevent = new int[]{15};
        pricecorrectevent = new double[]{35.50};
        bl = null;
    }

    @Test
    public void testCreateFestival() {
        try {
            manager.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), LocalTime.of(20, 30), Province.AGRIGENTO, Genre.ROCK, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2, bl);
            assertEquals(1, manager.getEventlist().size());
        }
        catch(Exception e){
                // TODO: handle exception
                System.out.println(e.getMessage());
            }




    }

    @Test
    public void testDontCreateFestival() {  //caso limite utilizzo un manager che abbia un subscription non accettabile
        try {
            manager1.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), LocalTime.of(20, 30), Province.AGRIGENTO, Genre.ROCK, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2, bl);
        } catch (Exception e) {

            assertEquals("Impossibile Creare l'evento:Festival Test.Non puoi creare altri Eventi", e.getMessage());
        }
    }

    @Test public void testDontCreateFestival2(){ // caso limite utilizzo un manager con numero di eventi creati maggiori al massimo
        try {
            manager2.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), LocalTime.of(20, 30), Province.AGRIGENTO, Genre.ROCK, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2, bl);
        } catch (Exception e) {

            assertEquals("Impossibile Creare l'evento:Festival Test.Non puoi creare altri Eventi", e.getMessage());
        }

    }

    @Test public void testDontCreateFestival3(){ // caso limite utilizzo test con parametri nulli su data, tempo e genere
        try {
            manager2.createFestival(1, null, "City Test", "Location Test", null,null, Province.AGRIGENTO, null, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2, bl);
            assertEquals(1, manager.getEventlist().size());
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    /*

    @Test public void testSetSubscription(){

        SubscriptionHandler subscriptionHandler= SubscriptionHandlerFactory.getInstance().getSubscriptionHandler();

        double price=50.00;
        subscriptionHandler.buySub(manager1,2,paymentAdapter,price);
        assertEquals(2,manager1.getSubscription());




    }

*/



    @Test
    public void testCreateTheater() {
        try {
            manager.createTheater(3, "Theater Test", "City Test", "Location Test", LocalDate.now(), LocalTime.of(20, 30), Province.AGRIGENTO, Genre.DRAMMA, 1, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Checco Zalone,Jerry Calà", "Description Test", "Author Test", bl);
            assertEquals(1, manager.getEventlist().size());
        }
        catch(Exception e){
                // TODO: handle exception
                System.out.println(e.getMessage());
            }

    }


    @Test
    public void testCreateConcert() {
        try {
            manager.createConcert(1, "Concert Test", "City Test", "Location Test", LocalDate.now(), LocalTime.of(20, 30), Province.AGRIGENTO, Genre.POP, 200, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele", "Description Test", bl);
            assertEquals(1, manager.getEventlist().size());
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCreateOther()  {
        try {
            manager.createOther(4, "Other Test", "City Test", "Location Test", LocalDate.now(), LocalTime.of(20, 30), Province.AGRIGENTO, Genre.ROCK, 400, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Comico", "Description Test", bl);
            assertEquals(1, manager.getEventlist().size());
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }



    @Test
    public void testOneMonthPassed() {  //testing su un manager in cui è passato sicuramente più di un mese
        try {
            boolean result = manager3.OneMonthPassed();
            assertTrue(result); // Deve essere falso, poiché non è passato un mese
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }


}
