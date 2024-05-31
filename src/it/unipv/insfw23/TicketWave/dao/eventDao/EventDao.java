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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
                String query = "INSERT INTO EVENT_ (ID_EVENT, NAME_, CITY, LOCATION, DATE_, TIME_, PROVINCE, GENRE, TYPE_, MAX_NUM_SEATS" +
                        "NUM_SEATS_TYPE, SOLD_BASE_SEATS, SOLD_PREMIUM_SEATS, SOLD_VIP_SEATS, REMAINING_BASE_SEATS, REMAINING_PREMIUM_SEATS, " +
                        "REMAINING_VIP_SEATS, BASE_PRICE, PREMIUM_PRICE, VIP_PRICE, ID_MANAGER, ARTISTS, AUTHOR, DESCRIPTION_, PHOTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                Date eventDate = Date.valueOf(event.getDate());
                Time eventTime = Time.valueOf(event.getTime());
                byte[] photoByteArray = imageToByteArray(event.getPhoto(), "jpg");
                String author = null;

                //setto i campi
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setInt(1, event.getIdEvent());
                preparedStatement.setString(2, event.getName());
                preparedStatement.setString(3, event.getCity());
                preparedStatement.setString(4, event.getLocation());
                preparedStatement.setDate(5, eventDate);
                preparedStatement.setTime(6, eventTime);
                preparedStatement.setString(7, event.getProvince().name());
                preparedStatement.setString(8, event.getGenre().name());
                preparedStatement.setString(9, event.getType().name());
                preparedStatement.setInt(10, event.getMaxNumberOfSeats());
                preparedStatement.setInt(11, event.getTypeOfSeats());

                //vettore biglietti venduti

                //vettore biglietti rimanenti

                //vettore prezzo biglietti

                switch (event.getTypeOfSeats()){
                    case 3:
                        preparedStatement.setInt(12, event.getTicketsSoldNumberForType()[0]);
                        preparedStatement.setInt(13, event.getTicketsSoldNumberForType()[1]);
                        preparedStatement.setInt(14, event.getTicketsSoldNumberForType()[2]);
                        preparedStatement.setInt(15, event.getSeatsRemainedNumberForType()[0]);
                        preparedStatement.setInt(16, event.getSeatsRemainedNumberForType()[1]);
                        preparedStatement.setInt(17, event.getSeatsRemainedNumberForType()[2]);
                        preparedStatement.setDouble(18, event.getPrices()[0]);
                        preparedStatement.setDouble(19, event.getPrices()[1]);
                        preparedStatement.setDouble(20, event.getPrices()[2]);
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
                preparedStatement.setString(22, event.getArtists());

                if(event.getType() == Type.THEATER){
                    Theater theatreEvent = (Theater) event;
                    author = theatreEvent.getAuthorName();
                }
                preparedStatement.setString(23, author);

                preparedStatement.setString(24, event.getDescription());
                preparedStatement.setBlob(25, byteArrayToBlob(photoByteArray));

                preparedStatement.executeUpdate();  // eseguo
                System.out.println("query eseguita");
            }
        }catch (SQLException e) {
            throw new SQLException("No zi non posso salvare i tuoi dati, c'è qualche prob");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConnectionDB.closeConnection(connection);

    }

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

}
