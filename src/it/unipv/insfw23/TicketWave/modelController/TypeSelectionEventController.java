package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.ManagerView;
import it.unipv.insfw23.TicketWave.modelView.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.NewFestivalView;
import it.unipv.insfw23.TicketWave.modelView.TypeSelectionEventView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TypeSelectionEventController {
	Stage window;
	ManagerView managerview;
	TypeSelectionEventView typeselevview;
	
	public TypeSelectionEventController(Stage primarystage, ManagerView managerview, TypeSelectionEventView typeselevview) {
		window = primarystage;
		this.managerview = managerview;
		this.typeselevview = typeselevview;
		initComponents();
	}
	
	public void initComponents() {
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
//				window.setScene(managerview);
				System.out.println("a");
			}
		};
		
		typeselevview.getAbortButton().setOnMouseClicked(abortButton);
		
		
		EventHandler<MouseEvent> forwardButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				
				System.out.println("o");
				
				if(typeselevview.getConcertoRadioButton().isSelected()) {
					NewConcertView newconc = new NewConcertView();
					//crea controller
					window.setScene(newconc);
				}else if(typeselevview.getFestivalRadioButton().isSelected()) {
					NewFestivalView newfest = new NewFestivalView();
					window.setScene(newfest);
				}
			}
		};
		
		typeselevview.getForwardButton().setOnMouseClicked(forwardButton);
	}
}
