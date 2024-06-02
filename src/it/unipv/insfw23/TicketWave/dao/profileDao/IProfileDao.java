package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.sql.SQLException;

public interface IProfileDao {
    public void insertManager(Manager manager) throws SQLException;

    public void insertCustomer(Customer customer) throws SQLException;
    public Manager selectManager(String mail, String password) throws SQLException;
    public Customer selectCustomer(String mail, String password) throws SQLException;
    public void updateManagerSub(Manager manager) throws SQLException;

}
