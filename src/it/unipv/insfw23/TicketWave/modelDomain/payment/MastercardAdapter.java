package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class MastercardAdapter implements IPaymentAdapter{

    private MastercardPayment mastercardPayment;

    public MastercardAdapter(MastercardPayment mPayment){
        this.mastercardPayment = mPayment;
    }
    @Override
    public boolean paymentMethod(User user){
        boolean result = mastercardPayment.msPaymentMethod(user);
        return result;
    }
}