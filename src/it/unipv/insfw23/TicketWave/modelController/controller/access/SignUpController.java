package it.unipv.insfw23.TicketWave.modelController.controller.access;

import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

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


    public SignUpController(Stage mainstage, SignUpView signUpView, CustomerView customerview, LoginView loginView) {

        this.signUpView = signUpView;
        this.mainstage = mainstage;
        this.customerview = customerview;
        this.loginView = loginView;
        // this.subscriptionSelectionView= subscriptionSelectionView;
        initComponents();

    }

    public void initComponents() {

        EventHandler<ActionEvent> goToLoginView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Azione da eseguire quando il pulsante "Torna Indietro" viene premuto
                System.out.println("Hai cliccato il pulsante Torna Indietro");
                //loginView= new LoginView();
                LoginController loginController= new LoginController(mainstage,signUpView,customerview,loginView,managerView);
                loginView.reSetBars();
                mainstage.setScene(loginView.getScene()); // Imposta la scena LoginView sullo Stage principal

            }
        };

        signUpView.getBackButton().setOnAction(goToLoginView);

        EventHandler<ActionEvent> goToSelection = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (signUpView.getCustomerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante registrati  come cliente");
                    customerview = new CustomerView();
                    CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                    customerview.reSetBars();

                    //set del customer, CHIAMATA AL DAO PER LA REGISTRAZIONE
                    Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
                    ArrayList<Ticket> tickets= new ArrayList<>();
                    Customer customer=new Customer("Mario","Rossi","2000-10-10","mariorossi@gmail.com","123",Province.BARI,favoriteGenre, 100,tickets);

                    ConnectedUser.getInstance().setUser(customer);
                    ConnectedUser.getInstance().setHome(customerview);
                    ConnectedUser.getInstance().setLoginView(loginView);

                    //

                    mainstage.setScene(customerview);
                    // Imposta la scena SignUpView sulla stage principale
                } else if (signUpView.getManagerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante registrati  come gestore");
                    subscriptionSelectionView = new SubscriptionSelectionView();
                    SubscriptionSelectionController subscriptionSelectionController = new SubscriptionSelectionController(mainstage,subscriptionSelectionView,signUpView);
                    subscriptionSelectionView.reSetBars();

                    //set del manager, CHIAMATA AL DAO PER LA REGISTRAZIONE
                    ArrayList<Notification> arrayListNotification = new ArrayList<>();
                    ArrayList<Event> arraylistevent = new ArrayList<>();
                    LocalDate datasub = LocalDate.of(2024, 02, 25);
                    Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","password1234", Province.CREMONA, "2324523432451420", arraylistevent,5,1,datasub,0);

                    ConnectedUser.getInstance().setUser(managerfinto);
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
