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
                    // managerEvent.clear();
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

                        for (int i = 0; i < managerEvent.size(); i++) { // DA FAR VOLARE
                            System.out.println(managerEvent.get(i).getCreator().getEventlist().get(i).getCreator().getEmail()); // CHECK DA RIMUOVERE *********************
                            System.out.println(managerEvent.get(i).getCreator().getEventlist().get(i).getName()); // DA RIMUOVERE *******************************
                        }
                        System.out.println(managerEvent + ":  ME"); // DA RIMUOVERE *******************************

                        result.addAll(managerEvent);
                        System.out.println(" --------------------- "); // DA RIMUOVERE ******************
                        managerEvent.clear(); // lo azzero per i prossimi manager che avranno creato eventi diversi
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
        int k = 2; // mi serve per settare i paramtri della query nel resultset1
        ArrayList<Event> result = new ArrayList<>();
        Manager manager;
        PreparedStatement statement1;
        ResultSet resultset1;

        try {
            try { // controllo che la query venga costruita correttamente
                    StringBuilder query = new StringBuilder("SELECT * FROM EVENT_ JOIN MANAGER ON EVENT_.ID_MANAGER = MANAGER.MAIL WHERE (EVENT_.NAME_ LIKE ? OR EVENT_.ARTISTS LIKE ?)");
                    if(!pr.isEmpty()) { // se è uguale a 0 => non ho messo filtri, non metto la parte di query "PROVINCE IN()"
                        query.append(" AND EVENT_.PROVINCE IN ( ");
                        for (int i = 0; i < pr.size(); i++) {
                            query.append("?");
                            if (i < pr.size() - 1) {
                                query.append(",");
                            }
                        }
                        query.append(" )");
                    }
                    if (!gen.isEmpty()){ // se è uguale a 0 => non ho messo filtri, non metto la parte di query "GENRE IN()"
                        query.append("AND EVENT_.GENRE IN ( ");
                        for (int i = 0; i < gen.size(); i++) {
                            query.append("?");
                            if (i < gen.size() - 1) {
                                query.append(",");
                            }
                        }
                        query.append(" );");
                    } else {
                        query.append(";");
                    }
                    // la query dinamica mi permette di prendere tutte le province e i generi clickati nella view
                System.out.println(query);
                System.out.println(gen.size()); // QUESTI 3 SONO CHECK DA RIMUOVERE *****************************
                System.out.println(pr.size());
                    statement1 = conn.prepareStatement(query.toString()); // la query che è di tipo StringBuilder la faccio diventare di tipo String
                    statement1.setString(1, "%" + searchField + "%"); // controlla se nel DB c'è un evento che ha da qualche parte nel nome la sottostringa searchField
                    statement1.setString(2, "%" + searchField + "%");
                    if (!pr.isEmpty()) { // stesso discorso di prima se non ho filtri sulle PROVINCE vado ai filtri per GENRE
                        for (String s : pr) { //setto i parametri con il ?. Vado avanti finché non arrivo a pr.size()
                            statement1.setString(k + 1, s);
                            k++;
                        }
                    }
                    if (!gen.isEmpty()) {
                        for (String s : gen) { // vado avanti finché non arrivo a gen.size()
                            statement1.setString(k + 1, s);
                            k++;
                        }
                    }
                System.out.println( statement1  ); // DA RIMUOVERE E' per fare un check *****************************************
                resultset1 = statement1.executeQuery();
                System.out.println(resultset1);
            } catch (SQLException e){
                throw new RuntimeException("La query non è stata eseguita correttamente (ResearchDAO riga 101)");
            }

            // FINE QUERY, INIZIO CREAZIONE OGGETTI DEL DOMINIO

            ArrayList <Manager> mgPrec = new ArrayList<>(); // Tengo un arraylist di manager, in modo da non creare uno stesso manager più volte (sarebbe sbagliato)
            ArrayList <Event> evManager = new ArrayList<>(); // tengo un arraylist di eventi, in modo da settare correttamente sul manager tutti gli eventi che ha creato

            while(resultset1.next()) { // finché ci sono risultati prima creo il manager e poi un evento
                manager = createManager(resultset1); // creo un manager con arrayList di eventi nulla
                System.out.println(manager.getEmail()); // check da rimuovere ***************************

                /*for (Manager m : mgPrec){ // Se il manager appena creato è uguale ad uno presente in mgPrec, non lo aggiungo in mgPrec
                    if (m != manager){
                        mgPrec.add(manager);
                    }
                }

                if(!mgPrec.getLast().equals(manager)){ // Se il manager precedente = al manager corrente => uso la stessa lista degli eventi, altrimenti la rifaccio per quello corrente
                    evManager.clear();
                    for (Event e : result){

                    }
                }
                manager.setEvent(evManager); // setto l'arraylist di eventi creati da quel manager */

                // creo l'evento
                result.add(createEvent(resultset1,manager)); // aggiungo a result l'evento corretto grazie a createEvent
                System.out.println(result.getLast().getName());
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
        double[] price = {rs.getDouble("BASE_PRICE"), rs.getDouble("PREMIUM_PRICE"), rs.getDouble("VIP_PRICE")};
        int[] seatsremaining = {rs.getInt("REMAINING_BASE_SEATS"), rs.getInt("REMAINING_PREMIUM_SEATS"), rs.getInt("REMAINING_VIP_SEATS")};
        int[] ticketSoldNumberForType = {rs.getInt("SOLD_BASE_SEATS"), rs.getInt("SOLD_PREMIUM_SEATS"), rs.getInt("SOLD_VIP_SEATS")};

        switch (Type.valueOf(rs.getString("TYPE_")).ordinal()) {
            case 0 -> {
                return new Concert(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), photo);
            }
            case 1 -> {
                return new Festival(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), countWords(rs.getString("ARTISTS")), photo);
            }
            case 2 -> {
                return new Theater(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), rs.getString("AUTHOR"), photo);
            }
            case 3 -> {
                return new Other(rs.getInt("ID_EVENT"), rs.getString("NAME_"),
                        rs.getString("CITY"), rs.getString("LOCATION"), ld, tm,
                        Province.valueOf(rs.getString("PROVINCE")), Genre.valueOf(rs.getString("GENRE")),
                        rs.getInt("MAX_NUM_SEATS"), rs.getInt("NUM_SEATS_TYPE"), seatsremaining, ticketSoldNumberForType, price,
                        manager, rs.getString("ARTISTS"), rs.getString("DESCRIPTION_"), photo);
            }
        }
        throw new RuntimeException("L'evento non è stato creato nel dominio (errore nel metodo CreateEvent del ResearchDAO)");
    }

    private String[] splitStringOnCommaOrSpace(String generic){ // se va tutto lo devo rimuovere, serviva solo se passavo delle stringhe al getFilteredEvent
        generic = generic.toUpperCase(); // faccio diventare tutto in maiscolo, perché nel DB ho tutto in maiuscolo
        String[] arrayString = generic.split("[,\\s]+"); // regex che splitta una stringa sulle virgone, sugli spazi, sui tab e sugli a capo.
        for (int i = 0; i < arrayString.length; i++){ // CHECK, VA TOLTO **********************************
            System.out.println(arrayString[i]);
        }
        return arrayString;
    }
}