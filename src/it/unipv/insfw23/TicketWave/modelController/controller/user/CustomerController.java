package it.unipv.insfw23.TicketWave.modelController.controller.user;

import it.unipv.insfw23.TicketWave.modelController.controller.access.LoginController;
import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;

import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
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
				//loginView = new LoginView();
				signUpView = new SignUpView();
				LoginController logcon = new LoginController(mainstage, loginView);
                loginView.reSetBars();
                //
                ConnectedUser.getInstance().unlogUser();
                ConnectedUser.getInstance().setHome(null);
                ConnectedUser.getInstance().setLoginView(null);
                //
                loginView.makeBlankPage();
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
       EventHandler<MouseEvent> openevent = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {

                //costruttore view
                TicketPageView tic = new TicketPageView();
                //costruttore controller
                TicketPageController buyticketcontroller = new TicketPageController(mainstage, tic,customerView.getTicketTab().getSelectionModel().getSelectedItem(),customerView);

                mainstage.setScene(tic);
            }
        };
        customerView.getTicketTab().setOnMouseClicked(openevent);

    }
}
