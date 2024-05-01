package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.event.NewFestivalView;
import it.unipv.insfw23.TicketWave.modelView.event.NewOtherView;
import it.unipv.insfw23.TicketWave.modelView.event.NewTheatreView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectionNewEventTypeController {
	Stage window;
	ManagerView managerview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public SelectionNewEventTypeController(Stage primarystage, ManagerView managerview, SelectionNewEventTypeView typeselevview, Manager loggedmanager) {
		window = primarystage;
		this.managerview = managerview;
		this.typeselevview = typeselevview;
		this.loggedmanager = loggedmanager;
		initComponents();
	}
	
	public void initComponents() {
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				managerview.reSetBars();
				window.setScene(managerview);
				
			}
		};
		
		typeselevview.getAbortButton().setOnMouseClicked(abortButton);
		
		
		EventHandler<MouseEvent> forwardButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				
				System.out.println("o");
				
				if(typeselevview.getConcertoRadioButton().isSelected()) {
					NewConcertView newconc = new NewConcertView();
					NewConcertController newconccontroller = new NewConcertController(window, newconc, typeselevview, loggedmanager);
					window.setScene(newconc);
				}else if(typeselevview.getFestivalRadioButton().isSelected()) {
					NewFestivalView newfest = new NewFestivalView();
					NewFestivalController newfestcontroller = new NewFestivalController(window, newfest, typeselevview, loggedmanager);
					window.setScene(newfest);
				}else if(typeselevview.getTeathreRadioButton().isSelected()) {
					NewTheatreView newtheatre = new NewTheatreView();
					NewTheatreController newtheatrecontroller = new NewTheatreController(window, newtheatre, typeselevview, loggedmanager);
					window.setScene(newtheatre);
				}else if(typeselevview.getOtherRadioButton().isSelected()) {
					NewOtherView newother = new NewOtherView();
					NewOtherController  newothercontroller = new NewOtherController(window, newother, typeselevview, loggedmanager);
					window.setScene(newother);
				}
			}
		};
		
		typeselevview.getForwardButton().setOnMouseClicked(forwardButton);
	}

}
