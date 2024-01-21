package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
public class Concert extends Event{
    String artist;

    // costruttore
    public Concert(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String artist) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre);
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
}
