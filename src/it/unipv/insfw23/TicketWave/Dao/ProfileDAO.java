package it.unipv.insfw23.TicketWave.Dao;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/*****
 *
 * COMPLETATA
 *
 */
public class ProfileDAO implements IProfileDAO {
    private String schema;
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
            st1.setInt(8, manager.getMaxNumberOfEvents());
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
        PreparedStatement statement2;
        ResultSet resultSet1;
        ResultSet resultSet2;
        Manager manager = null;
        try{
            String query="SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)";
            statement1 = conn.prepareStatement(query);
            statement1.setString(1, mail);
            statement1.setString(2, password);
            resultSet1 = statement1.executeQuery(query);
            if(resultSet1.next()){
                ArrayList<Event> createdEvents = new ArrayList<>();
                Date date = resultSet1.getDate(10);
                LocalDate dateLocal = date.toLocalDate();
                manager = new Manager(resultSet1.getString(1), resultSet1.getString(2),
                        resultSet1.getString(3), resultSet1.getString(4),
                        resultSet1.getString(5), resultSet1.getInt(6),
                        resultSet1.getString(7), createdEvents,
                        resultSet1.getInt(8), resultSet1.getInt(9), dateLocal,
                        resultSet1.getInt(11));
                try{
                    String query2="SELECT * FROM EVENT_ WHERE MANAGER_ID = ?";
                    statement2 = conn.prepareStatement(query2);
                    statement2.setString(1, mail);
                    resultSet2 = statement2.executeQuery(query2);
                    LocalDate ld = resultSet2.getDate(4).toLocalDate(); // converto data in LocalData

                    while(resultSet2.next()){
                        double[] price = {resultSet2.getDouble(12), resultSet2.getDouble(13), resultSet2.getDouble(14)};
                        int[] seatsremaining = {resultSet2.getInt(9), resultSet2.getInt(10), resultSet2.getInt(11)};
                        switch (resultSet2.getInt("TYPE")){
                            case 0:
                                Concert currentConcert = new Concert(resultSet2.getInt(1), resultSet2.getString(2),
                                                                        resultSet2.getString(3), ld,
                                                                        resultSet2.getString(5), Province.valueOf(resultSet2.getString(6)),
                                                                        resultSet2.getInt(7), resultSet2.getInt(8), seatsremaining, price,
                                                                        Genre.valueOf(resultSet2.getString(16)), manager, splitStringToArrayList(resultSet2.getString(18)));
                                createdEvents.add(currentConcert);
                                break;
                            case 1:
                                Festival currentFestival = new Festival(resultSet2.getInt(1), resultSet2.getString(2),
                                                                        resultSet2.getString(3), ld,
                                                                        resultSet2.getString(5), Province.valueOf(resultSet2.getString(6)),
                                                                        resultSet2.getInt(7), resultSet2.getInt(8),
                                                                        seatsremaining, price, Genre.valueOf(resultSet2.getString(16)),  manager, splitStringToArrayList(resultSet2.getString(18)));
                                createdEvents.add(currentFestival);
                                break;
                            case 2:
                                Theater currentTheatre = new Theater(resultSet2.getInt(1), resultSet2.getString(2),
                                                                        resultSet2.getString(3), ld,
                                                                        resultSet2.getString(5), Province.valueOf(resultSet2.getString(6)),
                                                                        resultSet2.getInt(7), resultSet2.getInt(8),
                                                                        seatsremaining, price, Genre.valueOf(resultSet2.getString(16)),  manager, splitStringToArrayList(resultSet2.getString(18)),
                                                            null, resultSet2.getString(19));
                                createdEvents.add(currentTheatre);
                                break;
                            case 3:
                                Other currentOther = new Other(resultSet2.getInt(1), resultSet2.getString(2),
                                                                    resultSet2.getString(3), ld,
                                                                    resultSet2.getString(5), Province.valueOf(resultSet2.getString(6)),
                                                                    resultSet2.getInt(7), resultSet2.getInt(8),
                                                                    seatsremaining, price, Genre.valueOf(resultSet2.getString(16)),  manager, splitStringToArrayList(resultSet2.getString(18)),
                                                                    resultSet2.getString(20));
                                createdEvents.add(currentOther);
                                break;
                        }
                    }
                    manager.setEvent(createdEvents);
                }catch (Exception e){e.printStackTrace();}
            }
        }catch (Exception e){e.printStackTrace();}
        ConnectionDB.closeConnection(conn);
        return manager;
    }

    @Override
    public void setSubscription(Manager manager) {

    }
    private ArrayList<String> splitStringToArrayList(String s){
        String[] arrayString = s.split(",");
        ArrayList<String> resArrayList = new ArrayList<>();
        resArrayList.addAll(Arrays.asList(arrayString));
        return resArrayList;
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
                        resultSet1.getString(2), resultSet1.getInt("PROVINCE"), splitStringToArrayGenre(resultSet1.getString("GENRE")));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.closeConnection(conn);
        return customer;
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