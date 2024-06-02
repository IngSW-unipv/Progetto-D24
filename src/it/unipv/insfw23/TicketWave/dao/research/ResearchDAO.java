package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResearchDAO implements IResearchDAO{
    private final String schema;
    private Connection conn;

    public ResearchDAO() { // è un Object easy
        super();
        this.schema = "TicketWaveDB";
    } // costruttore

    @Override
    public ArrayList<Event> getAllEvents() throws SQLException{
        conn = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(conn,schema);
        ArrayList<Event> result = new ArrayList<>();
        Manager manager = null;

        try {
                String query = "SELECT * FROM EVENT_ JOIN MANAGER ON EVENT_.ID_MANAGER = MANAGER.MAIL";
                PreparedStatement statement1 = conn.prepareStatement(query);
                ResultSet resultset1 = statement1.executeQuery(query);

                while (resultset1.next()) {
                    manager = createManager(resultset1, result);
                    result.add(createEvent(resultset1, manager)); // aggiungo a result un nuovo evento grazie a createEvent
                }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella query di ricerca dell'evento (ResearchDAO)");
        }
        ConnectionDB.closeConnection(conn); // chiudo la connessione
        return result;
    } // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
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
                    StringBuilder query = new StringBuilder("SELECT * FROM EVENT_ JOIN MANAGER ON MANAGER_ID = MAIL WHERE (EVENT_.NAME_ LIKE ? OR EVENT_.ARTISTS LIKE ?)");
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
                System.out.println( statement1  ); // DA RIMUOVERE E' per fare un check ***************************************** // ****** FIN QUI TUTTO BENE ********* //
                resultset1 = statement1.executeQuery(); // NON VA QUI
                System.out.println(resultset1);
            } catch (SQLException e){
                throw new RuntimeException("La query non è stata eseguita correttamente (ResearchDAO riga 101)");
            }

            ArrayList <Manager> mgPrec = new ArrayList<>(); // Tengo un arraylist di manager, in modo da non creare uno stesso manager più volte (sarebbe sbagliato)
            ArrayList <Event> evManager = new ArrayList<>(); // tengo un arraylist di eventi, in modo da settare correttamente sul manager tutti gli eventi che ha creato
            boolean flag = false;

            while(resultset1.next()) { // finché ci sono risultati prima creo il manager e poi un evento
                manager = createManager(resultset1, null); // creo un manager con arrayList di eventi nulla
                if (!mgPrec.isEmpty()) { // se l'array di manager è vuoto, non ha senso fare il check sotto
                    for (Manager m : mgPrec) {
                        if (manager.equals(m)) { // se il manager appena creato è già presente nei manager precedenti => manager = m
                            manager = m; // hanno la stessa reference
                            flag = true; // se sono uguali => il manager è già presente in mgPrec => non lo devo aggiungere a mgPrec
                        }
                    }
                }
                if(!manager.equals(mgPrec.getLast())){ // se il manager corrente è uguale al precedente => uso la stessa evManager, altrimenti la devo ricreare
                    evManager.clear(); // tolgo tutti gli eventi di questa arrayList, poiché cambiano da manager a manager
                    for (Event e : result) {
                        if (e.getCreator().equals(manager)) { // se il creatore dell'evento == manager allora aggiungo all'array list del manager quell'evento
                            evManager.add(e);
                        }
                    }
                }
                manager.setEvent(evManager); // setto l'arraylist di eventi creati da quel manager
                if(flag == false){ // se il flag è falso => questo manager non è nella mgPrec, lo devo aggiungere => è un nuovo manager
                    mgPrec.add(manager);
                } else {
                    flag = false; // se il flag è vero => questo manager esiste già nella mgPrec, non lo devo inserire e per il prox giro devo resettare il flag a false
                }
                // creo l'evento
                result.add(createEvent(resultset1,manager)); // aggiungo a result l'evento corretto grazie a createEvent
            }
        } catch (SQLException e) {
            throw new RuntimeException("Evento non trovato");
        }

        ConnectionDB.closeConnection(conn); // chiudo la connessione
        // devo sparare elemento per elemento di result dentro la table dei risultati della research view
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
        LocalDate ld2 = rs.getDate("SUBSCRIPTION_DATE").toLocalDate();
        return new Manager(rs.getString("NAME"), rs.getString("SURNAME"), rs.getDate("BIRTHDATE").toString(),
                rs.getString("MAIL"), rs.getString("PWD"), Province.valueOf(rs.getString("PROVINCE")),
                rs.getString("CARDNUMBER"), result, rs.getInt("MAXEVENTS"), rs.getInt("SUBSCRIPTION"),
                ld2, rs.getInt("COUNTER_CREATED_EVENTS"));
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