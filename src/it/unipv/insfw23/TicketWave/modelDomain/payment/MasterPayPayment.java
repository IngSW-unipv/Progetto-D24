package it.unipv.insfw23.TicketWave.modelDomain.payment;

/**
 * This class provide specific methods for processing payments via MasterPay.
 * This class simulates the software framework of a real payment entity, providing
 * payment outcomes. However, in a future implementation, the software should connect
 * to a real payment processor that operates similarly to MasterPay.
 *
 * Implements {@link IPaymentAdapter} interface.
 *
 */
public class MasterPayPayment implements IMasterPayPayment {

    @Override
    public boolean msPaymentMethod(double obPrice){

        if (obPrice >= 0) {
            return true;
        }
        else {
            System.out.println("Pagamento annullato");
            return false;
        }
    }
}