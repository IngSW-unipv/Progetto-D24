package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class ProfileDao implements IProfileDao {
    private String schema;
    private Connection connection;

    public ProfileDao() {
        super();
        this.schema = "";
    }

    @Override
    public void insertManager(Manager manager) throws SQLException {

        try {
            connection = ConnectionDB.startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){   // check se è tutto ok

                //query d'inserimento
                String query = "INSERT INTO MANAGER (name, surname, dateOfBirth, email, password, provinceOfResidence, " +
                        "creditCard, maxNumberOfEvents, subscription, subscriptionDate, counterCreatedEvents) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                //setto i campi
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, manager.getName());
                preparedStatement.setString(2, manager.getSurname());
                preparedStatement.setString(3, manager.getDateOfBirth());
                preparedStatement.setString(4, manager.getEmail());
                preparedStatement.setString(5, manager.getPassword());
                preparedStatement.setString(6, manager.getProvinceOfResidence().toString());
                preparedStatement.setString(7, manager.getCreditCard());
                preparedStatement.setInt(8, manager.getMaxNumberOfEvents());
                preparedStatement.setInt(9, 0);     // il subscription è =0 al momento dell'iscrizione
                preparedStatement.setDate(10, Date.valueOf(manager.getSubscriptionDate()));
                preparedStatement.setInt(11, 0);    //counterCreatedEvents=0 al momento dell'iscrizione

                preparedStatement.executeUpdate();  // eseguo
            }
        }catch (SQLException e) {
            throw new SQLException("No zi non posso salvare i tuoi dati, c'è qualche prob", e);
        }
        ConnectionDB.closeConnection(connection);

    }


    @Override
    public void insertCustomer(Customer customer)throws SQLException{
        try {
            connection = ConnectionDB.startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){


                String query = "INSERT INTO CUSTOMER(name, surname, dateOfBirth, email, password, provinceOfResidence, points, favouriteGenre) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement=connection.prepareStatement(query);

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getDateOfBirth());
                preparedStatement.setString(4, customer.getEmail());
                preparedStatement.setString(5, customer.getPassword());
                preparedStatement.setString(6, customer.getProvinceOfResidence().toString()); // Assuming Province has a proper toString() method
                preparedStatement.setInt(7, 0);  // points=0 al momento dell'iscrizione
                //preparedStatement.executeUpdate();

                for (Genre genre : customer.getFavoriteGenre()) {
                    preparedStatement.setString(1, genre.toString());
                }
                preparedStatement.executeUpdate();  // OCCHIO: non so se faccia l'update completo
                //o ho bisongno di fare un ulteriore statement, e poi metterlo all'interno del ciclo
            }
        } catch (SQLException e) {
            throw new SQLException("Errore per inserimento del Customer", e);
        }
        ConnectionDB.closeConnection(connection);
    }



    @Override
    public Manager selectManager(String mail, String password) {
        connection = ConnectionDB.startConnection(connection, schema);
        PreparedStatement statement1;
        PreparedStatement statement2;
        ResultSet resultSet1;
        ResultSet resultSet2;

        Manager manager = null;

        try {
            String query1 = "SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)";
            statement1 = connection.prepareStatement(query1);
            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {
                ArrayList<Event> createdEvents = new ArrayList<>();         //creo la arraylist da riempire
                Date date = resultSet1.getDate("SUBSCRIPTION_DATE");             //mi prendo la data di sub per castarla
                LocalDate subDate = date.toLocalDate();
                String provString = resultSet1.getString("PROVINCE");    //mi prendo la Province per castarla
                Province province = Province.valueOf(provString);

                manager = new Manager(resultSet1.getString("NAME_"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDAY"),
                        resultSet1.getString("MAIL"), null, Province.valueOf(resultSet1.getString("PROVINCE")), resultSet1.getString("CARDNUMBER"), createdEvents,
                        resultSet1.getInt("MAXEVENTS"), resultSet1.getInt("SUBSCRIPTION"), subDate, resultSet1.getInt("COUNTER_CREATED_EVENTS"));

                try {
                    String query2 = "SELECT * FROM EVENT WHERE ID_MANAGER = ?";
                    statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, mail);

                    resultSet2 = statement2.executeQuery();

                    while (resultSet2.next()) {
                        LocalDate currentDate = resultSet2.getDate("DATE_").toLocalDate(); // converto data in LocalData
                        double[] price = {resultSet2.getDouble("BASE_PRICE"), resultSet2.getDouble("PREMIUM_PRICE"), resultSet2.getDouble("VIP_PRICE")};
                        int[] seatsRemaining = {resultSet2.getInt("REMAINING_BASE_SEATS"), resultSet2.getInt("REMAINING_BASE_SEATS"), resultSet2.getInt("REMAINING_BASE_SEATS")};
                        int[] seatsSold = {resultSet2.getInt("SOLD_BASE_SEATS"), resultSet2.getInt("SOLD_BASE_SEATS"), resultSet2.getInt("SOLD_BASE_SEATS")};

                        switch (resultSet2.getInt("TYPE")) {
                            case 0:
                                Concert currentConcert = new Concert(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION"), resultSet2.getBlob("PHOTO"));
                                createdEvents.add(currentConcert);
                                break;

                            case 1:
                                Festival currentFestival = new Festival(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION"), countWords(resultSet2.getString("ARTISTS")),
                                        resultSet2.getBlob("PHOTO"));
                                createdEvents.add(currentFestival);
                                break;

                            case 2:
                                Theater currentTheatre = new Theater(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION"), resultSet2.getString("AUTHOR"),
                                        resultSet2.getBlob("PHOTO"));
                                createdEvents.add(currentTheatre);
                                break;

                            case 3:
                                Other currentOther = new Other(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION"),
                                        resultSet2.getBlob("PHOTO"));
                                createdEvents.add(currentOther);
                                break;
                        }
                    }
                    manager.setEvent(createdEvents);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConnectionDB.closeConnection(connection);
        return manager;
    }

    public int countWords(String input) {
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


    @Override
    public Customer selectCustomer(String mail, String password) {
        connection = ConnectionDB.startConnection(connection, schema);
        PreparedStatement statement1;
        PreparedStatement statement2;
        ResultSet resultSet1;
        ResultSet resultSet2;

        Customer customer = null;

        try {
            String query1 = "SELECT * FROM CUSTOMER WHERE (MAIL = ?) AND (PASSWORD = ?)";
            statement1 = connection.prepareStatement(query1);
            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {

                ArrayList<Ticket> boughtTickets = new ArrayList<>();   // creo l'arraylist per riempirla

                customer = new Customer(resultSet1.getString("NAME_"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDAY"),
                        resultSet1.getString("MAIL"), null, Province.valueOf(resultSet1.getString("PROVINCE")), splitStringToArrayGenre(resultSet1.getString("FAVOURITE_GENRE")),
                        resultSet1.getInt("POINTS"), boughtTickets);

                try {
                    String query2 = "SELECT * FROM TICKET WHERE ID_CUSTOMER = ?";
                    statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, mail);

                    resultSet2 = statement2.executeQuery();

                    while (resultSet2.next()) {
                        Ticket currentTicket = new Ticket(resultSet2.getString("BAR_CODE"), resultSet2.getDouble("PRICE"),
                                TicketType.valueOf(resultSet2.getString("TYPE")));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Genre[] splitStringToArrayGenre(String s) {
        String[] arrayString = s.split(",");
        Genre[] genreArray = new Genre[4];
        Genre singleGenre;
        int i = 0;
        for (String string : arrayString) {
            singleGenre = Genre.valueOf(string);
            genreArray[i] = singleGenre;
            i++;
        }
        return genreArray;
    }
}
