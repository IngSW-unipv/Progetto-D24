package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static it.unipv.insfw23.TicketWave.modelDomain.event.Genre.getGenreCodeArray;
import static it.unipv.insfw23.TicketWave.modelDomain.event.Genre.getGenreNameArray;
import static it.unipv.insfw23.TicketWave.modelDomain.event.Province.getProvinceCodeArray;
import static it.unipv.insfw23.TicketWave.modelDomain.event.Province.valueOf;

public class StatisticsHandlerArrayList {

    public WrapType typeStats(Manager manager) {
        int[] typeCode;
        String[] typeName;

        int eventCounter = 0;
        ArrayList<Event> eventList = manager.getEventlist();

        int[] typeCodeArray = manager.getTypeCodeArray();
        ArrayList<String> typeNameArray = new ArrayList<>();

        int typeCodeArrayLenght = Array.getLength(typeCodeArray);
        double[] results = new double[typeCodeArrayLenght];

        for (int j = 0; j< Array.getLength(typeCodeArray); j++) {
            for (Event currentEvent: eventList) {

                if(currentEvent.getKeyCode() == typeCodeArray[j]) {
                    typeNameArray.add(j, currentEvent.getClassName());
                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    eventCounter ++;
                    results[j] = results[j] + percResult;
                }
            }
            results[j] = results[j]/eventCounter;
            eventCounter = 0;
        }
        WrapType returnClass = new WrapType(results, typeNameArray);
        return returnClass;
    }

/*
    // la UI non ne ha bisogno se utilizzo nel metodo la classe Wrapper
    public String[] getTypeNameArray(Manager manager) {
        ArrayList<Event> eventList = manager.getEventlist();
        return eventList.get(0).getTypeNameArray();
    }
*/







    public WrapArtist artistStats(int typeCode, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        ArrayList<ArrayList<String>> artistNames = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();

        ArrayList<Integer> artistCounter = new ArrayList<>();
        //int[] artistCounter = new int[0];
        int j=0;

        for (Event currentEvent: eventList) {

            if(currentEvent.getKeyCode() == typeCode) {
                int index = artistNames.indexOf(currentEvent.getArtists());

                if(index >= 0) {

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    artistCounter.set(index, artistCounter.get(index) + 1);
                    results.set(index, results.get(index) + percResult);
                }

                else {
                    artistNames.add(currentEvent.getArtists());
                    int newindex = artistNames.indexOf(currentEvent.getArtists());

                    //come prelevo l'artista se sto scorrendo un vettore di eventi?
                    //possiamo fare un interfaccia con il metodo get artist e quindi poi
                    //usare un vettore di IArtistqualcosa

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    //artistCounter.add(newindex, 1);
                    artistCounter.set(newindex, artistCounter.get(newindex) + 1);
                    results.add(newindex, percResult);
                    //results.get(index) = results.get(index) + percResult;
                }
            }
        }
    /*
        if (artistNames.size() == results.size()) {
            for (int indexMod=0; indexMod<Array.getLength(results); indexMod++ ) {
                for (Integer currentArtistCount : artistCounter) {

                    results.set(indexMod, results.get(indexMod)/currentArtistCount);

                }
            }
        }
    */
        if (artistNames.size() == results.size()){
            for (int indexMod = 0; indexMod < artistNames.size(); indexMod++){
                results.set(indexMod, (results.get(indexMod)/ artistCounter.get(indexMod)));
            }
        }
        WrapArtist wrapRes = new WrapArtist(results, artistNames);
        return wrapRes;   //classe wrapper per restituire due array ceh servono, ovvero artistNames creata, e results
    }


/*
    //Al suo posto uso il metodo di ArrayList ".indexOf"
    public int ricercaStringa(ArrayList<String> vettore, String parola) {
        int index = -1;
        for (String currentValue: vettore) {
            if (currentValue == parola) {
                return currentValue.indexOf();
            }
        }
        return index;
    }
*/

/*
    public double[] artistStats2(int typeCode, Manager manager) {
        Event list[] = manager.getEvent();
        String artistNames[];
        double results[];
        int j=0;
        int artistCounter;

        for (int i=0; i<Array.getLength(list); i++) {
            if (list[i].getTypeCode() == typeCode) {
                int index = ricercaStringa(artistNames, list[i].getArtist);
                if(index == -1) {
                    artistNames[j] = list[i].getArtist;

                    for(int y=i; y<Array.getLength(list); y++) {
                        if (list[i].getArtist() == list[y].getArtist()) {
                            int maxn = list[i].getMaxNumberOfSeats();
                            int soldn = list[i].getTicketSoldNumber();

                            double percResult = (soldn/maxn)*100;
                            artistCounter++;
                            results[j] = results[j] + percResult;
                        }
                    }
                    results[j] = results[j]/artistCounter;
                    artistCounter = 0;
                    j++;
                }
            }
        }
        return results;
    }
*/

