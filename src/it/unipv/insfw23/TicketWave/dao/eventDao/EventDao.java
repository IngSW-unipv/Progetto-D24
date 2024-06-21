package it.unipv.insfw23.TicketWave.dao.eventDao;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;


/**
 * Implementation of the methods declared in {@link IEventDao}.
 * It encapsulates database interactions, providing a clean and reusable interface
 * for accessing database items.
 *
 */
public class EventDao implements IEventDao {
    private String schema;
    private Connection connection;

    public EventDao() {
        super();
        this.schema = "TicketWaveDB";
    }


    /**
     *Method that allows inserting an {@link Event} into the database.
     * @param event
     */
    @Override
    public void insertEvent(Event event) throws SQLException {

        //int eventNumber = selectEventNumber();

        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);  // apro connessione
            if (ConnectionDB.isOpen(connection)) {   // check se è tutto ok

                //query d'inserimento
                String query = "INSERT INTO EVENT_ (ID_EVENT, NAME_, CITY, LOCATION, DATE_, TIME_, PROVINCE, GENRE, TYPE_, MAX_NUM_SEATS," +
                        "NUM_SEATS_TYPE, SOLD_BASE_SEATS, SOLD_PREMIUM_SEATS, SOLD_VIP_SEATS, REMAINING_BASE_SEATS, REMAINING_PREMIUM_SEATS, " +
                        "REMAINING_VIP_SEATS, BASE_PRICE, PREMIUM_PRICE, VIP_PRICE, ID_MANAGER, ARTISTS, AUTHOR, DESCRIPTION_, PHOTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                Date eventDate = Date.valueOf(event.getDate());
                Time eventTime = Time.valueOf(event.getTime());
                //byte[] photoByteArray = imageToByteArray(event.getPhoto(), "jpg");
                InputStream photoDB = transformImageIntoInputStream(event.getPhoto());
                String author = null;


                //setto i campi
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, event.getIdEvent());
                System.out.println(event.getIdEvent());
                preparedStatement.setString(2, event.getName());
                System.out.println(event.getName());
                preparedStatement.setString(3, event.getCity());
                System.out.println(event.getCity());
                preparedStatement.setString(4, event.getLocation());
                System.out.println(event.getLocation());
                preparedStatement.setDate(5, eventDate);
                System.out.println(eventDate);
                preparedStatement.setTime(6, eventTime);
                System.out.println(eventTime);
                preparedStatement.setString(7, event.getProvince().name());
                System.out.println(event.getProvince().name());
                preparedStatement.setString(8, event.getGenre().name());
                System.out.println(event.getGenre().name());
                preparedStatement.setString(9, event.getType().name());
                System.out.println(event.getType().name());
                preparedStatement.setInt(10, event.getMaxNumberOfSeats());
                System.out.println(event.getMaxNumberOfSeats());
                preparedStatement.setInt(11, event.getTypeOfSeats());
                System.out.println(event.getTypeOfSeats());

                //vettore biglietti venduti
                //vettore biglietti rimanenti
                //vettore prezzo biglietti

