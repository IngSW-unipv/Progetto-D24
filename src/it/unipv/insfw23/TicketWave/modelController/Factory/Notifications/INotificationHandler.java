package it.unipv.insfw23.TicketWave.modelController.Factory.Notifications;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public interface INotificationHandler {
    public void sendNotificationSoldOut(Event ev);
    public void sendNotificationNewEvent(Event ev);
}