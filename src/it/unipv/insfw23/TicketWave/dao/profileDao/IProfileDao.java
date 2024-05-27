package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

public interface IProfileDao {
    public void insertManager();
    public void insertCustomer();
    public Manager selectManager();
    public Customer selectCustomer();
}
