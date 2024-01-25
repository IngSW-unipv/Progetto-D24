package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.time.LocalDateTime;
import it.unipv.insfw23.TicketWave.modelDomain.user.*;

public class Notification {
		int id;
		String emailreceiver;
		String msg;
		LocalDateTime time;
		
		public Notification(int id, User receiver, String msg) {
			this.id = id;
			emailreceiver = receiver.getEmail();
			this.msg = msg;
			time = LocalDateTime.now();
		}
}