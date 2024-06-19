package it.unipv.insfw23.TicketWave.dao.notificationDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import it.unipv.insfw23.TicketWave.dao.ConnectionDB;
import it.unipv.insfw23.TicketWave.dao.profileDao.IProfileDao;
import it.unipv.insfw23.TicketWave.modelController.factory.ConnectionDBFactory;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

/**
 * This class controls all management of the {@link Notification} class' persistence 
 * @see ConnectionDB
 * @see INotificationDao
 */
public class NotificationDao implements INotificationDao{
	
	private String schema;
	private Connection connection;
	
	/**
     * In this constructor the name of the DB is associated with the String Schema
     */
	public NotificationDao() {
		super();
		this.schema = "TicketWaveDB";
	}

	/**
	 * It provides the way to insert a {@link Notificationi} in the database
	 * @param notification a {@link Notification} created by the domain model
	 * @throws SQLException if a generic SQL exception has occurred
	 */
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
			
			throw new SQLException("A problem has occurred while trying to insert the notification");
		
		}
		
		ConnectionDBFactory.getInstance().getConnectionDB().closeConnection(connection);
	}

	/**
	 * This method allows you to know how many {@link Notification}s are there in the particular database instance
	 * @return the number of {@link Notification}s in the database
	 * @throws SQLException if a generic SQL exception has occurred
	 */
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
		
			throw new SQLException("A problem has occurred while trying to count the notifications");
		
		}
		
		ConnectionDBFactory.getInstance().getConnectionDB().closeConnection(connection);
		
		return notificationCounter;
	}

		
}
