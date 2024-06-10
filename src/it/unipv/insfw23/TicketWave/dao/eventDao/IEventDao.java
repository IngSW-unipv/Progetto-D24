package it.unipv.insfw23.TicketWave.dao.eventDao;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;

import java.sql.SQLException;

public interface IEventDao {
    public void insertEvent(Event event) throws SQLException;
    public Event selectEvent(int event_Id) throws SQLException;
    public void updateSeatsNumber(Event event) throws SQLException;
    public int selectEventNumber() throws SQLException;
}
