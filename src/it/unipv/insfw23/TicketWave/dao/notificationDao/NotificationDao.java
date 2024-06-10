package it.unipv.insfw23.TicketWave.dao.notificationDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;

public class NotificationDao implements INotificationDao{
	
	private String schema;
	private Connection connection;
	
	public NotificationDao() {
		super();
		this.schema = "TicketWaveDB";
	}

	@Override
	public void insertNotification(Notification notification) throws SQLException {
		connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);
		PreparedStatement statement;
		
		try {
			String query = "INSERT INTO NOTIFY (ID, MEXAGE, MAIL, DATE_, HOUR_) VALUES (?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, notification.getId());
			statement.setString(2, notification.getMsg());
			statement.setString(3, notification.getEmailReceiver());
			statement.setDate(4, Date.valueOf(notification.getDate()));
			statement.setTime(5, Time.valueOf(notification.getTime()));
			
			statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Problema nell'inserimento della notifica");
		}catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionDBFactory.getInstance().getConnectionDB().closeConnection(connection);
	}

	
	@Override
	public int selectNotificationNumber() throws SQLException {
		connection = ConnectionDBFactory.getInstance().getConnectionDB().startConnection(connection, schema);
		PreparedStatement statement;
		ResultSet resultSet;
		
		int notificationCounter = 0;
		
		try {
			String query = "SELECT COUNT(*) FROM NOTIFY";//DA CAMBIARE IN NOTIFICATION
			statement = connection.prepareStatement(query);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				notificationCounter = resultSet.getInt(1);
			}
				
		}catch (SQLException e) {
			throw new SQLException("Errore nel calcolo del numero di notifiche");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ConnectionDBFactory.getInstance().getConnectionDB().closeConnection(connection);
		
		return notificationCounter;
	}

		
}
