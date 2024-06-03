package it.unipv.insfw23.TicketWave.modelController.controller.access;

import it.unipv.insfw23.TicketWave.dao.profileDao.IProfileDao;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SignUpController {
    private Stage mainstage;

    // view da considerare

    private SignUpView signUpView;
    private ManagerView managerView;
    private CustomerView customerview;
    private LoginView loginView;
    private SubscriptionSelectionView subscriptionSelectionView;
    private ProfileDao profileDao;
    private User user= ConnectedUser.getInstance().getUser();


    public SignUpController(Stage mainstage, SignUpView signUpView, LoginView loginView) {

        this.signUpView = signUpView;
        this.mainstage = mainstage;
        this.loginView = loginView;
        //this.profileDao=new ProfileDao();
        // this.subscriptionSelectionView= subscriptionSelectionView;
        initComponents();

    }

    public void initComponents() {

        // Action sul logingbutton
        EventHandler<ActionEvent> goToLoginView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Azione da eseguire quando il pulsante "Torna Indietro" viene premuto
                System.out.println("Hai cliccato il pulsante Torna Indietro");
                //loginView= new LoginView();
                LoginController loginController= new LoginController(mainstage,loginView );
                loginView.reSetBars();
                loginView.makeBlankPage();
                mainstage.setScene(loginView.getScene()); // Imposta la scena LoginView sullo Stage principal

            }
        };

        signUpView.getBackButton().setOnAction(goToLoginView);

        // Action sul signupbutton
        EventHandler<ActionEvent> goToSelection = new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                //aggiungo queste stampe solo per controllare cosa ritornano i valori di controllo(claudio)
                System.out.println("checkEqualEmailAndPassword: " + signUpView.checkEqualEmailAndPassword());
                System.out.println("checkFieldsEmpty: " + signUpView.checkFieldsEmpty());


                if(signUpView.checkEqualEmailAndPassword()== false || signUpView.checkFieldsEmpty() == true) {
                    signUpView.setErrorLabel();


                }else if (signUpView.getCustomerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante registrati  come cliente");


                    //set del customer, CHIAMATA AL DAO PER LA REGISTRAZIONE

                    ArrayList<Ticket> tickets= new ArrayList<>();

                    Customer customer=new Customer(signUpView.getNameField().getText(),
                            signUpView.getSurnameField().getText(), signUpView.getDatePicker().getValue().toString(),
                            signUpView.getEmailField().getText(),signUpView.getPasswordField().getText(),
                            signUpView.getSelectedProvince(),signUpView.getSelectedGenres(), 0,tickets);  // setto  a zero i biglietti creati e i punti vanno presi dopo nelle altre view

                    try {
                        ProfileDao profileDao=new ProfileDao();
                        profileDao.insertCustomer(customer);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    ConnectedUser.getInstance().setUser(customer);
                    ConnectedUser.getInstance().setHome(customerview);
                    ConnectedUser.getInstance().setLoginView(loginView);

                    ArrayList<Ticket> arrayListTicket = customer.getTicketsList();
                    ArrayList<Notification> arrayListNotification = customer.getNotification();
                    CustomerView customerview = new CustomerView(customer.getName(),arrayListNotification,arrayListTicket,customer.getPoints() );
                    CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                    customerview.reSetBars();

                    //

                    mainstage.setScene(customerview);


                    // Imposta la scena SignUpView sulla stage principale
                } else if (signUpView.getManagerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante registrati  come gestore");
                    subscriptionSelectionView = new SubscriptionSelectionView();
                    SubscriptionSelectionController subscriptionSelectionController = new SubscriptionSelectionController(mainstage,subscriptionSelectionView,signUpView);
                    subscriptionSelectionView.reSetBars();

                    //set del manager, CHIAMATA AL DAO PER LA REGISTRAZIONE

                    ArrayList<Event> arraylistevent = new ArrayList<>();

                    Manager manager = new Manager(signUpView.getNameField().getText(), signUpView.getSurnameField().getText(),signUpView.getDatePicker().getValue().toString(),signUpView.getEmailField().getText(),signUpView.getPasswordField().getText(), signUpView.getSelectedProvince(), null, arraylistevent,1,0,LocalDate.now(),0);
                    //credit card, data sub max numberofevents, da prendere nella mastercardview, subcription impostato a 1 solo per creare gli eventi di prova, però deve essere cambiato dal subupdate

                    try {
                        profileDao.insertManager(manager);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    ConnectedUser.getInstance().setUser(manager);
                    ConnectedUser.getInstance().setLoginView(loginView);

                    //

                    mainstage.setScene(subscriptionSelectionView);
                    // Imposta la scena subscriptio sulla stage principale
                }
            }
        };
        signUpView.getSignUpButton().setOnAction(goToSelection);
    }
}
