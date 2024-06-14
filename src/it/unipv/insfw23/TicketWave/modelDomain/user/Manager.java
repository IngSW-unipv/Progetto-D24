package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.modelController.controller.access.LoginController;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import javafx.scene.image.Image;

import java.util.ArrayList;

import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * Manager domain class extends the User class for common attributes and methods

 */

public class Manager extends User {
    private String creditCard;
    private  int maxNumberOfEvents;
    private   int subscription;
    private ArrayList <Event> event;
    private LocalDate subscriptionDate;
    private int counterCreatedEvents;

    private final int MAX_EVENTS_FOR_FREE_SUB = 1;
   	private final int MAX_EVENTS_FOR_BASE_SUB = 5;
   	private final int MAX_EVENTS_FOR_PREMIUM_SUB = Short.MAX_VALUE;

    /**
     * Manager Constructor use some attributes of the User class
     * @param name
     * @param surname
     * @param dateOfBirth
     * @param email
     * @param password
     * @param provinceOfResidence
     *
     * and some other that are specified for the class itself
     * @param creditCard
     * @param event
     * @param maxNumberOfEvents
     * @param subscription
     * @param subscriptionDate
     * @param counterCreatedEvents set to zero in the insertManager method in the {@link ProfileDao}
     *
     *
     *
     */

    public Manager(String name, String surname, String dateOfBirth, String email, String password, Province provinceOfResidence, String creditCard, ArrayList <Event> event , int maxNumberOfEvents, int subscription, LocalDate subscriptionDate, int counterCreatedEvents){
        super (name,surname,dateOfBirth,email,password,provinceOfResidence);
        this.creditCard=creditCard;
        this.event=event;
        this.maxNumberOfEvents = maxNumberOfEvents;
        this.subscription=subscription;
        this.counterCreatedEvents=counterCreatedEvents;
        this.subscriptionDate =subscriptionDate;

        //contatore per eventi creati impostato a 0 dal momento che si istanzia il Manager
        //imposto subscription=0 di default,in modo tale che non appena viene creato non presenta nessun abbonamento
        // poi gli viene assegnanto dai metodi di SubscriptionHandler
        //in più il subscriptionHandler deve anche impostare il maxnumberofevents per subscription =1 o 2


    }

    //getters and setters


    /**
     * Returns MaxNumberOfEvents as an int.
     * @return MaxNumberOfEvents
     */
    public int getMaxNumberOfEvents() {
        return maxNumberOfEvents;
    }

    /**
     *
     * @return Subscription as an int
     */
    public int getSubscription() {
        return subscription;
    }

    /**
     * In addition to the registration, the method also sets the values of counterCreatedevents=0
     * The maximum number of events that can be created by the Manager is set based on the subscription value.
     * @param subscription
     *
     */
    public void setSubscription(int subscription) {
        this.subscription = subscription;
        this.subscriptionDate=LocalDate.now(); // setto anche la data per compattare il metodo
        this.counterCreatedEvents = 0;
        switch(subscription) {
        case 0:
        	this.maxNumberOfEvents = MAX_EVENTS_FOR_FREE_SUB;
        	break;
        case 1:
        	this.maxNumberOfEvents = MAX_EVENTS_FOR_BASE_SUB;
        	break;
        case 2:
        	this.maxNumberOfEvents = MAX_EVENTS_FOR_PREMIUM_SUB;
        	break;
        }
    }

    /**
     *
     * @return CreditCard as a {@link String}.
     */
    public String getCreditCard() {
        return creditCard;
    }


    /**
     *
     * @return SubscriptionDate as {@link LocalDate}.
     */
    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    /**
     *
     * @return CounterCreatedEvents as an int.
     */
    public int getCounterCreatedEvents() {
        return counterCreatedEvents;
    }

    /**
     *An Event is set by passing an {@link ArrayList} of events
     * @param event
     */
    public void setEvent(ArrayList<Event> event) {
        this.event = event;
    }

    /**
     * The method allows you to create a {@link Festival} only if the registration value is 0 or 1 and CounterCreatedEvents is
     * less than the maximum Events that have been created or if the registration value is 2 (infinite events can be created).
     * Otherwise, it throws an exception on stdout.
     *
     * @param idEvent
     * @param name
     * @param city
     * @param location
     * @param date
     * @param time
     * @param province
     * @param genre
     * @param maxNumberOfSeats
     * @param typeOfSeats
     * @param seatsRemainedNumberForType
     * @param ticketsSoldNumberForType
     * @param price
     * @param creator
     * @param artists
     * @param description
     * @param artistsNumber
     * @param photo
     * @return {@link Festival}
     * @throws Exception
     */


