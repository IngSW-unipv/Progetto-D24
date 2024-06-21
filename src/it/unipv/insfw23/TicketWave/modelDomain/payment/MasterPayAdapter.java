package it.unipv.insfw23.TicketWave.modelDomain.payment;



/**
 * This class provide specific methods for processing payments via MasterPay.
 * Is the connection between this software and the real payment infrastructure.
 * Implements {@link IPaymentAdapter} interface.
 *
 * @see MasterPayPayment
 */
public class MasterPayAdapter implements IPaymentAdapter{

    //public MasterPayAdapter(){};

    private IMasterPayPayment masterPayPayment;


    public MasterPayAdapter(){
        this.masterPayPayment = new MasterPayPayment();
    }


    /**
     *Method that passes the payments to the proprietary framework.
     * @param obPrice
     * @return result
     */
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = masterPayPayment.msPaymentMethod(obPrice);
        return result;
    }
}