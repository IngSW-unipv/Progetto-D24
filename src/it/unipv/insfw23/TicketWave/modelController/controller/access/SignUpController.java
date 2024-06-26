package it.unipv.insfw23.TicketWave.modelController.controller.access;


import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.exceptions.AccountAlreadyExistsException;
import it.unipv.insfw23.TicketWave.exceptions.GenreNotSelected;
import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * This controller manages all the buttons selected in {@link SignUpView}
 * EventHandler ActionEvent  goToLoginView: if you click on the backButton you go back to the {@link LoginView}
 * EventHandler ActionEvent goToSelection:if you click on the signUpButton , you go to the {@link SubscriptionSelectionView} if you want to sign in as a
 * manager, or you go to the {@link CustomerView} if you want to sign up as a customer
 */
public class SignUpController {
    private Stage mainstage;

    // view da considerare

    private SignUpView signUpView;
    private LoginView loginView;
    private SubscriptionSelectionView subscriptionSelectionView;
    private ProfileDao profileDao;
    
    private final int MAX_EVENTS_FOR_FREE_SUB = 1;

    private static final String nameRegex=new String("^[A-Z][a-zA-Z0-9_-]{1,50}$");
    private static final String mailRegex=new String("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,100}$");         //RFC 5322
    private static final String passwordRegex=new String("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,100}$");  //8 caratteri, maiuscola, minuscola, numero



    public SignUpController(Stage mainstage, SignUpView signUpView, LoginView loginView) {

        this.signUpView = signUpView;
        this.mainstage = mainstage;
        this.loginView = loginView;
        this.profileDao=new ProfileDao(); //DA CAMBIAREEE
        initComponents();

    }

    public void initComponents() {

        // Action sul logingbutton
        EventHandler<ActionEvent> goToLoginView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Azione da eseguire quando il pulsante "Torna Indietro" viene premuto
                System.out.println("You clicked on the backButton");
                //loginView= new LoginView();
                loginView.reSetBars();
                loginView.makeBlankPage();
                LoginController loginController= new LoginController(mainstage,loginView );
                
                mainstage.setScene(loginView); // Imposta la scena LoginView sullo Stage principal

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


                if(signUpView.checkEqualEmailAndPassword()== false) {
                    signUpView.setErrorLabel("Mail o password non corrispondenti");

                }
                else if(signUpView.checkFieldsEmpty() == true) {
                    signUpView.setErrorLabel("Campi vuoti o non validi");

                }else if(!Pattern.matches(nameRegex, signUpView.getNameField().getText()) || !Pattern.matches(nameRegex, signUpView.getSurnameField().getText())){
                        signUpView.setErrorLabel("Nome e cognome devono iniziare \ncon una lettera maiuscola");
                }else if(!(Pattern.matches(mailRegex, signUpView.getEmailField().getText()))){
                        signUpView.setErrorLabel("Mail non valida");
                }else if(!(Pattern.matches(passwordRegex, signUpView.getPasswordField().getText()))){
                        signUpView.setErrorLabel("La password deve avere almeno 8 caratteri, \ndeve contenere almento una lettera minuscola, \nuna maiuscola e un numero");


                }else if (signUpView.getCustomerRadioButton().isSelected()) {

                    signUpView.setErrorLabel("");

                    System.out.println("You are signing up as a customer");



                    //set del customer, CHIAMATA AL DAO PER LA REGISTRAZIONE

                    ArrayList<Ticket> tickets= new ArrayList<>();

                    Customer customer=new Customer(signUpView.getNameField().getText(),
                            signUpView.getSurnameField().getText(), signUpView.getDatePicker().getValue().toString(),
                            signUpView.getEmailField().getText(),signUpView.getPasswordField().getText(),
                            signUpView.getSelectedProvince(),signUpView.getSelectedGenres(), 0,tickets);  // setto  a zero i biglietti creati e i punti vanno presi dopo nelle altre view

                    try {
                        profileDao.insertCustomer(customer);


                        ArrayList<Ticket> arrayListTicket = customer.getTicketsList();
                        ArrayList<Notification> arrayListNotification = customer.getNotification();
                        CustomerView customerview = new CustomerView(customer.getName(),arrayListNotification,arrayListTicket,customer.getPoints() );
                        CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                        customerview.reSetBars();

                        ConnectedUser.getInstance().setUser(customer);
                        ConnectedUser.getInstance().setHome(customerview);
                        ConnectedUser.getInstance().setLoginView(loginView);

                    
                        mainstage.setScene(customerview);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (AccountAlreadyExistsException e) {
                        signUpView.setErrorLabel(e.getMessage());
                    } catch (GenreNotSelected e) {
                        signUpView.setErrorLabel(e.getMessage());
                    }


                    // Imposta la scena SignUpView sulla stage principale
                } else if (signUpView.getManagerRadioButton().isSelected()) {

                    signUpView.setErrorLabel("");

                    System.out.println("You are signing up as a manager");


                    //set del manager, CHIAMATA AL DAO PER LA REGISTRAZIONE

                    ArrayList<Event> arraylistevent = new ArrayList<>();

                    Manager manager = new Manager(signUpView.getNameField().getText(), signUpView.getSurnameField().getText(),signUpView.getDatePicker().getValue().toString(),signUpView.getEmailField().getText(),signUpView.getPasswordField().getText(), signUpView.getSelectedProvince(), null, arraylistevent,MAX_EVENTS_FOR_FREE_SUB,0,LocalDate.now(),0);
                    //credit card, data sub max numberofevents, da prendere nella mastercardview, subcription impostato a 1 solo per creare gli eventi di prova, però deve essere cambiato dal subupdate

                    try {
                        profileDao.insertManager(manager);

                        ConnectedUser.getInstance().setUser(manager);
                        ConnectedUser.getInstance().setLoginView(loginView);

                        subscriptionSelectionView = new SubscriptionSelectionView();
                        SubscriptionSelectionController subscriptionSelectionController = new SubscriptionSelectionController(mainstage,subscriptionSelectionView,signUpView);
                        subscriptionSelectionView.reSetBars();

                        mainstage.setScene(subscriptionSelectionView);// Imposta la scena subscriptio sulla stage principale
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    catch (AccountAlreadyExistsException e) {
                        signUpView.setErrorLabel(e.getMessage());
                    }


                    
                }
            }
        };
        signUpView.getSignUpButton().setOnAction(goToSelection);
    }
}
