package it.unipv.insfw23.TicketWave.modelController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResearchController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.LoginView;
import it.unipv.insfw23.TicketWave.modelView.ManagerView;
import it.unipv.insfw23.TicketWave.modelView.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ManagerController {
	
	Stage window;
	ManagerView managerview;
	LoginView logview;
//	Manager managerlogged;
	
	public ManagerController(Stage primarystage, ManagerView managerview, LoginView logview/*, Manager managerlogged*/) {
		window = primarystage;
		this.managerview = managerview;
		this.logview = logview;
//		this.managerlogged = managerlogged;
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
				TypeSelectionEventView typesel = new TypeSelectionEventView();
				TypeSelectionEventController typeselectioneventview = new TypeSelectionEventController(window, managerview, typesel/*, loggedmanager*/);
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
				StatisticControllerType statcontrtype = new StatisticControllerType(window, typestatsview/*, loggedmanager*/);
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
				int[] card = {2,6,5,1};
				ArrayList<Event> arraylistevent = new ArrayList<>();
				LocalDate data = LocalDate.of(1980, 12, 20);
				Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","passwd",2,card,arraylistevent,5,1,data,4);
				int[] vettfalsoprice = {5};
				ArrayList<String> arrfintoartista = new ArrayList<>();
				arrfintoartista.add("califano");
				Concert eventofinto = new Concert(12,"reunion","busto arstizio","via as",Province.LIVORNO,2,1,vettfalsoprice,Genre.INDIE,managerfinto,arrfintoartista,"califano");
				System.out.println(managerview.getTableev().getSelectionModel().getSelectedItem());
				//costruttore view
				TicketPageView tic = new TicketPageView();
				//costruttore controller
				BuyTicketController buyticketcontroller = new BuyTicketController(window, tic, eventofinto);
				//metodo che setta upperbar manager
				//opacita
				//
				window.setScene(tic);
			}
		};
		
		managerview.getTableev().setOnMouseClicked(openevent);;
	}
	
	
}
