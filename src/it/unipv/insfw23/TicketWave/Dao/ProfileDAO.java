package it.unipv.insfw23.TicketWave.Dao;

/*import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
    public User get(String mail, String password) {

        conn = ConnectionDB.startConnection(conn, schema);
        PreparedStatement statement1;
        ResultSet resultSet1;
        Manager manager= new manager();

        try{
            String query="SELECT * FROM MANAGER WHERE (MAIL = ?) AND (PASSWORD = ?)";

            statement1 = conn.prepareStatement(query);
            statement1.setString(1, mail);
            statement1.setString(2, password);

            resultSet1 = statement1.executeQuery(query);

            if(resultSet1.next()){
               // Manager manager = new Manager(resultSet1.getString(1));
            }

        }catch (Exception e){e.printStackTrace();}
        ConnectionDB.closeConnection(conn);
        return manager;
    }

    @Override
    public void setSubscription(Manager manager) {

    }
}
*/