package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class ProfileDAO implements IProfileDAO{

    private String schema;

    private User user;
    private Connection conn;

    public ProfileDAO(){
        super();
        this.schema = "";
    }

    // inserimenti
    @Override
    public Manager insertManager(Manager manager) {
        conn = ConnectionDB.startConnection(conn, schema);
        PreparedStatement st1;
        boolean esito=true;

        try{

            String query = "INSERT INTO MANAGER(NAME, SURNAME, BIRTHDATE, MAIL, PWD, PROVINCE, CARDNUMBER, MAXEVENTS, SUBSCRIPTION,SUBSCRIPTIONDATE,COUNTER_CREATED_EVENTS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            st1 = conn.prepareStatement(query);


            // Impostazione dei parametri per la query
            st1.setString(1, manager.getName());
            st1.setString(2, manager.getSurname());
            st1.setString(3,manager.getDateOfBirth());// Assuming getBirthdate() returns a java.util.Date
            st1.setString(4, manager.getEmail());
            st1.setString(5, manager.getPassword());
            st1.setInt(6, manager.getProvinceOfResidence());
            st1.setString(7, manager.getCreditCard());
            st1.setInt(8, manager.getMaxNumberofEvents());
            st1.setInt(9, manager.getSubscription());
            st1.setDate(10, Date.valueOf(manager.getSubscriptionDate()));;
            st1.setInt(11,manager.getCounterCreatedEvents());

            st1.executeQuery(query);

        }catch (Exception e){
            e.printStackTrace();
            esito=false;
        }

        ConnectionDB.closeConnection(conn);
        return manager;

    }




    @Override
    public Customer insertCustomer(Customer customer) {
        conn = ConnectionDB.startConnection(conn, schema);
        PreparedStatement st1;
        boolean esito=true;

        try{

            String query= "INSERT INTO CUSTOMER (NAME,SURNAME,BIRTHDATE,MAIL,PWS,PROVINCE,POINTS,FAVOURITE_GENRE) VALUES(?,?,?,?,?,?,?,?)";
            st1 = conn.prepareStatement(query);


            st1.setString(1, customer.getName());
            st1.setString(2, customer.getSurname());
            st1.setString(3,customer.getDateOfBirth());// Assuming getBirthdate() returns a java.util.Date
            st1.setString(4, customer.getEmail());
            st1.setString(5, customer.getPassword());
            st1.setInt(6, customer.getProvinceOfResidence());
            st1.setDouble(7,customer.getPoints());
            st1.setString(8,String.valueOf(customer.getFavoriteGenre()));

            st1.executeQuery(query);

        }catch (Exception e){
            e.printStackTrace();
            esito=false;
        }

        ConnectionDB.closeConnection(conn);
        return customer;

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getManager(String mail, String password) {

        conn = ConnectionDB.startConnection(conn, schema);
        PreparedStatement statement1;
        ResultSet resultSet1;

        Manager manager = null;
        try {
            String query = "SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)"; // serve una join con EVENT, se no da dove pesco l'evento associato a quel manager???

            statement1 = conn.prepareStatement(query);
            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery(query);



            if (resultSet1.next()) {
                ArrayList<Event> event = new ArrayList<>();
                LocalDate ld = resultSet1.getDate("SUBSCRIPTION_DATE").toLocalDate(); // prendo la data in formato DATE e la casto in LocalDate

                event = getManagerEvent(resultSet1.getString("EVENT")); // passo la stringa dell'evento al metodo che mi restituisce un'ArrayList di eventi

                manager = new Manager(resultSet1.getString("NAME"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDATE"), resultSet1.getString("MAIL"),
                                resultSet1.getString("PWD"), resultSet1.getInt("PROVINCE"), resultSet1.getString("CARDNUMBER"), event, resultSet1.getInt("MAXEVENTS"),
                                resultSet1.getInt("SUBSCRIPTION"), ld, resultSet1.getInt("COUNTER_CREATED_EVENTS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return manager;
    }

    public User getCustomer(String mail, String password){
        conn = ConnectionDB.startConnection(conn, schema);
        PreparedStatement statement1;
        ResultSet resultSet1;

        Customer customer = null;

        try{
            String query = "SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)";
            statement1 = conn.prepareStatement(query);

            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery(query);

            if (resultSet1.next()) {
                customer = new Customer(resultSet1.getString("NAME"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDATE"), resultSet1.getString(1),
                        resultSet1.getString(2), resultSet1.getInt("PROVINCE"), Genre.valueOf(resultSet1.getString("FAVOURITE_GENRE"))); // sto null qui non convince
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return customer;
    } // VA FATTO L'ARRAY DI GENERI

    @Override
    public void setSubscription(Manager manager) {

    }

    public ArrayList<Event> getManagerEvent(String event) throws SQLException {
        ResultSet resultSet2;
        PreparedStatement statement2;
        ArrayList<Event> result = new ArrayList<>();

        if (!ConnectionDB.isOpen(conn)){ // checcko se la connessione al DB è chiusa, se è chiusa la riapro, altrimenti rifaccio la connessione al DB
            conn = ConnectionDB.startConnection(conn,schema);
        } else {
            //ConnectionDB.closeConnection(conn);
            //conn = ConnectionDB.startConnection(conn,schema);
            String tipo;

            String query1 = "SELECT ID_EVENT, TYPE FROM EVENT_ INNER JOIN MANAGER ON EVENT_.ID_MANAGER = MANAGER.MAIL WHERE ID_MANAGER = MAIL "; // prendi gli eventi di un certo manager e poi creagli l'arrayList

            statement2 = conn.prepareStatement(query1);
            resultSet2 = statement2.executeQuery(query1);

            if (resultSet2.next()){
                tipo = resultSet2.getString("TYPE");
                event = resultSet2.getString("ID_EVENT");
                int [] seatsRemainingForType = new int[2];
                int [] priceForType = new int[2];
                LocalDate ld = resultSet2.getDate("DATE").toLocalDate(); // converto la data in LocalDate
                
                int i = 0;
                seatsRemainingForType[i] = resultSet2.getInt("REMAINING_BASE_SEATS"); // array di posti rimasti per tipo
                seatsRemainingForType[i+1] = resultSet2.getInt("REMAINING_PREMIUM_SEATS");
                seatsRemainingForType[i+1] = resultSet2.getInt("REMAINING_VIP_SEATS");
                
                i = 0;
                priceForType[i] = resultSet2.getInt("BASE_PRICE"); // array dei prezzi rimasti per tipo
                priceForType[i+1] = resultSet2.getInt("PREMIUM_PRICE");
                priceForType[i+1] = resultSet2.getInt("VIP_PRICE");
                
                Event e;
                switch (tipo) {
                    case "FESTIVAL" -> {
                        (Festival) e = new Festival(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                ld, Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAX_NUM_SEATS"),
                                resultSet2.getInt("NUM_SEATS_TYPE"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null);
                    }
                    case "CONCERTO" -> {
                        (Concert) e = new Concert(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null );
                    }
                    case "TEATRO" -> {
                        (Theater) e = new Theater(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null, resultSet2.getString("ARTISTS"), resultSet2.getString("AUTHOR"));
                    }
                    case "ALTRO" -> {
                        (Other) e = new Other(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType, Genre.valueOf(resultSet2.getString("GENRE")),
                                null, null, resultSet2.getString("DESCRIPTION") );
                    }
                }

                for (int j = 0; j < resultSet2.getRow(); j++){ // for fino all'ultima riga del resultSet
                    result.add(e); // non prende il valore interno dello switch case
                }
            }
        }
        return result;
    }
}
