package it.unipv.insfw23.TicketWave.modelController.factory.ticket;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class TicketHandlerFactory {
	
	private static ITicketHandler ticketHandler;
	private static final String TICKETHANDLER_PROPERTYNAME = "tickethandler.class.name";
	private static TicketHandlerFactory istance = null;
	
	private TicketHandlerFactory() {}
	
	//Singleton
	public static TicketHandlerFactory getIstance() {
		if(istance == null) {
			istance = new TicketHandlerFactory();
		}
		return istance;
	}
	
	public ITicketHandler getTicketHandler() {
		if(ticketHandler == null) {
			String ticketHandlerClassName;
			
			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("src/it/unipv/insfw23/TicketWave/properties"));
				ticketHandlerClassName = p.getProperty(TICKETHANDLER_PROPERTYNAME);
				
				//java reflection
				Constructor c = Class.forName(ticketHandlerClassName).getDeclaredConstructor();
				c.setAccessible(true);
				ticketHandler = (ITicketHandler)c.newInstance();
				c.setAccessible(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ticketHandler;
	}
	
}
