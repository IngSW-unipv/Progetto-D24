/*package it.unipv.insfw23.TicketWave.Dao.Research;

import it.unipv.insfw23.TicketWave.Dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.util.ArrayList;

public class ResearchDAO implements IResearchDAO{
    private String schema = "Ticket_Wave";
    private ConnectionDB connectionDB;

    public ResearchDAO() { // è un Object easy
        super();
    }

    @Override
    public ArrayList<Event> searchAllEvents() { // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
        ArrayList<Event> result = new ArrayList<>();
        connectionDB

        result.addAll(searchEvents());

        return null;
    }
    @Override
    public ArrayList<Event> searchParticularEvents(String search, User user) { // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo
        return null;
    }

    private ArrayList<Event>  searchEvents(){
        return null;
    }


}
*/