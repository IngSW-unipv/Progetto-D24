package it.unipv.insfw23.TicketWave.test.notification;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.NotificationHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

public class NotificationTest {

	private Event event;
	private Manager creator;
	private INotificationHandler notificationHandler;

	private final int MAX_EVENTS_FOR_FREE_SUB = 1;
	
	private final String MSG_SOLDOUT = "Evento soldout: ";
	private final String MSG_NEAR = "E' disponibile un nuovo evento nella tua provincia: ";
	private final String MSG_GENRE = "E' disponibile un nuovo evento del tuo genere preferito: ";
	private final String MSG_NEAR_GENRE = "E' disponibile un nuovo evento del tuo genere preferito nella tua provincia: ";
	
	ArrayList<String> customerNear = new ArrayList<>();
	ArrayList<String> customerPrefGenre = new ArrayList<>();
	
	@Before
	public void setUp() {
		//creazioni degli arraylist dei customer
		
		customerNear.add("Paolo");
		customerNear.add("Giuseppe");
		customerNear.add("Franco");
		customerNear.add("Luca");
		customerNear.add("Giorgio");
		
		customerPrefGenre.add("Francesco");
		customerPrefGenre.add("Fabio");
		customerPrefGenre.add("Paolo");
		customerPrefGenre.add("Luca");
		customerPrefGenre.add("Federico");
		
		//creazione manager
		ArrayList<Event> events = new ArrayList<>();
		creator = new Manager("Maurizio","Merluzzo","1980-03-20","mauricemerluzz@gmail.com","123456789",
				Province.AOSTA,"3456785676954038",events,MAX_EVENTS_FOR_FREE_SUB,0,LocalDate.now(),0);
		
		//creazione di un evento 
		int[] seatsRemainedForType = {60,20,25};
		int[] ticketSoldForType = {15,5,25};
		double[] prices = {35.50,70,100};
		Image bl = null;
		try {
			
			event = creator.createConcert(4,"Reunion","Firenze","via del palo",LocalDate.of(2024, 5, 23),LocalTime.of(20, 30),Province.ASTI,Genre.METAL,
								150,2,seatsRemainedForType,ticketSoldForType,prices,creator,"Califano","lalalala", bl);
		
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}	
		//event = creator.getEventlist().get(0);
		
		notificationHandler = NotificationHandlerFactory.getIstance().getNotificationHandler();
	}
	
	@Test
	public void notificationSoldoutTest() {
		assertTrue(creator.getNotification().isEmpty());
		
		notificationHandler.setCounterNotification(0);
		Notification notSoldOut =notificationHandler.sendNotificationSoldOut(event);
		creator.addNotification(notSoldOut);
		
		assertFalse(creator.getNotification().isEmpty());
		assertEquals(1, creator.getNotification().size());
		assertEquals(LocalDate.now(),creator.getNotification().getFirst().getDate());
		assertEquals(LocalTime.now().truncatedTo(ChronoUnit.SECONDS),creator.getNotification().getFirst().getTime());
		assertEquals(1,creator.getNotification().getFirst().getId());
		assertEquals("mauricemerluzz@gmail.com",creator.getNotification().getFirst().getEmailReceiver());
		assertEquals((MSG_SOLDOUT+event.getName()),creator.getNotification().getFirst().getMsg());
		
		
	}
	
	@Test
	public void notificationNewEventFreeSubTest() {
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		
		notifications = notificationHandler.sendNotificationNewEvent(event, customerNear, customerPrefGenre);
		assertTrue(notifications.isEmpty());
		
		
	}
	
	@Test
	public void notificationNewEventBaseSubTest() {
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		creator.setSubscription(1);
		
		notifications = notificationHandler.sendNotificationNewEvent(event, customerNear, customerPrefGenre);
		assertFalse(notifications.isEmpty());
		assertEquals(notifications.size(), 5);
		//controllo che le notifiche siano per i customer giusti e abbiano il giusto messaggio
		assertEquals("Paolo", notifications.get(0).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()), notifications.get(0).getMsg());
		
		assertEquals("Giuseppe", notifications.get(1).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()),notifications.get(1).getMsg());
		
		assertEquals("Franco", notifications.get(2).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()),notifications.get(2).getMsg());
		
		assertEquals("Luca", notifications.get(3).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()),notifications.get(3).getMsg());
		
		assertEquals("Giorgio", notifications.get(4).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()),notifications.get(4).getMsg());
	}
	
	@Test
	public void notificationNewEventPremiumSubTest() {
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		creator.setSubscription(2);
		
		notifications = notificationHandler.sendNotificationNewEvent(event, customerNear, customerPrefGenre);
		
		assertFalse(notifications.isEmpty());
		assertEquals(8, notifications.size());
		//controllo che le notifiche siano per i customer giusti e abbiano il giusto messaggio
		assertEquals("Francesco", notifications.get(0).getEmailReceiver());
		assertEquals((MSG_GENRE+event.getName()), notifications.get(0).getMsg());
		
		assertEquals("Fabio", notifications.get(1).getEmailReceiver());
		assertEquals((MSG_GENRE+event.getName()), notifications.get(1).getMsg());
		
		assertEquals("Paolo", notifications.get(2).getEmailReceiver());
		assertEquals((MSG_NEAR_GENRE+event.getName()), notifications.get(2).getMsg());
		
		assertEquals("Luca", notifications.get(3).getEmailReceiver());
		assertEquals((MSG_NEAR_GENRE+event.getName()), notifications.get(3).getMsg());
		
		assertEquals("Federico", notifications.get(4).getEmailReceiver());
		assertEquals((MSG_GENRE+event.getName()), notifications.get(4).getMsg());
		
		assertEquals("Giuseppe", notifications.get(5).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()), notifications.get(5).getMsg());
		
		assertEquals("Franco", notifications.get(6).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()), notifications.get(6).getMsg());
		
		assertEquals("Giorgio", notifications.get(7).getEmailReceiver());
		assertEquals((MSG_NEAR+event.getName()), notifications.get(7).getMsg());
		
	}
	
}






