package it.unipv.insfw23.TicketWave.modelDomain.subscription;

import it.unipv.insfw23.TicketWave.modelController.factory.subscription.ISubscriptionHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

/**
 * This class represents the subscription mananger, check if the payment has been made and
 * sets the subscription as needed.
 */
public class SubscriptionHandler implements ISubscriptionHandlerFactory {

	public SubscriptionHandler() {};


	/**
	 *Method that start the subscription payment, accepts the chosen method and sets the subscription.
	 * @param manager
	 * @param subscription
	 * @param payAdapter
	 * @param subPrice
	 */
	@Override
	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter, double subPrice) {

		boolean checkPayment = payAdapter.paymentMethod(subPrice);


		if (checkPayment == true) {  //se il pagamento va a buon fine, setto subscription, la data nuova e azzero gli eventi creati nel mese
			manager.setSubscription(subscription);
			System.out.println("Pagamento avvenuto con successo. Sottoscrizione e data corrente aggiornate.");

		}
		else {
			manager.setSubscription(-1);
			System.out.println("Pagamento negato");
		}
	}
}
