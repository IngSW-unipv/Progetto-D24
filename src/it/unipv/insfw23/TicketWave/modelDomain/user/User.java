package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;

import java.util.*;
/**
 * Abstract class that represents a generic Event.
 * Contains attributes and methods common and attributes  to {@link Manager}, {@link Customer}.
 *
 */
public abstract class User {

	private String name;
	private String surname;
	private String dateOfBirth;
	private String email;
	private String password;
	private Province provinceOfResidence;
	private ArrayList<Notification> notifications;

	/**
	 * Complete constructor to initialize all parameters.
	 * Note that an abstract class cannot be instantiated, this constructor will be used by classes that extend User to initialize parameters.
	 */
	public User(String name, String surname, String dateOfBirth, String email, String password, Province provinceOfResidence) {
		
		this.name=name;
		this.surname= surname;
		this.dateOfBirth= dateOfBirth;
		this.email=email;
		this.password= password;
		this.provinceOfResidence= provinceOfResidence;
		notifications = new ArrayList<Notification>();  //ArrayList di notifiche da caricare dalla connessione al DB


	}

	/**
	 *
	 * @return Name as a @{Link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the Name of the User
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return Surname as a {@link String}
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set the surname of the User
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 *
	 * @return DateOfBirth as a {@link String}
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}



	/**
	 *
	 * @return Email of the user as a {@link String}
	 */
	public String getEmail() {
		return email;
	}


	/**
	 *
	 * @return Password of the User as a {@link String}
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Set the Password of the User
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @return the ProvinceOfResidence as a {@link Province}
	 */
	public Province getProvinceOfResidence() { return provinceOfResidence;
	}


	/**
	 *
	 * @return Notification as an {@link ArrayList}
	 */
	public ArrayList<Notification> getNotification(){
		return notifications;
	}


	/**
	 * The method add a notification on the  {@link ArrayList} of Notification
	 * @param n
	 */
	public void addNotification(Notification n) {
		notifications.add(n);
	}

	/**
	 * Method to recognize a {@link Customer} or a {@link Manager}
	 * @return {@link Boolean}
	 */

	public abstract boolean isCustomer();


}
