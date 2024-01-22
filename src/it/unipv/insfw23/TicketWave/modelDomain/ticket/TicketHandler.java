package it.unipv.insfw23.TicketWave.modelDomain.ticket;
import TicketWave.src.it.unipv.ingsfw23.modelDomain.event.*;

public class TicketHandler {
	// possibile singletone
		public TicketHandler() {
			
		}
		
		public Ticket createTicket(Event event,TicketType type) {
			String barcode;
			double price;
			barcode = createbarcode(event,type);
			price = event.getPrice(type);
			Ticket t = new Ticket(barcode, price, type);
			return t;
		}
		
		private String createbarcode(Event event, TicketType type) {
			int idNumOfTicket;

			if(event.getSubdivisionOfSeatsRemaining(type) != 0) {
				event.decrSubdovisionOfSeatsRemaining(type);
				idNumOfTicket = event.getSubdivisionOfSeatsSoldByType(type) +1;
			}
			//implementare eccezione se i posti sono finiti
			String bar = (event.getIdEvent()+"-"+type+"-"+idNumOfTicket);
			return bar;
		}
		
		//metodo deleteticket
}
