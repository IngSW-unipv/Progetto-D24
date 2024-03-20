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
		
		//evento corretto
		int[] seatsremainedfortypecorrectevent = {60,20,25};
		int[] ticketsoldfortypecorrectevent = {15,5,25};
		double[] pricecorrectevent = {35.50,70,100};
		try {
			
			creator.createConcert(4,"Reunion","Firenze","via del palo",LocalDate.of(2024, 5, 23),Time.valueOf(LocalTime.of(20, 30)),Province.ASTI,Genre.METAL,
								Type.CONCERT,150,2,seatsremainedfortypecorrectevent,ticketsoldfortypecorrectevent,pricecorrectevent,creator,"Califano","lalalala");
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}	
		event = creator.getEventlist().get(0);
		
		
		ticketHandler = TicketHandlerFactory.getIstance().getTicketHandler();
	}

	@Test
	public void correctTicketsTest() {
		Ticket baseticket = null;
		Ticket premiumticket = null;
		Ticket vipticket = null;
		
		try {
		baseticket = ticketHandler.createTicket(event, TicketType.BASE);
		premiumticket = ticketHandler.createTicket(event, TicketType.PREMIUM);
		vipticket = ticketHandler.createTicket(event, TicketType.VIP);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		assertNotNull(baseticket);
		assertEquals(35.5, baseticket.getPrice(),0);
		assertEquals(TicketType.BASE, baseticket.getType());
		assertEquals("4-BASE-16", baseticket.getBarcode());
		
		assertNotNull(premiumticket);
		assertEquals(70, premiumticket.getPrice(),0);
		assertEquals(TicketType.PREMIUM, premiumticket.getType());
		assertEquals("4-PREMIUM-6", premiumticket.getBarcode());
		
		assertNotNull(vipticket);
		assertEquals(100, vipticket.getPrice(),0);
		assertEquals(TicketType.VIP, vipticket.getType());
		assertEquals("4-VIP-26", vipticket.getBarcode());
	}
	
	@Test
	public void noBaseTicketTest() {
		Ticket baseticket = null;
		int[] seatsremainedfortypeNoBaseRemainedevent = {0,20,25};
		int[] ticketsoldfortypeNoBaseRemainedevent = {75,5,25};
		
		event.setSeatsRemainedNumberForType(seatsremainedfortypeNoBaseRemainedevent);
		event.setTicketsSoldNumberForType(ticketsoldfortypeNoBaseRemainedevent);
		try {
		baseticket = ticketHandler.createTicket(event, TicketType.BASE);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		assertNull(baseticket);
		
	}
	
	
	@Test
	public void noPremiumTicketTest() {
		Ticket premiumticket = null;
		int[] seatsremainedfortypeNoPremiumRemainedevent = {5,0,25};
		int[] ticketsoldfortypeNoPremiumRemainedevent = {70,25,25};
		
		event.setSeatsRemainedNumberForType(seatsremainedfortypeNoPremiumRemainedevent);
		event.setTicketsSoldNumberForType(ticketsoldfortypeNoPremiumRemainedevent);
		try {
		premiumticket = ticketHandler.createTicket(event, TicketType.PREMIUM);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		assertNull(premiumticket);
		
	}
	
	
	@Test
	public void noVipTicketTest() {
		Ticket vipticket = null;
		int[] seatsremainedfortypeNoVipRemainedevent = {30,20,0};
		int[] ticketsoldfortypeNoVipRemainedevent = {45,5,50};
		
		event.setSeatsRemainedNumberForType(seatsremainedfortypeNoVipRemainedevent);
		event.setTicketsSoldNumberForType(ticketsoldfortypeNoVipRemainedevent);
		try {
		vipticket = ticketHandler.createTicket(event, TicketType.VIP);
		}catch(Exception e) {
			System.out.println(e.getMessage()); 
		}
		
		assertNull(vipticket);
		
	}
	
	@Test
	public void noAvailaibleTicketTest() {
		
		int[] seatsremainedfortypeNoTicketevent = {0,0,0};
		int[] ticketsoldfortypeNoTicketevent = {75,25,50};
		Exception exception;
		
		event.setSeatsRemainedNumberForType(seatsremainedfortypeNoTicketevent);
		event.setTicketsSoldNumberForType(ticketsoldfortypeNoTicketevent);
		
		exception = assertThrows(Exception.class, () -> {
									Ticket premiumticket;
									premiumticket = ticketHandler.createTicket(event, TicketType.PREMIUM);
									});
		assertEquals("Evento soldout", exception.getMessage());
		
	}

}
