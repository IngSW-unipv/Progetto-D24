package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import java.io.InputStream;
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
    } // costruttore

    @Override
    public ArrayList<Event> getAllEvents() throws SQLException{
        conn = ConnectionDB.startConnection(conn,schema);
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
            String query = "SELECT * FROM EVENT_ JOIN MANAGER ON MANAGER_ID = MAIL";
            statement1 = conn.prepareStatement(query);
            resultset1 = statement1.executeQuery(query);

            while (resultset1.next()) {
                LocalDate ld = resultset1.getDate("DATE_").toLocalDate();
                LocalTime tm = resultset1.getTime("TIME_").toLocalTime();
                Blob bl = resultset1.getBlob("PHOTO");
                //conversione dell'immagine da blob a image
                
                InputStream is = bl.getBinaryStream();
                Image photo = new Image(is);
                
                try {
                    String query2 = "SELECT * FROM MANAGER LEFT JOIN EVENT_ ON MAIL = MANAGER_ID"; // se funziona la query sopra, questa va fatta volare insieme al try catch, tengo solo la creazione dei vari manager
                    statement2 = conn.prepareStatement(query2);
                    resultset2 = statement2.executeQuery(query2);
                    LocalDate ld2 = resultset2.getDate("SUBSCRIPTION_DATE").toLocalDate();

                    if (resultset2.next()) {
                        manager = new Manager(resultset2.getString("NAME"), resultset2.getString("SURNAME"), resultset2.getDate("BIRTHDATE").toString(),
                                resultset2.getString("MAIL"), resultset2.getString("PWD"), Province.valueOf(resultset1.getString("PROVINCE")),
                                resultset2.getString("CARDNUMBER"), result, resultset2.getInt("MAXEVENTS"), resultset2.getInt("SUBSCRIPTION"),
                                ld2, resultset2.getInt("COUNTER_CREATED_EVENTS"));
                    }

                } catch (SQLException e) {
                    throw new RuntimeException("Errore nella query del manager (ResearchDAO)");
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
                                manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), photo);
                        result.add(currentConcert);
                    }
                    case 1 -> {
                        Festival currentFestival = new Festival(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                                resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                                Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                                resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                                manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), countWords(resultset1.getString("ARTISTS")), photo);
                        result.add(currentFestival);
                    }
                    case 2 -> {
                        Theater currentTheatre = new Theater(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                                resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                                Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                                resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                                manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), resultset1.getString("AUTHOR"), photo);
                        result.add(currentTheatre);
                    }
                    case 3 -> {
                        Other currentOther = new Other(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                                resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                                Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                                resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                                manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), photo);
                        result.add(currentOther);
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Errore nella query dell'evento (ResearchDAO)");
        }
        ConnectionDB.closeConnection(conn); // chiudo la connessione
        return result;
    } // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
    @Override
    public ArrayList<Event> getFilteredEvents(String searchField, String checkboxProvince, String checkboxGenre) throws SQLException{ // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo
        conn = ConnectionDB.startConnection(conn,schema);
        ArrayList<Event> result = new ArrayList<>();
        Manager manager;
        PreparedStatement statement1;
        ResultSet resultset1;

        try {
            if (!ConnectionDB.isOpen(conn)){
                ConnectionDB.startConnection(conn, schema);
            }
            String query = "SELECT * FROM EVENT_ JOIN MANAGER ON MANAGER_ID = MAIL WHERE checkboxProvince = PROVINCE AND checkboxGenre = GENRE AND (NAME_ LIKE ? OR ARTISTS LIKE ?)"; // prendo tutti gli attributi degli eventi che corrispondono ai filtri. 90 su 100 è cannata
            statement1 = conn.prepareStatement(query);
            statement1.setString(1, "%"+ searchField + "%");
            statement1.setString(2, "%"+ searchField + "%");
            resultset1 = statement1.executeQuery();
            while(resultset1.next()) { // finché ci sono risultati prima creo il manager e poi un evento
                manager = createManager(resultset1, result); // cannato perché  il resultset è vuoto, per cui creo un  manager con 0 eventi nell'arraylist, come faccio?
                // creo l'evento
                result.add(createEvent(resultset1,manager)); // result viene aggiornato dentro a create event con result.add

                // devo sparare elemento per elemento di result dentro la table dei risultati della research view, ma lo faccio da controller
            }
        } catch (SQLException e) {
            throw new RuntimeException("Evento non trovato");
        }




        ConnectionDB.closeConnection(conn); // chiudo la connessione
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

    private int countWords(String input){ // mi serve per contare quanti artisti sono stati messi nel festival, questo lo faccio separando la stringa
        if (input == null || input.isEmpty()) {
            return 0;
        }
        // Rimuove eventuali spazi bianchi prima e dopo la stringa
        input = input.trim();

        // Se la stringa è vuota dopo il trim, ritorna 0
        if (input.isEmpty()) {
            return 0;
        }
        // Divide la stringa in base alla virgola
        String[] words = input.split("\\s*,\\s*");
        return words.length;
    }

    private Manager createManager(ResultSet rs, ArrayList<Event> result) throws SQLException {
        Manager mg = null;
        LocalDate ld2 = rs.getDate("SUBSCRIPTION_DATE").toLocalDate();
        mg = new Manager(rs.getString("NAME"), rs.getString("SURNAME"), rs.getDate("BIRTHDATE").toString(),
                rs.getString("MAIL"), rs.getString("PWD"), Province.valueOf(rs.getString("PROVINCE")),
                rs.getString("CARDNUMBER"), result, rs.getInt("MAXEVENTS"), rs.getInt("SUBSCRIPTION"),
                ld2, rs.getInt("COUNTER_CREATED_EVENTS"));
        return mg;
    }

    private Event createEvent(ResultSet resultset1, Manager manager) throws SQLException {
        LocalDate ld = resultset1.getDate("DATE_").toLocalDate();
        LocalTime tm = resultset1.getTime("TIME_").toLocalTime();
        Blob bl = resultset1.getBlob("PHOTO");
        //conversione da blob a image
        InputStream is = bl.getBinaryStream();
        Image photo = new Image(is);
        
        double[] price = {resultset1.getDouble("BASE_PRICE"), resultset1.getDouble("PREMIUM_PRICE"), resultset1.getDouble("VIP_PRICE")};
        int[] seatsremaining = {resultset1.getInt("REMAINING_BASE_SEATS"), resultset1.getInt("REMAINING_PREMIUM_SEATS"), resultset1.getInt("REMAINING_VIP_SEATS")};
        int[] ticketSoldNumberForType = {resultset1.getInt("SOLD_BASE_SEATS"), resultset1.getInt("SOLD_PREMIUM_SEATS"), resultset1.getInt("SOLD_VIP_SEATS")};
        switch (resultset1.getInt("TYPE")) {
            case 0 -> {
                return new Concert(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                        resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                        resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), photo);
            }
            case 1 -> {
                return new Festival(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                        resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                        resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), countWords(resultset1.getString("ARTISTS")), photo);
            }
            case 2 -> {
                return new Theater(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                        resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                        resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), resultset1.getString("AUTHOR"), photo);
            }
            case 3 -> {
                return new Other(resultset1.getInt("ID_EVENT"), resultset1.getString("NAME_"),
                        resultset1.getString("CITY"), resultset1.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultset1.getString("PROVINCE")), Genre.valueOf(resultset1.getString("GENRE")),
                        resultset1.getInt("MAX_NUM_SEATS"), resultset1.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, resultset1.getString("ARTISTS"), resultset1.getString("DESCRIPTION_"), photo);
            }
        }
        return null; // nel caso in cui non rientra in nessun tipo => nullo => errore
    }
}