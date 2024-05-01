package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewOtherView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewOtherController {
	Stage window;
	NewOtherView newotherview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public NewOtherController(Stage primarystage, NewOtherView newotherview, SelectionNewEventTypeView typeselevview, Manager loggedmanager) {
		window = primarystage;
		this.newotherview = newotherview;
		this.typeselevview = typeselevview;
		this.loggedmanager = loggedmanager;
		initComponents();
	}
	
	public void initComponents() {
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
				
			}
		};
		
		newotherview.getAbortButton().setOnMouseClicked(abortButton);
		
		EventHandler<MouseEvent> forwardButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				
			}
		};
		
		newotherview.getForwardButton().setOnMouseClicked(forwardButton);
	}
}
