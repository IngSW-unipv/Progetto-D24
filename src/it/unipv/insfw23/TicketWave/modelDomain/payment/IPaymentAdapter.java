package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;


/**
 * Interface that every paymentAdapter object must implement (or extend if is an interface).
 * A paymentAdapter object is the connection between this software and the external payment infrastructure.
 * Implemented by {@link MasterPayAdapter} and {@link PayPolAdapter}.
 */
public interface IPaymentAdapter {

    public boolean paymentMethod(double obPrice);
}