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

    public LoginController(Stage mainstage,SignUpView signUpView, CustomerView customerView, LoginView loginView){
        this.mainstage= mainstage;
        this.signUpView= signUpView;
        this.customerview= customerView;
        this.loginView= loginView;
        initComponents();
    }

    public void initComponents() {

                EventHandler<ActionEvent> goToSignUpView = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // Azione da eseguire quando il pulsante "Registrati" viene premuto
                        System.out.println("Hai cliccato il pulsante Registrati");
                        mainstage.setScene(signUpView); // Imposta la scena SignUpView sulla stage principale
                    }
                };

                loginView.getRegButton().setOnAction(goToSignUpView); // Imposta l'handler sull'azione del pulsante "Registrati"
            }

}

                // Azione da eseguire quando il pulsante viene premuto
               /* System.out.println("Hai cliccato il bottone registrati");
               SignUpView signSelectionView = new SignUpView();
               SignUpController signUpController = new SignUpController(mainstage, signSelectionView);
                mainstage.setScene(signSelectionView);
            }
        };
       loginView.getLoginButton().setOnMouseClicked(goToSignUpSelection);

        loginButton.setOnAction(e -> {

            String email = emailField.getText();
            String password = passwordField.getText();
            if (customerRadioButton.isSelected()) {
                System.out.println("Login come utente con username: " + email + " e password: " + password);
            } else if (managerRadioButton.isSelected()) {
                System.out.println("Login come gestore con username: " + email + " e password: " + password);
            }
        });*/





