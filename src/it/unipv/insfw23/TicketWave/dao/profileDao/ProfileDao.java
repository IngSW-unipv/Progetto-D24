package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDao implements IProfileDao{
    private String schema;
    private Connection conn;
    public ProfileDao() {
        super();
        this.schema = "TicketWave";
    }

    @Override
    public void insertManager(Manager manager) throws SQLException {

        try {
            conn = ConnectionDB.startConnection(conn,schema);  // apro connessione
            if(ConnectionDB.isOpen(conn)){   // check se è tutto ok

                //query d'inserimento
                String query = "INSERT INTO MANAGER (name, surname, dateOfBirth, email, password, provinceOfResidence, " +
                                 "creditCard, maxNumberOfEvents, subscription, subscriptionDate, counterCreatedEvents) " +
                                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                //setto i campi
                PreparedStatement preparedStatement=conn.prepareStatement(query);
                preparedStatement.setString(1, manager.getName());
                preparedStatement.setString(2, manager.getSurname());
                preparedStatement.setString(3, manager.getDateOfBirth());
                preparedStatement.setString(4, manager.getEmail());
                preparedStatement.setString(5, manager.getPassword());
                preparedStatement.setString(6, manager.getProvinceOfResidence().toString());
                preparedStatement.setString(7, manager.getCreditCard());
                preparedStatement.setInt(8, manager.getMaxNumberOfEvents());
                preparedStatement.setInt(9, 0);     // il subscription è =0 al momento dell'iscrizione
                preparedStatement.setDate(10, Date.valueOf(manager.getSubscriptionDate()));
                preparedStatement.setInt(11, 0);    //counterCreatedEvents=0 al momento dell'iscrizione

                preparedStatement.executeUpdate();  // eseguo
        }
    }catch (SQLException e) {
            throw new SQLException("No zi non posso salvare i tuoi dati, c'è qualche prob", e);
    }
        ConnectionDB.closeConnection(conn);

    }


    @Override
    public void insertCustomer(Customer customer)throws SQLException{
        try {
            conn = ConnectionDB.startConnection(conn,schema);  // apro connessione
            if(ConnectionDB.isOpen(conn)){


                String query = "INSERT INTO CUSTOMER(name, surname, dateOfBirth, email, password, provinceOfResidence, points, favouriteGenre) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement=conn.prepareStatement(query);

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getDateOfBirth());
                preparedStatement.setString(4, customer.getEmail());
                preparedStatement.setString(5, customer.getPassword());
                preparedStatement.setString(6, customer.getProvinceOfResidence().toString()); // Assuming Province has a proper toString() method
                preparedStatement.setInt(7, 0);  // points=0 al momento dell'iscrizione
                //preparedStatement.executeUpdate();

                for (Genre genre : customer.getFavoriteGenre()) {
                        preparedStatement.setString(1, genre.toString());
                    }
                preparedStatement.executeUpdate();  // OCCHIO: non so se faccia l'update completo
                                                    //o ho bisongno di fare un ulteriore statement, e poi metterlo all'interno del ciclo
            }
        } catch (SQLException e) {
            throw new SQLException("Errore per inserimento del Customer", e);
        }
            ConnectionDB.closeConnection(conn);
    }



    @Override
    public Manager selectManager(){

        return null;
    }


    @Override
    public Customer selectCustomer(){

        return null;
    }
}
