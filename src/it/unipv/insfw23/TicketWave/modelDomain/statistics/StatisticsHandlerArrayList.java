package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class StatisticsHandlerArrayList {

    public double[] typeStats(Manager manager) {
        int[] typeCode;
        String[] typeName;

        int eventCounter = 0;
        ArrayList<Event> eventList = manager.getEventlist();

        typeCode = eventList.get(0).getTypeCodeArray();

        int typeCodeArrayLenght = Array.getLength(typeCode);
        double[] results = new double[typeCodeArrayLenght];

        for (int j = 0; j< Array.getLength(typeCode); j++) {
            for (Event currentEvent: eventList) {

                if(currentEvent.getTypeCode() == typeCode[j]) {

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
        return results;
    }

    public String[] getTypeNameArray(Manager manager) {
        ArrayList<Event> eventList = manager.getEventlist();
        return eventList.get(0).getTypeNameArray();
    }






    public double[] artistStats(int typeCode, Manager manager) {

        ArrayList<Event> eventList = manager.getEventlist();
        ArrayList<String> artistNames = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();
        artist
        int[] artistCounter = new int[0];
        int j=0;

        for (Event currentEvent: eventList) {

            if(currentEvent.getTypeCode() == typeCode) {
                int index = artistNames.indexOf(currentEvent.getArtist);

                if(index >= 0) {

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    artistCounter[index]++;
                    results.get(index) = results.get(index) + percResult;
                }

                else {
                    artistNames.get(j) = currentEvent.getArtist();
                    //come prelevo l'artista se sto scorrendo un vettore di eventi?
                    //possiamo fare un interfaccia con il metodo get artist e quindi poi
                    //usare un vettore di IArtistqualcosa

                    int maxn = currentEvent.getMaxNumberOfSeats();
                    int soldn = currentEvent.getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    artistCounter[j]++;
                    results.get(index) = results.get(index) + percResult;
                }
            }
        }
        for (Double Value:results) {
            for ()
            Value = Value/artistCounter[j];
        }
        return results;
    }



    public int ricercaStringa(ArrayList<String> vettore, String parola) {
        int index = -1;
        for (String currentValue: vettore) {
            if (currentValue == parola) {
                return currentValue.get;
            }
        }

        return index;
    }



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




    public double[] genreStats(int typeCode, Manager manager) {

        Event list[] = manager.getEvent();
        int genreCodeArray[] = list[0].getGenreCodeArray();
        double results[];
        int eventCounter = 0;

        for (int j=0; j<Array.getLength(genreCodeArray); j++) {
            for (int i=0; i<Array.getLength(list); i++) {

                if(list[i].getTypeCode() == typeCode) {

                    int maxn = list[i].getMaxNumberOfSeats();
                    int soldn = list[i].getTicketSoldNumber();

                    double percResult = (soldn/maxn)*100;
                    eventCounter++;
                    results[j] = results[j] + percResult;

                }
            }
            results[j] = results[j]/eventCounter;
            eventCounter = 0;
        }
        return results;
    }


    public String[] getGenreNameArray(Manager manager) {
        Event events[] = manager.getEvent();
        return events[0].getGenreNameArray();
    }





    public double[] provinceStats(int typeCode, String artistName, Manager manager) {
        Event list[] = manager.getEvent();
        int provinceCodeArray[] = list[0].getProvinceCodeArray();
        int eventCounter = 0;
        double results[];
        Arrays.fill(results, -1.0);

        for(int j=0; j<Array.getLength(provinceCodeArray); j++) {
            for (int i=0; i<Array.getLength(list); i++) {
                if (list[i].getTypeCode() == typeCode) {
                    if(list[i].getArtist() == artistName) {

                        int maxn = list[i].getMaxNumberOfSeats();
                        int soldn = list[i].getTicketSoldNumber();

                        double percResult = (soldn/maxn)*100;
                        eventCounter++;
                        results[j] = results[j] + percResult;
                    }
                }
            }
            results[j] = results[j]/eventCounter;
            eventCounter = 0;
        }
        return results;
    }


    public String[] getProvinceNameArray(Manager manager) {
        Event events[] = manager.getEvent();
        return events[0].getProvinceNameArray();
    }

}
