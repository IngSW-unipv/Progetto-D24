package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import java.util.ArrayList;

public class WrapArtist{
    private ArrayList<String> artistNameArray;
    private ArrayList<Double> artistResults;

    public WrapArtist(ArrayList<Double> results, ArrayList<String> typeNameArray) {
        this.artistNameArray = typeNameArray;
        this.artistResults = results;
    }
    public ArrayList<String> getArtistNameArray(){
        return artistNameArray;
    }
    public ArrayList<Double> getArtistResult(){ return artistResults;}
}