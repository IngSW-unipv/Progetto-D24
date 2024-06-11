package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object (DAO) interface for managing operations related to the {@link Event} entity in the database.
 * This interface provides methods to search for {@link Event} items with a search field and some filters.
 *
 * @see Event
 */
public interface IResearchDAO {
    ArrayList<Event> getFilteredEvents(String searchField, ArrayList<String> pr , ArrayList<String> gen) throws SQLException; // metodo della ricerca, esso fornisce i risultati
    ArrayList<Event> getAllEvents() throws SQLException; // Restituisco tutti gli eventi quando sul textField della ricerca non c'è nulla
}
