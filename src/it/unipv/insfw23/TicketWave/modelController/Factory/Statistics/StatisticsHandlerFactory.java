package it.unipv.insfw23.TicketWave.modelController.Factory.Statistics;

import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class StatisticsHandlerFactory {


    //Attributes
    private static StatisticsHandler statisticsHandler;
    private static final String STATISTICSHANDLER_PROPERTYNAME = "statisticshandler.class.name";
    private static StatisticsHandlerFactory instance = null;


    private StatisticsHandlerFactory() {
    }

    //Singleton
    public static StatisticsHandlerFactory getInstance() {
        if (instance == null) {
            instance = new StatisticsHandlerFactory();
        }//end-if
        return instance;
    }


    //Method to get statisticsHandler
    public IStatisticsHandler getStatisticsHandler() {
        if (statisticsHandler == null) {
            String statisticsHandlerClassName;

            try {
                //Obtaining path for statisticsHandler
                Properties p = new Properties(System.getProperties());
                p.load(new FileInputStream("it/unipv/insfw23/TicketWave/properties")); // path proprietaria
                statisticsHandlerClassName = p.getProperty(STATISTICSHANDLER_PROPERTYNAME);


                //JavaReflection
                Constructor c = Class.forName(statisticsHandlerClassName).getConstructor();
                statisticsHandler = (StatisticsHandler) c.newInstance();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return statisticsHandler;
    }
}