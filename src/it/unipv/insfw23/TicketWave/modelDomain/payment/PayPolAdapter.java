package it.unipv.insfw23.TicketWave.modelDomain.payment;

public class PayPolAdapter implements IPaymentAdapter{
    
    private IPaypolPayment paypalPayment;

    public PayPolAdapter(IPaypolPayment ppPayment){
        this.paypalPayment = ppPayment;
    }
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = paypalPayment.ppPaymentMethod(obPrice);
        return result;
    }
}