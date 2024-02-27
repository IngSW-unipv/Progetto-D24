package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Theater extends Event{
    String theatreCompany, authorName;

    // costruttore
    public Theater(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats,int [] seatsRemainedNumberForType, int[] price, Genre genre, Manager creator, ArrayList<String> artists, String theatreCompany, String authorName) {
        super(idEvent, name, city, date, location, province, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, price, genre, creator, artists);
        this.theatreCompany = theatreCompany;
        this.authorName = authorName;
    }

    // override metodi interfaccia EventType
    @Override
    public String getClassName() {
        return "Theater";
    }

    @Override
    public int getKeyCode() {
        return 3; // 3 = Theater
    }
}
