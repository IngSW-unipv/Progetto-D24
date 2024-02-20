package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class MastercardPayment {
	
	public boolean msPaymentMethod(User user){

		int[] creditCard = getCreditCard();

		if (creditCard.length == 16) {
			return true;
		}
		else {
			System.out.println("Numero carta non corretto, riprovare");
			return false;
		}
	}

	public int[] getCreditCard(){
		// chiede alla UI la carta di credito, da inserire al momento del pagamento, da tastiera
		int[] card = {1,1};
		return card;
	}
}
