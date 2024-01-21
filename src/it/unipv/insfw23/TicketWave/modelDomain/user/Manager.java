package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import java.util.List;
import java.lang.String;

public class Manager extends User implements IEventCreator {
    private String iban;
    private  int maxNumberofEvents;
    private   int subscription;
    private List <Event> event;

    public Manager( String name, String surname,String dateOfBirth,String email,String password,int provinceOfResidence, String iban,List <Event> event , int maxNumberofEvents){
        super (name,surname,dateOfBirth,email,password,provinceOfResidence);
        this.iban=iban;
        this.event=event;
        this.maxNumberofEvents=maxNumberofEvents;
        subscription=0;
        //imposto subscription=0 di default,in modo tale che non appena viene creato non presenta nessun abbonamento
        // poi gli viene assegnanto dai metodi di SubscriptionHandler


    }

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


    public void setEvent(List<Event> event) {
        this.event = event;
    }

    //metodi dell'interfaccia Creator da discutere

    @Override
    public void createEvent(String type, String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre) {

    }

    @Override
    public List <Event> getEvents() {
        return event;
    }

    public void notifyM(){

        if (subscription==1) {

            baseNotify();
        }
        else {
            if (subscription==2){
                premiumNotify();
            }



        }
    }

    private void baseNotify(Event e){
        Event ecomp;
        if (ecomp.getProvince()=e.getCity()){

        }


    }


}
