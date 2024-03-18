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
    Manager mg;

    @Test
    public void createFestival(){
        mg = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456",
                        events, 5, 1, LocalDate.now(), 0);
        int [] a = {20};
        int [] b = {2080};
        double [] p = {150};
        fs = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2024,4,20), Time.valueOf("14:04:00"), Province.COMO, Genre.EDM, Type.FESTIVAL, 3000,
                1, a, b, p, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", 3);
        // fare check su tutti i nomi inseriti, devono essere validi
    }

    @Test
    public void createConcert(){

    }

    @Test
    public void createTheater(){

    }

    @Test
    public void createOther(){

    }
}
