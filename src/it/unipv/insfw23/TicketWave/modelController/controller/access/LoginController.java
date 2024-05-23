package it.unipv.insfw23.TicketWave.modelController.controller.access;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
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
    private SignUpView signUpView;
    private CustomerView customerview;

    private LoginView loginView;
    private ManagerView managerView;


    public LoginController(Stage mainstage, SignUpView signUpView, CustomerView customerview, LoginView loginView,ManagerView managerView) {
        this.mainstage = mainstage;
        this.signUpView = signUpView;
        this.customerview = customerview;
        this.loginView = loginView;
        this.managerView=managerView;

        initComponents();
    }

    public void initComponents() {


        EventHandler<ActionEvent> goToSignUpView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // Azione da eseguire quando il pulsante "Registrati" viene premuto
                System.out.println("Hai cliccato il pulsante Registrati");
                signUpView = new SignUpView();
                SignUpController signUpController = new SignUpController(mainstage, signUpView, customerview, loginView);
                signUpView.reSetBars();
                mainstage.setScene(signUpView); // Imposta la scena SignUpView sulla stage principale
            }
        };

        loginView.getRegButton().setOnAction(goToSignUpView); // Imposta l'handler sull'azione del pulsante "Registrati"

        EventHandler<ActionEvent> goToCustomerorManagerView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (loginView.getCustomerRadioButton().isSelected()) {

                    /*creazione customer ed evento per poi creare vari biglietti e fare delle verifiche
                     *
                     * */

                    //customer esempio

                    Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
                    ArrayList<Ticket> tickets= new ArrayList<>();
                    Customer customer=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.BARI,favoriteGenre, 100,tickets);
                    System.out.println("Hai cliccato il pulsante Login come cliente");
                    customerview = new CustomerView();
                    IPaymentAdapter paymentpaypal;


                    CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                    customerview.reSetBars();

                    //
                    //ATTENZIONE, QUI VA LA CHIAMATA AL DAO
                    //
                    ConnectedUser.getInstance().setUser(customer);
                    ConnectedUser.getInstance().setHome(customerview);
                    ConnectedUser.getInstance().setLoginView(loginView);
                    //
                    mainstage.setScene(customerview); // Imposta la scena SignUpView sulla stage principale
                }
                else if (loginView.getManagerRadioButton().isSelected()) {

                    /*	modifiche fatte da Loris per simulare la creazione di un manager senza eventi, simulare la creazione di un evento
                     * 	da parte di questo manager e simulare aggiunta di questo evento al suo arraylist di eventi
                     * 	cosi da lavorare con un ipotetico manager che ha pubblicato degli eventi
                     * */


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
                    Time time = Time.valueOf("19:30:00");
                    //Concert eventofinto2 = new Concert(12,"reunion","busto arstizio",dataev2, "via dei matti ,0", Province.LIVORNO,300,2, seduterimasteev2, vettfalsopriceev2,Genre.INDIE,managerfinto,arrfintoartista2);

                    try {
                        managerfinto.createConcert(12, "reunion", "milano","discoteca",dataev1,time,Province.AGRIGENTO,Genre.HOUSE, 1000,3,seduterimasteev2,ticketsoldev2,vettfalsopriceev2,managerfinto,"U2","BELLO");
                                //managerfinto.createConcert(14, "festino", "Rozzano", dataev2, "stadio dell'albero", Province.MILANO, 10000, 3, seduterimasteev2, vettfalsopriceev2, Genre.HOUSE, managerfinto, arrfintoartista2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /*
                     * 	fine modifiche di Loris
                     * */


                    // Azione da eseguire quando il pulsante "Login" viene premuto
                    System.out.println("Hai cliccato il pulsante Login come gestore");
                    arraylistevent = managerfinto.getEventlist();
                    arrayListNotification = managerfinto.getNotification();
                    managerView = new ManagerView("Marco",arrayListNotification,arraylistevent);
                    //managerView.setEventsforTableev(managerfinto);
                    //managerView.reSetBars();
                    //ATTENZIONE, QUI VA LA CHIAMATA AL DAO
                    //
                    ConnectedUser.getInstance().setUser(managerfinto);
                    ConnectedUser.getInstance().setHome(managerView);
                    ConnectedUser.getInstance().setLoginView(loginView);
                    //
                    ManagerController managerController = new ManagerController(mainstage, managerView, loginView);
                    mainstage.setScene(managerView); // Imposta la scena SignUpView sulla stage principale }
                }// devi mettere managerview anche all'interno del costruttore



                // Imposta l'handler sull'azione del pulsante "Registrati"
            }

        };

        loginView.getLoginButton().setOnAction(goToCustomerorManagerView);


    }

}
