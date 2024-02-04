package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class PayPalPayment {

	public boolean ppPaymentMethod(User user){
		String mail = user.getEmail();
		return true;
	}

}
