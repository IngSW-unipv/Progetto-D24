package it.unipv.ingsfw.modelDomain;

public class SubscriptionHandler {

		//costruttore di default
	
	public void stopSub() {
	 
		
		// non dovrebbe servire il change sub percheè per cambiarlo posso comprarne un altro?
		
	}
	
	public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter) {
		boolean check;
		check = payAdapter.paymentMetod(manager);
		if (check == true) {
			manager.setSub(subscription);
		}
	}
}
