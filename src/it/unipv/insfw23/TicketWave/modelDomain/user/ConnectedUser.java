package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;

public class ConnectedUser {
    private static ConnectedUser istance;
    private User user;
    private IResettableScene home;
    private LoginView logView;

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
}

