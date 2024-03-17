package it.unipv.insfw23.TicketWave.Test.user;


import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManagerTest {

    private Manager manager,manager1;
    private ArrayList <Event> events= new ArrayList<>();
    private ArrayList <Event> events1= new ArrayList<>();



    @Before
    public void setUp() {
        // Preparazione dei dati per i test
        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456", events, 5, 1, LocalDate.now(), 0);
        manager1= new Manager("Valentino","Chiaro","2000-01-01","valentino@gmail.com","Cicalone",3,"1111111111000000,",events1,5,-1,LocalDate.now(),0);
    }

    @Test
    public void testCreateFestival() throws Exception{
        manager.createFestival(1, "Festival Test", "City Test", LocalDate.now(), "Location Test", Province.AGRIGENTO, 100, 1, new int[]{100}, new double[]{50.0}, Genre.ROCK, manager, new ArrayList<>());
        assertEquals(1, manager.getEvent().size());
    }
    @Test
    public void testDontCreateFestival() throws Exception{  //test per verificare che l'eccezione sia mandata correttamente se ad esempio l'abbonamento di un manager sia scaduto
        Exception exception = assertThrows(Exception.class, () -> {// in questo test non dovrebbe creare l'evento perchè il manager 1 ha l'abbonamento scaduto
                    manager1.createFestival(2, "Festival Test", "City Test", LocalDate.now(), "Location Test", Province.AGRIGENTO, 100, 1, new int[]{100}, new double[]{50.0}, Genre.ROCK, manager1, new ArrayList<>());
                });
                assertEquals("Impossibile Creare l'evento:Festival Test.Non puoi creare altri Eventi", exception.getMessage());


    }

    @Test
    public void testCreateConcert() throws Exception{
        manager.createConcert(1, "Concert Test", "City Test", LocalDate.now(), "Location Test", Province.AGRIGENTO, 200, 1, new int[]{200}, new double[]{100.0}, Genre.POP, manager, new ArrayList<>());
        assertEquals(1, manager.getEvent().size());
    }

    /*
    serve aggiornare la ENUM
    @Test
    public void testCreateTheater()  {
        manager.createTheater(3, "Theater Test", "City Test", LocalDate.now(), "Location Test", Province.AGRIGENTO, 300, 1, new int[]{300}, new double[]{150.0}, Genre.DRAMA, manager, new ArrayList<>(), "Theatre Company Test", "Author Test");
        assertEquals(1, manager.getEvent().size());
    }
*/
    @Test
    public void testCreateOther() throws Exception {
        manager.createOther(4, "Other Test", "City Test", LocalDate.now(), "Location Test", Province.AGRIGENTO, 400, 1, new int[]{400}, new double[]{200.0}, Genre.ROCK , manager, new ArrayList<>(), "Description Test");
        assertEquals(1, manager.getEvent().size());
    }

    @Test
    public void testOneMonthPassed() {
        boolean result= manager.OneMonthPassed();
        assertEquals(false,result); // Deve essere falso, poiché non è passato ancora un mese dalla data di iscrizione
    }

    @Test
    public void testisCustomer(){
        assertFalse(manager.isCustomer());
    }

    //MANCANO I METODI DI CONTROLLO LOGIN E REGISTRAZIONE, VERRANNO COMPLETATI UNA VOLTA CHE ABBIAMO IL DAO COMPLETO









}
