package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class LoginController {

    private Stage mainstage;

    // view da considerare
    private SignUpView signUpView;
    private CustomerView customerview;
    private LoginView loginView;


    public LoginController(Stage mainstage, SignUpView signUpView, CustomerView customerView, LoginView loginView) {
        this.mainstage = mainstage;
        this.signUpView = signUpView;
        this.customerview = customerView;
        this.loginView = loginView;

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

        EventHandler<ActionEvent> goToCustomerView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (loginView.getCustomerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante Login come cliente");
                    customerview = new CustomerView();
                    CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                    customerview.reSetBars();
                    mainstage.setScene(customerview); // Imposta la scena SignUpView sulla stage principale
                }
                else if (loginView.getManagerRadioButton().isSelected()) {


                    // Azione da eseguire quando il pulsante "Registrati" viene premuto
                    System.out.println("Hai cliccato il pulsante Login come gestore");
                    ManagerView managerView = new ManagerView();
                    ManagerController managerController = new ManagerController(mainstage, managerView, loginView);
                    managerView.reSetBars();
                    mainstage.setScene(managerView); // Imposta la scena SignUpView sulla stage principale }
                }// devi mettere managerview anche all'interno del costruttore



        // Imposta l'handler sull'azione del pulsante "Registrati"
            }

        };
       
        loginView.getLoginButton().setOnAction(goToCustomerView);
    }    
        
}
