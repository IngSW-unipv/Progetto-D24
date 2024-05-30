package it.unipv.insfw23.TicketWave.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class  ConnectionDB {

    private static final String PROPERTYDBDRIVER = "DBDRIVER";
    private static final String PROPERTYDBURL = "DBURL";
    private static final String PROPERTYNAME = "db_usn";
    private static final String PROPERTYPSW = "db_psw";
    private static String username;
    private static String password;
    private static String dbDriver;
    private static String dbURL;
    private static ConnectionDB conn;

    // Davide: ho cambiato il metodo init in un metodo costruttore pubblico così da poter usare il factory
    public ConnectionDB() {
        Properties p = new Properties(System.getProperties());
        try {
            p.load(new FileInputStream("it/unipv/insfw23/TicketWave/properties"));
            username=p.getProperty(PROPERTYNAME);
            password=p.getProperty(PROPERTYPSW);
            dbDriver =p.getProperty(PROPERTYDBDRIVER);
            dbURL =p.getProperty(PROPERTYDBURL);


        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Davide: ho cancellato la chiamata al metodo init che era all'inizio del metodo startConnection, perchè quella parte
    // di codice viene eseguita alla creazione della classe, quindi al momento della chimata di startConnection è già tutto inizializzato
    public static Connection startConnection(Connection conn, String schema)
    {
        System.out.println(dbURL+"ciao");

        if ( isOpen(conn) )
            closeConnection(conn);

        try
        {

            dbURL=String.format(dbURL,schema);
            System.out.println(dbURL);
            Class.forName(dbDriver);

            conn = DriverManager.getConnection(dbURL, username, password);// Apertura connessione

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    public static boolean isOpen(Connection conn)
    {
        if (conn == null)
            return false;
        else
            return true;
    }

    public static Connection closeConnection(Connection conn)
    {
        if ( !isOpen(conn) )
            return null;
        try
        {

            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
