package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class Festival extends Event {
    int artistsNumber;
    // costruttore


    public Festival(int idEvent, String name, String city, String location, LocalDate date, Time time, Province province, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, double[] price, Genre genre, Manager creator, String artists, String description, int artistsNumber) {
        super(idEvent, name, city, location, date, time, province, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, price, genre, creator, artists, description);
        this.artistsNumber = artistsNumber;
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
