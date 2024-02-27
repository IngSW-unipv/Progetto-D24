package it.unipv.insfw23.TicketWave.Dao.Research;

import it.unipv.insfw23.TicketWave.Dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;

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
    public ArrayList<Event> searchAllEvents() { // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
        conn = ConnectionDB.startConnection(conn,schema);
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Event> result = new ArrayList<>();

        try{
            String query = "SELECT * FROM EVENT_ ";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                result = getAllEvents(); // entro nel metodo che istanzia gli eventi
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public ArrayList<Event> searchParticularEvents(String search) { // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo
        return null;
    }

    private ArrayList<Event> getAllEvents() throws SQLException {
        ArrayList<Event> result = new ArrayList<>();
        PreparedStatement statement1;
        ResultSet resultset1;

        if (!ConnectionDB.isOpen(conn)){ // se non è aperta una connessione, allora aprila
            ConnectionDB.startConnection(conn,schema);
        }
        String query = "SELECT * FROM EVENT_";
        statement1 = conn.prepareStatement(query);
        resultset1 = statement1.executeQuery(query);

        while (resultset1.next()){
            LocalDate ld = resultset1.getDate("DATE_").toLocalDate();

            switch (resultset1.getInt("TYPE")) {
                case "CONCERTO" -> {
                    Concert currentConcert = new Concert(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")));
                    result.add(currentConcert);
                    break;
                }
                case "FESTIVAL" -> {
                    Festival currentFestival = new Festival(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")));
                    result.add(currentFestival);
                    break;
                }
                case "TEATRO" -> {
                    Theater currentTheatre = new Theater(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                            resultset1.getString("CITY"), ld,
                            resultset1.getString("LOCATION"), Province.valueOf(resultset1.getString("PROVINCE")),
                            resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS"), seatsremaining, price,
                            Genre.valueOf(resultset1.getString("GENRE")), manager, splitStringToArrayList(resultset1.getString("ARTISTS")),
                            null, resultset1.getString(19));
                    result.add(currentTheatre);
                    break;
                }
                case "ALTRO"-> {
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

        return result;
    }

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
