package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class MastercardAdapter implements IPaymentAdapter{

    //public MastercardAdapter(){};

    private IMastercardPayment mastercardPayment;


    public MastercardAdapter(IMastercardPayment mPayment){

        this.mastercardPayment = mPayment;
    }
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = mastercardPayment.msPaymentMethod(obPrice);
        return result;
    }
}