package it.unipv.insfw23.TicketWave.modelController.Factory.Notifications;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.NotificationHandler;

public class NotificationHandlerFactory {

	private static INotificationHandler notificationHandler;
	private static final String NOTIFICATIONHANDLER_PROPERTYNAME = "notificationhandler.class.name";
	private static NotificationHandlerFactory istance = null;
	
	private NotificationHandlerFactory() {}//costruttore non deve fare nulla
	
	
	//Singleton
	public static NotificationHandlerFactory getIstance(){
		if(istance == null) {
			istance = new NotificationHandlerFactory();
		}
		return istance;
	}
	
	public INotificationHandler getNotificationHandler() {
		if(notificationHandler == null) {
			String notificationHandlerClassName;
			
			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
				notificationHandlerClassName = p.getProperty(NOTIFICATIONHANDLER_PROPERTYNAME);
				
				//java reflection
				Constructor c = Class.forName(notificationHandlerClassName).getConstructor();
				notificationHandler = (INotificationHandler)c.newInstance();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return notificationHandler;
	}
	
	
}
