package it.unipv.insfw23.TicketWave.modelDomain.notifications;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import it.unipv.insfw23.TicketWave.modelDomain.user.*;

/**
 * This class represents a notification, an object whose function is to deliver messages to {@link User}s
 */

public class Notification {
	int id;
	String emailReceiver;
	String msg;
	LocalTime time;
	LocalDate date;
	
	/**
	 * This constructor associates the notification's data provided by the {@link NotificationHandler} with the creation date and time 
	 * @param id the identifier number of the created notification
	 * @param emailReceiver a {@link String} that allows to trace back to the notification's recipient {@link User}
	 * @param msg a {@link String} that represents the real message to communicate
	 */	
	public Notification(int id, String emailReceiver, String msg) {
		this.id = id;
		this.emailReceiver = emailReceiver;
		this.msg = msg;
		date = LocalDate.now();
		time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
	}
	
	/**
	 * returns the {@link Integer} number that identifies a notification
	 * @return the notification id
	 */
	public int getId() {
		return id;
	}

	/**
	 * returns a {@link String} to identify the notification recipient 
	 * @return the notification receiver in a {@link String}
	 */
	public String getEmailReceiver() {
		return emailReceiver;
	}
	
	/**
	 * provides the way to set who the notification is for
	 * @param email a {@link String} to identify the notification recipient
	 */
	public void setEmailReceiver(String email) {
		emailReceiver = email;
	}

	/**
	 * returns the notification message   
	 * @return the notification message as a {@link String}
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * returns the notification generation time
	 * @return a {@link LocalTime} that indicates the notification time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * provides the way to set the notification generation time      ,
	 * @param time a {@link LocalTime} value 
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
	 * returns the notification generation date
	 * @return a {@link LocalDate} that indicates the notification date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * provides the way to set the notification generation date
	 * @param date a {@link LocalDate} value
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
		
		
		
}