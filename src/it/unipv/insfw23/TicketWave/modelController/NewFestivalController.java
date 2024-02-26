package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.NewFestivalView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewFestivalController {

	Stage window;
	NewFestivalView newfestview;
	TypeSelectionEventView typeselevview;
	
	public NewFestivalController(Stage primarystage, NewFestivalView newfestview, TypeSelectionEventView typeselevview) {
		window = primarystage;
		this.newfestview = newfestview;
		this.typeselevview = typeselevview;
		initComponents();
	}
	
	public void initComponents() {
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
				System.out.println("34");
			}
		};
		
		newfestview.getAbortButton().setOnMouseClicked(abortButton);
	}
	
	
}
