package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;

import java.util.ArrayList;

public class WrapGenre{
    private ArrayList<Genre> prNameArray;
    private ArrayList<Double> numberResults;

    public WrapGenre(ArrayList<Double> results, ArrayList<Genre> typeNameArray) {
        this.prNameArray = typeNameArray;
        this.numberResults = results;
    }
    public ArrayList<Genre> getGenreArray(){
        return prNameArray;
    }

    public ArrayList<Double> getGenreResult(){
        return numberResults;
    }
}
