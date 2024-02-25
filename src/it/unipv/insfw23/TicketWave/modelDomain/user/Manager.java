package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import java.util.ArrayList;

import java.lang.String;
import java.time.LocalDate;

public class Manager extends User {
    private int [] creditCard= new int[16];
    private  int maxNumberofEvents;
    private   int subscription;
    private ArrayList <Event> event;

    private LocalDate subscriptionDate;


    private int CounterCreatedEvents;

    public Manager( String name, String surname,String dateOfBirth,String email,String password,int provinceOfResidence, int [] creditCard,ArrayList <Event> event , int maxNumberofEvents){
        super (name,surname,dateOfBirth,email,password,provinceOfResidence);
        this.creditCard=creditCard;
        this.event=event;
        this.maxNumberofEvents=maxNumberofEvents;
        this.subscription=0;
        this.CounterCreatedEvents=0;
        this.subscriptionDate = null;
        //contatore per eventi creati impostato a 0 dal momento che si istanzia il Manager
        //imposto subscription=0 di default,in modo tale che non appena viene creato non presenta nessun abbonamento
        // poi gli viene assegnanto dai metodi di SubscriptionHandler
        //in più il subscriptionHandler deve anche impostare il maxnumberofevents per subscription =1 o 2


    }

    //getters and setters

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

    public int[] getCreditCard() {
        return creditCard;
    }

    public void setEvent(ArrayList<Event> event) {
        this.event = event;
    }


    //seguono dei metodi di crea Festival, Concerto ecc..
    public void createFestival(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, ArrayList<String>[] artists, int artistsNumber, Manager creator) throws Exception {
        if(subscription==1 && CounterCreatedEvents<maxNumberofEvents ) {

            Event festival = new Festival(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, artists, artistsNumber, creator);
            event.add(festival);
            CounterCreatedEvents++;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public void createConcert(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String artist, Manager creator) throws Exception{
        if(subscription==1 && CounterCreatedEvents<maxNumberofEvents ) {

            Event concert= new Concert(idEvent,name,city,location,province,maxNumberOfSeats,price,genre,artist,creator);
            event.add(concert);
            CounterCreatedEvents++;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public void createTheater(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String theatreCompany, String authorName, Manager creator)throws Exception {
        if(subscription==1 && CounterCreatedEvents<maxNumberofEvents ) {

            Event theater = new Theater(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, theatreCompany, authorName,creator);
            event.add(theater);
            CounterCreatedEvents++;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public void createOther(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String description, Manager creator)throws Exception{
            if(subscription==1 && CounterCreatedEvents<maxNumberofEvents ) {

                Event other = new Other(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, description, creator);
                event.add(other);
                CounterCreatedEvents++;
            }
            else {
                throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
            }

    }


    public void setSubscriptionDate(LocalDate date) {
        this.subscriptionDate = date;
    }


    public boolean OneMonthPassed() {
        if (subscriptionDate == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate oneMonthLater = subscriptionDate.plusMonths(1);

        return currentDate.isAfter(oneMonthLater) || currentDate.isEqual((oneMonthLater));

    }


    public ArrayList<Event> getEventlist() {

        return event;
    }

    public int[] getTypeCodeArray(){
        int[] array = {1, 2, 3, 4};
        return array;
    }


/*
    @Override
    public boolean paymentMethod(User user) {
        return true;
    }
*/



}
