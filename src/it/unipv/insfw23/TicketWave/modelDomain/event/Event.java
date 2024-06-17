package it.unipv.insfw23.TicketWave.modelDomain.event;

import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;

/**
 * Abstract class that represents a generic Event.
 * Contains attributes and methods common to {@link Concert}, {@link Festival}, {@link Theater} and {@link Other}.
 */
public abstract class Event {

    // ATTRIBUTES:
    private final int idEvent;
    private String name, city, location;
    private LocalDate date;
    private LocalTime time;
    private Province province;
    private Genre genre;
    private final Type type;
    private final int maxNumberOfSeats;
    private int typeOfSeats; // indice dell'array ticketSoldNumberForType, serve per dire quante tipologie di posti ho: base + premium = 2, base = 1, base + premium + vip = 3 tipi di posti, mi serve per scorrere l'array
    private int [] seatsRemainedNumberForType, ticketsSoldNumberForType; // vettore biglietti venduti per tipo
    private double [] price; // vettore prezzi per i vari tipi di biglietto, es: Vip = 40€, Base = 15€...
    private final Manager creator;
    private final String artists, description; // tutti gli eventi hanno una descrizione

    private Image photo;

    // CONSTRUCTOR:

    /**
     * Complete constructor to initialize all parameters.
     * Note that an abstract class cannot be instantiated, this constructor will be used by classes that extend Event to initialize parameters.
     * @param idEvent
     * @param name
     * @param city
     * @param location
     * @param date
     * @param time
     * @param province
     * @param genre
     * @param type
     * @param maxNumberOfSeats
     * @param typeOfSeats
     * @param seatsRemainedNumberForType
     * @param ticketsSoldNumberForType
     * @param price
     * @param creator
     * @param artists
     * @param description
     * @param photo
     */

    public Event(int idEvent, String name, String city, String location, LocalDate date, LocalTime time, Province province, Genre genre, Type type, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description, Image photo) {
        this.idEvent = idEvent;
        this.name = name;
        this.city = city;
        this.location = location;
        setDate(date);
        this.time = time;
        this.province = province;
        this.genre = genre;
        this.type = type;
        this.maxNumberOfSeats = maxNumberOfSeats;
        setTypeOfSeats(typeOfSeats);
        setSeatsRemainedNumberForType(seatsRemainedNumberForType);
        setTicketsSoldNumberForType(ticketsSoldNumberForType);
        setPrice(price);
        this.creator = creator;
        this.artists = artists;
        this.description = description;
        this.photo = photo;       
    }


    // GETTER:

    /**
     * Returns the Event ID as an int.
     * @return idEvent
     */

    public int getIdEvent() {
        return idEvent;
    }

    /**
     * Returns the Event name as a {@link String}.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Event city as a {@link String}.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the Event location as a {@link String}.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the Event date as a {@link LocalDate}.
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the Event province as a {@link Province}.
     * @return province
     */
    public Province getProvince() {
        return province;
    }

    /**
     * Returns the Event name as a {@link String}.
     * @return name
     */
    public int getMaxNumberOfSeats() {
        return maxNumberOfSeats;
    }

    /**
     * Returns the Event ticketSoldNumberForType as an array of int.
     * @return ticketSoldNumberForType
     */
    public int[] getTicketsSoldNumberForType() {
        return ticketsSoldNumberForType;
    }

    /**
     * Returns the price corresponding to the input ticketType
     * @param type
     * @return the dimension of price
     */
    public double getPrice(TicketType type) {
        return price[type.ordinal()]; //ordinal torna la posizione di type nella enum 
    }

    /**
     * Returns the Event price as an array of double.
     * @return price
     */
    public double[] getPrices() {
        return price;
    }

    /**
     * Returns the Event type as a {@link Type}.
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the Event genre as a {@link Genre}.
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns the Event seatsReamainedNumberForType as an array of int.
     * @return seatsReamainedNumberForType
     */
    public int[] getSeatsRemainedNumberForType() {
        return seatsRemainedNumberForType;
    }

    /**
     * Returns the Event seatsRemaining as an int.
     * @return ntot
     */
    public int getSeatsRemaining() {
        int ntot = 0;
        for(int x : seatsRemainedNumberForType) {
            ntot += x;
        }
        return ntot;
    }

    /**
     * Returns the Event creator as a {@link Manager}.
     * @return creator
     */
    public Manager getCreator() {
        return creator;
    }

