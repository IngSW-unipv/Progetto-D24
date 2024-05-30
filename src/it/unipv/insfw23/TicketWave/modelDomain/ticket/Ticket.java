package it.unipv.insfw23.TicketWave.modelDomain.ticket;

public class Ticket {
	String barcode, eventName;
	int idEvent;
	double price;
	TicketType type;
	
	public Ticket(String barcode, double price, TicketType type, int idEvent, String eventName) {
		this.barcode = barcode;
		this.price = price;
		this.type = type;
		this.idEvent = idEvent;
		this.eventName = eventName;
	}

	// i setters non ci vanno perche' una volta creato il biglietto non vanno modificate le sue informazioni
	
	public String getBarcode() {
		return barcode;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public TicketType getType() {
		return type;
	}
	
	public String getEventName() {
		return eventName;
	}

	public int getIdEvent() {
		return idEvent;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return barcode;
	}


	
	
	
}
