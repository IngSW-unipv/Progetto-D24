package it.unipv.insfw23.TicketWave.Dao.Research;

import it.unipv.insfw23.TicketWave.Dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ResearchDAO implements IResearchDAO{
    private String schema = "Ticket_Wave";
    private Connection conn;

    public ResearchDAO() { // è un Object easy
        super();
    }

    @Override
    public ArrayList<Event> searchAllEvents() {
        conn = ConnectionDB.startConnection(conn,schema);
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Event> result = new ArrayList<>();

        try{
            result = getAllEvents(); // entro nel metodo che istanzia gli eventi
        }catch (Exception e){
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return result;
    } // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
    @Override
    public ArrayList<Event> searchFilteredEvents(String search) { // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo

        return null;
    }

    private ArrayList<Event> getAllEvents() throws SQLException {
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
            try {
                String query2 = "SELECT * FROM MANAGER WHERE MAIL = ?";
                statement2 = conn.prepareStatement(query2);
                resultset2 = statement2.executeQuery(query2);
                LocalDate ld2 = resultset2.getDate("SUBSCRIPTION_DATE").toLocalDate();

                if (resultset2.next()) {
                    manager = new Manager(resultset2.getString("NAME"), resultset2.getString("SURNAME"), resultset2.getDate("BIRTHDATE").toString(),
                            resultset2.getString("MAIL"), resultset2.getString("PWD"), resultset2.getInt("PROVINCE"),
                            resultset2.getString("CARDNUMBER"), result, resultset2.getInt("MAXEVENTS"), resultset2.getInt("SUBSCRIPTION"),
                            ld2, resultset2.getInt("COUNTER_CREATED_EVENTS"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            double[] price = {resultset1.getDouble("BASE_PRICE"), resultset1.getDouble("PREMIUM_PRICE"), resultset1.getDouble("VIP_PRICE")};
            int[] seatsremaining = {resultset1.getInt("REMAINING_BASE_SEATS"), resultset1.getInt("REMAINING_PREMIUM_SEATS"), resultset1.getInt("REMAINING_VIP_SEATS")};
            switch (resultset1.getInt("TYPE")) {
                case 0 -> {
                    Concert currentConcert = new Concert(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")));
                    result.add(currentConcert);
                    break;
                }
                case 1 -> {
                    Festival currentFestival = new Festival(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")));
                    result.add(currentFestival);
                    break;
                }
                case 2 -> {
                    Theater currentTheatre = new Theater(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")),
                            null, resultset1.getString(19));
                    result.add(currentTheatre);
                    break;
                }
                case 3 -> {
                    Other currentOther = new Other(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")),
                            resultset1.getString(20));
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
