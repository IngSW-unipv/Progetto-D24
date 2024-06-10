package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.*;

public class NotificationHandler implements INotificationHandler {
	
	private static NotificationHandler istance = null;
	final String MSG_SOLDOUT = "Evento soldout";
	final String MSG_NEAR = "E' disponibile un nuovo evento nella tua provincia";
	final String MSG_GENRE = "E' disponibile un nuovo evento del tuo genere preferito";
	final String MSG_NEAR_GENRE = "E' disponibile un nuovo evento del tuo genere preferito nella tua provincia";
	private int counterNotification = 0;

	
	private NotificationHandler() { //counterNotification -> num di notifiche create finora (sul db)
		//counterNotification = counterNotificationDao;
		counterNotification = 0; //da modificare con il prelievo del numero di notifiche fatte finore presenti sul db con COUNT
	}
	
	public static NotificationHandler getIstance() {
		if(istance == null) {
			istance = new NotificationHandler();
		}
		return istance;
	}
	
	//notifica evento sold out
	public Notification sendNotificationSoldOut(Event ev) {
		Notification n1;
		String creator = ev.getCreator().getEmail();
		counterNotification += 1;
		n1 = new Notification(counterNotification, creator, MSG_SOLDOUT);
		//creator.addNotification(n1);
		return n1;
	}
	
	
	//notifica creazione nuovo evento nella stessa provincia
/*	public void sendNotificationNewEvent(Event ev) {
		Province province;
		Notification n2;
		String msg2 = "E' disponibile un nuovo evento nella tua provincia";
		province = ev.getProvince();
		ArrayList<Customer> customerNear; //select al db where province = province dell'evento
		for(Customer c : customerNear) {
			counterNotification += 1;
			n2 = new Notification(counterNotification, c, msg2);
			c.addNotification(n2);
		}
	}
*/	
	public ArrayList<Notification> sendNotificationNewEvent(Event ev, ArrayList<String> customerNear, ArrayList<String> customerFavGenre) {
		ArrayList<Notification> notifications = new ArrayList<>();
		Province province = ev.getProvince();
		Genre genre = ev.getGenre();
		Notification n2;
		int subscription_creator = ev.getCreator().getSubscription();
		//ArrayList <Customer> customernear = new ArrayList<>(); //select al db where province = province
		
		switch(subscription_creator) {
		case 2:
			//ArrayList <Customer> customerfavgen = new ArrayList<>(); //select al db where favgen = favgen
			for(String cfav : customerFavGenre) {
				for(String cprov : customerNear) {
					if(cfav.equals(cprov)) {
						counterNotification += 1;
						n2 = new Notification(counterNotification, cfav, MSG_NEAR_GENRE);
						//cfav.addNotification(n2);
						notifications.add(n2);
						// toglie il customer corrente dalla lista di quelli vicini dato che gli è già stata notificata la creazione dell'evento
						customerNear.remove(cprov);
					}
					else {
						counterNotification += 1;
						n2 = new Notification(counterNotification, cfav, MSG_GENRE);
						//cfav.addNotification(n2);
						notifications.add(n2);
					}
				}
			}
		case 1:
			for(String cprov : customerNear) {
				counterNotification += 1;
				n2 = new Notification(counterNotification, cprov, MSG_NEAR);
				//cprov.addNotification(n2);
				notifications.add(n2);
			}
			break;
			
		}
		
		return notifications;
	}
	
	public void setCounterNotification(int counterNotification) {
		this.counterNotification = counterNotification;
	}
	
	
	
	
	
}
