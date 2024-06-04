package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.org.mindrot.jbcrypt.BCrypt;
import javafx.scene.image.Image;



public class ProfileDao implements IProfileDao {
    private String schema;
    private Connection connection;

    public ProfileDao() {
        super();
        this.schema = "TicketWaveDB";
    }

    @Override
    public void insertManager(Manager manager) throws SQLException {

        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){   // check se è tutto ok

                //query d'inserimento
                String query = "INSERT INTO MANAGER (NAME_, SURNAME, BIRTHDATE, MAIL, PWD, PROVINCE,CARDNUMBER, MAXEVENTS, SUBSCRIPTION, SUBSCRIPTION_DATE, COUNTER_CREATED_EVENTS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                //setto i campi
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, manager.getName());
                preparedStatement.setString(2, manager.getSurname());
                preparedStatement.setString(3, manager.getDateOfBirth());
                preparedStatement.setString(4, manager.getEmail());
                preparedStatement.setString(5, hashPassword(manager.getPassword()));
                preparedStatement.setString(6, manager.getProvinceOfResidence().toString());
                preparedStatement.setString(7, manager.getCreditCard());
                preparedStatement.setInt(8, manager.getMaxNumberOfEvents());
                preparedStatement.setInt(9, 0);     // il subscription è =0 al momento dell'iscrizione
                preparedStatement.setDate(10, Date.valueOf(manager.getSubscriptionDate()));
                preparedStatement.setInt(11, 0);    //counterCreatedEvents=0 al momento dell'iscrizione

