package it.unipv.insfw23.TicketWave.modelDomain.ticket;

import it.unipv.insfw23.TicketWave.modelController.factory.ticket.ITicketHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;

/**
 * This class represents the {@link Ticket} manager, performs the actions in order to create Tickets
 */
public class TicketHandler implements ITicketHandler{
			
		private static TicketHandler istance;

	
		private TicketHandler() {
			
		}
		
		/**
		 * Implements the pattern singleton to avoid multiple instances of this class
		 * @return the TicketHandler object
		 */
		public static TicketHandler getIstance() {
			if(istance == null) {
				istance = new TicketHandler();
			}
			return istance;
		}
				
		/**
		 * This method creates a {@link Ticket} for the {@link Event} provided as input whose type is provided as input
		 * @param event the {@link Event} for which you want to create a {@link Ticket}
		 * @param type a {@link TicketType} value indicating the ticket type
		 * @return a {@link Event} {@link Ticket}
		 * @throws Exception when you try to create a {@link Ticket} of an {@link Event} with no more available ticket
		 */
		public Ticket createTicket(Event event,TicketType type) throws Exception{
			if(event.getSeatsRemaining() == 0)
				throw new Exception("Event sold out");
			
			String barcode;
			double price;
			price = event.getPrice(type);
			barcode = createbarcode(event,type);
			if(barcode == null)
				return null;
			Ticket t = new Ticket(barcode, price, type, event.getIdEvent(), event.getName());
			
			return t;
		}
		
		/**
		 * This method concatenates the {@link TicketType}, the number of remaining seats and of ticket sold for that type and returns a specific barcode
		 * @param event
		 * @param type
		 * @return a {@link String} in the format "int-{@link TicketType}-int" used to identify a ticket
		 */
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
			
			return bar;
		}
		
}
