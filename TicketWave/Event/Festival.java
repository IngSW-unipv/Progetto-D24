package TicketWave.Event;
import java.lang.String;
public class Festival extends Event {
    String [] artists;
    int artistsNumber;

    // costruttore
    public Festival(String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre, String[] artists, int artistsNumber) {
        super(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre);
        this.artists = artists;
        this.artistsNumber = artistsNumber;
    }

    // override metodi interfaccia EventType
    @Override
    public void getClassName() {

    }

    @Override
    public void getKeyCode() {

    }
}
