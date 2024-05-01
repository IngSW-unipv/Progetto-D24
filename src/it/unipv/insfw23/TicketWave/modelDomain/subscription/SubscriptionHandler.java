package it.unipv.insfw23.TicketWave.modelDomain.subscription;

import it.unipv.insfw23.TicketWave.modelController.factory.subscription.ISubscriptionHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;


public class SubscriptionHandler implements ISubscriptionHandlerFactory {

	public SubscriptionHandler() {};

	@Override
	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter, double subPrice) {

		boolean checkPayment = payAdapter.paymentMethod(subPrice);


		if (checkPayment == true) {  //se il pagamento va a buon fine, setto subscription, e la data nuova
			manager.setSubscription(subscription);
			System.out.println("Pagamento avvenuto con successo. Sottoscrizione e data corrente aggiornate.");

		}
		else {
			manager.setSubscription(-1);
			System.out.println("Pagamento negato");
		}
	}
}
