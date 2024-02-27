package it.unipv.insfw23.TicketWave.Dao.Research;

import it.unipv.insfw23.TicketWave.Dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ResearchDAO implements IResearchDAO{
    private String schema = "Ticket_Wave";
    private Connection conn;

    public ResearchDAO() { // è un Object easy
        super();
    }

    @Override
    public ArrayList<Event> searchAllEvents() { // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
        conn = ConnectionDB.startConnection(conn,schema);
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Event> result = new ArrayList<>();

        try{
            String query = "SELECT * FROM Event ";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }




        return null;
    }
    @Override
    public ArrayList<Event> searchParticularEvents(String search) { // Quando qualcuno scrive sulla ResearchBar (TextField) e usa o meno i filtri, allora uso questo metodo
        return null;
    }


}
