package it.unipv.insfw23.TicketWave.modelDomain.payment;

public class MasterPayAdapter implements IPaymentAdapter{

    //public MasterPayAdapter(){};

    private IMasterPayPayment mastercardPayment;


    public MasterPayAdapter(IMasterPayPayment mPayment){

        this.mastercardPayment = mPayment;
    }
    @Override
    public boolean paymentMethod(double obPrice){
        boolean result = mastercardPayment.msPaymentMethod(obPrice);
        return result;
    }
}