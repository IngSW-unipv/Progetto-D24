package it.unipv.insfw23.TicketWave.modelController.factory.payment;
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PaymentFactory {

    private static MasterPayAdapter masterPayAdapter;
    private static PayPolAdapter paypolAdapter;
    private static PaymentFactory istance=null;

    private static final String M_PROPERTYNAME="masterpay.adapter.class.name";

    private static final String P_PROPERTYNAME="paypol.adapter.class.name";

    private PaymentFactory(){ //costruttore privato per rafFORZAre il pattern Singleton

    }

    public static  PaymentFactory getIstance(){  //Singleton
        if(istance==null){
            istance = new PaymentFactory();
        }
        return istance;
    }

    public static MasterPayAdapter getMasterPayAdapter() {
        if (masterPayAdapter == null) {
            String masterPayAdaptClassName;

            try {
                Properties prop = new Properties(System.getProperties());
                prop.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
                masterPayAdaptClassName = prop.getProperty(M_PROPERTYNAME);

                Constructor c = Class.forName(masterPayAdaptClassName).getConstructor();  //java reflection
                masterPayAdapter = (MasterPayAdapter) c.newInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return masterPayAdapter;
    }

        public static PayPolAdapter getPaypolAdapter(){
            if(paypolAdapter ==null){
                String paypalAdaptClassName;

                try{
                    Properties prop = new Properties(System.getProperties());
                    prop.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
                    paypalAdaptClassName=prop.getProperty(P_PROPERTYNAME);

                    Constructor c= Class.forName(paypalAdaptClassName).getConstructor();
                    paypolAdapter =(PayPolAdapter)c.newInstance();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return paypolAdapter;
    }



}
