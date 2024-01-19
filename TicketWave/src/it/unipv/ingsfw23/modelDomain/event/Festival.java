package TicketWave.src.it.unipv.ingsfw23.modelDomain.event;
import java.lang.String;
public class Festival extends Event {
    String [] artists;
    int artistsNumber;

    // costruttore
    public Festival(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String[] artists, int artistsNumber) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre);
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
