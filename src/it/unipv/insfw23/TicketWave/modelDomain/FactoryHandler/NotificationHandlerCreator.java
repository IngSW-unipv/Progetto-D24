package it.unipv.insfw23.TicketWave.modelDomain.FactoryHandler;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.NotificationHandler;

public class NotificationHandlerCreator extends HandlerCreator{

    @Override
    public Handler createHandler() {
        return NotificationHandler.getIstance();
    }
}
