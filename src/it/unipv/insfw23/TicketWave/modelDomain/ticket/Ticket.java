package it.unipv.insfw23.TicketWave.modelDomain.ticket;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;

/**
 * This class represents an {@link Event} ticket
 * @see TicketHandler
 */
public class Ticket {
	private String barcode, eventName;
	private int idEvent;
	private double price;
	private TicketType type;

	/**
	 * This method constructs a ticket with the given data
	 * @param barcode a {@link String} in the format "int-{@link TicketType}-int" which identifies the ticket
	 * @param price the ticket price
	 * @param type a {@link TicketType} value indicating the ticket type
	 * @param idEvent an int value which identifies the particular {@link Event}
	 * @param eventName the {@link Event} name
	 */
	public Ticket(String barcode, double price, TicketType type, int idEvent, String eventName) {
		this.barcode = barcode;
		this.price = price;
		this.type = type;
		this.idEvent = idEvent;
		this.eventName = eventName;
	}

	
	/**
	 * returns a {@link String} object that identifies this ticket
	 * @return the ticket barcode
	 */
	public String getBarcode() {
		return barcode;
	}
	
	/**
	 * returns the ticket price
	 * @return a {@link Double} number indicating the price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * provides the way to set the ticket price
	 * @param price a positive double value
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * returns the ticket type
	 * @return a {@link TicketType} value
	 */
	public TicketType getType() {
		return type;
	}
	
	/**
	 * returns the event name to which the ticket refers
	 * @return the event name as a {@link String}
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * returns an int value used to identify an {@link Event}
	 * @return the {@link Event} ID
	 */
	public int getIdEvent() {
		return idEvent;
	}	
	
}
