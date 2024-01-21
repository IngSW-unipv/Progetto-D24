package it.unipv.insfw23.TicketWave.modelDomain.user;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import java.util.List;

//interfaccia da discutere

public interface IEventCreator {
	void createEvent(String type,String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre);

	List <Event> getEvents();

}
