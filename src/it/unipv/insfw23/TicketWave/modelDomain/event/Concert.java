package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Concert extends Event{

    // costruttore
    public Concert(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int[] price, Genre genre, Manager creator, ArrayList<String>[] artists, String artist) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, typeOfSeats, price, genre, creator, artists);
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

    public String getArtist() { // getArtista
        return artist;
    }
}
