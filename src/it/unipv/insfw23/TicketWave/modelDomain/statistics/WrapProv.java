package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;

import java.util.ArrayList;

/**
 * This wrap class represents the result of a statistic and contains 2 arrayLists:
 * one for the numerical results (Double) and one for the corresponding provinces ({@link Province}).
 *
 * @see StatisticsHandler
 */
public class WrapProv{
    private ArrayList<Province> provNameArray;
    private ArrayList<Double> provResults;

    public WrapProv(ArrayList<Double> results, ArrayList<Province> typeNameArray) {
        this.provNameArray = typeNameArray;
        this.provResults = results;
    }
    public ArrayList<Province> getProvinceArray(){
        return provNameArray;
    }
    public ArrayList<Double> getProvResult(){ return provResults; }
}
