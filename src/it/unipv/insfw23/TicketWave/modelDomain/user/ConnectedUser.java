package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;

public class ConnectedUser {
    private static ConnectedUser istance;
    private User user;
    private IResettableScene home;

    private Event eventForTicket;

    private TicketType ticketType;
    private LoginView logView;
    private int newSubLevel;

    private ConnectedUser(){
        this.user = null;
    }

    public static ConnectedUser getInstance(){
        if(istance == null){
            istance = new ConnectedUser();
        }
        return istance;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }


    public Event getEventForTicket() {
        return eventForTicket;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setEventForTicket(Event eventForTicket) {
        this.eventForTicket = eventForTicket;
    }

    public void setHome(IResettableScene home) {this.home = home;}

    public IResettableScene getHome(){return home;}

    public LoginView getLoginView() {
        return logView;
    }

    public void setLoginView(LoginView logView) {
        this.logView = logView;
    }

    public void unlogUser(){
        this.user = null;
    }

	public int getNewSubLevel() {
		return newSubLevel;
	}

	public void setNewSubLevel(int newSubLevel) {
		this.newSubLevel = newSubLevel;
	}
    
    
}