                preparedStatement.executeUpdate();  // eseguo
                System.out.println("query eseguita");
            }
        }catch (SQLException e) {
            throw new SQLException("No zi non posso salvare i tuoi dati, c'è qualche prob", e);
        }
        ConnectionDB.closeConnection(connection);

    }




    @Override
    public void insertCustomer(Customer customer)throws SQLException{
        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){

                //utilizzo stringBuilder per separare i valori all'interno del db per ogni favoriteGenre
                StringBuilder genresBuilder = new StringBuilder();
                Genre[] favoriteGenres = customer.getFavoriteGenre();
                for (int i = 0; i < favoriteGenres.length; i++) {
                    if (i > 0) {
                        genresBuilder.append(",");  // aggiungo virgola
                    }
                    genresBuilder.append(favoriteGenres[i].toString());
                }

                String favoriteGenresStr = genresBuilder.toString();
                ///////////////////////////////////////////////////////

                String query = "INSERT INTO CUSTOMER(NAME_, SURNAME, BIRTHDATE, MAIL, PWD, PROVINCE, POINTS, FAVOURITE_GENRE) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

                PreparedStatement preparedStatement=connection.prepareStatement(query);

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getDateOfBirth());
                preparedStatement.setString(4, customer.getEmail());
                preparedStatement.setString(5, hashPassword(customer.getPassword()));
                preparedStatement.setString(6, customer.getProvinceOfResidence().toString()); // Assuming Province has a proper toString() method
                preparedStatement.setInt(7, 0);  // points=0 al momento dell'iscrizione
                preparedStatement.setString(8, favoriteGenresStr);  //settaggio parametri utilizzando la stringaBuildata

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Errore per inserimento del Customer", e);
        }
        ConnectionDB.closeConnection(connection);
    }



    @Override
    public Manager selectManager(String mail, String password) throws SQLException{
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
        PreparedStatement statement1;
        PreparedStatement statement2;
        PreparedStatement statement3;
        ResultSet resultSet1;
        ResultSet resultSet2;
        ResultSet resultSet3;

        Manager manager = null;

        try {
            String query1 = "SELECT * FROM MANAGER WHERE (MAIL = ?)";
            statement1 = connection.prepareStatement(query1);
            statement1.setString(1, mail);

            resultSet1 = statement1.executeQuery();

            String dbPassword = new String();
            boolean resultAvailable = false;

            if(resultSet1.next()){
                resultAvailable = true;
                Object genericDbPassword= resultSet1.getString("PWD");
                dbPassword = genericDbPassword.toString();
            }

            if (resultAvailable && checkPassword(password, dbPassword)) {

                ArrayList<Event> createdEvents = new ArrayList<>();         //creo la arraylist da riempire
                Date date = resultSet1.getDate("SUBSCRIPTION_DATE");             //mi prendo la data di sub per castarla
                LocalDate subDate = date.toLocalDate();

                manager = new Manager(resultSet1.getString("NAME_"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDATE"),
                        resultSet1.getString("MAIL"), null, Province.valueOf(resultSet1.getString("PROVINCE")), resultSet1.getString("CARDNUMBER"), createdEvents,
                        resultSet1.getInt("MAXEVENTS"), resultSet1.getInt("SUBSCRIPTION"), subDate, resultSet1.getInt("COUNTER_CREATED_EVENTS"));

                try {
                    String query2 = "SELECT * FROM EVENT_ WHERE ID_MANAGER = ?";

                    statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, mail);
                    resultSet2 = statement2.executeQuery();

                    while (resultSet2.next()) {

                        LocalDate currentDate = resultSet2.getDate("DATE_").toLocalDate(); // converto data in LocalData

                        double[] price = new double[resultSet2.getInt("NUM_SEATS_TYPE")];
                        int[] seatsRemaining = new int[resultSet2.getInt("NUM_SEATS_TYPE")];
                        int[] seatsSold = new int[resultSet2.getInt("NUM_SEATS_TYPE")];

                        switch (resultSet2.getInt("NUM_SEATS_TYPE")){
                            case 3:
                                price[2] = resultSet2.getDouble("VIP_PRICE");
                                seatsRemaining[2] = resultSet2.getInt("REMAINING_VIP_SEATS");
                                seatsSold[2] = resultSet2.getInt("SOLD_VIP_SEATS");

                            case 2:
                                price[1] = resultSet2.getDouble("PREMIUM_PRICE");
                                seatsRemaining[1] = resultSet2.getInt("REMAINING_PREMIUM_SEATS");
                                seatsSold[1] = resultSet2.getInt("SOLD_PREMIUM_SEATS");

                            case 1:
                                price[0] = resultSet2.getDouble("BASE_PRICE");
                                seatsRemaining[0] = resultSet2.getInt("REMAINING_BASE_SEATS");
                                seatsSold[0] = resultSet2.getInt("SOLD_BASE_SEATS");
                        }

                        // parte di conversione da blob a image in comune a tutti i case
                        Blob blobphoto = resultSet2.getBlob("PHOTO");
                        InputStream is = blobphoto.getBinaryStream();
                        Image photo = new Image(is);

                        
                        switch (Type.valueOf(resultSet2.getString("TYPE_")).ordinal()) {
                            case 0:
                                Festival currentFestival = new Festival(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION_"), countWords(resultSet2.getString("ARTISTS")),
                                        photo);
                                createdEvents.add(currentFestival);
                                break;

                            case 1:
                                Concert currentConcert = new Concert(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION_"), photo);
                                createdEvents.add(currentConcert);
                                break;

                            case 2:
                                Theater currentTheatre = new Theater(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION_"), resultSet2.getString("AUTHOR"),
                                        photo);
                                createdEvents.add(currentTheatre);
                                break;

                            case 3:
                                Other currentOther = new Other(resultSet2.getInt("ID_EVENT"), resultSet2.getString("NAME_"),
                                        resultSet2.getString("CITY"), resultSet2.getString("LOCATION"),
                                        currentDate, resultSet2.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet2.getString("PROVINCE")),
                                        Genre.valueOf(resultSet2.getString("GENRE")), resultSet2.getInt("MAX_NUM_SEATS"), resultSet2.getInt("NUM_SEATS_TYPE"),
                                        seatsRemaining, seatsSold, price, manager, resultSet2.getString("ARTISTS"), resultSet2.getString("DESCRIPTION_"),
                                        photo);
                                createdEvents.add(currentOther);
                                break;
                        }
                    }
                    manager.setEvent(createdEvents);

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Problema nel caricamento degli eventi");
                }


                try {
                    String query3 = "SELECT * FROM NOTIFY WHERE MAIL = ?";

                    statement3 = connection.prepareStatement(query3);
                    statement3.setString(1, mail);
                    resultSet3 = statement3.executeQuery();

                    ArrayList<Notification> notificationArrayList= new ArrayList<>();

                    while (resultSet3.next()) {
                        Notification currentNotify = new Notification(resultSet3.getInt("ID"), null,
                                resultSet3.getString("MEXAGE"));
                        currentNotify.setEmailReceiver(mail);
                        currentNotify.setDate(resultSet3.getDate("DATE_").toLocalDate());
                        currentNotify.setTime(resultSet3.getTime("HOUR_").toLocalTime());

                        manager.addNotification(currentNotify);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                return null;}
        } catch (SQLException e) {
            throw new RuntimeException("Utente non trovato o non registrato");
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
    public Customer selectCustomer(String mail, String password) throws SQLException{
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
        PreparedStatement statement1;
        PreparedStatement statement2;
        PreparedStatement statement3;
        ResultSet resultSet1;
        ResultSet resultSet2;
        ResultSet resultSet3;

        Customer customer = null;

        try {
            String query1 = "SELECT * FROM CUSTOMER WHERE (MAIL = ?)";
            statement1 = connection.prepareStatement(query1);
            statement1.setString(1, mail);

            resultSet1 = statement1.executeQuery();

            String dbPassword = new String();
            boolean resultAvailable = false;

            if(resultSet1.next()){
                resultAvailable = true;
                Object genericDbPassword= resultSet1.getString("PWD");
                dbPassword = genericDbPassword.toString();
            }
            if (resultAvailable && checkPassword(password, dbPassword)) {

                ArrayList<Ticket> boughtTickets = new ArrayList<>();   // creo l'arraylist per riempirla

                customer = new Customer(resultSet1.getString("NAME_"), resultSet1.getString("SURNAME"), resultSet1.getString("BIRTHDATE"),
                        resultSet1.getString("MAIL"), null, Province.valueOf(resultSet1.getString("PROVINCE")), splitStringToArrayGenre(resultSet1.getString("FAVOURITE_GENRE")),
                        resultSet1.getInt("POINTS"), boughtTickets);

                try {
                    String query2 = "SELECT * FROM TICKET WHERE ID_CUSTOMER = ?";
                    statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, mail);

                    resultSet2 = statement2.executeQuery();

                    while (resultSet2.next()) {
                        Ticket currentTicket = new Ticket(resultSet2.getString("BAR_CODE"), resultSet2.getDouble("PRICE"),
                                TicketType.valueOf(resultSet2.getString("TYPE_")),resultSet2.getInt("ID_EVENT"),resultSet2.getString("EVENT_NAME"));

                        customer.addTickets(currentTicket);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Errore nel caricamento dei biglietti");
                }

                try {
                    String query3 = "SELECT * FROM NOTIFY WHERE MAIL = ?";

                    statement3 = connection.prepareStatement(query3);
                    statement3.setString(1, mail);
                    resultSet3 = statement3.executeQuery();

                    ArrayList<Notification> notificationArrayList= new ArrayList<>();

                    while (resultSet3.next()) {
                        Notification currentNotify = new Notification(resultSet3.getInt("ID"), null,
                                resultSet3.getString("MEXAGE"));
                        currentNotify.setEmailReceiver(mail);
                        currentNotify.setDate(resultSet3.getDate("DATE_").toLocalDate());
                        currentNotify.setTime(resultSet3.getTime("HOUR_").toLocalTime());

                        customer.addNotification(currentNotify);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{ return null;}

        } catch (SQLException e) {
            throw new RuntimeException("Utente non trovato o non registrato");
        }
        ConnectionDB.closeConnection(connection);
        return customer;
    }
    
    private Genre[] splitStringToArrayGenre(String s) {
        String[] arrayString = s.split(",");
        Genre[] genreArray = new Genre[5];
        Genre singleGenre;
        int i = 0;
        for (String string : arrayString) {
            singleGenre = Genre.valueOf(string);
            genreArray[i] = singleGenre;
            i++;
        }
        return genreArray;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

	@Override
	public void updateManagerSub(Manager manager) throws SQLException {
		connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);
		PreparedStatement statement1;
		try {
			
			String query1 = "UPDATE MANAGER SET MAXEVENTS = ?, SUBSCRIPTION = ?, SUBSCRIPTION_DATE = ?, COUNTER_CREATED_EVENTS = ? WHERE MAIL = ?";
			
			statement1 = connection.prepareStatement(query1);
			switch (ConnectedUser.getInstance().getNewSubLevel()) {
			case 0:
				statement1.setInt(1, 1);
				break;
			case 1:
				statement1.setInt(1, 5);
				break;
			case 2:
				statement1.setInt(1, Short.MAX_VALUE);
			}
			statement1.setInt(2, ConnectedUser.getInstance().getNewSubLevel());
			statement1.setDate(3, Date.valueOf(LocalDate.now()));
			statement1.setInt(4, 0);
			statement1.setString(5, manager.getEmail());
			
			statement1.execute();
		} catch (SQLException e) {
			throw new SQLException("Errore nell'aggiornamento della sub");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ConnectionDB.closeConnection(connection);
	}


    @Override
    public ArrayList<String> selectCustomerByGenre (Genre genreToCheck) throws SQLException{
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
        PreparedStatement statement1;
        ResultSet resultSet1;

        ArrayList<String> customerWithGenreMail = new ArrayList<>();

        try {
            String query1 = "SELECT * FROM CUSTOMER";
            statement1 = connection.prepareStatement(query1);

            resultSet1 = statement1.executeQuery();

            while(resultSet1.next()) {

                Genre[] genreArray= splitStringToArrayGenre(resultSet1.getString("FAVOURITE_GENRE"));
                for(int i=0; i<genreArray.length; i++){
                    if(genreToCheck == genreArray[i]){
                        customerWithGenreMail.add(resultSet1.getString("MAIL"));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Utente non trovato o non registrato");
        }
        ConnectionDB.closeConnection(connection);
        return customerWithGenreMail;
    }

    public ArrayList<String> selectCustomerByProvince (Province provToCheck) throws SQLException{
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
        PreparedStatement statement1;
        ResultSet resultSet1;

        ArrayList<String> customerWithProvMail = new ArrayList<>();

        try {
            String query1 = "SELECT * FROM CUSTOMER";
            statement1 = connection.prepareStatement(query1);

            resultSet1 = statement1.executeQuery();

            while(resultSet1.next()) {

                Province province = Province.valueOf(resultSet1.getString("PROVINCE"));
                if(province == provToCheck){
                    customerWithProvMail.add(resultSet1.getString("MAIL"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Utente non trovato o non registrato");
        }
        ConnectionDB.closeConnection(connection);
        return customerWithProvMail;
    }

    public void updateCustomerPoints(Customer customer) throws SQLException {
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);
        PreparedStatement statement1;
        try {

            String query1 = "UPDATE CUSTOMER SET POINTS= ? WHERE MAIL = ?";

            statement1 = connection.prepareStatement(query1);

            statement1.setInt(1,customer.getPoints());
            statement1.setString(2, customer.getEmail());

            statement1.execute();
        } catch (SQLException e) {
            throw new SQLException("Errore nell'aggiornamento dei punti");
        }catch(Exception e) {
            e.printStackTrace();
        }

        ConnectionDB.closeConnection(connection);
    }
    

}
