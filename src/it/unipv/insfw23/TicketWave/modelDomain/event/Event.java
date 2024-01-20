package it.unipv.insfw23.TicketWave.modelDomain.event;

import java.lang.String;

import TicketWave.src.it.unipv.ingsfw23.modelDomain.ticket.TicketType;
public abstract class Event implements EventType {

    private int idEvent;
    private String name, city, location;
    private Province province;
    private int maxNumberOfSeats;
    private int [] ticketsSoldNumberForType; // vettore biglietti venduti per tipo
    private int [] price; // vettore prezzi per i vari tipi di biglietto, es: Vip = 40€, Base = 15€...
    private Genre genre;

    // costruttore

    public Event(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre) {
        this.idEvent = idEvent;
        this.name = name;
        this.city = city;
        this.location = location;
        this.province = province;
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.price = price;
        this.genre = genre;
    }


    // getter + setter


    public int getIdEvent() {
        return idEvent;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public Province getProvince() {
        return province;
    }

    public int getMaxNumberOfSeats() {
        return maxNumberOfSeats;
    }

    public int[] getTicketsSoldNumberForType() {
        return ticketsSoldNumberForType;
    }

    public int getPrice(TicketType type) {
        return price[type.ordinal()]; //ordinal torna la posizione di type nella enum
    }

    public Genre getGenre() {
        return genre;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setMaxNumberOfSeats(int maxNumberOfSeats) {
        this.maxNumberOfSeats = maxNumberOfSeats;
    }

    public void setTicketsSoldNumberForType(int[] ticketsSoldNumberForType) {
        this.ticketsSoldNumberForType = ticketsSoldNumberForType;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
