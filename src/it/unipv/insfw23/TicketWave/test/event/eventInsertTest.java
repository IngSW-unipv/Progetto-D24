package it.unipv.insfw23.TicketWave.test.event;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.exceptions.AccountAlreadyExistsException;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.application.Platform;
import javafx.scene.image.Image;

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
    
    /*
     * i 4 ID degli eventi di test vengono calcolati come i 4 numeri precedenti alla costante MAX_ID
     * e questi numeri non devono essere presenti nel database al momento del test
     */
    private final int MAX_ID = Short.MAX_VALUE; 
    											
    private final int MAX_EVENTS_FOR_BASE_SUB = 5;


    @BeforeClass
    public static void setUp2() {
    	Platform.startup(()->{}); //avvia il runtime di JavaFX per poter usare la classe Image
    }
    
    @Before
    public void setUp(){    	
        int[] remainedSeats = {20};
        int[] ticketSold = {2080};
        int maxSeats = 2100;
        double[] price = {150};
        
        Image photo = new Image("/it/unipv/insfw23/TicketWave/modelView/imagesResources/eventImageExample.jpg"); 

        manager = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgio1@example.com", "eminflex", Province.CATANIA, "1234567890123456",
                events, MAX_EVENTS_FOR_BASE_SUB, 1, LocalDate.now(), 0);

        eventFestivalTest = new Festival(MAX_ID-1, "Nameless", "Como", "Parco di Como", LocalDate.of(2024, 4, 20), LocalTime.of(14, 4), Province.COMO, Genre.EDM,
                maxSeats, 1, remainedSeats, ticketSold, price, manager, "Rooler, Salmo, Nello Taver", "Festival di musica EDM", photo);

        eventConcertTest = new Concert(MAX_ID-2, "Martin Garrix", "Milano", "San Siro", LocalDate.of(2024, 7, 24), LocalTime.of(21, 34), Province.MILANO, Genre.EDM,
        		maxSeats, 1, remainedSeats, ticketSold, price, manager, "Martin Garrix", "Concerto di Martin Garrix", photo);

        eventTheatreTest = new Theater(MAX_ID-3, "Franchino er Criminale", "Roma", "Teatro de Tivoli", LocalDate.of(2023, 10, 30), LocalTime.of(22, 50), Province.ROMA, Genre.COMMEDIA,
                maxSeats, 1, remainedSeats, ticketSold, price, manager, "Franchino er criminale", "Commedia di Franchino er criminale ", "Paolo", photo);

        eventOtherTest = new Other(MAX_ID-4, "Sagra della salsiccia", "Roma", "Mercato de Roma", LocalDate.of(2023, 7, 22), LocalTime.parse("19:00:00"), Province.ROMA, Genre.OTHER,
        		maxSeats, 1, remainedSeats, ticketSold, price, manager, null, "Sagra della salsiccia de Roma, er mejo", photo);
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
        boolean result = false;

        try{
            eventDao.insertEvent(eventFestivalTest);
            result = true;
        } catch(SQLException e) {
        	System.err.println(e.getMessage());
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
        boolean result = false;

        try{
            eventDao.insertEvent(eventConcertTest);
            result = true;
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
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
        boolean result = false;

        try{
            eventDao.insertEvent(eventTheatreTest);
            result = true;
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
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
        boolean result = false;

        try{
            eventDao.insertEvent(eventOtherTest);
            result = true;
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        }

        assertTrue(result);
    }

}
