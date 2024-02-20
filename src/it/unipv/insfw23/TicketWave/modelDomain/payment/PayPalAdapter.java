package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class PayPalAdapter implements IPaymentAdapter{
    
    private PayPalPayment paypalPayment;

    public PayPalAdapter(PayPalPayment ppPayment){
        this.paypalPayment = ppPayment;
    }
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = paypalPayment.ppPaymentMethod(obPrice);
        return result;
    }
}