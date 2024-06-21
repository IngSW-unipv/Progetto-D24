package it.unipv.insfw23.TicketWave.test.payment;

import it.unipv.insfw23.TicketWave.modelController.factory.payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PaymentTest {


    PaymentFactory factory;
    private MasterPayAdapter masterPayAdapter;
    private PayPolAdapter payPolAdapter;

    
    @Test
    public void getMasterPayAdapterTest() {

        MasterPayPayment masterPay= new MasterPayPayment();
        masterPayAdapter =PaymentFactory.getMasterPayAdapter(masterPay);
        assertNotNull(masterPayAdapter);

    }

    @Test
    public void getPaypolAdapterTest() {
        PayPolPayment paypol= new PayPolPayment();
        payPolAdapter = PaymentFactory.getPaypolAdapter(paypol);

        assertNotNull(payPolAdapter);

    }
}






















