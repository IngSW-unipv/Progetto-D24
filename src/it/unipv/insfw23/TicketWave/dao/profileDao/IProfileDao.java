package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public interface IProfileDao {
    public void insertManager();
    public void insertCustomer();
    public Manager selectManager(String mail, String password);
    public Customer selectCustomer(String mail, String password);
}
