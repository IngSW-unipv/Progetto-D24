package it.unipv.insfw23.TicketWave.Test.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

public class ManagerTest {

    private Manager manager, manager1,manager2;
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Event> events1 = new ArrayList<>();
    private ArrayList<Event> events2 = new ArrayList<>();
    private int[] seatsremainedfortypecorrectevent;
    int[] ticketsoldfortypecorrectevent;
    double[] pricecorrectevent;
    @Before
    public void setUp() {
        // Preparazione dei dati per i test
        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456", events, 5, 1, LocalDate.now(), 0);
        manager1 = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "Cicalone", 3, "1111111111000000", events1, 5, -1, LocalDate.now(), 0);
        manager2 = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "Cicalone", 3, "1111111111000000", events2, 5, 1, LocalDate.now(), 8);
        seatsremainedfortypecorrectevent = new int[]{60};
        ticketsoldfortypecorrectevent = new int[]{15};
        pricecorrectevent = new double[]{35.50};
    }

    @Test
    public void testCreateFestival() {
        try {
            manager.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), Time.valueOf(LocalTime.of(20, 30)), Province.AGRIGENTO, Genre.ROCK, Type.FESTIVAL, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2);
            assertEquals(1, manager.getEvent().size());
        }
        catch(Exception e){
                // TODO: handle exception
                System.out.println(e.getMessage());
            }




    }

    @Test
    public void testDontCreateFestival() {  //caso limite utilizzo un manager che abbia un subscription non accettabile
        try {
            manager1.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), Time.valueOf(LocalTime.of(20, 30)), Province.AGRIGENTO, Genre.ROCK, Type.FESTIVAL, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2);
        } catch (Exception e) {

            assertEquals("Impossibile Creare l'evento:Festival Test.Non puoi creare altri Eventi", e.getMessage());
        }
    }

    @Test public void testDontCreateFestival2(){ // caso limite utilizzo un manager con numero di eventi creati maggiori al massimo
        try {
            manager2.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), Time.valueOf(LocalTime.of(20, 30)), Province.AGRIGENTO, Genre.ROCK, Type.FESTIVAL, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2);
        } catch (Exception e) {

            assertEquals("Impossibile Creare l'evento:Festival Test.Non puoi creare altri Eventi", e.getMessage());
        }

    }

    @Test public void testDontCreateFestival3(){ // caso limite utilizzo test con parametri nulli su data, non si può creare un evento con data nulla  DA SISTEMARE LO ISTANZIA ANCORA
        try {
            manager2.createFestival(1, null, "City Test", "Location Test", null,null, Province.AGRIGENTO, null, Type.FESTIVAL, 100, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele,Calcutta", "Description Test", 2);
            assertEquals(2, manager.getEvent().size());
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }







    @Test
    public void testCreateTheater() {
        try {
            manager.createTheater(3, "Theater Test", "City Test", "Location Test", LocalDate.now(), Time.valueOf(LocalTime.of(20, 30)), Province.AGRIGENTO, Genre.DRAMMA, Type.THEATER, 1, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Checco Zalone,Jerry Calà", "Description Test", "Author Test");
            assertEquals(1, manager.getEvent().size());
        }
        catch(Exception e){
                // TODO: handle exception
                System.out.println(e.getMessage());
            }

    }


    @Test
    public void testCreateConcert() {
        try {
            manager.createConcert(1, "Concert Test", "City Test", "Location Test", LocalDate.now(), Time.valueOf(LocalTime.of(20, 30)), Province.AGRIGENTO, Genre.POP, Type.CONCERT, 200, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Pino Daniele", "Description Test");
            assertEquals(1, manager.getEvent().size());
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCreateOther()  {
        try {
            manager.createOther(4, "Other Test", "City Test", "Location Test", LocalDate.now(), Time.valueOf(LocalTime.of(20, 30)), Province.AGRIGENTO, Genre.ROCK, Type.OTHER, 400, 1, seatsremainedfortypecorrectevent, ticketsoldfortypecorrectevent, pricecorrectevent, manager, "Comico", "Description Test");
            assertEquals(1, manager.getEvent().size());
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }



    @Test
    public void testOneMonthPassed() {
        try {
            boolean result = manager.OneMonthPassed();
            assertFalse(result); // Deve essere falso, poiché non è passato un mese
        }
        catch(Exception e){
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }


}
