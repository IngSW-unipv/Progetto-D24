package it.unipv.insfw23.TicketWave.dao.ticketDao;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class inserts the new {@link Ticket} when a customer purchases it
 * @see ConnectionDB
 * @see ITicketDao
 */
public class TicketDao implements ITicketDao {

    private String schema;
    private Connection connection;

    /**
     * In this constructor the name of the DB is associated with the String Schema
     */

    public TicketDao() {
        super();
        this.schema = "TicketWaveDB";
    }

    /**
     * This method allows to insert new tickets on the TicketWaveDB
     * @param ticket is taken from the
     * @param customer when we are on the {@link it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView} or{@link it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView}
     * @throws SQLException when a general problem occurs
     */
    @Override
    public void insertTicket(Ticket ticket, Customer customer) throws SQLException{


        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){

                String query= "INSERT INTO TICKET(BAR_CODE,ID_CUSTOMER,ID_EVENT,EVENT_NAME,PRICE,TYPE_) VALUES (?,?,?,?,?,?)";

                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, ticket.getBarcode());  // va bene?
                preparedStatement.setString(2,customer.getEmail());
                preparedStatement.setInt(3,ticket.getIdEvent());
                preparedStatement.setString(4,ticket.getEventName());
                preparedStatement.setDouble(5,ticket.getPrice());
                preparedStatement.setString(6, ticket.getType().toString());  // va bene?? da fare Stringa,cambiare db

                preparedStatement.executeUpdate();


            }


            }catch (SQLException e) {
            throw new SQLException("Impossible to insert a new Ticket", e);
        }
        ConnectionDB.closeConnection(connection);
            }




}
