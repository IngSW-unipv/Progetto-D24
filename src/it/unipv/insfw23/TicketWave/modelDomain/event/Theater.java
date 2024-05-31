package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.sql.Blob;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;
public class Theater extends Event{
    String authorName;

    // costruttore


    public Theater(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, String authorName, Image photo) {
        super(idEvent, name, city, location, date, time, province, genre, Type.THEATER, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType, price, creator, artists, description, photo);
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
