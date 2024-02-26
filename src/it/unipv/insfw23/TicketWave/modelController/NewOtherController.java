package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.NewOtherView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewOtherController {
	
	Stage window;
	NewOtherView newotherview;
	TypeSelectionEventView typeselevview;
	
	public NewOtherController(Stage primarystage, NewOtherView newotherview, TypeSelectionEventView typeselevview) {
		window = primarystage;
		this.newotherview = newotherview;
		this.typeselevview = typeselevview;
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
