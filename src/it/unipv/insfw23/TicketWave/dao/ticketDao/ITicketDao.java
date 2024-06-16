package it.unipv.insfw23.TicketWave.dao.ticketDao;

import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;

import java.sql.SQLException;
/**
 * Data Access Object (DAO) interface for managing operations related to the {@link Ticket} entity in the database.
 * @see Ticket
 */


public interface ITicketDao {

    public void insertTicket(Ticket ticket,Customer customer) throws SQLException;
}
