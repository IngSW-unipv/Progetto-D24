package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import javafx.scene.image.Image;

import java.sql.Blob;
import java.sql.Time;
import java.util.ArrayList;

import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;

public class Manager extends User {
    private String creditCard;
    private  int maxNumberOfEvents;
    private   int subscription;
    private ArrayList <Event> event;
    private LocalDate subscriptionDate;
    private int counterCreatedEvents;

    public Manager(String name, String surname, String dateOfBirth, String email, String password, Province provinceOfResidence, String creditCard, ArrayList <Event> event , int maxNumberOfEvents, int subscription, LocalDate subscriptionDate, int counterCreatedEvents){
        super (name,surname,dateOfBirth,email,password,provinceOfResidence);
        this.creditCard=creditCard;
        this.event=event;
        this.maxNumberOfEvents = maxNumberOfEvents;
        this.subscription=subscription; //impostata dal signUp controller  (recupero con loginDao)
        this.counterCreatedEvents=counterCreatedEvents; //impostata dal signUp controller (recupero con loginDao)
        this.subscriptionDate =subscriptionDate;  //impostata dal SignUP controller (recupero con loginDao)
        //contatore per eventi creati impostato a 0 dal momento che si istanzia il Manager
        //imposto subscription=0 di default,in modo tale che non appena viene creato non presenta nessun abbonamento
        // poi gli viene assegnanto dai metodi di SubscriptionHandler
        //in più il subscriptionHandler deve anche impostare il maxnumberofevents per subscription =1 o 2


    }

    //getters and setters

    public int getMaxNumberOfEvents() {
        return maxNumberOfEvents;
    }

    public void setMaxNumberOfEvents(int maxNumberOfEvents) {
        this.maxNumberOfEvents = maxNumberOfEvents;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
        this.subscriptionDate=LocalDate.now(); // setto anche la data per compattare il metodo
    }

    public String getCreditCard() {
        return creditCard;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public ArrayList<Event> getEvent() {
        return event;
    }

    public int getCounterCreatedEvents() {
        return counterCreatedEvents;
    }

    public void setEvent(ArrayList<Event> event) {
        this.event = event;
    }

    //seguono dei metodi di crea Festival, Concerto ecc..
    public Event createFestival(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, int artistsNumber, Image photo) throws Exception {
        if((subscription == 1 && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Event festival = new Festival(idEvent, name, city,location,date,time,province,genre,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,ticketsSoldNumberForType,price,creator,artists,description,artistsNumber,photo);
            event.add(festival);
            counterCreatedEvents++;
            return festival;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public Event createConcert(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Image photo) throws Exception{
        if((subscription == 1 && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Event concert= new Concert(idEvent,name,city,location,date,time,province,genre,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,ticketsSoldNumberForType,price,creator,artists,description,photo);
            event.add(concert);
            counterCreatedEvents++;
            return concert;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public Event createTheater(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, String authorName, Image photo)throws Exception {
        if((subscription == 1 && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Event theater = new Theater(idEvent, name, city, location,date,time, province,genre,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,ticketsSoldNumberForType,price,creator,artists,description,authorName,photo);
            event.add(theater);
            counterCreatedEvents++;
            return theater;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public Event createOther(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre,int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Image photo)throws Exception{
        if((subscription == 1 && counterCreatedEvents < maxNumberOfEvents) || subscription == 2) {

            Event other = new Other(idEvent, name, city, location, date,time,province,genre,maxNumberOfSeats,typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType,price, creator,artists,description,photo);
            event.add(other);
            counterCreatedEvents++;
            return other;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }

    }


    public boolean OneMonthPassed() {  //controllo da effettuare nel Login Dao  una volta che è scaduto l'abbonamento, quando si prova a creare un evento nuovo e si clicca il bottone si rimanda alla pagina di acquisto.
        if (subscriptionDate == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate oneMonthLater = subscriptionDate.plusMonths(1);  //calcolo un mese successivo alla data corrente

        return currentDate.isAfter(oneMonthLater) || currentDate.isEqual((oneMonthLater)); //se la data corrente è >= della data di iscrizione + un mese, ritorna 1

    }


    public ArrayList<Event> getEventlist() {

        return event;
    }

    @Override
    public boolean isCustomer() {
        return false;
    }

    public boolean anotherEvents(){
        if (maxNumberOfEvents-counterCreatedEvents>0){
            return true;
        }
        else {return false;}
    }
}