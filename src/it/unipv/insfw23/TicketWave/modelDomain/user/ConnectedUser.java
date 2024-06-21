package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;

/**
 * public class (singleton) that is used to different stages at the login and holds a user as
 * a private attribute
 */
public class ConnectedUser {
    /**
     * private variables used in the methods below
     */
    private static ConnectedUser istance;
    private User user;
    private IResettableScene home;

    private Event eventForTicket;

    private TicketType ticketType;
    private LoginView logView;
    private int newSubLevel;
    
    private final int LOGOUT_LEVEL_SUB = -2;

    private ConnectedUser(){
        this.user = null;
    }

    /**
     * This method is used when no user is connected and it
     * to the platform, and it creates a new instance.
     * @return istance
     */
    public static ConnectedUser getInstance(){
        if(istance == null){
            istance = new ConnectedUser();
        }
        return istance;
    }

    /**
     * This method sets the user that you pass to the method
     * @param user
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     *
     * @return user as an {@link User}
     */
    public User getUser(){
        return user;
    }

    /**
     *used to associate the event to the ticket that is considered
     * @return eventForTicket as {@link Event}
     */
    public Event getEventForTicket() {
        return eventForTicket;
    }

    /**
     * used to
     * @return ticketType as {@link TicketType}
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * sets the ticketType that you pass as a parameter
     * @param ticketType
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * sets eventForTicket to associate the event to the ticket that is considered
     * @param eventForTicket
     */
    public void setEventForTicket(Event eventForTicket) {
        this.eventForTicket = eventForTicket;
    }

    /**
     * sets the home for the different views
     * @param home
     */
    public void setHome(IResettableScene home) {
    	this.home = home;
    }

    /**
     * returns the home for the different views
     * @return home as {@link IResettableScene}
     */
    public IResettableScene getHome(){
    	return home;
    }

    /**
     *
     * @return logView as {@link LoginView}
     */
    public LoginView getLoginView() {
        return logView;
    }

    /**
     * sets loginView
     * @param logView
     */
    public void setLoginView(LoginView logView) {
        this.logView = logView;
    }

    /**
     * returns the number associated to the new subscription
     * @return newSubLevel as int
     */
	public int getNewSubLevel() {
		return newSubLevel;
	}

    /**
     * sets the number associated to the new subscription
     * @param newSubLevel
     */
	public void setNewSubLevel(int newSubLevel) {
		this.newSubLevel = newSubLevel;
	}

    /**
     * used to set the user with no parameter when you logout
     */
    public void logoutMethod(){
        this.user = null;
        this.home = null;
        this.logView = null;
        this.eventForTicket = null;
        this.newSubLevel = LOGOUT_LEVEL_SUB;
        this.ticketType = null;
    }
}

