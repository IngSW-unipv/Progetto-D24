package TicketWave.event;
import java.lang.String;
public class Other extends Event{
    String description;

    // costruttore
    public Other(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] ticketsSoldNumberForType, int[] price, Genre genre, String description) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, ticketsSoldNumberForType, price, genre);
        this.description = description;
    }

    // override metodi interfaccia EventType
    @Override
    public String getClassName() {
        return "Other";
    }

    @Override
    public int getKeyCode() {
        return 0; // 0 = Other
    }
}
