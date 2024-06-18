package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

/**
 * This class represents the {@link Notification}s manager, creates the notifications associating the right message 
 * with its recipient
 */
public class NotificationHandler implements INotificationHandler {
	
	private static NotificationHandler istance = null;
	private final String MSG_SOLDOUT = "Evento soldout: ";
	private final String MSG_NEAR = "E' disponibile un nuovo evento nella tua provincia: ";
	private final String MSG_GENRE = "E' disponibile un nuovo evento del tuo genere preferito: ";
	private final String MSG_NEAR_GENRE = "E' disponibile un nuovo evento del tuo genere preferito nella tua provincia: ";
	private int counterNotification; //tiene conto del numero di notifiche create fin'ora in maniera progressiva

	/**
	 * This constructor sets by default the number that counts the notifications created by this object to zero
	 */
	private NotificationHandler() { 
		counterNotification = 0; //se non diversamente indicato inizia a creare notifiche partendo da un id uguale a 0
	}
	
	/**
	 * Implements the pattern singleton to avoid multiple instances of this class
	 * @return the NotificationHandler object
	 */
	public static NotificationHandler getIstance() {
		if(istance == null) {
			istance = new NotificationHandler();
		}
		return istance;
	}
	
	
	/**
	 * returns a {@link Notification} for the {@link Manager} who created the sold out {@link Event}
	 * @param ev the sold out {@link Event} 
	 * @return the created {@link Notification} object 
	 */
	public Notification sendNotificationSoldOut(Event ev) {
		Notification n1;
		String creator = ev.getCreator().getEmail();
		counterNotification += 1;
		n1 = new Notification(counterNotification, creator, (MSG_SOLDOUT+ev.getName()));
		return n1;
	}
	
	
	/**
	 * creates the {@link Notification}s depending on the {@link Manager}'s level of subscription. If subscription level is 1 {@link Customer}s in the same
	 * {@link Province} are notified, instead if the level is 2 both {@link Customer}s in the same {@link Province} and {@link Customer}s who have the 
	 * {@link Event}'s  {@link Genre} in their favorites ones are notified
	 * @param ev The {@link Event} for which you want to notify the publication
	 * @param customerNear
	 * @param customerFavGenre
	 * @return an {@link ArrayList} of {@link Notification} 
	 */
	public ArrayList<Notification> sendNotificationNewEvent(Event ev, ArrayList<String> customerNear, ArrayList<String> customerFavGenre) {
		ArrayList<Notification> notifications = new ArrayList<>();
		ArrayList<String> customerAlreadyNotifiedForProvince = new ArrayList<String>();
		Notification n2;
		int subscription_creator = ev.getCreator().getSubscription();
		Boolean notDoubleCondition;
		
		switch(subscription_creator) {
		case 2:
			
			for(int i = 0; i < customerFavGenre.size(); i++) {
				
				notDoubleCondition = true;
				for(int j = 0; j < customerNear.size(); j++) {
					
					if(customerFavGenre.get(i).equals(customerNear.get(j))) {
						counterNotification += 1;
						n2 = new Notification(counterNotification, customerFavGenre.get(i), (MSG_NEAR_GENRE+ev.getName()));
						notifications.add(n2);
						//tengo traccia di chi ha già ricevuto una notifica sia per vicinanza che per genere, così da non rinotificare la vicinanza nel case 1
						customerAlreadyNotifiedForProvince.add(customerFavGenre.get(i));
						notDoubleCondition = false; //vado al customerFavGen successivo
					}
				}
				
				if(notDoubleCondition) {
					counterNotification += 1;
					n2 = new Notification(counterNotification, customerFavGenre.get(i), (MSG_GENRE+ev.getName()));
					//cfav.addNotification(n2);
					notifications.add(n2);
				}
			}
			//tolgo dai customer da notificare per la vicinanza quelli già notifcati per vicinanza e genere
			customerNear.removeAll(customerAlreadyNotifiedForProvince);
			System.out.println(customerAlreadyNotifiedForProvince);
		case 1:
			for(String cprov : customerNear) {
				counterNotification += 1;
				n2 = new Notification(counterNotification, cprov, (MSG_NEAR+ev.getName()));
				notifications.add(n2);
			}
			break;
			
		}
		
		return notifications;
	}
	
	/**
	 * provides the way to set the {@link Notification} counter
	 * @param counterNotification the number from which to start to create notifications
	 */
	public void setCounterNotification(int counterNotification) {
		this.counterNotification = counterNotification;
	}
	
	
	
	
	
}
