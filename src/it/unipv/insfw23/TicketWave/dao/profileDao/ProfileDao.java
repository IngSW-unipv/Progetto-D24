package it.unipv.insfw23.TicketWave.dao.profileDao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.sql.Connection;

public class ProfileDao implements IProfileDao{
    private String schema;
    private Connection conn;
    public ProfileDao() {
        super();
        this.schema = "";
    }

    @Override
    public void insertManager(){

    }


    @Override
    public void insertCustomer(){

    }


    @Override
    public Manager selectManager(){

        return null;
    }


    @Override
    public Customer selectCustomer(){

        return null;
    }
}
