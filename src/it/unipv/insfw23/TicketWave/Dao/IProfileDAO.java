package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IProfileDAO {

    Manager insertManager(Manager manager);
    Customer insertCustomer(Customer customer);

    void update(User user);
    User getManager(String mail, String password);
    User getCustomer(String mail, String password);
    void setSubscription(Manager manager);
}
