package it.unipv.insfw23.TicketWave.test.user;

import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.exceptions.AccountAlreadyExistsException;
import it.unipv.insfw23.TicketWave.exceptions.GenreNotSelected;
import it.unipv.insfw23.TicketWave.exceptions.WrongPasswordException;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

public class ProfileTest {

    ProfileDao profileDao;
    private Customer customerTest;
    private Manager managerTest;

    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private final int MAX_EVENTS_FOR_BASE_SUB = 5;

    /*
     * al momento del test non devono essere presenti 
     */
    @Before
    public void setUp(){
        this.managerTest = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "passwordsegreta", Province.PAVIA, "1111111111000000", events, MAX_EVENTS_FOR_BASE_SUB, 0, LocalDate.now(), 0);
        Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
        this.customerTest = new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.BARI,favoriteGenre, 0, tickets);
    }
    

    @Test
    public void signUpAndLoginManagerTest(){
    	boolean signUp = false; //condizione vera quando va a buon fine la registrazione del manager
    	boolean login = false; //condizione vera quando va a buon fine l'accesso al manager
    	profileDao = new ProfileDao();
    	Manager managerCheck = null;
    	
    	try {
			profileDao.insertManager(managerTest);
			signUp = true;
		} catch(AccountAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	
    	if(signUp) {
    		try {
    			managerCheck = profileDao.selectManager("valentino@gmail.com", "passwordsegreta");
    			if(!(managerCheck == null)) {
    				login = true;
    			}else {
    				System.out.println("account non trovato");//il metodo selectManager ritorna null se non trova corrispondenze nel db
    			}
    		}catch (WrongPasswordException e) {
    			System.out.println(e.getMessage());
    		}catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	assertTrue(signUp);
    	assertTrue(login);
    	assertTrue(managerCheck(managerTest, managerCheck)); //condizione vera quando il manager prelevato da db coincide con quello registrato
    }


    @Test
    public void signUpAndLoginCustomerTest() {
    	boolean signUp = false; //condizione vera quando va a buon fine la registrazione del customer
    	boolean login = false; //condizione vera quando va a buon fine l'accesso al customer
    	profileDao = new ProfileDao();
    	Customer customerCheck = null;
    	
		try{
	        profileDao.insertCustomer(customerTest);
	        signUp = true;
	    }catch (AccountAlreadyExistsException e) {
	        System.out.println(e.getMessage());
        } catch (GenreNotSelected e) {
            System.out.println(e.getMessage());
	    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		 
	    if(signUp) {
			try {
				customerCheck = profileDao.selectCustomer("mariorossi@gmail.com", "123");
				if(!(customerCheck == null))
					login = true;
				else
					System.out.println("account non trovato");//il metodo selectCustomer ritorna null se non trova corrispondenze nel db
			}catch (WrongPasswordException e) {
				System.out.println(e.getMessage());
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	    }
		 
	    assertTrue(signUp);
     	assertTrue(login);
     	assertTrue(customerCheck(customerTest, customerCheck)); //condizione vera quando il customer prelevato da db coincide con quello registrato
    }

    private boolean managerCheck(Manager manager1, Manager manager2) {
        boolean result = true;
        if (!Objects.equals(manager1.getName(), manager2.getName())) { 
            return false;
        }
        if (!Objects.equals(manager1.getSurname(), manager2.getSurname())) {
            return false;       
        }
        if (!Objects.equals(manager1.getDateOfBirth(), manager2.getDateOfBirth())) {
            return false;
        }
        if (!Objects.equals(manager1.getEmail(), manager2.getEmail())) {
            return false;
        }
        if (!Objects.equals(manager1.getProvinceOfResidence(), manager2.getProvinceOfResidence())) {
            return false;
        }
        if (!Objects.equals(manager1.getCreditCard(), manager2.getCreditCard())) {
            return false;
        }
        if (!Objects.equals(manager1.getEventlist(), manager2.getEventlist())) {
        	return false;
        }
        if (!Objects.equals(manager1.getMaxNumberOfEvents(), manager2.getMaxNumberOfEvents())) {
        	return false;
        }
        if (!Objects.equals(manager1.getSubscription(), manager2.getSubscription())) {
        	return false;
        }
        if (!Objects.equals(manager1.getSubscriptionDate(), manager2.getSubscriptionDate())) {
        	return false;
        }
        if (!Objects.equals(manager1.getCounterCreatedEvents(), manager2.getCounterCreatedEvents())) {
        	return false;
        } 
        
        return result;
    }

    private boolean customerCheck(Customer customer1, Customer customer2){
        boolean result = true;
        if (!Objects.equals(customer1.getName(), customer2.getName())) {
        	return false;
        }
        if (!Objects.equals(customer1.getSurname(), customer2.getSurname())) {
        	return false;
        }
        if (!Objects.equals(customer1.getDateOfBirth(), customer2.getDateOfBirth())) {
        	return false;
        }
        if (!Objects.equals(customer1.getEmail(), customer2.getEmail())) {
        	return false;
        }
        if (!Objects.equals(customer1.getProvinceOfResidence(), customer2.getProvinceOfResidence())) {
        	return false;
        }
        if (!Arrays.equals(customer1.getFavoriteGenre(), customer2.getFavoriteGenre())) {
        	return false;
        }
        if (!Objects.equals(customer1.getPoints(), customer2.getPoints())) {
        	return false;
        }
        if (!Objects.equals(customer1.getTicketsList(), customer2.getTicketsList())) {
        	return false;
        }
        return result;
    }
}
