package it.unipv.insfw23.TicketWave.dao.ticketDao;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDao implements ITicketDao {

    private String schema;
    private Connection connection;

    public TicketDao() {
        super();
        this.schema = "TicketWaveDB";
    }

    @Override
    public void insertTicket(Ticket ticket, Customer customer) throws SQLException{

        try {
            connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection,schema);  // apro connessione
            if(ConnectionDB.isOpen(connection)){

                String query= "INSERT INTO TICKET(BUY_CODE,ID_CUSTOMER,ID_EVENT,EVENT_NAME,PRICE,TYPE_) VALUES (?,?,?,?,?,?)";

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
            throw new SQLException("No zi non posso salvare i tuoi dati, c'è qualche prob", e);
        }
        ConnectionDB.closeConnection(connection);
            }




}
