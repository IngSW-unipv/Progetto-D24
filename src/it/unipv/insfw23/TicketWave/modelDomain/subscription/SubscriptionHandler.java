package it.unipv.insfw23.TicketWave.modelDomain.subscription;

import it.unipv.insfw23.TicketWave.modelDomain.FactoryHandler.Handler;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.NotificationHandler;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import java.time.LocalDate;

<<<<<<< HEAD
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

=======
public class SubscriptionHandler implements Handler {

	private static SubscriptionHandler istance = null; // Singleton
	//costruttore di default

	private SubscriptionHandler() {
	}
	public static SubscriptionHandler getIstance() {
		if(istance == null) {
			istance = new SubscriptionHandler();
		}
		return istance;
	}
>>>>>>> origin/master

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

	public void handle(){
		System.out.println("Gestendo le sub");
	}
}
