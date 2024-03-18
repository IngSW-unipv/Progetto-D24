package it.unipv.insfw23.TicketWave.modelDomain.payment;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public interface IPaymentAdapter {

    public boolean paymentMethod(double obPrice);
}