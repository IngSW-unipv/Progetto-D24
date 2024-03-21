package it.unipv.insfw23.TicketWave.modelController.Factory.Payment;
import it.unipv.insfw23.TicketWave.modelDomain.payment.MastercardAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.payment.PayPalAdapter;

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

    public static MastercardAdapter getMastercardAdapter() {
        if (mastercardAdapter == null) {
            String mastercardAdaptClassName;

            try {
                Properties prop = new Properties(System.getProperties());
                prop.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
                mastercardAdaptClassName = prop.getProperty(M_PROPERTYNAME);

                Constructor c = Class.forName(mastercardAdaptClassName).getConstructor();  //java reflection
                mastercardAdapter = (MastercardAdapter) c.newInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return mastercardAdapter;
    }

        public static PayPalAdapter getPaypalAdapter(){
            if(paypalAdapter==null){
                String paypalAdaptClassName;

                try{
                    Properties prop = new Properties(System.getProperties());
                    prop.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
                    paypalAdaptClassName=prop.getProperty(P_PROPERTYNAME);

                    Constructor c= Class.forName(paypalAdaptClassName).getConstructor();
                    paypalAdapter=(PayPalAdapter)c.newInstance();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return paypalAdapter;
    }



}
