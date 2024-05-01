package it.unipv.insfw23.TicketWave.modelController.factory.payment;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PaymentFactory {

    private static MastercardAdapter mastercardAdapter;
    private static PayPalAdapter paypalAdapter;
    private static PaymentFactory istance=null;

    private static final String M_PROPERTYNAME="mastercard.adapter.class.name";

    private static final String P_PROPERTYNAME="paypal.adapter.class.name";

    private PaymentFactory(){ //costruttore privato per rafFORZAre il pattern Singleton

    }

    public static  PaymentFactory getIstance(){  //Singleton
        if(istance==null){
            istance = new PaymentFactory();
        }
        return istance;
    }

    public static MastercardAdapter getMastercardAdapter(IMastercardPayment payment) {
        if (mastercardAdapter == null) {
            String mastercardAdaptClassName;

            try {
                Properties prop = new Properties(System.getProperties());
                prop.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
                mastercardAdaptClassName = prop.getProperty(M_PROPERTYNAME);

                Constructor c = Class.forName(mastercardAdaptClassName).getConstructor(IMastercardPayment.class);  //java reflection
                mastercardAdapter = (MastercardAdapter) c.newInstance(payment);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return mastercardAdapter;
    }

        public static PayPalAdapter getPaypalAdapter(IPaypalPayment payment){
            if(paypalAdapter==null){
                String paypalAdaptClassName;

                try{
                    Properties prop = new Properties(System.getProperties());
                    prop.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
                    paypalAdaptClassName=prop.getProperty(P_PROPERTYNAME);

                    Constructor c= Class.forName(paypalAdaptClassName).getConstructor(IPaypalPayment.class);
                    paypalAdapter=(PayPalAdapter)c.newInstance(payment);
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return paypalAdapter;
    }



}
