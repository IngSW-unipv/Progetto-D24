package it.unipv.insfw23.TicketWave.Test.event;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestEvent {
    private Festival fs;
    private Concert co;
    private Theater th;
    private Other ot;
    private ArrayList<Event> events = new ArrayList<>();
    Manager mg = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456",
                            events, 5, 1, LocalDate.now(), 0);;

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
}
