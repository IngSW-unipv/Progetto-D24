package TicketWave.src.it.unipv.ingsfw23.modelDomain.event;
import java.lang.String;
public class Theater extends Event{
    String theatreCompany, authorName;

    // costruttore
    public Theater(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] price, Genre genre, String theatreCompany, String authorName) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, price, genre);
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
