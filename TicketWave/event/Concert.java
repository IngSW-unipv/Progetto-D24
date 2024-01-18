package TicketWave.event;
import java.lang.String;
public class Concert extends Event{
    String artist;
    int posti;

    public void metod(){

    }

    // costruttore
    public Concert(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int[] ticketsSoldNumberForType, int[] price, Genre genre, String artist) {
        super(idEvent, name, city, location, province, maxNumberOfSeats, ticketsSoldNumberForType, price, genre);
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
