package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Other extends Event{
    String description;

    // costruttore
    public Other(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int [] seatsRemainedNumberForType,int[] price, Genre genre, Manager creator, ArrayList<String> artists, String description) {
        super(idEvent, name, city, date, location, province, maxNumberOfSeats, typeOfSeats,seatsRemainedNumberForType, price, genre, creator, artists);
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
