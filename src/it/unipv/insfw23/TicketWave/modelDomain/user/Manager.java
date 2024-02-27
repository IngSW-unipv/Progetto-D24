package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import java.util.ArrayList;

import java.lang.String;
import java.time.LocalDate;

public class Manager extends User {
    private String creditCard;
    private  int maxNumberofEvents;
    private   int subscription;
    private ArrayList <Event> event;

    private LocalDate subscriptionDate;


    private int counterCreatedEvents;

    public Manager( String name, String surname,String dateOfBirth,String email,String password,int provinceOfResidence, String creditCard,ArrayList <Event> event , int maxNumberofEvents,int subscription,LocalDate subscriptionDate,int counterCreatedEvents){
        super (name,surname,dateOfBirth,email,password,provinceOfResidence);
        this.creditCard=creditCard;
        this.event=event;
        this.maxNumberofEvents=maxNumberofEvents;
        this.subscription=subscription; //impostata dal signUp controller  (recupero con loginDao)
        this.counterCreatedEvents=counterCreatedEvents; //impostata dal signUp controller (recupero con loginDao)
        this.subscriptionDate =subscriptionDate;  //impostata dal SignUP controller (recupero con loginDao)
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
    public void createFestival(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int [] seatsRemainedNumberForType, int[] price, Genre genre, Manager creator, ArrayList<String> artists) throws Exception {
        if(subscription==1 || subscription==2 && counterCreatedEvents <maxNumberofEvents ) {

            Event festival = new Festival(idEvent, name, city, date, location, province, maxNumberOfSeats,typeOfSeats, seatsRemainedNumberForType, price, genre,creator, artists);
            event.add(festival);
            counterCreatedEvents++;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public void createConcert(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats,int[]seatsRemainedNumberForType, int[] price, Genre genre, Manager creator, ArrayList<String> artists) throws Exception{
        if(subscription==1 || subscription==2 && counterCreatedEvents <maxNumberofEvents ) {

            Event concert= new Concert(idEvent,name,city,date,location,province,maxNumberOfSeats,typeOfSeats,seatsRemainedNumberForType,price,genre,creator,artists);
            event.add(concert);
            counterCreatedEvents++;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public void createTheater(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats,int [] seatsRemainedNumberForType, int[] price, Genre genre, Manager creator, ArrayList<String> artists, String theatreCompany, String authorName)throws Exception {
        if(subscription==1 || subscription==2 && counterCreatedEvents <maxNumberofEvents ) {

            Event theater = new Theater(idEvent, name, city,date, location, province, maxNumberOfSeats,typeOfSeats, seatsRemainedNumberForType, price, genre, creator,artists,theatreCompany, authorName);
            event.add(theater);
            counterCreatedEvents++;
        }
        else {
            throw new Exception("Impossibile Creare l'evento:" + name + ".Non puoi creare altri Eventi");
        }
    }

    public void createOther(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int [] seatsRemainedNumberForType,int[] price, Genre genre, Manager creator, ArrayList<String> artists, String description)throws Exception{
        if(subscription==1 || subscription==2 && counterCreatedEvents <maxNumberofEvents ) {

                Event other = new Other(idEvent, name, city, date, location, province, maxNumberOfSeats,typeOfSeats, seatsRemainedNumberForType, price,genre,creator,artists,description);
                event.add(other);
                counterCreatedEvents++;
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


    @Override
    public boolean isCustomer() {
        return false;
    }
}
