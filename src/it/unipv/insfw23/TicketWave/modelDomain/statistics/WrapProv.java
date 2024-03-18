package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;

import java.util.ArrayList;

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
