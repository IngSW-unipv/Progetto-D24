package it.unipv.insfw23.TicketWave.Test.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

public class ManagerTest {

    private Manager manager, manager1;
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Event> events1 = new ArrayList<>();

    @Before
    public void setUp() {
        // Preparazione dei dati per i test
        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456", events, 5, 1, LocalDate.now(), 0);
        manager1 = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "Cicalone", 3, "1111111111000000", events1, 5, -1, LocalDate.now(), 0);
    }

    @Test
    public void testCreateFestival() throws Exception {
        manager.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), null, Province.AGRIGENTO, Genre.ROCK, Type.FESTIVAL, 100, 1, new int[]{100}, new int[]{0}, new double[]{50.0}, manager, "Pino Daniele,Calcutta", "Description Test", 2);
        assertEquals(1, manager.getEvent().size());
    }

    @Test
    public void testDontCreateFestival() {
        Exception exception = assertThrows(Exception.class, () -> {
            manager1.createFestival(1, "Festival Test", "City Test", "Location Test", LocalDate.now(), null, Province.AGRIGENTO, Genre.ROCK, Type.FESTIVAL, 100, 1, new int[]{100}, new int[]{0}, new double[]{50.0}, manager, "Pino Daniele,Calcutta", "Description Test", 2);
        });
        assertEquals("Impossibile Creare l'evento:Festival Test.Non puoi creare altri Eventi", exception.getMessage());
    }




    @Test
    public void testCreateTheater() throws Exception {
        manager.createTheater(3, "Theater Test", "City Test", "Location Test", LocalDate.now(), null, Province.AGRIGENTO, Genre.DRAMMA, 300, 1, new int[]{300}, new int[]{0}, new double[]{150.0}, manager, new ArrayList<>(), "Description Test", "Author Test");
        assertEquals(1, manager.getEvent().size());
    }


    @Test
    public void testCreateConcert() throws Exception {
        manager.createConcert(1, "Concert Test", "City Test", "Location Test", LocalDate.now(), null, Province.AGRIGENTO, Genre.POP, Type.CONCERT, 200, 1, new int[]{200}, new int[]{0}, new double[]{100.0}, manager, "Pino Daniele", "Description Test");
        assertEquals(1, manager.getEvent().size());
    }

    @Test
    public void testCreateOther() throws Exception {
        manager.createOther(4, "Other Test", "City Test", "Location Test", LocalDate.now(), null, Province.AGRIGENTO, Genre.ROCK, Type.OTHER, 400, 1, new int[]{400}, new int[]{0}, new double[]{200.0}, manager, "Comico", "Description Test");
        assertEquals(1, manager.getEvent().size());
    }



    @Test
    public void testOneMonthPassed() {
        boolean result = manager.OneMonthPassed();
        assertFalse(result); // Deve essere falso, poiché non è passato un mese
    }


}
