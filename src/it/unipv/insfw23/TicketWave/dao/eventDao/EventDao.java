package it.unipv.insfw23.TicketWave.dao.eventDao;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Theater;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class EventDao implements IEventDao{
    private String schema;
    private Connection connection;

    public EventDao() {
        super();
        this.schema = "TicketWaveDB";
    }

    @Override
    public void insertEvent(Event event) throws SQLException {

        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){   // check se è tutto ok

                //query d'inserimento
                String query = "INSERT INTO EVENT_ (ID_EVENT, NAME_, CITY, LOCATION, DATE_, TIME_, PROVINCE, GENRE, TYPE_, MAX_NUM_SEATS," +
                        "NUM_SEATS_TYPE, SOLD_BASE_SEATS, SOLD_PREMIUM_SEATS, SOLD_VIP_SEATS, REMAINING_BASE_SEATS, REMAINING_PREMIUM_SEATS, " +
                        "REMAINING_VIP_SEATS, BASE_PRICE, PREMIUM_PRICE, VIP_PRICE, ID_MANAGER, ARTISTS, AUTHOR, DESCRIPTION_) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                Date eventDate = Date.valueOf(event.getDate());
                Time eventTime = Time.valueOf(event.getTime());
                //byte[] photoByteArray = imageToByteArray(event.getPhoto(), "jpg");
                InputStream photoDB = transformImageIntoInputStream(event.getPhoto());
                String author = null;


                //setto i campi
                PreparedStatement preparedStatement=connection.prepareStatement(query);
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

                switch (event.getTypeOfSeats()){
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

                        preparedStatement.setInt(15, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setInt(16, event.getSeatsRemainedNumberForType()[1]);

                        preparedStatement.setDouble(18, event.getPrices()[0]);
                        preparedStatement.setDouble(19, event.getPrices()[1]);

                        break;

                    case 1:
                        preparedStatement.setInt(12, event.getTicketsSoldNumberForType()[0]);

                        preparedStatement.setInt(15, event.getSeatsRemainedNumberForType()[0]);

                        preparedStatement.setDouble(18, event.getPrices()[0]);

                        break;
                }
                preparedStatement.setString(21, event.getCreator().getEmail());
                System.out.println(event.getCreator().getEmail());
                preparedStatement.setString(22, event.getArtists());
                System.out.println(event.getArtists());

                if(event.getType() == Type.THEATER){
                    Theater theatreEvent = (Theater) event;
                    author = theatreEvent.getAuthorName();
                }
                preparedStatement.setString(23, author);
                System.out.println(author);
                preparedStatement.setString(24, event.getDescription());
                System.out.println(event.getDescription());
                //preparedStatement.setBlob(25, photoDB);


                System.out.println("prova pre-esecuzione");
                preparedStatement.executeUpdate();  // eseguo
                System.out.println("query eseguita");
            }
        }catch (SQLException e) {
            throw new SQLException("No zi non posso salvare i tuoi dati, c'è qualche prob");
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

    private InputStream transformImageIntoInputStream (final Image image )
    {
        BufferedImage awtBufferedImage = SwingFXUtils.fromFXImage( image , null );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {
            ImageIO.write( awtBufferedImage , "jpg" , outputStream );
            byte[] res = outputStream.toByteArray();
            InputStream inputStream = new ByteArrayInputStream( res );
            return inputStream;
        }
        catch ( IOException e ) { e.printStackTrace(); }
        throw new IllegalStateException( "Should not reach this point in method `transformImageIntoInputStream`." );
    }

}
