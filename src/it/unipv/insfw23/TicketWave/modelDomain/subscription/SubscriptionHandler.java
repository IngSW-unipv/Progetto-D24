package it.unipv.ingsfw23.modelDomain;
import it.unipv.ingsfw23.modelDomain.user.*;
public class SubscriptionHandler {

		//costruttore di default
	
	public void stopSub() {
	 
		
		// non dovrebbe servire il change sub percheè per cambiarlo posso comprarne un altro?
		//si mettiamo solo un metodo, mi sembra più logico
	}
	
	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter) {
		boolean check;
		check = payAdapter.paymentMetod(manager);
		if (check == true) {
			manager.setSub(subscription);
		}
	}
}
