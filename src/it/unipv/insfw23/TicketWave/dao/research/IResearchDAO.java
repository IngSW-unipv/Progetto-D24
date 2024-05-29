package it.unipv.insfw23.TicketWave.dao.research;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IResearchDAO {
    public ArrayList<Event> getFilteredEvents(String search) throws SQLException; // metodo della ricerca, esso fornisce i risultati
    public ArrayList<Event> getAllEvents() throws SQLException; // Restituisco tutti gli eventi quando sul textField della ricerca non c'è nulla
}
