package it.unipv.insfw23.TicketWave.modelDomain.subscription;

import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.time.LocalDate;

public class SubscriptionHandler {

		//costruttore di default
	
	public void stopSub(){
		
		// non dovrebbe servire il change sub percheè per cambiarlo posso comprarne un altro?
		//si mettiamo solo un metodo, mi sembra più logico
	}
	
	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter, double subPrice) {

		boolean checkPayment = payAdapter.paymentMethod(subPrice);

		if (manager.OneMonthPassed()) { // se è passato un mese o più dall'ultima sub
			manager.setSubscription(-1); // setto subscription a -1 (scaduta)
		}

		if (checkPayment == true) {  //se il pagamento va a buon fine, setto subscription, e la data nuova
			manager.setSubscription(subscription);
			manager.setSubscriptionDate(LocalDate.now());
			System.out.println("Pagamento avvenuto con successo. Sottoscrizione e data corrente aggiornate.");

		}
		else {
			manager.setSubscription(-1);
			System.out.println("Pagamento negato");
		}
	}
}
