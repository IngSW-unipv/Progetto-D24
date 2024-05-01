package it.unipv.insfw23.TicketWave.modelController.factory.notifications;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;

public interface INotificationHandler {
    public void sendNotificationSoldOut(Event ev);
    public void sendNotificationNewEvent(Event ev);
}