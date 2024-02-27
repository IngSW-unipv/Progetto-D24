package it.unipv.insfw23.TicketWave.modelDomain.event;

import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class Provadaeliminare {
	public static void main(String[] args) {
		int intvett[] = {2,5};
		ArrayList<Event> arrev = new ArrayList<>();
		LocalDate date;
		date = LocalDate.now();
		System.out.println(date);
		Manager m = new Manager("sa","asf","aaf","egns","er",3,intvett,arrev,5,7,date,4);
		
		System.out.println(m);
		ArrayList<Event> arrev2 = new ArrayList<>();
		ArrayList<String> arrstr = new ArrayList<>();
		
	//	Event ev2 = new Event();
		
		Event ev1 = new Concert(23,"tr","dkngs","dksngs",Province.valueOf("AGRIGENTO"),4,5,intvett,Genre.HOUSE,m,arrstr,"sa");

		
		arrev2.add(ev1);
		
		m.setEvent(arrev2);
		
		System.out.println(m);
		System.out.println(m.getEventlist().get(0).getCreator());
		
		Manager m2 = m.getEventlist().get(0).getCreator();
		
		System.out.println();
		
	}
	
}