    public WrapGenre genreStats(int typeCode, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        int[] genreCodeArray= getGenreCodeArray();
        double[] results = new double[genreCodeArray.length];
        int eventCounter = 0;

        for (int j=0; j<Array.getLength(genreCodeArray); j++) {
            for (Event currentEvent: eventList) {

                if(currentEvent.getKeyCode() == typeCode) {

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    eventCounter++;
                    results[j] = results[j] + percResult;

                }
            }
            results[j] = results[j]/eventCounter;
            eventCounter = 0;
        }
        WrapGenre resReturn = new WrapGenre(results, getGenreNameArray());
        return resReturn;
    }

/*
    public String[] getGenreNameArray(Manager manager) {
        Event events[] = manager.getEvent();
        return events[0].getGenreNameArray();
    }

*/



    public WrapProvince provinceStats(int typeCode, ArrayList<String> artistName, Manager manager) {
        ArrayList<Event> eventList = manager.getEventlist();
        int[] provinceCodeArray = getProvinceCodeArray();
        int eventCounter = 0;
        ArrayList<Double> results = new ArrayList<>();
        ArrayList<String> namesRes = new ArrayList<>();

        for(int j=0; j<Array.getLength(provinceCodeArray); j++) {
            for (Event currentEvent: eventList) {
                if (currentEvent.getKeyCode() == typeCode) {
                    if(currentEvent.getArtists() == artistName) {

                        Province province = currentEvent.getProvince();
                        if(namesRes.indexOf(province.name()) < 0){
                            namesRes.add(province.name());
                        }
                        int index = namesRes.indexOf(province.name());

                        int maxn = currentEvent.getMaxNumberOfSeats();
                        int soldn = currentEvent.getTicketSoldNumber();

                        double percResult = (soldn/maxn)*100;
                        eventCounter++;
                        results.set(index, (results.get(index) + percResult)); //= results.get(index) + percResult;
                    }
                }
            }
            results.set(j, results.get(j)/eventCounter);
            eventCounter = 0;
        }
        WrapProvince resLocation = new WrapProvince(results, namesRes);
        return resLocation;
    }

/*
    public String[] getProvinceNameArray(Manager manager) {
        Event events[] = manager.getEvent();
        return events[0].getProvinceNameArray();
    }
*/
}



class WrapType {
    private ArrayList<String> typeNameArray;
    private double[] typeResults;

    public WrapType(double[] results, ArrayList<String> typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }

    public ArrayList<String> getNameArrayType() {
        return typeNameArray;
    }

    public double[] getTypeRes() {
        return typeResults;
    }
}

class WrapGenre{
    private String[] typeNameArray;
    private double[] typeResults;

    public WrapGenre(double[] results, String[] typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }
    public String[] getNameArrayGenre(){
        return typeNameArray;
    }

    public double[] getGenreRes(){
        return typeResults;
    }
}


class WrapArtist{
    private ArrayList<ArrayList<String>> typeNameArray;
    private ArrayList<Double> typeResults;

    public WrapArtist(ArrayList<Double> results, ArrayList<ArrayList<String>> typeNameArray) {
        this.typeNameArray = typeNameArray;
        this.typeResults = results;
    }
    public ArrayList<ArrayList<String>> getNameArrayArtist(){
        return typeNameArray;
    }

    public ArrayList<Double> getArtistRes(){
        return typeResults;
    }
}

class WrapProvince{
    private ArrayList<String> prNameArray;
    private ArrayList<Double> numberResults;

    public WrapProvince(ArrayList<Double> results, ArrayList<String> typeNameArray) {
        this.prNameArray = typeNameArray;
        this.numberResults = results;
    }
    public ArrayList<String> getNameArrayProvince(){
        return prNameArray;
    }

    public ArrayList<Double> getProvinceRes(){
        return numberResults;
    }
}