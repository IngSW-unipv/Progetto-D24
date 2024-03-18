package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;

import java.util.ArrayList;

public class WrapProv{
    private ArrayList<Province> prNameArray;
    private ArrayList<Double> numberResults;

    public WrapProv(ArrayList<Double> results, ArrayList<Province> typeNameArray) {
        this.prNameArray = typeNameArray;
        this.numberResults = results;
    }
    public ArrayList<Province> getProvinceArray(){
        return prNameArray;
    }
    public ArrayList<Double> getProvResult(){ return numberResults; }
}
