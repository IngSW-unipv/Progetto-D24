package it.unipv.insfw23.TicketWave.modelDomain.event;

import java.lang.String;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;

import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
public abstract class Event {
    private int idEvent;
    private String name, city, location;
    private LocalDate date;
    private Time time;
    private Province province;
    private Genre genre;
    private Type type;
    private int maxNumberOfSeats;
    private int typeOfSeats; // indice dell'array ticketSoldNumberForType, serve per dire quante tipologie di posti ho: base + premium = 2, base = 1, base + premium + vip = 3 tipi di posti, mi server per scorrere l'array
    private int [] seatsRemainedNumberForType, ticketsSoldNumberForType; // vettore biglietti venduti per tipo
    private double [] price; // vettore prezzi per i vari tipi di biglietto, es: Vip = 40€, Base = 15€...
    private Manager creator;
    private String artists, description; // tutti gli eventi hanno una descrizione

    //private File photo;

    // costruttore

    public Event(int idEvent, String name, String city, String location, LocalDate date, Time time, Province province, Genre genre, Type type, int maxNumberOfSeats, int typeOfSeats, int[] seatsRemainedNumberForType, int[] ticketsSoldNumberForType, double[] price, Manager creator, String artists, String description) {
        this.idEvent = idEvent;
        this.name = name;
        this.city = city;
        this.location = location;
        this.date = date;
        this.time = time;
        this.province = province;
        this.genre = genre;
        this.type = type;
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.typeOfSeats = typeOfSeats;
        this.seatsRemainedNumberForType = seatsRemainedNumberForType;
        this.ticketsSoldNumberForType = ticketsSoldNumberForType;
        this.price = price;
        this.creator = creator;
        this.artists = artists;
        this.description = description;
        setDate(date);
        setTypeOfSeats(typeOfSeats);
        setSeatsRemainedNumberForType(seatsRemainedNumberForType);
        setTicketsSoldNumberForType(ticketsSoldNumberForType);
        setPrice(price);
    }


    // Getter //

    public int getIdEvent() {
        return idEvent;
    }

    public String getName() {
        return name;
    } // mi serve nel ResultResearchView per la TableView

    public String getCity() {
        return city;
    } // mi serve nel ResultResearchView per la TableView

    public String getLocation() {
        return location;
    } // mi serve nel ResultResearchView per la TableView
    
    public LocalDate getDate() {
    	return date;
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

    public double getPrice(TicketType type) {
        return price[type.ordinal()]; //ordinal torna la posizione di type nella enum
    }
    
    public double[] getPrices() {
    	return price;
    }

    public Type getType() {
        return type;
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
    		ntot += x;
    	}
    	return ntot;
    }
    
    public Manager getCreator() {
    	return creator;
    }
    
    public void updateSeatsRemainedAndTicketSoldForType(int type) {
    	seatsRemainedNumberForType[type]--;
    	ticketsSoldNumberForType[type]++;
    }

    public int getTicketSoldNumber () { // ritorna tutti i ticket venduti
        int i;
        int result = 0;

        for (i = 0; i < typeOfSeats; i++){
            result = result + ticketsSoldNumberForType[i];
        }
        return result;
    }

    public String getArtists() {
        return artists;
    }

    public Time getTime() {
        return time;
    }

    public int getTypeOfSeats() {
        return typeOfSeats;
    }

    public double[] getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // public File getPhoto() { return photo; }

    // Setter //
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

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDate(LocalDate date) { // controllo che la data sia ammissibile per essere messa in un evento
        if (date != null){
            if (date.isBefore(LocalDate.of(2000, 1, 1)) ||  date.isAfter(LocalDate.of(2050, 1, 1))){ // check sulla data, se la data è prima del 2000 o dopo il 2050 è impossibile creare l'evento
                throw new IllegalArgumentException("Immettere una nuova data, data non valida");
            } else {
                this.date = date;
            }
        } else {
            throw new IllegalArgumentException("La data ha valore nullo, immettere una nuova data");
        }
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setTypeOfSeats(int typeOfSeats) {
        if(typeOfSeats > 2){
            throw new IllegalArgumentException("In TicketWave si possono avere un massimo di 3 tipi di sedute/biglietti, immettere un valore valido");
        } else {
            this.typeOfSeats = typeOfSeats;
        }
    }

    public void setSeatsRemainedNumberForType(int[] seatsRemainedNumberForType) {
        if(seatsRemainedNumberForType.length > 2){
            throw new IllegalArgumentException("In TicketWave si possono avere un massimo di 3 tipi di sedute, reimmettere i valori riguardanti le sedute rimanenti");
        } else {
            this.seatsRemainedNumberForType = seatsRemainedNumberForType;
        }
    }

    public void setTicketsSoldNumberForType(int[] ticketsSoldNumberForType) {
        if(ticketsSoldNumberForType.length > 2){
            throw new IllegalArgumentException("In TicketWave si possono avere un massimo di 3 tipi di ticket, reimmettere i valori riguardanti i ticket venduti per tipo");
        } else {
            this.ticketsSoldNumberForType = ticketsSoldNumberForType;
        }
    }

    public void setPrice(double[] price) {
        if(price.length > 2){
            throw new IllegalArgumentException("In TicketWave si possono avere un massimo di 3 tipi di ticket, reimmettere i valori riguardanti i prezzi dei ticket");
        } else {
            this.price = price;
        }
    }

    public void setCreator(Manager creator) {
        this.creator = creator;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public void setPhoto(File photo) { this.photo = photo};
}
