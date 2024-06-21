package it.unipv.insfw23.TicketWave.test.event;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Test the creation of an event like a {@link Concert}, {@link Festival}, {@link Theater}, {@link Other}.
 */

public class TestEvent {
    private Festival fs;
    private Concert co;
    private Theater th;
    private Other ot;
    private ArrayList<Event> events;
    private Manager mg;
    private Image bl;
    private final int MAX_EVENTS_FOR_BASE_SUB = 5;
    private final int MAX_EVENTS_FOR_PREMIUM_SUB = Short.MAX_VALUE;
    
    @Before
    public void setup(){
        events = new ArrayList<>();
        mg = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", Province.CATANIA, "1234567890123456",
                            events, MAX_EVENTS_FOR_BASE_SUB, 1, LocalDate.now(), 0);
        bl = null;
    }
    
    @Test
    public void createFestivalTest(){
        
        int[] seatsRemainedForType = {20};
        int[] ticketSoldForType = {2080};
        double[] prices = {150};
        fs = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2025, 4, 20), LocalTime.of(14, 4), Province.COMO, Genre.EDM,
                2100, 1, seatsRemainedForType, ticketSoldForType, prices, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", bl);
        
        assertEquals(0, fs.getIdEvent());
        assertEquals("Nameless", fs.getName());
        assertEquals("Como", fs.getCity());
        assertEquals("Parco di Como", fs.getLocation());
        assertEquals("2025-04-20", fs.getDate().toString());
        assertEquals("14:04", fs.getTime().toString());
        assertEquals("COMO", fs.getProvince().toString());
        assertEquals("EDM", fs.getGenre().toString());
        assertEquals(2100, fs.getMaxNumberOfSeats());
        assertEquals(1, fs.getTypeOfSeats());
        assertArrayEquals(new int[] {20}, fs.getSeatsRemainedNumberForType());
        assertArrayEquals(new int[] {2080}, fs.getTicketsSoldNumberForType());
        assertArrayEquals(new double[] {150}, fs.getPrices(), 0);
        assertEquals("giorgiom@example.com", fs.getCreator().getEmail());
        assertEquals("Rooler, Salmo, Nello Taver", fs.getArtists());
        assertEquals("Festival di musica EDM", fs.getDescription());
        assertEquals(null, fs.getPhoto());
            
    }

    @Test
    public void createConcertTest(){
        
        int[] seatsRemainedForType = {20, 10};
        int[] ticketsoldForType = {1480, 490};
        double[] prices = {150, 300};
        co = new Concert(1, "Martin Garrix", "Milano", "San Siro", LocalDate.of(2024, 7, 24), LocalTime.of(21, 34), Province.MILANO, Genre.EDM, 2000,
                2, seatsRemainedForType, ticketsoldForType, prices, mg, "Martin Garrix", "Concerto di Martin Garrix", bl);
        
        assertEquals(1, co.getIdEvent());
        assertEquals("Martin Garrix", co.getName());
        assertEquals("Milano", co.getCity());
        assertEquals("San Siro", co.getLocation());
        assertEquals("2024-07-24", co.getDate().toString());
        assertEquals("21:34", co.getTime().toString());
        assertEquals("MILANO", co.getProvince().toString());
        assertEquals("EDM", co.getGenre().toString());
        assertEquals(2000, co.getMaxNumberOfSeats());
        assertEquals(2, co.getTypeOfSeats());
        assertArrayEquals(new int[] {20,10}, co.getSeatsRemainedNumberForType());
        assertArrayEquals(new int[] {1480,490}, co.getTicketsSoldNumberForType());
        assertArrayEquals(new double[] {150,300}, co.getPrices(), 0);
        assertEquals("giorgiom@example.com", co.getCreator().getEmail());
        assertEquals("Martin Garrix", co.getArtists());
        assertEquals("Concerto di Martin Garrix", co.getDescription());
        assertEquals(null, co.getPhoto());
    }

    @Test
    public void createTheaterTest(){
    
        int[] seatsRemainedForType = {23, 10, 50};
        int[] ticketSoldForType = {477, 490, 150};
        double[] prices = {50, 300, 1000};
        th = new Theater(2, "Franchino er criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2025, 10, 30), LocalTime.of(22, 50), Province.ROMA, Genre.COMMEDIA,
                1200, 3, seatsRemainedForType, ticketSoldForType, prices, mg, "Franchino er criminale", "Commedia di Franchino er criminale", "Paolo", bl);
    
        assertEquals(2, th.getIdEvent());
        assertEquals("Franchino er criminale", th.getName());
        assertEquals("Roma", th.getCity());
        assertEquals("Teatro de Tivoli", th.getLocation());
        assertEquals("2025-10-30", th.getDate().toString());
        assertEquals("22:50", th.getTime().toString());
        assertEquals("ROMA", th.getProvince().toString());
        assertEquals("COMMEDIA", th.getGenre().toString());
        assertEquals(1200, th.getMaxNumberOfSeats());
        assertEquals(3, th.getTypeOfSeats());
        assertArrayEquals(new int[] {23, 10, 50}, th.getSeatsRemainedNumberForType());
        assertArrayEquals(new int[] {477, 490, 150}, th.getTicketsSoldNumberForType());
        assertArrayEquals(new double[] {50, 300, 1000}, th.getPrices(), 0);
        assertEquals("giorgiom@example.com", th.getCreator().getEmail());
        assertEquals("Franchino er criminale", th.getArtists());
        assertEquals("Commedia di Franchino er criminale", th.getDescription());
        assertEquals("Paolo", th.getAuthorName());
        assertEquals(null, th.getPhoto());
    
    }

    @Test
    public void createOtherTest(){
    
        int[] seatsRemainedForType = {70,20};
        int[] ticketSoldForType = {6,4};
        double[] prices = {80,200};
        ot = new Other(3, "Sagra della salsiccia", "Roma", "Mercato de Roma", LocalDate.of(2025, 7, 22), LocalTime.parse("19:00:00"), Province.ROMA, Genre.ALTRO, 
        		100, 2, seatsRemainedForType, ticketSoldForType, prices, mg, "Califano", "Sagra della salsiccia de Roma, er mejo", bl);
        
        assertEquals(3, ot.getIdEvent());
        assertEquals("Sagra della salsiccia", ot.getName());
        assertEquals("Roma", ot.getCity());
        assertEquals("Mercato de Roma", ot.getLocation());
        assertEquals("2025-07-22", ot.getDate().toString());
        assertEquals("19:00", ot.getTime().toString());
        assertEquals("ROMA", ot.getProvince().toString());
        assertEquals("ALTRO", ot.getGenre().toString());
        assertEquals(100, ot.getMaxNumberOfSeats());
        assertEquals(2, ot.getTypeOfSeats());
        assertArrayEquals(new int[] {70, 20}, ot.getSeatsRemainedNumberForType());
        assertArrayEquals(new int[] {6, 4}, ot.getTicketsSoldNumberForType());
        assertArrayEquals(new double[] {80, 200}, ot.getPrices(), 0);
        assertEquals("giorgiom@example.com", ot.getCreator().getEmail());
        assertEquals("Califano", ot.getArtists());
        assertEquals("Sagra della salsiccia de Roma, er mejo", ot.getDescription());
        assertEquals(null, ot.getPhoto());
    
    }

    @Test
    public void checkEventsCreatedFromManagerTest(){

        //creo degli eventi
        int[] a = {920};
        int[] b = {2080};
        double[] p = {150};
        Festival festival = new Festival(0, "Nameless", "Como", "Parco di Como", LocalDate.of(2025, 4, 20), LocalTime.parse("14:04:00"), Province.COMO, Genre.EDM, 3000,
                1, a, b, p, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", bl);
        Festival festival2 = new Festival(1, "Nameless", "Como", "Parco di Como", LocalDate.of(2025, 4, 20), LocalTime.parse("14:04:00"), Province.COMO, Genre.EDM, 3000,
                1, a, b, p, mg, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", bl);
        Other other = new Other(3, "Sagra della salsiccia", "Roma", "Mercato de Roma", LocalDate.of(2025, 7, 22), LocalTime.parse("19:00:00"), Province.ROMA, Genre.ALTRO, 3000,
                1, a, b, p, mg, "Califano", "Sagra della salsiccia de Roma, er mejo", bl);
        Theater theater = new Theater(2, "Franchino er Criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2025, 10, 30), LocalTime.parse("22:50:00"), Province.ROMA, Genre.COMMEDIA,
                3000, 1, a, b, p, mg, "Franchino er criminale", "Commedia di Franchino er criminale ", "Paolo", bl);
        Theater theater1 = new Theater(5, "Franchino er Criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2025, 10, 30), LocalTime.parse("22:50:00"), Province.ROMA, Genre.COMMEDIA,
                3000, 1, a, b, p, mg, "Franchino er criminale", "Commedia di Franchino er criminale ", "Paolo", bl);
        // popolo l'array list di Eventi
        ArrayList<Event> ev1 = new ArrayList<>();
        ev1.add(festival);
        ev1.add(festival2);
        ev1.add(other);
        ev1.add(theater);
        ev1.add(theater1);
        // creo il manager
        Manager mg1 = new Manager("Paolo", "Bisio", "1970-02-07", "Paolo@example.com", "dajeRoma", Province.ROMA, "423432523523",
                ev1, MAX_EVENTS_FOR_PREMIUM_SUB, 2, LocalDate.now(), 0);
        // check + stampa delle reference degli eventi
        assertEquals(5, mg1.getEventlist().size()); // controllo che 5 sia la size dell'arrayList di eventi in manager
   
    }

    @After
    public void clear(){
        fs = null;
        co = null;
        th = null;
        ot = null;
        events.clear();
        mg = null;
        
    }
}
