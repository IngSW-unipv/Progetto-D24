package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ResearchDAO implements IResearchDAO{
    private String schema = "Ticket_Wave";
    private Connection conn;

    public ResearchDAO() { // è un Object easy
        super();
    }

    @Override
    public ArrayList<Event> getAllEvents() {
        conn = ConnectionDB.startConnection(conn,schema);
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Event> result = new ArrayList<>();

        try{
            result = getAllEventsFromDB(); // entro nel metodo che istanzia gli eventi
        }catch (Exception e){
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return result;
    } // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
    @Override
    public ArrayList<Event> getFilteredEvents(String search) { // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo

        return null;
    }

    // Caso click sulla lente di ingrandimento senza aver ricercato nulla
    private ArrayList<Event> getAllEventsFromDB() throws SQLException {
        ArrayList<Event> result = new ArrayList<>();
        Manager manager = null;
        PreparedStatement statement1;
        PreparedStatement statement2;
        ResultSet resultset1;
        ResultSet resultset2;

    try {
        if (!ConnectionDB.isOpen(conn)) { // se non è aperta una connessione, allora aprila
            ConnectionDB.startConnection(conn, schema);
        }
        String query = "SELECT * FROM EVENT_ WHERE MANAGER_ID = ?";
        statement1 = conn.prepareStatement(query);
        resultset1 = statement1.executeQuery(query);

        while (resultset1.next()) {
            LocalDate ld = resultset1.getDate("DATE_").toLocalDate();
            LocalTime tm = resultset1.getTime("TIME_").toLocalTime();
            Blob bl = resultset1.getBlob("PHOTO");
            try {
                String query2 = "SELECT * FROM MANAGER WHERE MAIL = ?";
                statement2 = conn.prepareStatement(query2);
                resultset2 = statement2.executeQuery(query2);
                LocalDate ld2 = resultset2.getDate("SUBSCRIPTION_DATE").toLocalDate();

                if (resultset2.next()) {
                    manager = new Manager(resultset2.getString("NAME"), resultset2.getString("SURNAME"), resultset2.getDate("BIRTHDATE").toString(),
                            resultset2.getString("MAIL"), resultset2.getString("PWD"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset2.getString("CARDNUMBER"), result, resultset2.getInt("MAXEVENTS"), resultset2.getInt("SUBSCRIPTION"),
                            ld2, resultset2.getInt("COUNTER_CREATED_EVENTS"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            double[] price = {resultset1.getDouble("BASE_PRICE"), resultset1.getDouble("PREMIUM_PRICE"), resultset1.getDouble("VIP_PRICE")};
            int[] seatsremaining = {resultset1.getInt("REMAINING_BASE_SEATS"), resultset1.getInt("REMAINING_PREMIUM_SEATS"), resultset1.getInt("REMAINING_VIP_SEATS")};
            int[] ticketSoldNumberForType = {resultset1.getInt("SOLD_BASE_SEATS"), resultset1.getInt("SOLD_PREMIUM_SEATS"), resultset1.getInt("SOLD_VIP_SEATS")};
            switch (resultset1.getInt("TYPE")) {
                case 0 -> {
                    Concert currentConcert = new Concert(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                            Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                            manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), bl);
                    result.add(currentConcert);
                    break;
                }
                case 1 -> {
                    Festival currentFestival = new Festival(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                            Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                            manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), resultset1.getInt("NUM_ARTISTS"), bl);
                    result.add(currentFestival);
                    break;
                }
                case 2 -> {
                    Theater currentTheatre = new Theater(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                            Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                            manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), resultset1.getString("AUTHOR"), bl);
                    result.add(currentTheatre);
                    break;
                }
                case 3 -> {
                    Other currentOther = new Other(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                            Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                            manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), bl);
                    result.add(currentOther);
                    break;
                }
            }
        }
    } catch (Exception e){
        System.out.println("Hai swaggato troppo, chiudo e riapro");
    }
        return result;
    } // Ritorna tutti gli eventi

    private ArrayList<String> splitStringToArrayList(String s){
        String[] arrayString = s.split(",");
        ArrayList<String> resArrayList = new ArrayList<>();
        resArrayList.addAll(Arrays.asList(arrayString));
        return resArrayList;
    }

    private Genre[] splitStringToArrayGenre(String s){
        String[] arrayString = s.split(",");
        Genre [] gr = new Genre[4];
        Genre g;
        int i = 0;

        for (String st : arrayString){
            g = Genre.valueOf(st);
            gr[i]= g;
            i++;
        }
        return gr;
    }

}