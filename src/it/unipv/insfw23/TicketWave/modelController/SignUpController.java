package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.LoginView;
import it.unipv.insfw23.TicketWave.modelView.SignUpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SignUpController {
        private Stage mainstage;

        // view da considerare

        private SignUpView signUpView;
        private CustomerView customerview;
        private LoginView loginView;



    public SignUpController(Stage mainstage,SignUpView signUpView,CustomerView customerview, LoginView loginView){

            this.signUpView=signUpView;
            this.mainstage= mainstage;
            this.customerview=customerview;
            this.loginView=loginView;
            initComponents();

        }
    public void initComponents() {

        EventHandler<ActionEvent> goToLoginView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Azione da eseguire quando il pulsante "Torna Indietro" viene premuto
                System.out.println("Hai cliccato il pulsante Torna Indietro");
                mainstage.setScene(loginView.getScene()); // Imposta la scena LoginView sullo Stage principal
                loginView.reSetBars();
            }
        };

        signUpView.getBackButton().setOnAction(goToLoginView);

    EventHandler<ActionEvent> goToCustomerView = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            if(signUpView.getCustomerRadioButton().isSelected()){


                System.out.println("Hai cliccato il pulsante registrati  come cliente");
                CustomerView customerview= new CustomerView();
                CustomerController customerController= new CustomerController();
                customerview.reSetBars();
                mainstage.setScene(customerview); // Imposta la scena SignUpView sulla stage principale
            }/* else if(signUpView.getManagerRadioButton().isSelected()){


                // Azione da eseguire quando il pulsante "Registrati" viene premuto
                System.out.println("Hai cliccato il pulsante Login come gestore");
                customerview.reSetBars();
                mainstage.setScene(managerview); // Imposta la scena SignUpView sulla stage principale }*/
        }// devi mettere managerview anche all'interno del costruttore
    };
    signUpView.getSignUpButton().setOnAction(goToCustomerView);
}
}

