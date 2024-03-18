package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import it.unipv.insfw23.TicketWave.modelDomain.user.*;

public class Notification {
		int id;
		String emailreceiver;
		String msg;
		LocalTime time;
		LocalDate date;
		
		public Notification(int id, User receiver, String msg) {
			this.id = id;
			emailreceiver = receiver.getEmail();
			this.msg = msg;
			date = LocalDate.now();
			time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public LocalTime getTime() {
			return time;
		}

		public void setTime(LocalTime time) {
			this.time = time;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}
		
		
		
}