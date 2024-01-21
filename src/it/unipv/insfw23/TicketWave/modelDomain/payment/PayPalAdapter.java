public class PayPalAdapter() implements IPaymentAdapter{
    
    private PayPalPayment paypalPayment;

    public PayPalAdapter(PayPalPayment ppPayment){
        this.paypalPayment = ppPayment;
    }

    public boolean paymentMetod(Manager manager){
        result = paypalPayment.ppPaymentMetod(manager);
        return result;
    }
}