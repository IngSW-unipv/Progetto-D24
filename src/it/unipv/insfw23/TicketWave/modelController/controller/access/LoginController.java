package it.unipv.insfw23.TicketWave.modelController.controller.access;


import java.sql.SQLException;
import java.util.ArrayList;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.exceptions.AccountNotFoundException;
import it.unipv.insfw23.TicketWave.exceptions.WrongPasswordException;
import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import javafx.event.*;
import javafx.stage.Stage;

/**
 * This controller manages all the buttons selected in {@link LoginView}
 * EventHandler ActionEvent goToSignUpView: if you click on the signUpButton you go to the {@link SignUpView}
 * EventHandler ActionEvent goToCustomerorManagerView: if you click on the loginButton and on the customerButton you go to the {@link CustomerView} else
 * if you click on the managerButton you go to the {@link ManagerView}
 */
public class LoginController {

    private Stage mainstage;
    private LoginView loginView;



    public LoginController(Stage mainstage ,LoginView loginView) {
        this.mainstage = mainstage;
        this.loginView = loginView;


        initComponents();
    }

    public void initComponents() {



        EventHandler<ActionEvent> goToSignUpView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // Azione da eseguire quando il pulsante "Registrati" viene premuto
                System.out.println("You clicked on the signUpButton");
                SignUpView signUpView = new SignUpView();
                SignUpController signUpController = new SignUpController(mainstage, signUpView, loginView);
                signUpView.reSetBars();
                mainstage.setScene(signUpView); // Imposta la scena SignUpView sulla stage principale
            }
        };

        loginView.getRegButton().setOnAction(goToSignUpView); // Imposta l'handler sull'azione del pulsante "Registrati"

        EventHandler<ActionEvent> goToCustomerorManagerView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ProfileDao profileDao = new ProfileDao();

                 if (loginView.getCustomerRadioButton().isSelected() && loginView.checkEmptyFields()==false) {
                     System.out.println("You clicked on the loginButton as a Customer");
                     Customer loggedCustomer = null;

                    try {
                        loggedCustomer = profileDao.selectCustomer(loginView.getMail().getText(), loginView.getPassword().getText());
                        if (loggedCustomer == null){
                            throw new AccountNotFoundException();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException("No logged user");
                    } catch (AccountNotFoundException e) {
                        loginView.setErrorLabel(e.getMessage());
                    } catch (WrongPasswordException e) {
                         loginView.setErrorLabel(e.getMessage());
                    }
                     //

                    if(loggedCustomer != null){
                        System.out.println("You clicked on the loginButton as a Customer");
                        ArrayList<Ticket> arrayListTicket = loggedCustomer.getTicketsList();
                        ArrayList<Notification> arrayListNotification = loggedCustomer.getNotification();
                        CustomerView customerview = new CustomerView(loggedCustomer.getName(),arrayListNotification,arrayListTicket,loggedCustomer.getPoints() );

                        CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                        customerview.reSetBars();

                        ConnectedUser.getInstance().setUser(loggedCustomer);
                        ConnectedUser.getInstance().setHome(customerview);
                        ConnectedUser.getInstance().setLoginView(loginView);
                        //
                        mainstage.setScene(customerview); // Imposta la scena SignUpView sulla stage principale
                    }
                }
                else if (loginView.getManagerRadioButton().isSelected() && loginView.checkEmptyFields()==false) {

                    Manager loggedManager = null;


                    try{
                        loggedManager = profileDao.selectManager(loginView.getMail().getText(), loginView.getPassword().getText());

                        if (loggedManager == null){
                            throw new AccountNotFoundException();
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException("No logged user");
                    } catch (AccountNotFoundException | WrongPasswordException e ) {
                        loginView.setErrorLabel(e.getMessage());
                    }

                    // Azione da eseguire quando il pulsante "Login" viene premuto
                    if(loggedManager != null){
                        System.out.println("You clicked on the loginButton as a Manager");
                        if(loggedManager.oneMonthPassed() && loggedManager.getSubscription() != 0 && loggedManager.getSubscription()!=-1){ //controllo sull'abbonamento scaduto
                            loggedManager.setSubscription(-1);
                            try {
                                profileDao.updateManagerSub(loggedManager);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        ArrayList<Event> arrayListEvent = loggedManager.getEventlist();
                        ArrayList<Notification> arrayListNotification = loggedManager.getNotification();
                        ManagerView managerView = new ManagerView(loggedManager.getName(),arrayListNotification,arrayListEvent,loggedManager.getSubscription(),loggedManager.getCounterCreatedEvents());


                        ConnectedUser.getInstance().setUser(loggedManager);
                        ConnectedUser.getInstance().setHome(managerView);
                        ConnectedUser.getInstance().setLoginView(loginView);
                        //
                        ManagerController managerController = new ManagerController(mainstage, managerView, loginView);
                        mainstage.setScene(managerView); // Imposta la scena SignUpView sulla stage principale }
                    }

                }// devi mettere managerview anche all'interno del costruttore

                else{loginView.setErrorLabel("Campi vuoti o non validi");}

                // Imposta l'handler sull'azione del pulsante "Registrati"
            }

        };

        loginView.getLoginButton().setOnAction(goToCustomerorManagerView);


    }

}
