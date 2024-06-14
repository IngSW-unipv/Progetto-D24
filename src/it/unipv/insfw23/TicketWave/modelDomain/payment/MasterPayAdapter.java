package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapArtist;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

/**
 * This class provide specific methods for processing payments via MasterPay.
 * Is the connection between this software and the real payment infrastructure.
 * Implements {@link IPaymentAdapter} interface.
 *
 * @see MasterPayPayment
 */
public class MasterPayAdapter implements IPaymentAdapter{

    //public MasterPayAdapter(){};

    private IMasterPayPayment mastercardPayment;


    public MasterPayAdapter(IMasterPayPayment mPayment){
        this.mastercardPayment = mPayment;
    }


    /**
     *Method that passes the payments to the proprietary framework.
     * @param obPrice
     * @return result
     */
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = mastercardPayment.msPaymentMethod(obPrice);
        return result;
    }
}