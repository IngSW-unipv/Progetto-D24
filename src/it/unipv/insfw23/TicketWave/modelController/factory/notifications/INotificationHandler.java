package it.unipv.insfw23.TicketWave.modelController.factory.notifications;

import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;

public interface INotificationHandler {
    public Notification sendNotificationSoldOut(Event ev);//una notifica al creatore
    public ArrayList<Notification> sendNotificationNewEvent(Event ev, ArrayList<String> customerNear, ArrayList<String> customerFavGenre);//tutte le notifiche ai clienti
    public void setCounterNotification(int counterNotification);
}