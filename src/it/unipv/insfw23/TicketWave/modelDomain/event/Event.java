package it.unipv.insfw23.TicketWave.modelDomain.event;

import java.lang.String;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;

import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
public abstract class Event implements EventType {
    private int idEvent;
    private String name, city, location;
    private Province province;
    private int maxNumberOfSeats;
    private int typeOfSeats; // indice dell'array ticketSoldNumberForType, serve per dire quante tipologie di posti ho: base + premium = 2, base = 1, base + premium + vip = 3 tipi di posti, mi server per scorrere l'array
    private int [] seatsRemainedNumberForType;
    private int [] ticketsSoldNumberForType; // vettore biglietti venduti per tipo
    private int [] price; // vettore prezzi per i vari tipi di biglietto, es: Vip = 40€, Base = 15€...
    private Genre genre;
    private Manager creator;

    // costruttore

    public Event(int idEvent, String name, String city, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int[] price, Genre genre, Manager creator) {
        this.idEvent = idEvent;
        this.name = name;
        this.city = city;
        this.location = location;
        this.province = province;
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.typeOfSeats = typeOfSeats;
        this.price = price;
        this.genre = genre;
        this.creator = creator;
    }


    // getter + setter


    public int getIdEvent() {
        return idEvent;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public Province getProvince() {
        return province;
    }

    public int getMaxNumberOfSeats() {
        return maxNumberOfSeats;
    }

    public int[] getTicketsSoldNumberForType() {
        return ticketsSoldNumberForType;
    }

    public int getPrice(TicketType type) {
        return price[type.ordinal()]; //ordinal torna la posizione di type nella enum
    }

    public Genre getGenre() {
        return genre;
    }
    
    public int[] getSeatsRemainedNumberForType() {
    	return seatsRemainedNumberForType;
    }
    
    public int getSeatsRemaining() {
    	int ntot = 0;
    	for(int x : seatsRemainedNumberForType) {
    		ntot += seatsRemainedNumberForType[x];
    	}
    	return ntot;
    }
    
    public Manager getCreator() {
    	return creator;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setMaxNumberOfSeats(int maxNumberOfSeats) {
        this.maxNumberOfSeats = maxNumberOfSeats;
    }

    public void setTicketsSoldNumberForType(int[] ticketsSoldNumberForType) {
        this.ticketsSoldNumberForType = ticketsSoldNumberForType;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public void updateSeatsRemainedAndTicketSoldForType(int type) {
    	seatsRemainedNumberForType[type]--;
    	ticketsSoldNumberForType[type]++;
    }

    // ultimi getter richiesti
    public int getTicketSoldNumber () { // ritorna tutti i ticket venduti
        int i;
        int result = 0;

        for (i = 0; i < typeOfSeats; i++){
            result = result + ticketsSoldNumberForType[i];
        }
        return result;
    }
}
