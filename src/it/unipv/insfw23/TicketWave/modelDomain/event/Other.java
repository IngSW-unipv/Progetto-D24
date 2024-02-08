package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Other extends Event{
    String description;

    // costruttore
    public Other(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String description, Manager creator) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, creator);
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
