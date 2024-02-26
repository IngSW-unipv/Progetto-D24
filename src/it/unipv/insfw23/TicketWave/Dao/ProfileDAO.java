package it.unipv.insfw23.TicketWave.Dao;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import java.sql.PreparedStatement;

public class ProfileDAO implements IProfileDAO{

    private String schema;
    private ConnectionDB conn;

    public ProfileDAO(){
        super();
        this.schema="TICKETWAVE";
        conn=ConnectionDB.getIstance();
    }
/*
    @Override
    public boolean insertManager(Manager manager) {
        conn=ConnectionDB.getIstance();
        PreparedStatement st1=null;

        boolean esito=true;

        try(
                String query="INSERT INTO MANAGER(NAME,SURNAME,BIRTHDATE,MAIL,PWD,PROVINCE,CARDNUMBER,MAXEVENTS,SUBSCRIPTION) VALUES (?,?,?,?,?,?,?,?,?)";
                st1=conn.prepareStatement(query);

                st1.setString(1,manager.getName());
                st1.setString(2,manager.getSurname());
                st1.setString(3, manager.getDateOfBirth());
                st1.setString(4, manager.getEmail());
                st1.setString(5,manager.getPassword());
                st1.setString(6,manager.getProvinceOfResidence());
                st1.setString(7,manager.getCreditCard())

                )


    }
    */

    @Override
    public void update(User user) {

    }

    @Override
    public User get(User user) {
        return null;
    }

    @Override
    public void setSubscription(Manager manager) {




    }
}
