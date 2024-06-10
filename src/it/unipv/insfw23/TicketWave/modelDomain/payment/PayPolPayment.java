package it.unipv.insfw23.TicketWave.modelDomain.payment;

public class PayPolPayment implements IPaypolPayment {

    public boolean ppPaymentMethod( double obPrice){
        if(obPrice >= 0){
            return true;
        }
        else {
            System.out.println("Pagamento annullato");
            return false;
        }
    }

}