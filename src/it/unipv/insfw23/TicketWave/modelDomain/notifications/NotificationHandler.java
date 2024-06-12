package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.*;

public class NotificationHandler implements INotificationHandler {
	
	private static NotificationHandler istance = null;
	private final String MSG_SOLDOUT = "Evento soldout";
	private final String MSG_NEAR = "E' disponibile un nuovo evento nella tua provincia";
	private final String MSG_GENRE = "E' disponibile un nuovo evento del tuo genere preferito";
	private final String MSG_NEAR_GENRE = "E' disponibile un nuovo evento del tuo genere preferito nella tua provincia";
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
		ArrayList<String> customerAlreadyNotifiedForProvince = new ArrayList<String>();
		Province province = ev.getProvince();
		Genre genre = ev.getGenre();
		Notification n2;
		int subscription_creator = ev.getCreator().getSubscription();
		Boolean notDoubleCondition;
		System.out.println("dim dei vicini: ");
		for(String s : customerNear) {
			System.out.println(s);
		}
		System.out.println("dim dei gen: ");
		for(String s1 : customerFavGenre) {
			System.out.println(s1);
		}
		
		switch(subscription_creator) {
		case 2:
			
			for(int i = 0; i < customerFavGenre.size(); i++) {
				
				notDoubleCondition = true;
				for(int j = 0; j < customerNear.size(); j++) {
					
					if(customerFavGenre.get(i).equals(customerNear.get(j))) {
						counterNotification += 1;
						n2 = new Notification(counterNotification, customerFavGenre.get(i), MSG_NEAR_GENRE);
						notifications.add(n2);
						//tengo traccia di chi ha già ricevuto una notifica sia per vicinanza che per genere, così da non rinotificare la vicinanza nel case 1
						customerAlreadyNotifiedForProvince.add(customerFavGenre.get(i));
						notDoubleCondition = false; //vado al customerFavGen successivo
					}
				}
				
				if(notDoubleCondition) {
					counterNotification += 1;
					n2 = new Notification(counterNotification, customerFavGenre.get(i), MSG_GENRE);
					//cfav.addNotification(n2);
					notifications.add(n2);
				}
			}
			//tolgo dai customer da notificare per la vicinanza quelli già notifcati per vicinanza e genere
			customerNear.removeAll(customerAlreadyNotifiedForProvince);
			System.out.println(customerAlreadyNotifiedForProvince);
		case 1:
			//System.out.println(customerNear);
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
