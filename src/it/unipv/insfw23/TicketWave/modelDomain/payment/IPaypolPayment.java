package it.unipv.insfw23.TicketWave.modelDomain.payment;

/**
 * Interface that every payment method object must implement (or extend if is an interface) to
 * work as PayPol(invented payment method).
 * Implemented by {@link PayPolPayment}.
 */
public interface IPaypolPayment {
    public boolean ppPaymentMethod(double obPrice);
}
