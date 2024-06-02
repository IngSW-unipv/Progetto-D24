package it.unipv.insfw23.TicketWave.modelController.controller.access;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
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
public class LoginController {

    private Stage mainstage;

    // view da considerare
    //private SignUpView signUpView;
    private CustomerView customerview;
    private SignUpView signUpView;
    private LoginView loginView;
    private ManagerView managerView;


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
                System.out.println("Hai cliccato il pulsante Registrati");
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
                     System.out.println("Hai cliccato il pulsante login come cliente");
                    Customer loggedCustomer;
                    /*creazione customer ed evento per poi creare vari biglietti e fare delle verifiche
                     *
                     * */

                    //customer esempio
/*
                    Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
                    ArrayList<Ticket> tickets= new ArrayList<>();
                    Customer customer=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.BARI,favoriteGenre, 100,tickets);
 */
                    //
                    //ATTENZIONE, QUI VA LA CHIAMATA AL DAO
                    try {
                        loggedCustomer = profileDao.selectCustomer(loginView.getMail().getText(), loginView.getPassword().getText());
                    } catch (SQLException e) {
                        throw new RuntimeException("Utente non registrato");
                    }
                        //

                    if(loggedCustomer != null){
                        System.out.println("Hai cliccato il pulsante Login come cliente");
                         CustomerView customerview = new CustomerView();

                        CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                        customerview.reSetBars();

                        ConnectedUser.getInstance().setUser(loggedCustomer);
                        ConnectedUser.getInstance().setHome(customerview);
                        ConnectedUser.getInstance().setLoginView(loginView);
                        //
                        mainstage.setScene(customerview); // Imposta la scena SignUpView sulla stage principale
                    }
                    else{System.out.println("Email o password errate");
                            loginView.setErrorLabel();}

                }
                else if (loginView.getManagerRadioButton().isSelected() && loginView.checkEmptyFields()==false) {

                    /*	modifiche fatte da Loris per simulare la creazione di un manager senza eventi, simulare la creazione di un evento
                     * 	da parte di questo manager e simulare aggiunta di questo evento al suo arraylist di eventi
                     * 	cosi da lavorare con un ipotetico manager che ha pubblicato degli eventi
                     * */
                    Manager loggedManager;

                    //ATTENZIONE, QUI VA LA CHIAMATA AL DAO
                    try{
                        loggedManager = profileDao.selectManager(loginView.getMail().getText(), loginView.getPassword().getText());
                    } catch (SQLException e) {
                        throw new RuntimeException("Utente non registrato");
                    }

                    //
/*
                    ArrayList<Notification> arrayListNotification = new ArrayList<>();
                    ArrayList<Event> arraylistevent = new ArrayList<>();
                    LocalDate datasub = LocalDate.of(2024, 02, 25);
                    Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","password1234",Province.CREMONA, "2324523432451420", arraylistevent,5,1,datasub,0);

                    int[] seduteRImasteev1 = {200,100};
                    double[] vettfalsopriceev1 = {5,320};
                    LocalDate dataev1 = LocalDate.of(2025, 03, 20);
                    ArrayList<String> arrfintoartista1 = new ArrayList<>();
                    arrfintoartista1.add("califano");
                    //Concert eventofinto1 = new Concert(12,"reunion","busto arstizio",dataev1, "via dei matti ,0", Province.LIVORNO,300,2, seduteRImasteev1, vettfalsopriceev1,Genre.INDIE,managerfinto,arrfintoartista1);

                    int[] seduterimasteev2 = {100,80,12};
                    int[] ticketsoldev2 = {700,20,88};
                    double[] vettfalsopriceev2 = {11,250,500};
                    LocalDate dataev2 = LocalDate.of(2024, 10, 02);
                    ArrayList<String> arrfintoartista2 = new ArrayList<>();
                    arrfintoartista2.add("loredana berte");
                    LocalTime time = LocalTime.of(19, 30);
                    Image bl = null;
                    //Concert eventofinto2 = new Concert(12,"reunion","busto arstizio",dataev2, "via dei matti ,0", Province.LIVORNO,300,2, seduterimasteev2, vettfalsopriceev2,Genre.INDIE,managerfinto,arrfintoartista2);

                    try {
                        managerfinto.createConcert(12, "reunion", "milano","discoteca",dataev1,time,Province.AGRIGENTO,Genre.HOUSE, 1000,3,seduterimasteev2,ticketsoldev2,vettfalsopriceev2,managerfinto,"U2","BELLO", bl);
                                //managerfinto.createConcert(14, "festino", "Rozzano", dataev2, "stadio dell'albero", Province.MILANO, 10000, 3, seduterimasteev2, vettfalsopriceev2, Genre.HOUSE, managerfinto, arrfintoartista2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /*
                     * 	fine modifiche di Loris
                     *
                     */



                    // Azione da eseguire quando il pulsante "Login" viene premuto
                    if(loggedManager != null){
                        System.out.println("Hai cliccato il pulsante Login come gestore");
                        ArrayList<Event> arrayListEvent = loggedManager.getEventlist();
                        ArrayList<Notification> arrayListNotification = loggedManager.getNotification();
                        ManagerView managerView = new ManagerView(loggedManager.getName(),arrayListNotification,arrayListEvent);
                        //managerView.setEventsforTableev(managerfinto);
                        //managerView.reSetBars();

                        ConnectedUser.getInstance().setUser(loggedManager);
                        ConnectedUser.getInstance().setHome(managerView);
                        ConnectedUser.getInstance().setLoginView(loginView);
                        //
                        ManagerController managerController = new ManagerController(mainstage, managerView, loginView);
                        mainstage.setScene(managerView); // Imposta la scena SignUpView sulla stage principale }
                    }
                    else {
                        loginView.setErrorLabel();
                        System.out.println("Email o password errate");}

                }// devi mettere managerview anche all'interno del costruttore



                // Imposta l'handler sull'azione del pulsante "Registrati"
            }

        };

        loginView.getLoginButton().setOnAction(goToCustomerorManagerView);


    }

}
