package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class MastercardPayment implements IMastercardPayment{

    @Override
    public boolean msPaymentMethod(double obPrice){

        if (obPrice > 0) {
            return true;
        }
        else {
            System.out.println("Pagamento annullato");
            return false;
        }
    }
}