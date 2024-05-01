package it.unipv.insfw23.TicketWave.modelController.factory.ticket;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;

public interface ITicketHandler {
	public Ticket createTicket(Event event,TicketType type) throws Exception;
}
