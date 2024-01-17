package TicketWave.user;
import TicketWave.Event.*;

import java.lang.String;

public class Manager extends User {
	
	private String iban;
	private Event event[]; 
	private int maxNumberOfEvents; 
	protected int subscription;
	                     
	public Manager (String name, String surname, String dateOfBirth, String email, String password, int provinceOfResidence,String iban,int subscription, int maxNumberOfEvents,Event event[]) {
		super(name,surname,dateOfBirth,email,password,provinceOfResidence);
		this.iban=iban; 
		this.maxNumberOfEvents=maxNumberOfEvents;
		this.event= event;
		this.subscription=subscription;
		subscription=0;
		
		
	}
	
	
	

/*
	@Override
	public void createEvent(Event event[], int maxNumberOfEvents,String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre){
		for(int i=0; i<maxNumberOfEvents;i++) {
			if (event [i] == null) {
				switch 
				
				event[i]= new Event (name,city,location,province,maxNumberOfSeats,ticketsSoldNumber,genre);
			}
		
		} 
	}
	
*/

	@Override
	public void createEvent(String type,String name, String city, String location, Province province, int maxNumberOfSeats, int ticketsSoldNumber, Genre genre) {
		int index=0;
		if (index<maxNumberOfEvents) {
			
		
		switch (type) {
		
		//non posso mettere i parametri del costruttore di Event ma dovrei mettere i parametri di tutti i costruttori sulla firma dell'evento,non conviene  usare Adapter? 
		 
		case "Theater":
			event[index]=new Theater(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre, location);
			index++;
			break; 
			
		case "Festival":
			event[index]=new Festival(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre, location);
			index++;
			
		case "Concert":
			event[index]=new Concert(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre, location);
			index++;
			
		case "Other":
			event[index]=new Other(name, city, location, province, maxNumberOfSeats, ticketsSoldNumber, genre, location);
			index++;
			
			
			
		}
	}
	
	}

	@Override
	public Event getEvents() {
		Event e;
		for(int i=0; i<event.length;i++) {
			e=event[i];
			return e; 
			

		 
		
		
		}
	}
}




	




	

		
	
		
	


	
	

	
	
	
	


