package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class Festival extends Event {
    private ArrayList<String> [] artists;
    int artistsNumber;

    // costruttore
    public Festival(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, ArrayList<String>[] artists, int artistsNumber, Manager creator) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre, creator);
        this.artists = artists;
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
