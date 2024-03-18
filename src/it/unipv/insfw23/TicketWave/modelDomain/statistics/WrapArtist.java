package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import java.util.ArrayList;

public class WrapArtist{
    private ArrayList<String> typeNameArray;
    private ArrayList<Double> typeResults;

    public WrapArtist(ArrayList<Double> results, ArrayList<String> typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }
    public ArrayList<String> getArtistNameArray(){
        return typeNameArray;
    }
    public ArrayList<Double> getArtistResult(){
        return typeResults;
    }
}