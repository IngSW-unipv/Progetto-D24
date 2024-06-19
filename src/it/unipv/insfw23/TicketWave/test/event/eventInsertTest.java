package it.unipv.insfw23.TicketWave.test.event;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class eventInsertTest {
    private EventDao eventDao;
    private Event eventFestivalTest;
    private Event eventConcertTest;
    private Event eventTheatreTest;
    private Event eventOtherTest;
    private ArrayList<Event> events = new ArrayList<>();
    private int MAX_EVENTS_FOR_BASE_SUB = 5;

    @Before
    public void setUp(){
        int[] remainedSeats = {20};
        int[] ticketSold = {2080};
        double[] price = {150};
        Image photo = new Image("/it/unipv/insfw23/TicketWave/modelView/imagesResources/eventImageExample.jpg"); // da problemi questa riga (non posso mettere imagine null perchè non è previsto da db)

        Manager manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgio1@example.com", "eminflex", Province.CATANIA, "1234567890123456",
                events, MAX_EVENTS_FOR_BASE_SUB, 1, LocalDate.now(), 0);

        eventFestivalTest = new Festival(35, "Nameless", "Como", "Parco di Como", LocalDate.of(2024, 4, 20), LocalTime.of(14, 4), Province.COMO, Genre.EDM,
                3000, 1, remainedSeats, ticketSold, price, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", photo);

        eventConcertTest = new Concert(1, "Martin Garrix", "Milano", "San Siro", LocalDate.of(2024, 7, 24), LocalTime.of(21, 34), Province.MILANO, Genre.EDM, 2000,
                2, remainedSeats, ticketSold, price, manager, "Martin Garrix", "Concerto di Martin Garrix", photo);

        eventTheatreTest = new Theater(2, "Franchino er Criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2023, 10, 30), LocalTime.of(22, 50), Province.ROMA, Genre.COMMEDIA,
                1200, 3, remainedSeats, ticketSold, price, manager, "Franchino er criminale", "Commedia di Franchino er criminale ", "Paolo", photo);

        eventOtherTest = new Other(3, "Sagra della salsiccia", "Roma", "Mercato de Roma", LocalDate.of(2023, 7, 22), LocalTime.parse("19:00:00"), Province.ROMA, null, 0,
                0, remainedSeats, ticketSold, price, manager, null, "Sagra della salsiccia de Roma, er mejo", photo);
    }

    @Test
    public void insertionTest1(){
        eventDao = new EventDao();
        boolean result = true;

        try{
            eventDao.insertEvent(eventFestivalTest);
            result = true;
        } catch (SQLException e) {
            result = false;
        }

        assertTrue(result);
    }

    @Test
    public void insertionTest2(){
        eventDao = new EventDao();
        boolean result = true;

        try{
            eventDao.insertEvent(eventConcertTest);
            result = true;
        } catch (SQLException e) {
            result = false;
        }

        assertTrue(result);
    }

    @Test
    public void insertionTest3(){
        eventDao = new EventDao();
        boolean result = true;

        try{
            eventDao.insertEvent(eventTheatreTest);
            result = true;
        } catch (SQLException e) {
            result = false;
        }

        assertTrue(result);
    }


    @Test
    public void insertionTest4(){
        eventDao = new EventDao();
        boolean result = true;

        try{
            eventDao.insertEvent(eventOtherTest);
            result = true;
        } catch (SQLException e) {
            result = false;
        }

        assertTrue(result);
    }

}
