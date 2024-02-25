package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class Festival extends Event {
    int artistsNumber;
    // costruttore
    public Festival(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int[] price, Genre genre, Manager creator, ArrayList<String>[] artists, int artistsNumber) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, typeOfSeats, price, genre, creator, artists);
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
