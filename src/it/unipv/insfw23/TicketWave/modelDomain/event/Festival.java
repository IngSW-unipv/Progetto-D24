package it.unipv.insfw23.TicketWave.modelDomain.event;
import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

/**
 *  This class extends the {@link Event} and represents a Festival. A Festival is created by a {@link Manager}
 */
public class Festival extends Event {

    // ATTRIBUTES:
    int artistsNumber;

    // CONSTRUCTOR:
    public Festival(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, int artistsNumber, Image photo) {
        super(idEvent, name, city, location, date, time, province, genre, Type.FESTIVAL, maxNumberOfSeats, typeOfSeats, seatsRemainedNumberForType, ticketsSoldNumberForType, price, creator, artists, description, photo);
        this.artistsNumber = artistsNumber;
    }

}