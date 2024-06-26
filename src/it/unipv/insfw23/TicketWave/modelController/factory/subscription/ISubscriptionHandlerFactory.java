package it.unipv.insfw23.TicketWave.modelController.factory.subscription;

import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public interface ISubscriptionHandlerFactory {
    public void buySub(Manager manager, int subscription, IPaymentAdapter payAdapter, double subPrice);
}