    /**
     * Returns the Event ticketSoldNumber as an int.
     * @return result
     */
    public int getTicketSoldNumber () { // ritorna tutti i ticket venduti
        int i;
        int result = 0;

        for (i = 0; i < typeOfSeats; i++){
            result = result + ticketsSoldNumberForType[i];
        }
        return result;
    }

    /**
     * Returns the Event artists as a {@link String}.
     * @return asrtists
     */
    public String getArtists() {
        return artists;
    }

    /**
     * Returns the Event time as a {@link LocalTime}.
     * @return time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the Event typeOfSeats as an int.
     * @return typeOfSeats
     */
    public int getTypeOfSeats() {
        return typeOfSeats;
    }

    /**
     * Return the Event description as a {@link String}.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return the Event photo as an {@link Image}.
     * @return photo
     */
    public Image getPhoto() {
    	return photo;
    }

    // SETTER:
    /**
     * Sets a new Event name as a {@link String}.
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new Event city as a {@link String}.
     *
     * @param city new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets a new Event location as a {@link String}.
     *
     * @param location new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets a new Event province as a {@link Province}.
     *
     * @param province new province
     */
    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     * Sets a new Event genre as a {@link Genre}.
     *
     * @param genre new genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Sets a new Event date as a {@link LocalDate}.
     *
     * @param date new date
     */
    public void setDate(LocalDate date) { // controllo che la data sia ammissibile per essere messa in un evento
        if (date != null){
            if (date.isBefore(LocalDate.of(2000, 1, 1)) ||  date.isAfter(LocalDate.of(2050, 1, 1))){ // check sulla data, se la data è prima del 2000 o dopo il 2050 è impossibile creare l'evento
                throw new IllegalArgumentException("Please enter new date, invalid date");
            } else {
                this.date = date;
            }
        } else {
            throw new IllegalArgumentException("The date has a null value, enter a new date");
        }
    }

    /**
     * Sets a new Event time as a {@link LocalTime}.
     *
     * @param time new time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Sets a new Event typeOfSeats as an int.
     *
     * @param typeOfSeats new typeOfSeats
     */
    public void setTypeOfSeats(int typeOfSeats) {
        if(typeOfSeats > 3){
            throw new IllegalArgumentException("In TicketWave you can have a maximum of 3 types of seats/tickets, enter a valid value");
        } else {
            this.typeOfSeats = typeOfSeats;
        }
    }

    /**
     * Sets a new Event seatsRemainedNumberForType as an array of int.
     *
     * @param seatsRemainedNumberForType new seatsRemainedNumberForType
     */
    public void setSeatsRemainedNumberForType(int[] seatsRemainedNumberForType) {
        if(seatsRemainedNumberForType.length > 3){
            throw new IllegalArgumentException("In TicketWave you can have a maximum of 3 types of seats, re-enter the values relating to the remaining seats");
        } else {
            this.seatsRemainedNumberForType = seatsRemainedNumberForType;
        }
    }

    /**
     * Sets a new Event ticketsSoldNumberForType as an array of int.
     *
     * @param ticketsSoldNumberForType new ticketsSoldNumberForType
     */
    public void setTicketsSoldNumberForType(int[] ticketsSoldNumberForType) {
        if(ticketsSoldNumberForType.length > 3){
            throw new IllegalArgumentException("In TicketWave you can have a maximum of 3 types of tickets, re-enter the values regarding tickets sold by type");
        } else {
            this.ticketsSoldNumberForType = ticketsSoldNumberForType;
        }
    }

    /**
     * Sets a new Event price as an array of double.
     *
     * @param price new price
     */
    public void setPrice(double[] price) {
        if(price.length > 3){
            throw new IllegalArgumentException("In TicketWave you can have a maximum of 3 types of tickets, re-enter the values regarding the ticket prices");
        } else {
            this.price = price;
        }
    }

    /**
     * Sets a new Event photo as an {@link Image}.
     *
     * @param photo new photo
     */
    public void setPhoto(Image photo) {
    	this.photo = photo;
    }

    /**
     * Increment the ticketsSoldNumberForType and decrement the seatsRemainedNumberForType by 1 based on the type passed in input
     *
     */
    public void updateSeatsRemainedAndTicketSoldForType(int type) {
        seatsRemainedNumberForType[type]--;
        ticketsSoldNumberForType[type]++;
    }
}
