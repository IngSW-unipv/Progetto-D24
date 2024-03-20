package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelController.Factory.Statistics.IStatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static it.unipv.insfw23.TicketWave.modelDomain.event.Province.valueOf;

public class StatisticsHandler implements IStatisticsHandler {

    public StatisticsHandler() {
    }

    @Override
    public WrapType typeStats(Manager manager) {

        int eventCounter = 0;
        ArrayList<Event> eventList = manager.getEventlist();

        Type[] typeArray = Type.values();

        int typeCodeArrayLenght = Array.getLength(typeArray);
        double[] results = new double[typeCodeArrayLenght];

        for (int j = 0; j < Array.getLength(typeArray); j++) {
            for (Event currentEvent : eventList) {

                if (currentEvent.getType() == typeArray[j]) {

                    double maxn = (double) currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn / maxn) * 100;
                    eventCounter++;
                    results[j] = results[j] + percResult;
                }
            }
            if (eventCounter != 0){ results[j] = results[j] / eventCounter;}
            eventCounter = 0;
        }
        WrapType returnClass = new WrapType(results, typeArray);
        return returnClass;
    }

    @Override
    public WrapArtist artistStats(Type typeCode, Manager manager){

        ArrayList<Event> eventList = manager.getEventlist();
        ArrayList<String> artistNames = new ArrayList<>();

        ArrayList<Double> results = new ArrayList<>();
        ArrayList<Integer> artistCounter = new ArrayList<>();

        for (Event currentEvent : eventList) {

            if (currentEvent.getType() == typeCode) {
                int index = artistNames.indexOf(currentEvent.getArtists());

                if (index >= 0) {

                    double maxn = (double) currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn / maxn) * 100;

                    artistCounter.set(index, artistCounter.get(index) + 1);
                    results.set(index, results.get(index) + percResult);
                }
                else {
                    artistNames.add(currentEvent.getArtists());
                    int newindex = artistNames.indexOf(currentEvent.getArtists());

                    double maxn = (double) currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn / maxn) * 100;

                    artistCounter.add(newindex, 1);
                    results.add(newindex, percResult);
                }
            }
        }

        if (artistCounter.size() == results.size()) {
            for (int indexMod = 0; indexMod < artistNames.size(); indexMod++) {
                results.set(indexMod, (results.get(indexMod) / artistCounter.get(indexMod)));
            }
        }
        else {System.out.println("Problemiiiiiiii");}

        WrapArtist wrapRes = new WrapArtist(results, artistNames);
        return wrapRes;   //classe wrapper per restituire i due array che servono, ovvero artistNames creata, e results
    }

    @Override
    public WrapGenre genreStats(Type typeCode, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        Genre[] genreArray = Genre.values();

        ArrayList<Genre> genreArrayRes = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();

        int eventCounter = 0;

        for (int j = 0; j < Array.getLength(genreArray); j++) {
            for (Event currentEvent : eventList) {

                if (currentEvent.getType() == typeCode && currentEvent.getGenre() == genreArray[j]) {

                    eventCounter++;
                    int index = genreArrayRes.indexOf(currentEvent.getGenre());

                    if (index >= 0) {

                        double maxn = (double) currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn / maxn) * 100;
                        results.set(index, results.get(index) + percResult);
                    }
                    else {
                        genreArrayRes.add(currentEvent.getGenre());
                        int newindex = genreArrayRes.indexOf(currentEvent.getGenre());

                        double maxn = (double) currentEvent.getMaxNumberOfSeats();
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
    public WrapProv provinceStats(Type typeCode, String artistName, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        Province[] provinceArray = Province.values();

        ArrayList<Province> provinceArrayRes = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();

        int eventCounter = 0;

        for (int j = 0; j < Array.getLength(provinceArray); j++) {
            for (Event currentEvent : eventList) {

                if (currentEvent.getType() == typeCode && currentEvent.getArtists() == artistName
                        && currentEvent.getProvince() == provinceArray[j]) {

                    eventCounter++;
                    int index = provinceArrayRes.indexOf(currentEvent.getProvince());

                    if (index >= 0) {

                        double maxn = (double) currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn / maxn) * 100;
                        results.set(index, results.get(index) + percResult);
                    } else {
                        provinceArrayRes.add(currentEvent.getProvince());
                        int newindex = provinceArrayRes.indexOf(currentEvent.getProvince());

                        double maxn = (double) currentEvent.getMaxNumberOfSeats();
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








