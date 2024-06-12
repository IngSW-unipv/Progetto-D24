package it.unipv.insfw23.TicketWave.test.payment;

import it.unipv.insfw23.TicketWave.modelController.factory.payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentTest {

    private IPaymentAdapter iPaymentAdapter;

    private PaymentFactory paymentFactory;
    PaymentFactory factory;
    private MasterPayAdapter masterPayAdapter;
    private PayPolAdapter payPolAdapter;
    private MasterPayPayment masterPayPayment;
    private PayPolPayment payPolPayment;



    private  double price;

    @Before
    public void setUp(){

        price=50.00;


    }
    @Test
    public void testGetMasterPayAdapter() {

        MasterPayPayment masterPay= new MasterPayPayment();
        masterPayAdapter =PaymentFactory.getIstance().getMasterPayAdapter(masterPay);
        assertNotNull(masterPayAdapter);

    }

    @Test
    public void testGetPaypolAdapter() {
        PayPolPayment paypol= new PayPolPayment();
        payPolAdapter = PaymentFactory.getIstance().getPaypolAdapter(paypol);

        assertNotNull(payPolAdapter);

    }
}






















