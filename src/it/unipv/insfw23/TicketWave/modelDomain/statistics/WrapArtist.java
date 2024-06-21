package it.unipv.insfw23.TicketWave.modelDomain.statistics;


import java.util.ArrayList;


/**
 * This wrap class represents the result of a statistic and contains 2 arrayLists:
 * one for the numerical results (Double) and one for the corresponding artists (String).
 *
 * @see StatisticsHandler
 */
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
    
    public ArrayList<Double> getArtistResult(){ 
    	return artistResults;
    }
}