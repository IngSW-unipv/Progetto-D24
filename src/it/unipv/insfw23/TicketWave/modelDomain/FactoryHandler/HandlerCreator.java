package it.unipv.insfw23.TicketWave.modelDomain.FactoryHandler;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.NotificationHandler;

public abstract class HandlerCreator { // classe astratta Factory
    public abstract Handler createHandler();
}
