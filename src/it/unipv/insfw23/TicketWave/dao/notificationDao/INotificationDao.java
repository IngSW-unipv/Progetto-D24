package it.unipv.insfw23.TicketWave.dao.notificationDao;

import java.sql.SQLException;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;

public interface INotificationDao {
	public void insertNotification(Notification notification) throws SQLException;
	public int selectNotificationNumber() throws SQLException;
}
