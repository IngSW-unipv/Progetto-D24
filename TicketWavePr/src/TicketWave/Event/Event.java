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

    // getter + setter


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

    public int getTicketsSoldNumber() {
        return ticketsSoldNumber;
    }

    public Genre getGenre() {
        return genre;
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

    public void setTicketsSoldNumber(int ticketsSoldNumber) {
        this.ticketsSoldNumber = ticketsSoldNumber;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
