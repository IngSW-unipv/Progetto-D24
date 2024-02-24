package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB istance;

    // Costruttore privato (Singleton) per l'unica istanza di connessione al DB
    private ConnectionDB() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = " "; // url di connessione al DB MySQL
        String username = "Java"; // user creato (su DB MySQL) per la connessione a Java
        String password = "1234"; // password dello user sopra
        Connection connection = null;

        // try - catch per vedere se la connessione va a buon fine, altrimenti Exception
        try {
            connection = DriverManager.getConnection(url, username, password); // prova di connessione

            if(connection != null){
                System.out.println("Database connected!");
            } else {
                System.out.println("Can't connect to the Database!");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }

        // chiusura della connessione, altrimenti Exception
        try {
            if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
        }
    }

    public static ConnectionDB getIstance(){ // metodo per richiamare l'unica istanza di connessione
        if(istance == null){
            istance = new ConnectionDB();
        }
        return istance;
    }
}
