package it.unipv.insfw23.TicketWave.modelDomain.ticket;

import it.unipv.insfw23.TicketWave.modelController.Factory.Notifications.NotificationHandlerFactory;
import it.unipv.insfw23.TicketWave.modelController.Factory.ticket.ITicketHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.*;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.*;

public class TicketHandler implements ITicketHandler{
		
	
//		private static TicketHandler istance;
		
		public TicketHandler() {
			
		}
	
//		private TicketHandler() {
//			
//		}
//		
//		public static TicketHandler getIstance() {
//			if(istance == null) {
//				istance = new TicketHandler();
//			}
//			return istance;
//		}
		
		public Ticket createTicket(Event event,TicketType type) {
			String barcode;
			double price;
			price = event.getPrice(type);
			barcode = createbarcode(event,type);			
			Ticket t = new Ticket(barcode, price, type);
			//se non ci sono piu' biglietti notifica il creatore dell'evento
			if(event.getSeatsRemaining() == 0) {
				//Manager creator;
				//creator = event.getCreator();
				NotificationHandlerFactory.getIstance().getNotificationHandler().sendNotificationSoldOut(event);
			}
			return t;
		}
		
		private String createbarcode(Event event, TicketType type) {
			int idNumOfTicket = 0;
			String bar;

			if(event.getSeatsRemainedNumberForType()[type.ordinal()] != 0) {
				event.updateSeatsRemainedAndTicketSoldForType(type.ordinal());
				idNumOfTicket = event.getTicketsSoldNumberForType()[type.ordinal()];
				bar = (event.getIdEvent()+"-"+type+"-"+idNumOfTicket);
			}
			else
				bar = null;//quando posti sono finiti
			//implementare comportamento se posti finiti
			return bar;
		}
		
		//metodo deleteticket
}
