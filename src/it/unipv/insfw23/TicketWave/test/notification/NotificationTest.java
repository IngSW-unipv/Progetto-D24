package it.unipv.insfw23.TicketWave.test.notification;

import static org.junit.Assert.*;

import java.sql.Blob;
import java.sql.Time;
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
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

public class NotificationTest {

	private Event event;
	private Manager creator;
	private INotificationHandler notificationHandler;

	
	@Before
	public void setUp() {
		ArrayList<Event> events = new ArrayList<>();
		creator = new Manager("Maurizio","Merluzzo","1980-03-20","mauricemerluzz@gmail.com","123456789",
				Province.AOSTA,"3456785676954038",events,5,1,LocalDate.now(),0);
		
		//evento corretto
		int[] seatsremainedfortypecorrectevent = {60,20,25};
		int[] ticketsoldfortypecorrectevent = {15,5,25};
		double[] pricecorrectevent = {35.50,70,100};
		Image bl = null;
		try {
			
			creator.createConcert(4,"Reunion","Firenze","via del palo",LocalDate.of(2024, 5, 23),LocalTime.of(20, 30),Province.ASTI,Genre.METAL,
								150,2,seatsremainedfortypecorrectevent,ticketsoldfortypecorrectevent,pricecorrectevent,creator,"Califano","lalalala", bl);
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}	
		event = creator.getEventlist().get(0);
		
		notificationHandler = NotificationHandlerFactory.getIstance().getNotificationHandler();
	}
	
	@Test
	public void notificationSoldoutTest() {
		assertTrue(creator.getNotification().isEmpty());
		
		notificationHandler.sendNotificationSoldOut(event);
		
		assertFalse(creator.getNotification().isEmpty());
		assertEquals(1, creator.getNotification().size());
		assertEquals(LocalDate.now(),creator.getNotification().getFirst().getDate());
		assertEquals(LocalTime.now().truncatedTo(ChronoUnit.SECONDS),creator.getNotification().getFirst().getTime());
		assertEquals(1,creator.getNotification().getFirst().getId());
		assertEquals("mauricemerluzz@gmail.com",creator.getNotification().getFirst().getEmailReceiver());
		assertEquals("Evento soldout",creator.getNotification().getFirst().getMsg());
		
		
	}
}
