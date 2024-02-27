package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileDAO implements IProfileDAO{

    private String schema;
    private Connection conn;

    public ProfileDAO(){
        super();
        this.schema = "";
    }

    @Override
    public void insert(User user) {


    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getManager(String mail, String password) {

        conn = ConnectionDB.startConnection(conn, schema);
        PreparedStatement statement1;
        PreparedStatement statement2;
        ResultSet resultSet1;
        ResultSet resultSet2;
        Manager manager = null;

        try{
            String query="SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)";

            statement1 = conn.prepareStatement(query);
            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery(query);

            if(resultSet1.next()){

                ArrayList<Event> createdEvents = new ArrayList<>();
                Date date = resultSet1.getDate(10);
                LocalDate dateLocal = date.toLocalDate();
                manager = new Manager(resultSet1.getString(1), resultSet1.getString(2),
                                             resultSet1.getString(3), resultSet1.getString(4),
                                             resultSet1.getString(5), resultSet1.getInt(6),
                                             resultSet1.getString(7), createdEvents,
                                             resultSet1.getInt(8), resultSet1.getInt(9), dateLocal,
                                             resultSet1.getInt(11));

                try{
                    String query2="SELECT * FROM EVENT_ WHERE MANAGER_ID = ?";

                    statement2 = conn.prepareStatement(query2);
                    statement2.setString(1, mail);
                    resultSet2 = statement2.executeQuery(query2);

                    while(resultSet2.next()){
                        double[] price = {resultSet2.getDouble(12), resultSet2.getDouble(13), resultSet2.getDouble(14)};
                        int[] seatsremaining = {resultSet2.getInt(9), resultSet2.getInt(10), resultSet2.getInt(11)};


                        switch (resultSet2.getInt(15)){
                            case 0:

                                Concert currentConcert = new Concert(resultSet2.getInt(1), resultSet2.getString(2),
                                        resultSet2.getString(3), resultSet2.getDate(4),
                                        resultSet2.getString(5), Province.valueOf(resultSet2.getString(6)),
                                        resultSet2.getInt(7), resultSet2.getInt(8), seatsremaining, price,
                                        Genre.valueOf(resultSet2.getString(9)), manager, splitStringToArrayList(resultSet2.getString(10));
                                createdEvents.add(currentConcert);
                                break;

                            case 1:
                                Concert currentFestival = new Concert(resultSet2.getInt(1), resultSet2.getString(2),
                                        resultSet2.getString(3), resultSet2.getDate(4),
                                        resultSet2.getString(5), resultSet2.getString(6),
                                        resultSet2.getInt(7), resultSet2.getInt(8),
                                        resultSet2.getString(9), manager, splitStringToArrayList(resultSet2.getString(10));
                                createdEvents.add(currentFestival);
                                break;

                            case 2:
                                Concert currentTheatre = new Concert(resultSet2.getInt(1), resultSet2.getString(2),
                                        resultSet2.getString(3), resultSet2.getDate(4),
                                        resultSet2.getString(5), resultSet2.getString(6),
                                        resultSet2.getInt(7), resultSet2.getInt(8),
                                        resultSet2.getString(9), manager, splitStringToArrayList(resultSet2.getString(10), resultSet2.getString(19),
                                        resultSet2.getString(20);
                                createdEvents.add(currentTheatre);
                                break;

                            case 3:
                                Concert currentOther = new Concert(resultSet2.getInt(1), resultSet2.getString(2),
                                        resultSet2.getString(3), resultSet2.getDate(4),
                                        resultSet2.getString(5), resultSet2.getString(6),
                                        resultSet2.getInt(7), resultSet2.getInt(8),
                                        resultSet2.getString(9), manager, splitStringToArrayList(resultSet2.getString(10), resultSet2.getString(21);
                                createdEvents.add(currentOther);
                                break;
                        }

                    }

                    manager.setEvent(createdEvents);
                }catch (Exception e){e.printStackTrace();}
            }

        }catch (Exception e){e.printStackTrace();}
        ConnectionDB.closeConnection(conn);

        return manager;
    }

    @Override
    public void setSubscription(Manager manager) {

    }

    private ArrayList<String> splitStringToArrayList(String s){
        String[] arrayString = s.split(",");
        ArrayList<String> resArrayList = new ArrayList<>();
        resArrayList.addAll(Arrays.asList(arrayString));

        return resArrayList;
    }
}
