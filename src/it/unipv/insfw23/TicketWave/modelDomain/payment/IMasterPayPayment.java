package it.unipv.insfw23.TicketWave.modelDomain.payment;

/**
 * Interface that every payment method object must implement (or extend if is an interface) to
 * work as MasterPay (invented payment method).
 * Implemented by {@link MasterPayPayment}.
 */
public interface IMasterPayPayment {
    public boolean msPaymentMethod(double obPrice);
}
