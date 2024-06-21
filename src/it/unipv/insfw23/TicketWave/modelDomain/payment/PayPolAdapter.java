package it.unipv.insfw23.TicketWave.modelDomain.payment;


/**
 * This class provide specific methods for processing payments via PayPol.
 * Is the connection between this software and the real payment infrastructure.
 * Implements {@link IPaymentAdapter} interface.
 *
 * @see PayPolPayment
 */
public class PayPolAdapter implements IPaymentAdapter{
    
    private IPaypolPayment paypolPayment;

    public PayPolAdapter(){
        this.paypolPayment = new PayPolPayment();
    }

    /**
     *Method that passes the payments to the proprietary framework.
     * @param obPrice
     * @return result
     */
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = paypolPayment.ppPaymentMethod(obPrice);
        return result;
    }
}