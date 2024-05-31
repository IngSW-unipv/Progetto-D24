package it.unipv.insfw23.TicketWave.dao.eventDao;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;

import java.sql.SQLException;

public interface IEventDao {
    public void insertEvent(Event event) throws SQLException;
}
