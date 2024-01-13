package TicketWave.Event;
import java.lang.String;
public class Concert extends Event{
    String artist;

    // costruttore
    public Concert(String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre, String artist) {
        super(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre);
        this.artist = artist;
    }

    // override metodi interfaccia EventType
    @Override
    public void getClassName() {

    }

    @Override
    public void getKeyCode() {

    }
}
