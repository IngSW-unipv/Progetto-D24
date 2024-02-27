package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Concert extends Event{

    // costruttore
    public Concert(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, double[] price, Genre genre, Manager creator, ArrayList<String> artists) {
        super(idEvent, name, city, date, location, province, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, price, genre, creator, artists);
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
