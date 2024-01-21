package it.unipv.ingsfw.modelDomain;

public class MastercardPayment {
	
	public boolean msPaymentMetod(Manager manager){
		String card = manager.getCreditCard();
		return true;
	}

}
