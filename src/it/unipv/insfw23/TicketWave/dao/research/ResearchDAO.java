package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This class represents a ResearchDAO and implements all the methods declared in {@link IResearchDAO}.
 * It encapsulates database interactions, providing a clean and reusable interface for accessing {@link Event} data.
 *
 */
public class ResearchDAO implements IResearchDAO{

    // ATTRIBUTES:
    private final String schema;
    private Connection conn;

    // CONSTRUCTOR:
    public ResearchDAO() {
        super();
        this.schema = "TicketWaveDB";
    }

    // PUBLIC METHODS:

    /**
     * Return all the Event from the database
     * @return result
     * @throws SQLException  It's thrown if the query for creating events or if the query for creating managers fails
     */
    @Override
    public ArrayList<Event> getAllEvents() throws SQLException{ // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
        conn = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(conn,schema);
        ArrayList<Event> result = new ArrayList<>();
        ArrayList<Event> managerEvent = new ArrayList<>();
        Manager manager;

        if (ConnectionDB.isOpen(conn)) {
            try {
                String query = "SELECT * FROM MANAGER";
                PreparedStatement statement1 = conn.prepareStatement(query);
                ResultSet resultset1 = statement1.executeQuery();

                while(resultset1.next()) { // creazione Manager
                    manager = createManager(resultset1);

                    try {
                        String query2 = "SELECT * FROM EVENT_ WHERE ID_MANAGER = ?"; // query per prendere tutti gli eventi
                        PreparedStatement statement2 = conn.prepareStatement(query2);
                        statement2.setString(1, manager.getEmail());
                        ResultSet resultSet2 = statement2.executeQuery();

                        while (resultSet2.next()) { // creazione Evento
                            managerEvent.add(createEvent(resultSet2, manager));
                        }

                        if (!managerEvent.isEmpty()) { // se managerEvent non è vuoto allora setta i creatori di ogni evento al suo interno e metti tutti gli eventi che contiene in result
                            manager.setEvent(managerEvent); // setto gli eventi creati da quel manager
                            result.addAll(managerEvent);
                            managerEvent = new ArrayList<>(); // lo azzero per i prossimi manager che avranno creato eventi diversi
                        }
                    } catch (SQLException e){
                        System.err.print("SQL exception occurred in search due to event search query. " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.err.print("SQL exception occurred in search due to manager search query. " + e.getMessage());
            }
        }
        ConnectionDB.closeConnection(conn); // chiudo la connessione
        return result;
    }

    /**
     * Returns all the events that match with the search field and that comply with the filters set. If I haven't set a search field, all the events that match the filters set are returned.
     * @param searchField represents the text written in the textField of the ResearchView
     * @param provinceFilters represents the provinceFilters selected by the ResearchView
     * @param genreFilters represents the genreFilters selected by the ResearchView
     * @return return
     * @throws SQLException It's thrown if the query for creating events or if the query for creating managers fails
     */
    @Override
    public ArrayList<Event> getFilteredEvents(String searchField, ArrayList<String> provinceFilters , ArrayList<String> genreFilters) throws SQLException{ // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo
        conn = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(conn,schema);
        ArrayList<Event> result = new ArrayList<>();
        ArrayList<Event> managerEvent = new ArrayList<>();
        Manager manager;

         // controllo che la query venga costruita correttamente
         if (ConnectionDB.isOpen(conn)){
             try{
                 String query = "SELECT * FROM MANAGER";
                 PreparedStatement statement1 = conn.prepareStatement(query);
                 ResultSet resultset1 = statement1.executeQuery();

                 while(resultset1.next()){
                     try{
                         manager = createManager(resultset1);

                         StringBuilder query2 = new StringBuilder("SELECT * FROM EVENT_ WHERE ID_MANAGER = ? AND (NAME_ LIKE ? OR ARTISTS LIKE ?)");
                         if(!provinceFilters.isEmpty()) { // se è uguale a 0 => non ho messo filtri => non metto la parte di query "PROVINCE IN()"
                             query2.append(" AND PROVINCE IN ( ");
                             for (int i = 0; i < provinceFilters.size(); i++) {
                                 query2.append("?");
                                 if (i < provinceFilters.size() - 1) {
                                     query2.append(",");
                                 }
                             }
                             query2.append(" )");
                         }
                         if (!genreFilters.isEmpty()){ // se è uguale a 0 => non ho messo filtri => non metto la parte di query "GENRE IN()"
                             query2.append("AND GENRE IN ( ");
                             for (int i = 0; i < genreFilters.size(); i++) {
                                 query2.append("?");
                                 if (i < genreFilters.size() - 1) {
                                     query2.append(",");
                                 }
                             }
                             query2.append(" );");
                         } else {
                             query2.append(";");
                         }
                         // la query dinamica mi permette di prendere tutte le province e i generi clickati nella view
                         PreparedStatement statement2 = conn.prepareStatement(query2.toString()); // la query che è di tipo StringBuilder la faccio diventare di tipo String
                         statement2.setString(1, manager.getEmail());
                         statement2.setString(2, "%" + searchField + "%"); // controlla se nel DB c'è un evento che ha da qualche parte nel nome la sottostringa searchField
                         statement2.setString(3, "%" + searchField + "%");
                         int k = 3; // mi serve per settare i paramtri della query nel resultset2
                         if (!provinceFilters.isEmpty()) { // stesso discorso di prima se non ho filtri sulle PROVINCE vado ai filtri per GENRE
                             for (String s : provinceFilters) { //setto i parametri con il ?. Vado avanti finché non arrivo a pr.size()
                                 statement2.setString(k + 1, s);
                                 k++;
                             }
                         }
                         if (!genreFilters.isEmpty()) {
                             for (String s : genreFilters) { // vado avanti finché non arrivo a gen.size()
                                 statement2.setString(k + 1, s);
                                 k++;
                             }
                         }
                         ResultSet resultSet2 = statement2.executeQuery();

                         // While annidato seconda query
                         while(resultSet2.next()){
                             managerEvent.add(createEvent(resultSet2, manager));
                         }
                         manager.setEvent(managerEvent);
                         result.addAll(managerEvent);
                         managerEvent = new ArrayList<>();
                     } catch (SQLException e){
                         System.err.print("SQL exception occurred in filtered-search due to event search query. " + e.getMessage());
                     }
                 }
             } catch (SQLException e){
                 System.err.print("SQL exception occurred in filtered-search due to manager search query. " + e.getMessage());
             }
         }
        ConnectionDB.closeConnection(conn); // chiudo la connessione
        return result;
    }

    // PRIVATE METHODS:

    /**
     * This method creates a manager by taking its data from the database. This is useful for creating an event later
     * @param resultSet1 represents the resultset of manager query
     * @return new Manager()
     * @throws SQLException It's thrown if the manager is not created correctly in the domain
     */
    private Manager createManager(ResultSet resultSet1) throws SQLException {
        try {
            LocalDate subDate = resultSet1.getDate("SUBSCRIPTION_DATE").toLocalDate();
            return new Manager(resultSet1.getString("NAME_"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDATE"),
                    resultSet1.getString("MAIL"), null, Province.valueOf(resultSet1.getString("PROVINCE")), resultSet1.getString("CARDNUMBER"),null,
                    resultSet1.getInt("MAXEVENTS"), resultSet1.getInt("SUBSCRIPTION"), subDate, resultSet1.getInt("COUNTER_CREATED_EVENTS"));
        } catch (SQLException e) {
            throw new RuntimeException("Manager not created correctly (error in the createManager method of the ResearchDAO)");
        }

    }

    /**
     * This method creates a specific event by taking data from the database and uses the previously created manager as the event creator
     * @param resultSet represents the resultset of event query
     * @param manager represents the manager created previously into the createManager method
     * @return new Event()
     * @throws SQLException It's thrown if the event is not created correctly in the domain
     */
    private Event createEvent(ResultSet resultSet, Manager manager) throws SQLException {
        LocalDate ld = resultSet.getDate("DATE_").toLocalDate();
        LocalTime tm = resultSet.getTime("TIME_").toLocalTime();
        //conversione da blob a image
        Blob bl = resultSet.getBlob("PHOTO");
        InputStream is = bl.getBinaryStream();
        Image photo = new Image(is);
        
        double[] price = new double[resultSet.getInt("NUM_SEATS_TYPE")];
        int[] seatsRemaining = new int[resultSet.getInt("NUM_SEATS_TYPE")];
        int[] ticketSoldNumberForType = new int[resultSet.getInt("NUM_SEATS_TYPE")];

        switch (resultSet.getInt("NUM_SEATS_TYPE")){
            case 3:
                price[2] = resultSet.getDouble("VIP_PRICE");
                seatsRemaining[2] = resultSet.getInt("REMAINING_VIP_SEATS");
                ticketSoldNumberForType[2] = resultSet.getInt("SOLD_VIP_SEATS");

            case 2:
                price[1] = resultSet.getDouble("PREMIUM_PRICE");
                seatsRemaining[1] = resultSet.getInt("REMAINING_PREMIUM_SEATS");
                ticketSoldNumberForType[1] = resultSet.getInt("SOLD_PREMIUM_SEATS");

            case 1:
                price[0] = resultSet.getDouble("BASE_PRICE");
                seatsRemaining[0] = resultSet.getInt("REMAINING_BASE_SEATS");
                ticketSoldNumberForType[0] = resultSet.getInt("SOLD_BASE_SEATS");
        }
        switch (Type.valueOf(resultSet.getString("TYPE_")).ordinal()) {
            case 0 -> {
                return new Festival(resultSet.getInt("ID_EVENT"), resultSet.getString("NAME_"),
                        resultSet.getString("CITY"), resultSet.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultSet.getString("PROVINCE")), Genre.valueOf(resultSet.getString("GENRE")),
                        resultSet.getInt("MAX_NUM_SEATS"), resultSet.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, resultSet.getString("ARTISTS"), resultSet.getString("DESCRIPTION_"), photo);
            }
            case 1 -> {
                return new Concert(resultSet.getInt("ID_EVENT"), resultSet.getString("NAME_"),
                        resultSet.getString("CITY"), resultSet.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultSet.getString("PROVINCE")), Genre.valueOf(resultSet.getString("GENRE")),
                        resultSet.getInt("MAX_NUM_SEATS"), resultSet.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, resultSet.getString("ARTISTS"), resultSet.getString("DESCRIPTION_"), photo);
            }
            case 2 -> {
                return new Theater(resultSet.getInt("ID_EVENT"), resultSet.getString("NAME_"),
                        resultSet.getString("CITY"), resultSet.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultSet.getString("PROVINCE")), Genre.valueOf(resultSet.getString("GENRE")),
                        resultSet.getInt("MAX_NUM_SEATS"), resultSet.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, resultSet.getString("ARTISTS"), resultSet.getString("DESCRIPTION_"), resultSet.getString("AUTHOR"), photo);
            }
            case 3 -> {
                return new Other(resultSet.getInt("ID_EVENT"), resultSet.getString("NAME_"),
                        resultSet.getString("CITY"), resultSet.getString("LOCATION"), ld, tm,
                        Province.valueOf(resultSet.getString("PROVINCE")), Genre.valueOf(resultSet.getString("GENRE")),
                        resultSet.getInt("MAX_NUM_SEATS"), resultSet.getInt("NUM_SEATS_TYPE"), seatsRemaining, ticketSoldNumberForType, price,
                        manager, resultSet.getString("ARTISTS"), resultSet.getString("DESCRIPTION_"), photo);
            }
        }
        throw new RuntimeException("The event was not created correctly in the domain (error in the CreateEvent method of the ResearchDAO)");
    }
}