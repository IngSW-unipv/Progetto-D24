package it.unipv.insfw23.TicketWave.modelController.factory.subscription;

import it.unipv.insfw23.TicketWave.modelDomain.subscription.SubscriptionHandler;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class SubscriptionHandlerFactory {
    private static SubscriptionHandlerFactory instance = null;
    private static final String SUBSCRIPTIONHANDLER_PROPERTYNAME = "subscriptionhandler.class.name";
    private SubscriptionHandler subscriptionHandler;

    private SubscriptionHandlerFactory() {
    }

    // Singleton per prendere l'unica istanza del SubscriptionHandlerFactory
    public static SubscriptionHandlerFactory getInstance() {
        if (instance == null) {
            instance = new SubscriptionHandlerFactory();
        }//end-if
        return instance;
    }

    // Metodo per prendere l'istanza dell'unico SubscriptionHandler
    public SubscriptionHandler getSubscriptionHandler() {
        if (subscriptionHandler == null) {
            String subscriptionHandlerClassName;

            try {
                //Obtaining path for subscriptionHandler
                Properties p = new Properties(System.getProperties());
                p.load(new FileInputStream("it/unipv/insfw23/TicketWave/properties")); // path proprietaria
                subscriptionHandlerClassName = p.getProperty(SUBSCRIPTIONHANDLER_PROPERTYNAME);


                //JavaReflection
                Constructor c = Class.forName(subscriptionHandlerClassName).getConstructor();
                subscriptionHandler = (SubscriptionHandler) c.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return subscriptionHandler;
    }


}
