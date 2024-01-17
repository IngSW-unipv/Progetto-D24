package TicketWave.user;
import TicketWave.Event.*;

public interface IEventCreator {
	void createEvent(String type,String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre);
	Event getEvents(); 

}
