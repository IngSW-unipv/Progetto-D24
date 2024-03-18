package it.unipv.insfw23.TicketWave.modelDomain.FactoryHandler;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.NotificationHandler;

public class NotificationHandlerCreator extends HandlerCreator{ // implementazione del Factory, cioè classe che crea un Handler

    @Override
    public Handler createHandler() {
        return NotificationHandler.getIstance();
    }
}
