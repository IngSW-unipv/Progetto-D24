package it.unipv.insfw23.TicketWave.modelController.controller.user;

import it.unipv.insfw23.TicketWave.modelController.controller.access.LoginController;
import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;

import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
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
    Customer loggedCustomer;

    public CustomerController(Stage mainstage, CustomerView customerView, LoginView loginView) {
        this.mainstage = mainstage;
        this.customerView = customerView;
        this.loginView = loginView;
        this.loggedCustomer = (Customer) ConnectedUser.getInstance().getUser();
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
       /*EventHandler<MouseEvent> openevent = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {

                //costruttore view
                TicketPageView tic = new TicketPageView();
                //costruttore controller
                TicketPageController buyticketcontroller = new TicketPageController(mainstage, tic,customerView.getTicketTab().getSelectionModel().getSelectedItem(),customerView);

                mainstage.setScene(tic);
            }
        };
        customerView.getTicketTab().setOnMouseClicked(openevent);*/
        EventHandler<MouseEvent> openTicket = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
//				creo un manager finto per creare un evento finto

			/*	creazione di un finto manager e un finto evento
			/	int[] card = {2,6,5,1};
			/	int[] seduteRImaste = {20, 20};
			/	ArrayList<Event> arraylistevent = new ArrayList<>();
			/	LocalDate data = LocalDate.of(2024, 12, 20);
			/	Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","passwd",2, "23245234324", arraylistevent,5,1,data,4);
			/	int[] vettfalsoprice = {5};
			/	ArrayList<String> arrfintoartista = new ArrayList<>();
			/	arrfintoartista.add("califano");
			/	Concert eventofinto = new Concert(12,"reunion","busto arstizio",data, "via dei matti ,0", Province.LIVORNO,2,1, seduteRImaste, vettfalsoprice,Genre.INDIE,managerfinto,arrfintoartista);
			*/
                System.out.println(customerView.getTicketTab().getSelectionModel().getSelectedItem());
                //costruttore view
                TicketPageView tic = new TicketPageView();
                //costruttore controller
                TicketPageController buyticketcontroller = new TicketPageController(mainstage, tic, customerView.getTicketTab().getSelectionModel().getSelectedItem(),customerView);
                //metodo che setta upperbar manager
                //opacita
                //
                mainstage.setScene(tic);
            }
        };
        customerView.getTicketTab().setOnMouseClicked(openTicket);

    }
}
