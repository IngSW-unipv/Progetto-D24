package it.unipv.insfw23.TicketWave.modelDomain.subscription;

import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;

import java.time.LocalDate;

public class SubscriptionHandler {
	private static SubscriptionHandler istance;


	// costruttore privato per singleton
	private SubscriptionHandler() {};

	public static SubscriptionHandler getIstance(){
		if(istance == null){
			istance = new SubscriptionHandler();
		}
		return istance;
	}


	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter, double subPrice) {

		boolean checkPayment = payAdapter.paymentMethod(subPrice);


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
