package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.*;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public class ProfileDAO implements IProfileDAO{

    private String schema;

    private User user;
    private Connection conn;

    public ProfileDAO(){
        super();
        this.schema = "";
    }

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
    public User get(String userClass, String mail, String password) {

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
                manager = new Manager(resultSet1.getString(1), resultSet1.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return manager;
    }

    @Override
    public void setSubscription(Manager manager) {

    }
}