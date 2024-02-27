package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class Festival extends Event {
    int artistsNumber;
    // costruttore
    public Festival(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int [] seatsRemainedNumberForType,int[] price, Genre genre, Manager creator, ArrayList<String> artists) {
        super(idEvent, name, city, date, location, province, maxNumberOfSeats, typeOfSeats,seatsRemainedNumberForType, price, genre, creator, artists);
        this.artistsNumber = artists.size();
    }

    // override metodi interfaccia EventType
    @Override
    public String getClassName() {
        return "Festival";
    }

    @Override
    public int getKeyCode() {
        return 2; // 2 = Festival
    }
}