    //***** Metodi di creazione degli Eventi*****
    public Festival createFestival(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, int artistsNumber, Image photo) throws Exception {
        if(((subscription == 1 || subscription == 0) && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Festival festival = new Festival(idEvent, name, city,location,date,time,province,genre,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,ticketsSoldNumberForType,price,creator,artists,description,artistsNumber,photo);
            event.add(festival);
            counterCreatedEvents++;
            return festival;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    /**
     * The method allows you to create a {@link Concert} Event  only if the subscription value is 0 or 1 and CounterCreatedEvents is
     * less than the maximum Events that have been created or if the registration value is 2 (infinite events can be created).
     * Otherwise, it throws an exception on stdout.
     *
     * @param idEvent
     * @param name
     * @param city
     * @param location
     * @param date
     * @param time
     * @param province
     * @param genre
     * @param maxNumberOfSeats
     * @param typeOfSeats
     * @param seatsRemainedNumberForType
     * @param ticketsSoldNumberForType
     * @param price
     * @param creator
     * @param artists
     * @param description
     * @param photo
     * @return {@link Concert}
     * @throws Exception
     */
    public Concert createConcert(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Image photo) throws Exception{
        if(((subscription == 1 || subscription == 0) && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Concert concert= new Concert(idEvent,name,city,location,date,time,province,genre,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,ticketsSoldNumberForType,price,creator,artists,description,photo);
            event.add(concert);
            counterCreatedEvents++;
            return concert;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }


    /**
     * The method allows you to create a {@link Theater} Event  only if the subscription value is 0 or 1 and CounterCreatedEvents is
     * less than the maximum Events that have been created or if the registration value is 2 (infinite events can be created).
     * Otherwise, it throws an exception on stdout.
     *
     * @param idEvent
     * @param name
     * @param city
     * @param location
     * @param date
     * @param time
     * @param province
     * @param genre
     * @param maxNumberOfSeats
     * @param typeOfSeats
     * @param seatsRemainedNumberForType
     * @param ticketsSoldNumberForType
     * @param price
     * @param creator
     * @param artists
     * @param description
     * @param authorName
     * @param photo
     * @return  a {@link Theater}
     * @throws Exception
     */
    public Theater createTheater(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, String authorName, Image photo)throws Exception {
        if(((subscription == 1 || subscription == 0) && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Theater theater = new Theater(idEvent, name, city, location,date,time, province,genre,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,ticketsSoldNumberForType,price,creator,artists,description,authorName,photo);
            event.add(theater);
            counterCreatedEvents++;
            return theater;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    /**
     * The method allows you to create a {@link Other} Event only if the subscription value is 0 or 1 and CounterCreatedEvents is
     * less than the maximum Events that have been created or if the registration value is 2 (infinite events can be created).
     * Otherwise, it throws an exception on stdout.
     * @param idEvent
     * @param name
     * @param city
     * @param location
     * @param date
     * @param time
     * @param province
     * @param genre
     * @param maxNumberOfSeats
     * @param typeOfSeats
     * @param seatsRemainedNumberForType
     * @param ticketsSoldNumberForType
     * @param price
     * @param creator
     * @param artists
     * @param description
     * @param photo
     * @return {@link Other}
     * @throws Exception
     */
    public Other createOther(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre,int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Image photo)throws Exception{
        if(((subscription == 1 || subscription == 0) && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Other other = new Other(idEvent, name, city, location, date,time,province,genre,maxNumberOfSeats,typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType,price, creator,artists,description,photo);
            event.add(other);
            counterCreatedEvents++;
            return other;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }

    }

    /**
     *
     * The method check that a month has passed since the last subscription was made
     * @return {@link Boolean}, true if One Month as Passed since the last subscription was made, false if subscriptionDate is null
     * @see LoginController
     *
     *
     */
    public boolean oneMonthPassed() {  //controllo Effettuato nel LoginController per il LoggedManager
        if (subscriptionDate == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate oneMonthLater = subscriptionDate.plusMonths(1);  //calcolo un mese successivo alla data corrente

        return currentDate.isAfter(oneMonthLater) || currentDate.isEqual((oneMonthLater)); //se la data corrente è >= della data di iscrizione + un mese, ritorna true

    }


    /**
     *
     * @return Event as an {@link ArrayList}
     */

    public ArrayList<Event> getEventlist() {

        return event;
    }

    /**
     * Inherited from the superclass {@link User}
     * @return {@link Boolean} false
     */
    @Override
    public boolean isCustomer() {
        return false;
    }

    /**
     *
     * @return {@link Boolean} true if the manager can still create events,false otherwise.
     */
    public boolean anotherEvents(){
        if (maxNumberOfEvents-counterCreatedEvents>0){
            return true;
        }
        else {return false;}
    }
}