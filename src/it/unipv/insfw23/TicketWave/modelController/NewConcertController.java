package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewConcertController {
	
	Stage window;
	NewConcertView newconcview;
	TypeSelectionEventView typeselevview;
//	Manager loggedmanager;
	
	public NewConcertController(Stage primarystage, NewConcertView newconcview, TypeSelectionEventView typeselevview/*, loggedmanager*/) {
		window = primarystage;
		this.newconcview = newconcview;
		this.typeselevview = typeselevview;
//		this.loggedmanager = loggedmanager;
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
		
		EventHandler<MouseEvent> confirmButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				System.out.println(newconcview.getNameTextField());
			}
		};
		
		newconcview.getConfirmButton().setOnMouseClicked(confirmButton);
	}
}
