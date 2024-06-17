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
import java.util.Objects;

import static org.junit.Assert.*;

public class profileTest {

    ProfileDao profileDao;
    private Customer customerTest;
    private Manager managerTest;

    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private final int MAX_EVENTS_FOR_BASE_SUB = 5;


    @Before
    public void setUp(){
        this.managerTest = new Manager("Valentino", "Chiaro", "2000-01-01", "valentino@gmail.com", "passwordsegreta", Province.PAVIA, "1111111111000000", events, MAX_EVENTS_FOR_BASE_SUB, -1, LocalDate.now(), 0);
        Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
        this.customerTest = new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.BARI,favoriteGenre, 100, tickets);
    }
    @Test
    public void signUpTest1() {
        profileDao = new ProfileDao();
        boolean result = true;

        try{
            profileDao.insertManager(managerTest);
            result = true;
        }catch (AccountAlreadyExistsException e) {
            result = false;
        } catch (SQLException e) {
            result = false;
        }

        assertTrue(result);
    }


    @Test
    public void loginTest1(){
        profileDao = new ProfileDao();
        boolean result = true;
        Manager managerCheck = null;

        try{
            managerCheck = profileDao.selectManager("valentino@gmail.com", "passwordsegreta");
            result = true;

        }catch (WrongPasswordException e) {
            result = false;
        }catch (SQLException e) {
            result = false;
        }

        assertTrue(managerCheck(managerTest, managerCheck));
        assertTrue(result);
    }



    @Test
    public void signUpTest2() {
        profileDao = new ProfileDao();
        boolean result = true;

        try{
            profileDao.insertCustomer(customerTest);
            result = true;

        }catch (AccountAlreadyExistsException e) {
            result = false;
        } catch (GenreNotSelected e) {
            result = false;
        } catch (SQLException e) {
            result = false;
        }

        assertTrue(result);
    }


    @Test
    public void loginTest2(){
        profileDao = new ProfileDao();
        boolean result = true;
        Customer customerCheck = null;

        try{
            customerCheck = profileDao.selectCustomer("mariorossi@gmail.com", "123");
            result = true;

        }  catch (WrongPasswordException e) {
            result = false;
        }catch (SQLException e) {
            result = false;
        }

        assertTrue(customerCheck(customerTest, customerCheck));
        assertTrue(result);
    }



    private boolean managerCheck(Manager manager1, Manager manager2) {
        boolean result = true;
        if (Objects.equals(manager1.getName(), manager2.getName())) {
            result = true;
        }
        if (Objects.equals(manager1.getSurname(), manager2.getSurname())) {
            result = true;
        }
        if (Objects.equals(manager1.getDateOfBirth(), manager2.getDateOfBirth())) {
            result = true;
        }
        if (Objects.equals(manager1.getEmail(), manager2.getEmail())) {
            result = true;
        }
        if (Objects.equals(manager1.getProvinceOfResidence(), manager2.getProvinceOfResidence())) {
            result = true;
        }
        if (Objects.equals(manager1.getCreditCard(), manager2.getCreditCard())) {
            result = true;
        }
        if (Objects.equals(manager1.getEventlist(), manager2.getEventlist())) {
            result = true;
        }
        if (Objects.equals(manager1.getMaxNumberOfEvents(), manager2.getMaxNumberOfEvents())) {
            result = true;
        }
        if (Objects.equals(manager1.getSubscription(), manager2.getSubscription())) {
            result = true;
        }
        if (Objects.equals(manager1.getSubscriptionDate(), manager2.getSubscriptionDate())) {
            result = true;
        }
        if (Objects.equals(manager1.getCounterCreatedEvents(), manager2.getCounterCreatedEvents())) {
            result = true;
        } else {
            return false;
        }

        return result;
    }

    private boolean customerCheck(Customer customer1, Customer customer2){
        boolean result = true;
        if (Objects.equals(customer1.getName(), customer2.getName())) {
            result = true;
        }
        if (Objects.equals(customer1.getSurname(), customer2.getSurname())) {
            result = true;
        }
        if (Objects.equals(customer1.getDateOfBirth(), customer2.getDateOfBirth())) {
            result = true;
        }
        if (Objects.equals(customer1.getEmail(), customer2.getEmail())) {
            result = true;
        }
        if (Objects.equals(customer1.getProvinceOfResidence(), customer2.getProvinceOfResidence())) {
            result = true;
        }
        if (Objects.equals(customer1.getFavoriteGenre(), customer2.getFavoriteGenre())) {
            result = true;
        }
        if (Objects.equals(customer1.getPoints(), customer2.getPoints())) {
            result = true;
        }
        if (Objects.equals(customer1.getTicketsList(), customer2.getTicketsList())) {
            result = true;
        }
        else{ return false;}

        return result;
    }
}