                switch (event.getTypeOfSeats()) {
                    case 3:
                        preparedStatement.setInt(12, event.getTicketsSoldNumberForType()[0]);
                        System.out.println(event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setInt(13, event.getTicketsSoldNumberForType()[1]);
                        System.out.println(event.getTicketsSoldNumberForType()[1]);
                        preparedStatement.setInt(14, event.getTicketsSoldNumberForType()[2]);
                        System.out.println(event.getTicketsSoldNumberForType()[2]);
                        preparedStatement.setInt(15, event.getSeatsRemainedNumberForType()[0]);
                        System.out.println(event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setInt(16, event.getSeatsRemainedNumberForType()[1]);
                        System.out.println(event.getSeatsRemainedNumberForType()[1]);
                        preparedStatement.setInt(17, event.getSeatsRemainedNumberForType()[2]);
                        System.out.println(event.getSeatsRemainedNumberForType()[2]);
                        preparedStatement.setDouble(18, event.getPrices()[0]);
                        System.out.println(event.getPrices()[0]);
                        preparedStatement.setDouble(19, event.getPrices()[1]);
                        System.out.println(event.getPrices()[1]);
                        preparedStatement.setDouble(20, event.getPrices()[2]);
                        System.out.println(event.getPrices()[2]);
                        break;

                    case 2:
                        preparedStatement.setInt(12, event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setInt(13, event.getTicketsSoldNumberForType()[1]);
                        preparedStatement.setNull(14, Types.INTEGER);
                        preparedStatement.setInt(15, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setInt(16, event.getSeatsRemainedNumberForType()[1]);
                        preparedStatement.setNull(17, Types.INTEGER);
                        preparedStatement.setDouble(18, event.getPrices()[0]);
                        preparedStatement.setDouble(19, event.getPrices()[1]);
                        preparedStatement.setNull(20, Types.INTEGER);
                        break;

                    case 1:
                        preparedStatement.setInt(12, event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setNull(13, Types.INTEGER);
                        preparedStatement.setNull(14, Types.INTEGER);
                        preparedStatement.setInt(15, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setNull(16, Types.INTEGER);
                        preparedStatement.setNull(17, Types.INTEGER);
                        preparedStatement.setDouble(18, event.getPrices()[0]);
                        preparedStatement.setNull(19, Types.INTEGER);
                        preparedStatement.setNull(20, Types.INTEGER);

                        break;
                }
                preparedStatement.setString(21, event.getCreator().getEmail());
                System.out.println(event.getCreator().getEmail());
                preparedStatement.setString(22, event.getArtists());
                System.out.println(event.getArtists());

                if (event.getType() == Type.THEATER) {
                    Theater theatreEvent = (Theater) event;
                    author = theatreEvent.getAuthorName();
                }
                preparedStatement.setString(23, author);
                System.out.println(author);
                preparedStatement.setString(24, event.getDescription());
                System.out.println(event.getDescription());
                preparedStatement.setBlob(25, photoDB);


                System.out.println("pre-execution check");
                preparedStatement.executeUpdate();  // eseguo
                System.out.println("query executed");
            }
        } catch (SQLException e) {        	
//            e.printStackTrace();
            throw new SQLException("Problems inserting event item in eventDao");
        }
        ConnectionDB.closeConnection(connection);

    }
/*
    public static byte[] imageToByteArray(Image image, String formatName) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, formatName, baos);
        return baos.toByteArray();
    }

    // Metodo per convertire un array di byte in un Blob
    public static Blob byteArrayToBlob(byte[] byteArray) throws SQLException {
        return new SerialBlob(byteArray);
    }

 */

    private InputStream transformImageIntoInputStream(final Image image) {
        BufferedImage awtBufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(awtBufferedImage, "jpg", outputStream);
            byte[] res = outputStream.toByteArray();
            InputStream inputStream = new ByteArrayInputStream(res);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Problems in `transformImageIntoInputStream`");
    }


    /**
     *Method that allows calculating the number of events that are in the database.
     * @return eventNumber
     */
    public int selectEventNumber() throws SQLException {
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);  // apro connessione
        PreparedStatement statement1;
        ResultSet resultSet1;

        int eventNumber = 0;

        try {
            String query1 = "SELECT COUNT(*) FROM EVENT_";
            statement1 = connection.prepareStatement(query1);

            resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {
                eventNumber = resultSet1.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("User not found or unregistered");
        }
        ConnectionDB.closeConnection(connection);
        return eventNumber;
    }



    /**
     *Method that allows retrieving an {@link Event} from the database starting from its id.
     * @param event_Id
     * @return selectedEvent
     */
    public Event selectEvent(int event_Id) throws SQLException {
        Event selectedEvent = null;
        connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);  // apro connessione
        PreparedStatement statement1;
        ResultSet resultSet1;
        Manager manager = null;
        String managerId = null;

        try {
            String query1 = "SELECT * FROM EVENT_ WHERE ID_EVENT = ?";
            statement1 = connection.prepareStatement(query1);
            statement1.setInt(1, event_Id);
            resultSet1 = statement1.executeQuery();

            while (resultSet1.next()) {

                managerId = resultSet1.getString("ID_MANAGER");
                LocalDate currentDate = resultSet1.getDate("DATE_").toLocalDate(); // converto data in LocalData

                double[] price = new double[resultSet1.getInt("NUM_SEATS_TYPE")];
                int[] seatsRemaining = new int[resultSet1.getInt("NUM_SEATS_TYPE")];
                int[] seatsSold = new int[resultSet1.getInt("NUM_SEATS_TYPE")];

                switch (resultSet1.getInt("NUM_SEATS_TYPE")) {
                    case 3:
                        price[2] = resultSet1.getDouble("VIP_PRICE");
                        seatsRemaining[2] = resultSet1.getInt("REMAINING_VIP_SEATS");
                        seatsSold[2] = resultSet1.getInt("SOLD_VIP_SEATS");

                    case 2:
                        price[1] = resultSet1.getDouble("PREMIUM_PRICE");
                        seatsRemaining[1] = resultSet1.getInt("REMAINING_PREMIUM_SEATS");
                        seatsSold[1] = resultSet1.getInt("SOLD_PREMIUM_SEATS");

                    case 1:
                        price[0] = resultSet1.getDouble("BASE_PRICE");
                        seatsRemaining[0] = resultSet1.getInt("REMAINING_BASE_SEATS");
                        seatsSold[0] = resultSet1.getInt("SOLD_BASE_SEATS");
                }

                // parte di conversione da blob a image in comune a tutti i case
                Blob blobphoto = resultSet1.getBlob("PHOTO");
                InputStream is = blobphoto.getBinaryStream();
                Image photo = new Image(is);


                switch (Type.valueOf(resultSet1.getString("TYPE_")).ordinal()) {
                    case 0:
                        Festival currentFestival = new Festival(resultSet1.getInt("ID_EVENT"), resultSet1.getString("NAME_"),
                                resultSet1.getString("CITY"), resultSet1.getString("LOCATION"),
                                currentDate, resultSet1.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet1.getString("PROVINCE")),
                                Genre.valueOf(resultSet1.getString("GENRE")), resultSet1.getInt("MAX_NUM_SEATS"), resultSet1.getInt("NUM_SEATS_TYPE"),
                                seatsRemaining, seatsSold, price, manager, resultSet1.getString("ARTISTS"), resultSet1.getString("DESCRIPTION_"),
                                photo);
                        selectedEvent = currentFestival;
                        break;

                    case 1:
                        Concert currentConcert = new Concert(resultSet1.getInt("ID_EVENT"), resultSet1.getString("NAME_"),
                                resultSet1.getString("CITY"), resultSet1.getString("LOCATION"),
                                currentDate, resultSet1.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet1.getString("PROVINCE")),
                                Genre.valueOf(resultSet1.getString("GENRE")), resultSet1.getInt("MAX_NUM_SEATS"), resultSet1.getInt("NUM_SEATS_TYPE"),
                                seatsRemaining, seatsSold, price, manager, resultSet1.getString("ARTISTS"), resultSet1.getString("DESCRIPTION_"), photo);
                        selectedEvent = currentConcert;
                        break;

                    case 2:
                        Theater currentTheatre = new Theater(resultSet1.getInt("ID_EVENT"), resultSet1.getString("NAME_"),
                                resultSet1.getString("CITY"), resultSet1.getString("LOCATION"),
                                currentDate, resultSet1.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet1.getString("PROVINCE")),
                                Genre.valueOf(resultSet1.getString("GENRE")), resultSet1.getInt("MAX_NUM_SEATS"), resultSet1.getInt("NUM_SEATS_TYPE"),
                                seatsRemaining, seatsSold, price, manager, resultSet1.getString("ARTISTS"), resultSet1.getString("DESCRIPTION_"), resultSet1.getString("AUTHOR"),
                                photo);
                        selectedEvent = currentTheatre;
                        break;

                    case 3:
                        Other currentOther = new Other(resultSet1.getInt("ID_EVENT"), resultSet1.getString("NAME_"),
                                resultSet1.getString("CITY"), resultSet1.getString("LOCATION"),
                                currentDate, resultSet1.getTime("TIME_").toLocalTime(), Province.valueOf(resultSet1.getString("PROVINCE")),
                                Genre.valueOf(resultSet1.getString("GENRE")), resultSet1.getInt("MAX_NUM_SEATS"), resultSet1.getInt("NUM_SEATS_TYPE"),
                                seatsRemaining, seatsSold, price, manager, resultSet1.getString("ARTISTS"), resultSet1.getString("DESCRIPTION_"),
                                photo);
                        selectedEvent = currentOther;
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems in selecting event item in eventDao");
        }
        return selectedEvent;
    }



    /**
     *Method that allows updating the number of sold tickets and the remaining seats of
     * an {@link Event} in the database.
     * @param event
     */
    public void updateSeatsNumber(Event event) throws SQLException {
        PreparedStatement statement1;
        ResultSet resultSet1;

        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);  // apro connessione
            if (ConnectionDB.isOpen(connection)) {   // check se è tutto ok

                //query d'inserimento
                String query = "UPDATE EVENT_ SET SOLD_BASE_SEATS = ?, SOLD_PREMIUM_SEATS = ?, SOLD_VIP_SEATS = ?, REMAINING_BASE_SEATS = ?, REMAINING_PREMIUM_SEATS = ?, REMAINING_VIP_SEATS = ? WHERE ID_EVENT = ?";


                //setto i campi
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(7, event.getIdEvent());

                switch (event.getTypeOfSeats()) {
                    case 3:
                        preparedStatement.setInt(1, event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setInt(2, event.getTicketsSoldNumberForType()[1]);
                        preparedStatement.setInt(3, event.getTicketsSoldNumberForType()[2]);
                        preparedStatement.setInt(4, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setInt(5, event.getSeatsRemainedNumberForType()[1]);
                        preparedStatement.setInt(6, event.getSeatsRemainedNumberForType()[2]);
                        break;

                    case 2:
                        preparedStatement.setInt(1, event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setInt(2, event.getTicketsSoldNumberForType()[1]);
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setInt(4, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setInt(5, event.getSeatsRemainedNumberForType()[1]);
                        preparedStatement.setNull(6, Types.INTEGER);
                        break;

                    case 1:
                        preparedStatement.setInt(1, event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setNull(2, Types.INTEGER);
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setInt(4, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setNull(5, Types.INTEGER);
                        preparedStatement.setNull(6, Types.INTEGER);
                        break;
                }
                System.out.println("pre-execution check");
                preparedStatement.executeUpdate();  // eseguo
                System.out.println("query executed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Problems updating seats data in eventDao");
        }
        ConnectionDB.closeConnection(connection);
    }


}
