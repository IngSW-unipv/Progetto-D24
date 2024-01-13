package TicketWave.Event;
import java.lang.String;
public class Other extends Event{
    String description;

    // costruttore
    public Other(String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre, String description) {
        super(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre);
        this.description = description;
    }

    // override metodi interfaccia EventType
    @Override
    public void getClassName() {

    }

    @Override
    public void getKeyCode() {

    }
}
