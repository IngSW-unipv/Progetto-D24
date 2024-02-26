package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

public interface IProfileDAO {

    void insert(User user);

    void update(User user);

    User get(String userClass, String mail, String password);

    void setSubscription(Manager manager);
}
