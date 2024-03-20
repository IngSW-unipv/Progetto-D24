package it.unipv.insfw23.TicketWave.Test.payment;

import it.unipv.insfw23.TicketWave.modelController.Factory.Payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentTest {

    private IPaymentAdapter iPaymentAdapter;

    private PaymentFactory paymentFactory;
    PaymentFactory factory;
    private MastercardAdapter mastercardAdapter;
    private PayPalAdapter payPalAdapter;
    private MastercardPayment mastercardPayment;
    private PayPalPayment payPalPayment;



    private  double price;

    @Before
    public void setUp(){

        price=50.00;


    }
    @Test
    public void testGetMastercardAdapter() {

        mastercardAdapter=PaymentFactory.getIstance().getMastercardAdapter();

        assertNotNull(mastercardAdapter);

    }

    @Test
    public void testGetPaypalAdapter() {
        payPalAdapter= PaymentFactory.getIstance().getPaypalAdapter();

        assertNotNull(payPalAdapter);

    }
}






















