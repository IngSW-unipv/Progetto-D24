package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Theater extends Event{
    String authorName;

    // costruttore


    public Theater(int idEvent, String name, String city, String location, LocalDate date, Time time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, String authorName) {
        super(idEvent, name, city, location, date, time, province, genre, Type.THEATER, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType, price, creator, artists, description);
        this.authorName = authorName;
    }
}
