package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Concert extends Event{

    // costruttore


    public Concert(int idEvent, String name, String city, String location, LocalDate date, Time time, Province province, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, double[] price, Genre genre, Manager creator, String artists, String description) {
        super(idEvent, name, city, location, date, time, province, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, price, genre, creator, artists, description);
    }

    // override metodi interfaccia EventType
    @Override
    public String getClassName() {
        return "Concert";
    }

    @Override
    public int getKeyCode() {
        return 1; // 1 = Concert
    }

}
