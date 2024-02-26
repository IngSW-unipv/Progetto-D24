package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.NewTheatreView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewTheatreController {
	Stage window;
	NewTheatreView newtheatreview;
	TypeSelectionEventView typeselevview;
	
	public NewTheatreController(Stage primarystage, NewTheatreView newtheatreview, TypeSelectionEventView typeselevview) {
		window = primarystage;
		this.newtheatreview = newtheatreview;
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
		
		newtheatreview.getAbortButton().setOnMouseClicked(abortButton);
	}
}
