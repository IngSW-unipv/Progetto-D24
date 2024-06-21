package it.unipv.insfw23.TicketWave.dao.notificationDao;

import java.sql.SQLException;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;

/**
 * Data Access Object (DAO) interface for managing operations related to the {@link Notification} entity in the database.
 * This interface provides methods to perform Insert and Select operations.
 *
 */
public interface INotificationDao {
	public void insertNotification(Notification notification) throws SQLException;
	public int selectNotificationNumber() throws SQLException;
}
