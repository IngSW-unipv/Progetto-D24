package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewConcertController {
	
	Stage window;
	NewConcertView newconcview;
	TypeSelectionEventView typeselevview;
	
	public NewConcertController(Stage primarystage, NewConcertView newconcview, TypeSelectionEventView typeselevview) {
		window = primarystage;
		this.newconcview = newconcview;
		this.typeselevview = typeselevview;
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
		
		newconcview.getAbortButton().setOnMouseClicked(abortButton);
	}
}
