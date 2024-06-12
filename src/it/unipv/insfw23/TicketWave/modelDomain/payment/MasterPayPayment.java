package it.unipv.insfw23.TicketWave.modelDomain.payment;

public class MasterPayPayment implements IMasterPayPayment {

    @Override
    public boolean msPaymentMethod(double obPrice){

        if (obPrice >= 0) {
            return true;
        }
        else {
            System.out.println("Pagamento annullato");
            return false;
        }
    }
}