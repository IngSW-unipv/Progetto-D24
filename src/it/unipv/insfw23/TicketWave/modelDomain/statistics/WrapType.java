package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Type;

public class WrapType {
    private Type[] typeNameArray;
    private double[] typeResults;
    public WrapType(double[] results, Type[] typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }

    public Type[] getTypeArray() { return typeNameArray;}
    public double[] getTypeResult() {
            return typeResults;
        }
}
