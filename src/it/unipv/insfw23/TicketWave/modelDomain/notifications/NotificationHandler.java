package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.*;

public class NotificationHandler implements INotification{
	
	private static NotificationHandler istance = null;
	private int counterNotification;
	final String msg2 = "E' disponibile un nuovo evento nella tua provincia";
	final String msg3 = "E' disponibile un nuovo evento del tuo genere preferito";
	final String msg4 = "E' disponibile un nuovo evento del tuo genere preferito nella tua provincia";
	
	private NotificationHandler(int counterNotification) { //counterNotification -> num di notifiche create finora (sul db)
		this.counterNotification = counterNotification;
	}
	
	public static NotificationHandler getIstance() {
		if(istance == null) {
			int numNotific = 0; //da modificare con il prelievo del numero di notifiche fatte finore presenti sul db con COUNT
			istance = new NotificationHandler(numNotific);
		}
		return istance;
	}
	
	//notifica evento sold out
	public void sendNotificationSoldOut(Event ev) {
		String msg1 = "Evento soldout";
		Notification n1;
		Manager creator = ev.getCreator();
		counterNotification += 1;
		n1 = new Notification(counterNotification, creator, msg1);
		creator.addNotification(n1);
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
	public void sendNotificationNewEvent(Event ev) {
		Province province = ev.getProvince();
		Genre genre = ev.getGenre();
		Notification n2;
		String msg2 = "E' disponibile un nuovo evento nella tua provincia";
		String msg3 = "E' disponibile un nuovo evento del tuo genere preferito";
		String msg4 = "E' disponibile un nuovo evento del tuo genere preferito nella tua provincia";
		int subscription_creator = ev.getCreator().getSubscription();
		ArrayList <Customer> customernear; //select al db where province = province
		
		switch(subscription_creator) {
		case 2:
			ArrayList <Customer> customerfavgen; //select al db where favgen = favgen
			for(Customer cfav : customerfavgen) {
				for(Customer cprov : customernear) {
					if(cfav.getEmail().equals(cprov.getEmail())) {
						counterNotification += 1;
						n2 = new Notification(counterNotification, cfav, msg4);
						cfav.addNotification(n2);
					}
					else {
						counterNotification += 1;
						n2 = new Notification(counterNotification, cfav, msg3);
					}
				}
			}
		case 1:
			for(Customer c : customernear) {
				counterNotification += 1;
				n2 = new Notification(counterNotification, c, msg2);
				c.addNotification(n2);
			}
			break;
			
		}
		
	}
	
	
	
	
	
	
	
}
