package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.sql.Blob;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;
public class Other extends Event{

    // costruttore


    public Other(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Image photo) {
        super(idEvent, name, city, location, date, time, province, genre, Type.OTHER, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType, price, creator, artists, description, photo);
    }
}