package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.Factory.Statistics.IStatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.subscription.SubscriptionHandler;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static it.unipv.insfw23.TicketWave.modelDomain.event.Province.valueOf;

public class StatisticsHandler implements IStatisticsHandler {

    private static StatisticsHandler istance;


    // costruttore privato per singleton
    private StatisticsHandler() {};

    public static StatisticsHandler getIstance(){
        if(istance == null){
            istance = new StatisticsHandler();
        }
        return istance;
    }


    @Override
    public WrapType typeStats(Manager manager) {
        int[] typeCode;
        String[] typeName;

        int eventCounter = 0;
        ArrayList<Event> eventList = manager.getEventlist();

        enumType[] typeArray = enumType.values();

        int typeCodeArrayLenght = Array.getLength(typeArray);
        double[] results = new double[typeCodeArrayLenght];

        for (int j = 0; j < Array.getLength(typeArray); j++) {
            for (Event currentEvent : eventList) {

                if (currentEvent.getType() == typeArray[j]) {

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn / maxn) * 100;
                    eventCounter++;
                    results[j] = results[j] + percResult;
                }
            }
            results[j] = results[j] / eventCounter;
            eventCounter = 0;
        }

        WrapType returnClass = new WrapType(results, typeArray);
        return returnClass;
    }

    @Override
    public WrapArtist artistStats(enumType typeCode, Manager manager){

        ArrayList<Event> eventList = manager.getEventlist();
        ArrayList<String> artistNames = new ArrayList<>();

        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Integer> artistCounter = new ArrayList<>();

        for (Event currentEvent : eventList) {

            if (currentEvent.getType() == typeCode) {
                int index = artistNames.indexOf(currentEvent.getArtists());

                if (index >= 0) {

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn / maxn) * 100;

                    artistCounter.set(index, artistCounter.get(index) + 1);
                    results.set(index, results.get(index) + percResult);
                } else {
                    artistNames.add(currentEvent.getArtists());
                    int newindex = artistNames.indexOf(currentEvent.getArtists());

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn / maxn) * 100;

                    artistCounter.set(newindex, 1);
                    results.add(newindex, percResult);

                }
            }
        }

        if (artistNames.size() == results.size()) {
            for (int indexMod = 0; indexMod < artistNames.size(); indexMod++) {
                results.set(indexMod, (results.get(indexMod) / artistCounter.get(indexMod)));
            }
        }

        WrapArtist wrapRes = new WrapArtist(results, artistNames);
        return wrapRes;   //classe wrapper per restituire i due array che servono, ovvero artistNames creata, e results
    }

    @Override
    public WrapGenre genreStats(enumType typeCode, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        enumGenre[] genreArray = enumGenre.values();

        ArrayList<enumGenre> genreArrayRes = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();

        int eventCounter = 0;

        for (int j = 0; j < Array.getLength(genreArray); j++) {
            for (Event currentEvent : eventList) {

                if (currentEvent.getType() == typeCode && currentEvent.getGenre() == genreArray[j]) {

                    eventCounter++;
                    int index = genreArrayRes.indexOf(currentEvent.getGenre());

                    if (index >= 0) {

                        int maxn = currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn / maxn) * 100;
                        results.set(index, results.get(index) + percResult);
                    } else {
                        genreArrayRes.add(currentEvent.getGenre());
                        int newindex = genreArrayRes.indexOf(currentEvent.getGenre());

                        int maxn = currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn / maxn) * 100;
                        results.add(newindex, percResult);
                    }

                }
            }

            if (eventCounter != 0) {
                int indexMod = genreArrayRes.indexOf(genreArray[j]);
                results.set(indexMod, (results.get(indexMod) / eventCounter));
                eventCounter = 0;
            }
        }

        WrapGenre resReturn = new WrapGenre(results, genreArrayRes);
        return resReturn;
    }


    @Override
    public WrapProv provinceStats(enumType typeCode, String artistName, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        enumProvince[] provinceArray = enumProvince.values();

        ArrayList<enumProvince> provinceArrayRes = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();

        int eventCounter = 0;

        for (int j = 0; j < Array.getLength(provinceArray); j++) {
            for (Event currentEvent : eventList) {

                if (currentEvent.getType() == typeCode && currentEvent.getArtists() == artistName
                        && currentEvent.getProvince() == provinceArray[j]) {

                    eventCounter++;
                    int index = provinceArrayRes.indexOf(currentEvent.getProvince());

                    if (index >= 0) {

                        int maxn = currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn / maxn) * 100;
                        results.set(index, results.get(index) + percResult);
                    } else {
                        provinceArrayRes.add(currentEvent.getProvince());
                        int newindex = provinceArrayRes.indexOf(currentEvent.getProvince());

                        int maxn = currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn / maxn) * 100;
                        results.add(newindex, percResult);
                    }

                }
            }

            if (eventCounter != 0) {
                int indexMod = provinceArrayRes.indexOf(provinceArray[j]);
                results.set(indexMod, (results.get(indexMod) / eventCounter));
                eventCounter = 0;
            }
        }

        WrapProv resReturn = new WrapProv(results, provinceArrayRes);
        return resReturn;
    }
}

public class WrapType {
    private enumType[] typeNameArray;
    private double[] typeResults;

    public WrapType(double[] results, enumType[] typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }

    public enumType[] getTypeArray() { return typeNameArray;}
    public double[] getTypeResult() {
        return typeResults;
    }
}


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

public class WrapGenre{
    private ArrayList<enumGenre> prNameArray;
    private ArrayList<Double> numberResults;

    public WrapGenre(ArrayList<Double> results, ArrayList<enumGenre> typeNameArray) {
        this.prNameArray = typeNameArray;
        this.numberResults = results;
    }
    public ArrayList<enumGenre> getGenreArray(){
        return prNameArray;
    }

    public ArrayList<Double> getGenreResult(){
        return numberResults;
    }
}

public class WrapProv{
    private ArrayList<enumProvince> prNameArray;
    private ArrayList<Double> numberResults;

    public WrapProv(ArrayList<Double> results, ArrayList<enumProvince> typeNameArray) {
        this.prNameArray = typeNameArray;
        this.numberResults = results;
    }
    public ArrayList<enumProvince> getProvinceArray(){
            return prNameArray;
        }
    public ArrayList<Double> getProvResult(){ return numberResults; }
}