package it.unipv.insfw23.TicketWave.modelController.Controller.Access;

import it.unipv.insfw23.TicketWave.modelController.Controller.Subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelController.Controller.User.CustomerController;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SignUpController {
    private Stage mainstage;

    // view da considerare

    private SignUpView signUpView;
    private ManagerView managerView;
    private CustomerView customerview;
    private LoginView loginView;
    private SubscriptionSelectionView subscriptionSelectionView;
    private PaymentSelectionView paymentSelectionView;


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
                    mainstage.setScene(customerview);
                    // Imposta la scena SignUpView sulla stage principale
                } else if (signUpView.getManagerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante registrati  come gestore");
                    subscriptionSelectionView = new SubscriptionSelectionView();
                    SubscriptionSelectionController subscriptionSelectionController = new SubscriptionSelectionController(mainstage,subscriptionSelectionView,paymentSelectionView);

                    subscriptionSelectionView.reSetBars();
                    mainstage.setScene(subscriptionSelectionView);
                    // Imposta la scena subscriptio sulla stage principale
                }
            }
        };
        signUpView.getSignUpButton().setOnAction(goToSelection);
    }
}
