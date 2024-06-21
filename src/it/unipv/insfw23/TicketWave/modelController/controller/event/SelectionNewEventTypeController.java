package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelView.event.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.event.NewFestivalView;
import it.unipv.insfw23.TicketWave.modelView.event.NewOtherView;
import it.unipv.insfw23.TicketWave.modelView.event.NewTheatreView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class represents the controller that manages all the {@link javafx.scene.control.Button} selected in {@link SelectionNewEventTypeView} and allows 
 * to continue in the {@link Event} creation process.
 */
public class SelectionNewEventTypeController {
	private Stage window;
	private ManagerView managerView;
	private SelectionNewEventTypeView selNewEvTypeView;

	/**
	 * This constructor takes as input the current UI , i.e. a {@link SelectionNewEventTypeView}, the previous UI, that is a {@link ManagerView}, the program 
	 * Stage and calls an initialization method
	 * @param primaryStage the {@link Stage} of this program
	 * @param managerView a {@link ManagerView}
	 * @param selNewEvTypeView a {@link SelectionNewEventTypeView}, the controlled one by this controller
	 */
	public SelectionNewEventTypeController(Stage primaryStage, ManagerView managerView, SelectionNewEventTypeView selNewEvTypeView) {
		window = primaryStage;
		this.managerView = managerView;
		this.selNewEvTypeView = selNewEvTypeView;
		initComponents();
	}
	
	/**
	 * This method has two {@link EventHandler}s associated with the {@link SelectionNewEventTypeView} buttons.
	 * 
	 * abortButton EventHandler: if you click on the abort button you return to the {@link ManagerView}
	 * 
	 * forwardButton EventHandler: if you click on the forward button, depending on which {@link RadioButton} is selected you can go to the {@link NewConcertView}, 
	 * to the {@link NewFestivalView}, to the {@link NewTheatreView} or to the {@link NewOtherView}
	 */
	public void initComponents() {
		
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {			
			@Override
			public void handle(MouseEvent event) {
				managerView.reSetBars();
				window.setScene(managerView);	
			}
		};		
		selNewEvTypeView.getAbortButton().setOnMouseClicked(abortButton);
		
		
		EventHandler<MouseEvent> forwardButton = new EventHandler<>() {			
			@Override
			public void handle(MouseEvent arg0) {
				// sequenza di if che crea la view corretta a seconda della tipologia di evento selezionato
				if(selNewEvTypeView.getConcertoRadioButton().isSelected()) {
					NewConcertView newconc = new NewConcertView();
					NewConcertController newconccontroller = new NewConcertController(window, newconc, selNewEvTypeView);
					window.setScene(newconc);
				}else if(selNewEvTypeView.getFestivalRadioButton().isSelected()) {
					NewFestivalView newfest = new NewFestivalView();
					NewFestivalController newfestcontroller = new NewFestivalController(window, newfest, selNewEvTypeView);
					window.setScene(newfest);
				}else if(selNewEvTypeView.getTeathreRadioButton().isSelected()) {
					NewTheatreView newtheatre = new NewTheatreView();
					NewTheatreController newtheatrecontroller = new NewTheatreController(window, newtheatre, selNewEvTypeView);
					window.setScene(newtheatre);
				}else if(selNewEvTypeView.getOtherRadioButton().isSelected()) {
					NewOtherView newother = new NewOtherView();
					NewOtherController  newothercontroller = new NewOtherController(window, newother, selNewEvTypeView);
					window.setScene(newother);
				}
			}
		};
		selNewEvTypeView.getForwardButton().setOnMouseClicked(forwardButton);

	}
	
}
