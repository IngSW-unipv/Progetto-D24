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
            String query = "SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)";

            statement1 = conn.prepareStatement(query);
            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery(query);



            if (resultSet1.next()) {
                ArrayList<Event> event = new ArrayList<>();
                event = getManagerEvent(resultSet1.getString("Event")); // passo la stringa dell'evento all metodo che mi restituisce un'ArrayList di eventi

                manager = new Manager(resultSet1.getString("name"), resultSet1.getString("surname"), resultSet1.getString(3), resultSet1.getString(4),
                                resultSet1.getString(5), resultSet1.getInt(6), resultSet1.getString(7), event, resultSet1.getInt(9),
                                resultSet1.getInt(10), resultSet1.getDate(11), resultSet1.getInt(12));
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
                customer = new Customer(resultSet1.getString("name"), resultSet1.getString("surname"), resultSet1.getString("dateofbirth"), resultSet1.getString(1),
                        resultSet1.getString(2), resultSet1.getInt("provinceOfResidence"), Genre.valueOf(resultSet1.getString("Genre"))); // sto null qui non convince
            }


        }catch(Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return customer;
    }

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
                        (Festival) e = new Festival(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null);
                    }
                    case "CONCERTO" -> {
                        (Concert) e = new Concert(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null);
                    }
                    case "TEATRO" -> {
                        (Theater) e = new Theater(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null);
                    }
                    case "ALTRO" -> {
                        (Other) e = new Other(1, resultSet2.getString("NAME"), resultSet2.getString("CITY"), ld,
                                resultSet2.getString("LOCATION"), Province.valueOf(resultSet2.getString("PROVINCE")), resultSet2.getInt("MAXNUMBEROFSEATS"),
                                resultSet2.getInt("TYPEOFSEATS"), seatsRemainingForType, priceForType,
                                Genre.valueOf(resultSet2.getString("GENRE")), null, null);
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
