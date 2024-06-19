package it.unipv.insfw23.TicketWave.test.event;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.exceptions.AccountAlreadyExistsException;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.application.Platform;
import javafx.scene.image.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class eventInsertTest {
    private EventDao eventDao;
    private ProfileDao profileDao;
    private Manager manager;
    private Event eventFestivalTest;
    private Event eventConcertTest;
    private Event eventTheatreTest;
    private Event eventOtherTest;
    private ArrayList<Event> events = new ArrayList<>();
    private int MAX_EVENTS_FOR_BASE_SUB = 5;
    private boolean condition = true;

    @BeforeClass
    public static void setUp2() {
    	Platform.startup(()->{});
    }
    
    @Before
    public void setUp(){    	
        int[] remainedSeats = {20};
        int[] ticketSold = {2080};
        double[] price = {150};
        
//        System.out.println(condition);
//        if(condition) {
//        	System.out.println("stampa");
//        	Platform.startup(()->{});
//        	this.condition = false;
//        	System.out.println(condition);
//        }
        
        Image photo = new Image("/it/unipv/insfw23/TicketWave/modelView/imagesResources/eventImageExample.jpg"); // da problemi questa riga (non posso mettere imagine null perchè non è previsto da db)

        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgio1@example.com", "eminflex", Province.CATANIA, "1234567890123456",
                events, MAX_EVENTS_FOR_BASE_SUB, 1, LocalDate.now(), 0);

        eventFestivalTest = new Festival(Short.MAX_VALUE-1, "Nameless", "Como", "Parco di Como", LocalDate.of(2024, 4, 20), LocalTime.of(14, 4), Province.COMO, Genre.EDM,
                3000, 1, remainedSeats, ticketSold, price, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", photo);

        eventConcertTest = new Concert(Short.MAX_VALUE-2, "Martin Garrix", "Milano", "San Siro", LocalDate.of(2024, 7, 24), LocalTime.of(21, 34), Province.MILANO, Genre.EDM, 2000,
                1, remainedSeats, ticketSold, price, manager, "Martin Garrix", "Concerto di Martin Garrix", photo);

        eventTheatreTest = new Theater(Short.MAX_VALUE-3, "Franchino er Criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2023, 10, 30), LocalTime.of(22, 50), Province.ROMA, Genre.COMMEDIA,
                1200, 1, remainedSeats, ticketSold, price, manager, "Franchino er criminale", "Commedia di Franchino er criminale ", "Paolo", photo);

        eventOtherTest = new Other(Short.MAX_VALUE-4, "Sagra della salsiccia", "Roma", "Mercato de Roma", LocalDate.of(2023, 7, 22), LocalTime.parse("19:00:00"), Province.ROMA, Genre.OTHER, 0,
                1, remainedSeats, ticketSold, price, manager, null, "Sagra della salsiccia de Roma, er mejo", photo);
    }
    

    @Test
    public void insertionTest1(){
    	profileDao = new ProfileDao();
    	try {
			profileDao.insertManager(manager);
		} catch(AccountAlreadyExistsException e) {
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
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
    	profileDao = new ProfileDao();
    	try {
			profileDao.insertManager(manager);
		} catch(AccountAlreadyExistsException e) {
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
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
    	profileDao = new ProfileDao();
    	try {
			profileDao.insertManager(manager);
		} catch(AccountAlreadyExistsException e) {
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
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
    	profileDao = new ProfileDao();
    	try {
			profileDao.insertManager(manager);
		} catch(AccountAlreadyExistsException e) {
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
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
