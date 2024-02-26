package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResearchController;
import it.unipv.insfw23.TicketWave.modelView.LoginView;
import it.unipv.insfw23.TicketWave.modelView.ManagerView;
import it.unipv.insfw23.TicketWave.modelView.SignUpView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManagerController {
	
	Stage window;
	ManagerView managerview;
	
	public ManagerController(Stage primarystage, ManagerView managerview) {
		window = primarystage;
		this.managerview = managerview;
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
//				window.setScene(logview);
			}
			
		};
		
		managerview.getLogoutButton().setOnMouseClicked(logoutButton);
		
		EventHandler<MouseEvent> newEventButton = new EventHandler<>() {

			@Override
			public void handle(MouseEvent event) {
				//crea controller per typeselectionevent
				TypeSelectionEventView typesel = new TypeSelectionEventView();
				TypeSelectionEventController typeselectioneventview = new TypeSelectionEventController(window, managerview, typesel);
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
				StatisticControllerType statcontrtype = new StatisticControllerType(window, typestatsview);
				window.setScene(typestatsview);
				
			}
		};
		
		managerview.getStatsButton().setOnMouseClicked(statsButton);
		
		EventHandler<MouseEvent> profileButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
//				window.setScene(managerview);
				
			}
		};
		
	}
	
	
}