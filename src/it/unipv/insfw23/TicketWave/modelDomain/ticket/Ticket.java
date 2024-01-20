package it.unipv.insfw23.TicketWave.modelDomain.ticket;

public class Ticket {
	String barcode;
	double price;
	TicketType type;
	
	public Ticket(String barcode, double price, TicketType type) {
		this.barcode = barcode;
		this.price = price;
		this.type = type;
	}

	// i setters non ci vanno perch� una volta creato il biglietto non vanno modificate le sue informazioni
	
	public String getBarcode() {
		return barcode;
	}

	public double getPrice() {
		return price;
	}

	public TicketType getType() {
		return type;
	}
}
