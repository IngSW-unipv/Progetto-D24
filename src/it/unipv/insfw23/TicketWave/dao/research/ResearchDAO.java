package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import javax.xml.transform.Result;
import java.awt.*;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ResearchDAO implements IResearchDAO{
    private final String schema;
    private Connection conn;

    public ResearchDAO() { // è un Object easy
        super();
        this.schema = "TicketWaveDB";
    } // costruttore

    @Override
    public ArrayList<Event> getAllEvents() throws SQLException{ // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi ----- FUNZIONA
        conn = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(conn,schema);
        ArrayList<Event> result = new ArrayList<>();
        ArrayList<Event> managerEvent = new ArrayList<>();
        Manager manager = null;

        if (ConnectionDB.isOpen(conn)) {
            try {
                String query = "SELECT * FROM MANAGER";
                PreparedStatement statement1 = conn.prepareStatement(query);
                ResultSet resultset1 = statement1.executeQuery();

                while(resultset1.next()) { // creazione Manager
                    manager = createManager(resultset1);
                    System.out.println(manager.getEmail()); // DA RIMUOVERE ************************
                    String query2 = "SELECT * FROM EVENT_ WHERE ID_MANAGER = ?"; // query per prendere tutti gli eventi
                    PreparedStatement statement2 = conn.prepareStatement(query2);
                    statement2.setString(1, manager.getEmail());
                    ResultSet resultSet2 = statement2.executeQuery();

                    while (resultSet2.next()) { // creazione Evento
                        managerEvent.add(createEvent(resultSet2, manager));
                        System.out.println(managerEvent); // DA RIMUOVERE *******************************
                        System.out.println(manager.getEmail() + ": M-EMAIL"); // DA RIMUOVERE *******************************
                    }
                    if(!managerEvent.isEmpty()){ // se managerEvent non è vuoto allora setta i creatori di ogni evento al suo interno e metti tutti gli eventi che contiene in result
                        manager.setEvent(managerEvent); // setto gli eventi creati da quel manager
                        System.out.println(manager.getEventlist()+ ":  M");

                        System.out.println(managerEvent + ":  ME"); // DA RIMUOVERE *******************************

                        result.addAll(managerEvent);
                        System.out.println(" --------------------- "); // DA RIMUOVERE ******************
                        managerEvent = new ArrayList<>(); // lo azzero per i prossimi manager che avranno creato eventi diversi
                        System.out.println(managerEvent); // DA RIMUOVERE ******************
                    }
                    System.out.println(result + ": RIN"); // DA RIMUOVERE *******************************

                    for(Event e : result){ // Check da RIMUOVERE **************************
                        System.out.println("CONTENUTO DI RESULT");
                        System.out.println(e.getCreator());
                        System.out.println(e.getCreator().getEventlist().size());
                        System.out.println(e.getCreator().getEventlist());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Errore nella query di ricerca dell'evento (ResearchDAO riga 42)");
            }
        }
        ConnectionDB.closeConnection(conn); // chiudo la connessione
        return result;
    }
    @Override
    public ArrayList<Event> getFilteredEvents(String searchField, ArrayList<String> pr , ArrayList<String> gen) throws SQLException{ // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo
        conn = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(conn,schema);
        ArrayList<Event> result = new ArrayList<>();
        ArrayList<Event> managerEvent = new ArrayList<>();
        Manager manager = null;

         // controllo che la query venga costruita correttamente
         if (ConnectionDB.isOpen(conn)){
             try{
                 String query = "SELECT * FROM MANAGER";
                 PreparedStatement statement1 = conn.prepareStatement(query);
                 ResultSet resultset1 = statement1.executeQuery();

                 while(resultset1.next()){
                     manager = createManager(resultset1);
                     System.out.println(manager.getEmail()); // DA RIMUOVERE ************************

                     if (manager.getCounterCreatedEvents() != 0){ // se gli eventi creati dal manager sono 0, non ha neanche senso che faccia la query
                         StringBuilder query2 = new StringBuilder("SELECT * FROM EVENT_ WHERE ID_MANAGER = ? AND (NAME_ LIKE ? OR ARTISTS LIKE ?)");
                         if(!pr.isEmpty()) { // se è uguale a 0 => non ho messo filtri => non metto la parte di query "PROVINCE IN()"
                             query2.append(" AND PROVINCE IN ( ");
                             for (int i = 0; i < pr.size(); i++) {
                                 query2.append("?");
                                 if (i < pr.size() - 1) {
                                     query2.append(",");
                                 }
                             }
                             query2.append(" )");
                         }
                         if (!gen.isEmpty()){ // se è uguale a 0 => non ho messo filtri => non metto la parte di query "GENRE IN()"
                             query2.append("AND GENRE IN ( ");
                             for (int i = 0; i < gen.size(); i++) {
                                 query2.append("?");
                                 if (i < gen.size() - 1) {
                                     query2.append(",");
                                 }
                             }
                             query2.append(" );");
                         } else {
                             query2.append(";");
                         }
                         // la query dinamica mi permette di prendere tutte le province e i generi clickati nella view
                         System.out.println(query2);
                         PreparedStatement statement2 = conn.prepareStatement(query2.toString()); // la query che è di tipo StringBuilder la faccio diventare di tipo String
                         statement2.setString(1, manager.getEmail());
                         statement2.setString(2, "%" + searchField + "%"); // controlla se nel DB c'è un evento che ha da qualche parte nel nome la sottostringa searchField
                         statement2.setString(3, "%" + searchField + "%");
                         int k = 3; // mi serve per settare i paramtri della query nel resultset2
                         if (!pr.isEmpty()) { // stesso discorso di prima se non ho filtri sulle PROVINCE vado ai filtri per GENRE
                             for (String s : pr) { //setto i parametri con il ?. Vado avanti finché non arrivo a pr.size()
                                 statement2.setString(k + 1, s);
                                 k++;
                             }
                         }
                         if (!gen.isEmpty()) {
                             for (String s : gen) { // vado avanti finché non arrivo a gen.size()
                                 statement2.setString(k + 1, s);
                                 k++;
                             }
                         }
                         System.out.println(statement2); // DA RIMUOVERE E' per fare un check *****************************************
                         ResultSet resultSet2 = statement2.executeQuery();

                         // While annidato seconda query
                         while(resultSet2.next()){
                             managerEvent.add(createEvent(resultSet2, manager));
                         }
                         manager.setEvent(managerEvent);
                         result.addAll(managerEvent);
                         managerEvent = new ArrayList<>();
                         for(Event e : result){ // Check da RIMUOVERE **************************
                             System.out.println("CONTENUTO DI RESULT");
                             System.out.println(e.getCreator());
                             System.out.println(e.getCreator().getEventlist().size());
                             System.out.println(e.getCreator().getEventlist());
                         }
                     } else {
                         System.out.println("Questo manager non ha creato eventi");
                     }
                 }
             } catch (SQLException e){
                 throw new RuntimeException("Non va la query della ricerca con filtri");
             }
         }
        ConnectionDB.closeConnection(conn); // chiudo la connessione
        return result;
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

    private Manager createManager(ResultSet resultSet1) throws SQLException {
        try {
            LocalDate subDate = resultSet1.getDate("SUBSCRIPTION_DATE").toLocalDate();
            return new Manager(resultSet1.getString("NAME_"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDATE"),
                    resultSet1.getString("MAIL"), null, Province.valueOf(resultSet1.getString("PROVINCE")), resultSet1.getString("CARDNUMBER"),null,
                    resultSet1.getInt("MAXEVENTS"), resultSet1.getInt("SUBSCRIPTION"), subDate, resultSet1.getInt("COUNTER_CREATED_EVENTS"));
        } catch (SQLException e) {
            throw new RuntimeException("Manager non creato correttamente (ResearchDAO createManager)");
        }

    }

    private Event createEvent(ResultSet rs, Manager manager) throws SQLException {
        LocalDate ld = rs.getDate("DATE_").toLocalDate();
        LocalTime tm = rs.getTime("TIME_").toLocalTime();
        //conversione da blob a image
        Blob bl = rs.getBlob("PHOTO");
        InputStream is = bl.getBinaryStream();
        Image photo = new Image(is);
        
        double[] price = new double[rs.getInt("NUM_SEATS_TYPE")];
        int[] seatsRemaining = new int[rs.getInt("NUM_SEATS_TYPE")];
        int[] ticketSoldNumberForType = new int[rs.getInt("NUM_SEATS_TYPE")];

        switch (rs.getInt("NUM_SEATS_TYPE")){
            case 3:
                price[2] = rs.getDouble("VIP_PRICE");
                seatsRemaining[2] = rs.getInt("REMAINING_VIP_SEATS");
                ticketSoldNumberForType[2] = rs.getInt("SOLD_VIP_SEATS");

            case 2:
                price[1] = rs.getDouble("PREMIUM_PRICE");
                seatsRemaining[1] = rs.getInt("REMAINING_PREMIUM_SEATS");
                ticketSoldNumberForType[1] = rs.getInt("SOLD_PREMIUM_SEATS");

            case 1:
                price[0] = rs.getDouble("BASE_PRICE");
                seatsRemaining[0] = rs.getInt("REMAINING_BASE_SEATS");
                ticketSoldNumberForType[0] = rs.getInt("SOLD_BASE_SEATS");
        }
        switch (Type.valueOf(rs.getString("TYPE_")).ordinal()) {
            case 0 -> {
                return new Concert(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), photo);
            }
            case 1 -> {
                return new Festival(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), countWords(rs.getString("ARTISTS")), photo);
            }
            case 2 -> {
                return new Theater(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), rs.getString("AUTHOR"), photo);
            }
            case 3 -> {
                return new Other(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), photo);
            }
        }
        throw new RuntimeException("L'evento non è stato creato nel dominio (errore nel metodo CreateEvent del ResearchDAO)");
    }
}