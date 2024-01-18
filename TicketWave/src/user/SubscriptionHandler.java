package user;
import TicketWave.Event.*;

public class SubscriptionHandler {
	private Manager m; 

	
	 void ChangeSub(int typeSubscription, String iban) {
		
		 if( m.subscription==1  && typeSubscription ==2) {
			 //PAY-MODE with iban
			 m.subscription=2;
			 
		 }
		 else if(m.subscription==2  && typeSubscription ==1) {
			 //PAY-MODE with iban
			 m.subscription=1;
		 }
		 
	 }
	 
	 
	  void buySub(Manager m, int typeSubscription, String iban) {
		  if (typeSubscription==1) {
			  //PAY-MODE with iban 
			  m.subscription=1;
			  
		  }
		  else if(typeSubscription==2) {
			  //PAY-MODE with iban
			  m.subscription=2;
			  
		  }
	  }
	
	
	

	}
