package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.sql.Blob;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
public class Concert extends Event{

    // costruttore dao


    public Concert(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Blob photo) {
        super(idEvent, name, city, location, date, time, province, genre, Type.CONCERT, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType, price, creator, artists, description, photo);
    }
    
    
}