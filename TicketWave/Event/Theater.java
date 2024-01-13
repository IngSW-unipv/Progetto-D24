package TicketWave.Event;
import java.lang.String;
public class Theater extends Event{
    String theatreCompany, authorName;

    // costruttore
    public Theater(String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre, String theatreCompany, String authorName) {
        super(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre);
        this.theatreCompany = theatreCompany;
        this.authorName = authorName;
    }

    // override metodi interfaccia EventType
    @Override
    public void getClassName() {

    }

    @Override
    public void getKeyCode() {

    }
}
