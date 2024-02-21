package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Concert extends Event{
    String artist;

    // costruttore
    public Concert(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String artist, Manager creator) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, creator);
        this.artist = artist;
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
