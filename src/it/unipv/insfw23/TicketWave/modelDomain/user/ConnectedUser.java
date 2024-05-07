package it.unipv.insfw23.TicketWave.modelDomain.user;

import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;

public class ConnectedUser {
    private static ConnectedUser istance;
    private User user;

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

    public void unlogUser(){
        this.user = null;
    }
}

