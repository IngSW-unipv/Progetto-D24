package it.unipv.insfw23.TicketWave.modelController.Controller.user;

import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResearchController;
import it.unipv.insfw23.TicketWave.modelView.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.LoginView;
import it.unipv.insfw23.TicketWave.modelView.ManagerView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResultResearchView;
import it.unipv.insfw23.TicketWave.modelView.SignUpView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerController {
    private LoginView loginView;
    private Stage mainstage;
    private CustomerView customerView;
    private SignUpView signUpView;
    private ManagerView managerView;

    public CustomerController(Stage mainstage, CustomerView customerView, LoginView loginView) {
        this.mainstage = mainstage;
        this.customerView = customerView;
        this.loginView = loginView;
        initComponents();
    }

    public void initComponents() {
        EventHandler<MouseEvent> logoutButton = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("logout");
//				LoginView logview = new LoginView();
//				SignUpView signupview = new SignUpView();
//				LoginController logcon = new LoginController(window, signupview, null, logview);
                loginView.reSetBars();
                mainstage.setScene(loginView.getScene());
            }

        };

        customerView.getLogoutButton().setOnMouseClicked(logoutButton);
        EventHandler<MouseEvent> profileButton = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("vai al profilo");
                customerView.reSetBars();
                mainstage.setScene(customerView);

            }
        };

        customerView.getProfileButton().setOnMouseClicked(profileButton);

        EventHandler<MouseEvent> searchButton = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("vai alla ricerca");
                ResearchView researchview = new ResearchView();
                ResearchController rescontroller = new ResearchController(mainstage, researchview);
                mainstage.setScene(researchview);
            }
        };
        customerView.getSearchButton().setOnMouseClicked(searchButton);


    }
}
