package it.unipv.insfw23.TicketWave.modelController.controller.user;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.modelController.controller.access.LoginController;
import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.access.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.SQLException;

/**
 * This controller manages all the buttons selected in {@link CustomerView}
 * EventHandler<MouseEvent> logoutButton : if you click on the logout button you
 * go back to the {@link LoginView}
 * EventHandler<MouseEvent> profileButton: if the profile button is clicked you go back to the
 * {@link CustomerView}
 * EventHandler<MouseEvent> searchButton: if the search button is clicked you go to the {@link ResearchView}
 * where you can buy search for new Events
 * EventHandler<MouseEvent> openTicket: if you click on one row of the ticketTableView you go to the
 * {@link  TicketPageView} where you can see the information about the ticket you bought
 */
public class CustomerController {
    private LoginView loginView;
    private Stage mainstage;
    private CustomerView customerView;
   // private SignUpView signUpView;
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
				LoginController logcon = new LoginController(mainstage, loginView);
                loginView.reSetBars();
                /*
                ConnectedUser.getInstance().unlogUser();
                ConnectedUser.getInstance().setHome(null);
                ConnectedUser.getInstance().setLoginView(null);
                */
                loginView.makeBlankPage();
                mainstage.setScene(loginView.getScene());
                ConnectedUser.getInstance().logoutMethod();
            }

        };
        customerView.getLogoutButton().setOnMouseClicked(logoutButton);


        EventHandler<MouseEvent> profileButton = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("go to profile");
                customerView.reSetBars();
                mainstage.setScene(customerView);

            }
        };
        customerView.getProfileButton().setOnMouseClicked(profileButton);


        EventHandler<MouseEvent> searchButton = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("go to research");
                ResearchView researchview = new ResearchView();
                ResearchController rescontroller = new ResearchController(mainstage, researchview);
                mainstage.setScene(researchview);
            }
        };
        customerView.getSearchButton().setOnMouseClicked(searchButton);

        EventHandler<MouseEvent> openTicket = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
//				creo un manager finto per creare un evento finto
                TicketPageView tic = new TicketPageView();
                
                try {
                EventDao eventDao = new EventDao();
                
                
               	System.out.println(customerView.getTicketTab().getSelectionModel().getSelectedItem().getIdEvent());
               	int idEvent = customerView.getTicketTab().getSelectionModel().getSelectedItem().getIdEvent();
               	
               	Event selectedEvent = eventDao.selectEvent(idEvent);
               	if(selectedEvent.getSeatsRemaining() == 0){
               		tic.setForNotBuyable();
               	}
                //costruttore controller

                TicketPageController buyticketcontroller = new TicketPageController(mainstage, tic, selectedEvent, customerView);
                    
                } catch(NullPointerException e){
                	/*per quando si clicca su una riga della tabella non popolata o quando si lascia lo scrollbar col mouse interno alla table senza avere
              	  	cliccato un ticket prima
                	 */
                	return;
                }catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                //metodo che setta upperbar manager
                //opacita
                //
                tic.setForNotBuyable();
                mainstage.setScene(tic);
            }
        };
        customerView.getTicketTab().setOnMouseClicked(openTicket);

    }
}
