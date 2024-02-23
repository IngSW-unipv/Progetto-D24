package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public interface INotification {
	public void sendNotificationSoldOut(Event ev); 
	public void sendNotificationNewEvent(Event ev);
}
