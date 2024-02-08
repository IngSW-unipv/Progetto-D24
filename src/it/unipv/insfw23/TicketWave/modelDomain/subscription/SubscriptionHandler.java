package it.unipv.insfw23.TicketWave.modelDomain.subscription;

import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public class SubscriptionHandler {

		//costruttore di default
	
	public void stopSub(){
		
		// non dovrebbe servire il change sub percheè per cambiarlo posso comprarne un altro?
		//si mettiamo solo un metodo, mi sembra più logico
	}
	
	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter) {

		boolean checkPayment = payAdapter.paymentMethod(manager);

		if (checkPayment == true) {
			manager.setSubscription(subscription);
		}
		else {
			System.out.println("Pagamento negato");
		}
	}
}
