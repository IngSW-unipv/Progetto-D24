package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;

import java.util.*;

public abstract class User {

	private String name;
	private String surname;
	private String dateOfBirth;
	private String email;
	private String password;
	private int provinceOfResidence;
	private ArrayList<Notification> notifications;
	
	public User(String name, String surname, String dateOfBirth, String email, String password, int provinceOfResidence) {
		
		this.name=name;
		this.surname= surname;
		this.dateOfBirth= dateOfBirth;
		this.email=email;
		this.password= password;
		this.provinceOfResidence= provinceOfResidence;
		//caricamento dell'arraylist delle notifiche dal db
		notifications = new ArrayList<Notification>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProvinceOfResidence() { return provinceOfResidence;
	}

	public ArrayList<Notification> getNotification(){
		return notifications;
	}
	
	public void addNotification(Notification n) {
		notifications.add(n);
	}

	public abstract boolean isCustomer();


}
