package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;


/**
 * This wrap class represents the result of a statistic and contains 2 arrays:
 * one for the numerical results (double) and one for the corresponding types ({@link Type}).
 *
 * @see StatisticsHandler
 */
public class WrapType {
    private Type[] typeNameArray;
    private double[] typeResults;
    public WrapType(double[] results, Type[] typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }

    public Type[] getTypeArray() {
        return typeNameArray;
    }

    public double[] getTypeResult() {
            return typeResults;
        }
}
