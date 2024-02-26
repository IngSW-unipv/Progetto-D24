package it.unipv.insfw23.TicketWave.Dao.Research;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.util.ArrayList;

public interface IResearchDAO {
    public ArrayList<Event> searchParticularEvents(String search, User user); // metodo della ricerca, esso fornisce i risultati
    public ArrayList<Event> searchAllEvents(); // Restituisco tutti gli eventi quando sul textField della ricerca non c'è nulla
}
