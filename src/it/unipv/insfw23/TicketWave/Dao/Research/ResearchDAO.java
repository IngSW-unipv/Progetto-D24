package it.unipv.insfw23.TicketWave.Dao.Research;

import it.unipv.insfw23.TicketWave.Dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResearchDAO implements IResearchDAO{
    private String schema = "Ticket_Wave";
    private Connection conn;

    public ResearchDAO() { // è un Object easy
        super();
    }

    @Override
    public ArrayList<Event> searchAllEvents(int idEvent, String name, String city, LocalDate date, String location, Province province, int maxNumberOfSeats, int typeOfSeats, int [] seatsRemainedNumberForType, int[] price, Genre genre, Manager creator, ArrayList<String> artists) { // Quando sulla ResearchBar non ho nulla, allora restituisco tutti gli eventi
        conn = ConnectionDB.startConnection(conn,schema);
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Event> result = new ArrayList<>();

        try{
            String query = "SELECT * FROM Event ";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();


            while(rs.next()){
                result.add(new Event());
            }
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
