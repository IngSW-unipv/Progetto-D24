package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import java.util.ArrayList;

import java.lang.String;

public class Manager extends User {
    private String iban;
    private  int maxNumberofEvents;
    private   int subscription;
    private ArrayList <Event> event;

    public Manager( String name, String surname,String dateOfBirth,String email,String password,int provinceOfResidence, String iban,ArrayList <Event> event , int maxNumberofEvents){
        super (name,surname,dateOfBirth,email,password,provinceOfResidence);
        this.iban=iban;
        this.event=event;
        this.maxNumberofEvents=maxNumberofEvents;
        subscription=0;
        //imposto subscription=0 di default,in modo tale che non appena viene creato non presenta nessun abbonamento
        // poi gli viene assegnanto dai metodi di SubscriptionHandler


    }

    //getters and setters

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }


    public int getMaxNumberofEvents() {
        return maxNumberofEvents;
    }

    public void setMaxNumberofEvents(int maxNumberofEvents) {
        this.maxNumberofEvents = maxNumberofEvents;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }


    public void setEvent(ArrayList<Event> event) {
        this.event = event;
    }


    //seguono dei metodi di crea Festival, Concerto ecc..
    //li ho lasciati referenziati come Event..good?
    public void createFestival(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String[] artists, int artistsNumber) {
        Event festival= new Festival(idEvent,name,city,location,province,maxNumberOfSeats,price,genre,artists,artistsNumber);
        event.add(festival);

    }

    public void createConcert(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String artist) {
        Event concert= new Concert(idEvent,name,city,location,province,maxNumberOfSeats,price,genre,artist);
        event.add(concert);

    }

    public void createTheater(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String theatreCompany, String authorName) {
        Event theater = new Theater(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, theatreCompany, authorName);
        event.add(theater);
    }

    public void createOther(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String description){
        Event other= new Other(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, description);
        event.add(other);
    }

// segue il metodo notify, ma si rifà al metodo di object..override o cambio nome? tipo "notifyM"?
//tipo notifyM?
    public void notify(){

        if (subscription==1) {

            baseNotify();
        }
        else {
            if (subscription==2){
                premiumNotify();
            }
        }
    }


}
