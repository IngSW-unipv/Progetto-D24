package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.*;

public class NotificationHandler implements INotification{
	
	private static NotificationHandler istance = null;
	private int counterNotification;
	
	private NotificationHandler(int counterNotification) { //counterNotification -> num di notifiche create finora (sul db)
		this.counterNotification = counterNotification;
	}
	
	public static NotificationHandler getIstance() {
		if(istance == null) {
			int numNotific = 0; //da modificare con il prelievo del numero di notifiche fatte finore presenti sul db
			istance = new NotificationHandler(numNotific);
		}
		return istance;
	}
	
	//notifica evento sold out
	public void sendNotificationSoldOut(Event ev, Manager creator) {
		String msg1 = "Evento soldout";
		Notification n1;
		counterNotification += 1;
		n1 = new Notification(counterNotification, creator, msg1);
		creator.addNotification(n1);
	}
	
	//notifica creazione nuovo evento nella stessa provincia
	public void sendNotificationNewEvent(Event ev) {
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
	
	
	
	
	
	
	
	
}
