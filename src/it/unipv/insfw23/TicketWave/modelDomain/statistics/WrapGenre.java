package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;

import java.util.ArrayList;

public class WrapGenre{
    private ArrayList<Genre> genreNameArray;
    private ArrayList<Double> genreResults;

    public WrapGenre(ArrayList<Double> results, ArrayList<Genre> typeNameArray) {
        this.genreNameArray = typeNameArray;
        this.genreResults = results;
    }
    public ArrayList<Genre> getGenreArray(){
        return genreNameArray;
    }

    public ArrayList<Double> getGenreResult(){
        return genreResults;
    }
}
