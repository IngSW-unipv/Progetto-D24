package it.unipv.insfw23.TicketWave.dao.ticketDao;

import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;

import java.sql.SQLException;

public interface ITicketDao {

    public void insertTicket(Ticket ticket,Customer customer) throws SQLException;
}
