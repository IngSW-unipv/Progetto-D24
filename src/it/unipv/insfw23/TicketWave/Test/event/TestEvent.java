package it.unipv.insfw23.TicketWave.Test.event;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestEvent {
    private Festival fs;
    private Concert co, co1;
    private Theater th;
    private Other ot;
    private ArrayList<Event> events;
    private Manager mg;

    @Before
    public void setup(){
        mg = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456",
                            events, 5, 1, LocalDate.now(), 0);
        events = new ArrayList<Event>();
    }
    @Test
    public void createFestival(){
        int [] a = {20};
        int [] b = {2080};
        double [] p = {150};
        fs = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2024,4,20), Time.valueOf("14:04:00"), Province.COMO, Genre.EDM, Type.FESTIVAL, 3000,
                1, a, b, p, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3);

    }

    @Test
    public void createConcert(){
        int [] a = {20, 10};
        int [] b = {1480, 490};
        double [] p = {150, 300};
        co = new Concert(1, "Martin Garrix", "Milano", "San Siro", LocalDate.of(2024,7,24), Time.valueOf("21:34:00"), Province.MILANO, Genre.EDM, Type.CONCERT, 2000,
                2, a, b, p, mg, "Martin Garrix", "Concerto di Martin Garrix");
    }

    @Test
    public void createTheater(){
        int [] a = {23, 10, 50};
        int [] b = {477, 490, 150};
        double [] p = {50, 300, 1000};
        th = new Theater(2, "Franchino er Criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2023,10,30), Time.valueOf("22:50:00"), Province.ROMA, Genre.COMMEDIA, Type.THEATER, 1200,
                3, a, b, p, mg, "Franchino er criminale", "Commedia di Franchino er criminale ", "Paolo");
    }

    @Test
    public void createOther(){
        int [] a = {0};
        int [] b = {0};
        double [] p = {0};
        ot = new Other(3, "Sagra della salsiccia", "Roma", "Mercato de Roma", LocalDate.of(2023,7,22), Time.valueOf("19:00:00"), Province.ROMA, null, Type.OTHER, 0,
                0, a, b, p, mg, null, "Sagra della salsiccia de Roma, er mejo");
    }

    // li lascio oppure no i test sotto a questo commento?
    @Test
    public void createLimitCaseConcert(){
        int [] a = {999999999, 999999999, 999999999, 999999999};
        int [] b = {999999999, 999999999, 999999999, 999999999};
        double [] p = {999999999, 999999999, 999999999, 999999999};
        // caso minimo / nullo
        co = new Concert(0, null, null, null, null, null, null, null, null, 0,
                0, null, null, null, null, null, null);

        // caso con stringhe lunghe e valori al limite, numero di sedute > 3 che sono quelle rappresentabili
        co1 = new Concert(100000, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
                            LocalDate.of(10000,10,30), Time.valueOf("24:59:99"), Province.MILANO, Genre.EDM, Type.CONCERT, 999999999,
                4, a, b, p, mg, "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ",
                "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.");
    }

    @Test
    public void countEventsCreatedFromManager(){
        assertEquals(6, mg.getEvent().size()); // controllo che 6 sia la size dell'arrayList di eventi in manager
        for(int i=0; i < 6; i++){
            System.out.println(mg.getEvent().get(i));
        }
    }
    @After
    public void clear(){
        fs = null;
        co = null;
        co1 = null;
        th = null;
        ot = null;
        events.clear();
        mg = null;
        System.gc(); // dico al Garbage Collector di rimuovere dalla memoria gli oggetti inutilizzati
    }
}
