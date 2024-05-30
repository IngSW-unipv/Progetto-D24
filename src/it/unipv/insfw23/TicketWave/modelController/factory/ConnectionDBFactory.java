package it.unipv.insfw23.TicketWave.modelController.factory;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class ConnectionDBFactory {
    //Attributes
    private static ConnectionDB connectionDB;
    private static final String CONNECTIONDB_PROPERTYNAME = "connectiondb.class.name";
    private static ConnectionDBFactory instance = null;


    private ConnectionDBFactory() {
    }

    //Singleton
    public static ConnectionDBFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionDBFactory();
        }//end-if
        return instance;
    }


    //Method to get statisticsHandler
    public ConnectionDB getConnectionDB() {
        if (connectionDB == null) {
            String connectionDBClassName;

            try {
                //Obtaining path for connectionDB
                Properties p = new Properties(System.getProperties());
                p.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties")); // path proprietaria
                connectionDBClassName = p.getProperty(CONNECTIONDB_PROPERTYNAME);


                //JavaReflection
                Constructor c = Class.forName(connectionDBClassName).getConstructor();
                connectionDB = (ConnectionDB) c.newInstance();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return connectionDB;
    }
}
