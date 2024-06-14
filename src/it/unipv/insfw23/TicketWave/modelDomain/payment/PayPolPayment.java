package it.unipv.insfw23.TicketWave.modelDomain.payment;


/**
 * This class provide specific methods for processing payments via PayPol.
 * This class simulates the software framework of a real payment entity, providing
 * payment outcomes. However, in a future implementation, the software should connect
 * to a real payment processor that operates similarly to PayPol.
 *
 * Implements {@link IPaymentAdapter} interface.
 *
 */
public class PayPolPayment implements IPaypolPayment {

    public boolean ppPaymentMethod( double obPrice){
        if(obPrice >= 0){
            return true;
        }
        else {
            System.out.println("Pagamento annullato");
            return false;
        }
    }

}