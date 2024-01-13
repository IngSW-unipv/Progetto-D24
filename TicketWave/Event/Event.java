package TicketWave.Event;

import java.lang.String;
public abstract class Event implements EventType {
    private String name, city, location;
    private Province province; // mi serve l'enumeration di Province per associare l'enum all'attributo
    private int maxNumberOfSeats, ticketsSoldNumber;
    private Genre genre;

    // costruttore
    public Event(String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre) {
        this.name = name;
        this.city = city;
        this.location = location;
        this.province = province;
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.ticketsSoldNumber = ticketsSoldNumber;
        this.genre = genre;
    }

}
