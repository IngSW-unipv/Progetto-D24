package it.unipv.insfw23.TicketWave.Test.ticket;

import static org.junit.Assert.*;

import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unipv.insfw23.TicketWave.modelController.Factory.ticket.ITicketHandler;
import it.unipv.insfw23.TicketWave.modelController.Factory.ticket.TicketHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class TicketTest {
	
	private ITicketHandler ticketHandler;
	private Event event;
	private Manager creator;
	
	@Before
	public void setUp() {
		
		ArrayList<Event> events = new ArrayList<>();
		creator = new Manager("Maurizio","Merluzzo","1980-03-20","mauricemerluzz@gmail.com","123456789",
								43,"3456785676954038",events,5,1,LocalDate.now(),0);
		
		int[] seatsremainedfortype = {60,20};
		int[] ticketsoldfortype = {15,5};
		double[] price = {35.50,70};
		try {
			creator.createConcert(4,"Reunion","Firenze","via del palo",LocalDate.of(2024, 5, 23),
					Time.valueOf(LocalTime.of(20, 30)),Province.ASTI,Genre.METAL,Type.CONCERT,
					100,2,seatsremainedfortype,ticketsoldfortype,price,creator,"Califano",
					"lalalala");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}	
		event = creator.getEventlist().get(0);
		
		ticketHandler = TicketHandlerFactory.getIstance().getTicketHandler();
	}

	@Test
	public void newTicketTest() {
//		Ticket ticket;
//		ticket = ticketHandler.createTicket(event, TicketType.BASE);
		assertNotNull(ticketHandler.createTicket(event, TicketType.BASE));
		
	}

}
