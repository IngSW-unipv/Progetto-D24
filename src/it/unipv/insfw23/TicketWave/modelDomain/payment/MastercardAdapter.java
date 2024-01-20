public class MastercardAdapter() implements IPaymentAdapter{

    private MastercardPayment mastercardPayment;

    public MastercardAdapter(MastercardPayment mPayment){
        this.mastercardPayment = mPayment;
    }

    public boolean paymentMetod(Manager manager){
        result = mastercardPayment.msPaymentMetod(manager);
        return result;
    }
}