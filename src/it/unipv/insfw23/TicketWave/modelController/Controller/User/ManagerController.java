package it.unipv.insfw23.TicketWave.modelController.Controller.User;

import it.unipv.insfw23.TicketWave.modelController.Controller.BuyTicketController;
import it.unipv.insfw23.TicketWave.modelController.Controller.Event.SelectionNewEventTypeController;
import it.unipv.insfw23.TicketWave.modelController.Controller.Research.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.Controller.Statistics.TypeStatsController;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.statistics.TypeStatsView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;

public class ManagerController {
	Stage window;
	ManagerView managerview;
	LoginView logview;
	Manager managerlogged;
	
	public ManagerController(Stage primarystage, ManagerView managerview, LoginView logview, Manager managerlogged) {
		window = primarystage;
		this.managerview = managerview;
		this.logview = logview;
		this.managerlogged = managerlogged;
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
				logview.reSetBars();
				window.setScene(logview.getScene());
			}
			
		};
		
		managerview.getLogoutButton().setOnMouseClicked(logoutButton);
		
		EventHandler<MouseEvent> newEventButton = new EventHandler<>() {

			@Override
			public void handle(MouseEvent event) {
				//crea controller per typeselectionevent
				SelectionNewEventTypeView typesel = new SelectionNewEventTypeView();
				//SelectionNewEventTypeController typeselectioneventview = new SelectionNewEventTypeController(window, managerview, typesel/*, loggedmanager*/);
				window.setScene(typesel);
			}
			
		};
		
		managerview.getNewEventButton().setOnMouseClicked(newEventButton);
		
		EventHandler<MouseEvent> searchButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				System.out.println("vai alla ricerca");
				ResearchView researchview = new ResearchView();
				ResearchController rescontroller = new ResearchController(window, researchview);
				window.setScene(researchview);
			}
		};
		
		managerview.getSearchButton().setOnMouseClicked(searchButton);
		
		EventHandler<MouseEvent> statsButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				TypeStatsView typestatsview = new TypeStatsView();
				TypeStatsController statcontrtype = new TypeStatsController(window, typestatsview/*, loggedmanager*/);
				window.setScene(typestatsview);
				
			}
		};
		
		managerview.getStatsButton().setOnMouseClicked(statsButton);
		
		EventHandler<MouseEvent> profileButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				managerview.reSetBars();
				window.setScene(managerview);
				
			}
		};
		
		managerview.getProfileButton().setOnMouseClicked(profileButton);
		
		EventHandler<MouseEvent> openevent = new EventHandler<>() {
			
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
				System.out.println(managerview.getTableEv().getSelectionModel().getSelectedItem());
				//costruttore view
				TicketPageView tic = new TicketPageView();
				//costruttore controller
				//BuyTicketController buyticketcontroller = new BuyTicketController(window, tic, managerview.getTableev().getSelectionModel().getSelectedItem(), true);
				//metodo che setta upperbar manager
				//opacita
				//
				//window.setScene(tic);
			}
		};
		
		managerview.getTableEv().setOnMouseClicked(openevent);;
	}
}
