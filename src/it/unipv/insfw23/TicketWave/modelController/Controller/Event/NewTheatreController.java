package it.unipv.insfw23.TicketWave.modelController.Controller.Event;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewTheatreView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewTheatreController {
	Stage window;
	NewTheatreView newtheatreview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public NewTheatreController(Stage primarystage, NewTheatreView newtheatreview, SelectionNewEventTypeView typeselevview, Manager loggedmanager) {
		window = primarystage;
		this.newtheatreview = newtheatreview;
		this.typeselevview = typeselevview;
		this.loggedmanager = loggedmanager;
		initComponents();
	}
	
	public void initComponents() {
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
			}
		};
		
		newtheatreview.getAbortButton().setOnMouseClicked(abortButton);
	}
}
