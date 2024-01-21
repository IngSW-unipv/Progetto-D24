package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
public class Other extends Event{
    String description;

    // costruttore
    public Other(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String description) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre);
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
