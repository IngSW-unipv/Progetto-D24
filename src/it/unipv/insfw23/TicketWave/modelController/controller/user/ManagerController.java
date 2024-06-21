package it.unipv.insfw23.TicketWave.modelController.controller.user;

import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelController.controller.event.SelectionNewEventTypeController;
import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.controller.statistics.TypeStatsController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapType;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.statistics.TypeStatsView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import it.unipv.insfw23.TicketWave.modelView.user.NoMoreEventsPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
/**
 *  This class represents the controller that manages all the {@link javafx.scene.control.Button} selected in {@link ManagerView}
 */
public class ManagerController {
	private Stage mainStage;
	private ManagerView managerview;
	private LoginView logview;
	private Manager loggedmanager;

	private double xStageSize;
	private double yStageSize;
	
	/**
	 * This constructor take as input the current UI , a {@link ManagerView}, the {@link LoginView} and the program Stage. It calculates the 
	 * {@link NoMoreEventsPopup} position and calls an initialization method
	 * @param primarystage the {@link Stage} of this program
	 * @param managerview a {@link ManagerView}, the controlled one by this controller
	 * @param logview a {@link LoginView}
	 */
	public ManagerController(Stage primarystage, ManagerView managerview, LoginView logview) {
		mainStage = primarystage;
		this.managerview = managerview;
		this.logview = logview;
		this.loggedmanager = (Manager) ConnectedUser.getInstance().getUser();
		this.xStageSize = mainStage.getX() + mainStage.getWidth() - NoMoreEventsPopup.getIstance().getWidth() - 360;
		this.yStageSize = mainStage.getY() + 95;
		initComponents();
	}
	
	/**
	 * This method has seven {@link EventHandler}s associated with the {@link ManagerView} buttons.
	 * 
	 * logoutButton EventHandler: if you click on the logout button you go back to the {@link LoginView}.
	 * 
	 * newEventButton EventHandler: if you click on the newEvent button, this handler checks if you can create new events: if you can you go to {@link SelectionNewEventTypeView} 
	 * where you can continue with the event creation process else the handler sets the {@link NoMoreEventsPopup} and denies you to continue.
	 * 
	 * searchButton EventHandler: if you click on the search button you go to the {@link ResearchView} where you can search {@link Event}s.
	 * 
	 * statsButton EventHandler: if you click the stats button you go to the {@link TypeStatsView} where are displayed the sales statistics data.
	 * 
	 * profileButton EventHandler: if you click on the profile button you go back to the {@link ManagerView}.
	 * 
	 * openEvent EventHandler: if you click on one row of the eventsTable you go to the {@link TicketPageView} where you can see the information about the
	 * {@link Event} you published.
	 * 
	 * subscriptionButton EventHandler: if you click on the subscription button you go to the {@link SubscriptionSelectionView} where you can change your
	 * subscription
	 */
	public void initComponents() {
		
		EventHandler<MouseEvent> logoutButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("logout");
				logview.reSetBars();
				logview.makeBlankPage();
				mainStage.setScene(logview);
				ConnectedUser.getInstance().logoutMethod();
			}
		};
		managerview.getLogoutButton().setOnMouseClicked(logoutButton);

		
		EventHandler<MouseEvent> newEventButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				if (loggedmanager.anotherEvents()) {
					SelectionNewEventTypeView typesel = new SelectionNewEventTypeView();
					SelectionNewEventTypeController typeselectioneventview = new SelectionNewEventTypeController(mainStage, managerview, typesel);
					mainStage.setScene(typesel);

				} else {

					if(loggedmanager.getSubscription()==-1){
						NoMoreEventsPopup.getIstance().setMessageLabel("Abbonamento scaduto! Prosegui per rinnovare");
					}
					else{
						NoMoreEventsPopup.getIstance().setMessageLabel("Hai raggiunto il numero massimo di eventi per \nquesto mese! \nSe non vuoi aspettare, cambia subito il tuo abbonamento!");
					}
					NoMoreEventsPopup.getIstance().setX(xStageSize);
					NoMoreEventsPopup.getIstance().setY(yStageSize);
					NoMoreEventsPopup.getIstance().show(mainStage);
					NoMoreEventsPopupController popupController = new NoMoreEventsPopupController(mainStage, NoMoreEventsPopup.getIstance().getBackButton(), NoMoreEventsPopup.getIstance().getSubscriptionButton(), managerview);
				}
			}
		};
		managerview.getNewEventButton().setOnMouseClicked(newEventButton);


		EventHandler<MouseEvent> searchButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("going to research");
				ResearchView researchview = new ResearchView();
				ResearchController rescontroller = new ResearchController(mainStage, researchview);
				mainStage.setScene(researchview);
			}
		};
		managerview.getSearchButton().setOnMouseClicked(searchButton);



		EventHandler<ActionEvent> statsButton = new EventHandler<>(){
			@Override
			public void handle(ActionEvent actionEvent){
				//creo una nuova classe di statistiche, a cui passo il manager
				StatisticsHandler statDominio = new StatisticsHandler();
				WrapType typeRes = statDominio.typeStats(loggedmanager);
				//Al costruttore di type view, devo passare i risultati del metodo, e la classe di statistiche di dominio
				TypeStatsView typeView = new TypeStatsView(typeRes);
				TypeStatsController typeController = new TypeStatsController(mainStage, typeView);
				mainStage.setScene(typeView);
			}
		};
		UpperBar.getIstance().getStatsButton().setOnAction(statsButton);


		EventHandler<MouseEvent> profileButton = new EventHandler<>() {		
			@Override
			public void handle(MouseEvent event) {
				managerview.reSetBars();
				mainStage.setScene(managerview);				
			}
		};	
		managerview.getProfileButton().setOnMouseClicked(profileButton);


		EventHandler<MouseEvent> openEvent = new EventHandler<>() {			
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Event selected: "+managerview.getTableEv().getSelectionModel().getSelectedItem());
				//costruzione view
				TicketPageView tic = new TicketPageView();
				tic.setForNotBuyable();
				//costruzione controller
				try {
					TicketPageController buyticketcontroller = new TicketPageController(mainStage, tic, managerview.getTableEv().getSelectionModel().getSelectedItem(),managerview);
				} catch(NullPointerException e){
                	/* per quando si clicca su una riga della tabella non popolata o quando si lascia lo scrollbar col mouse interno alla table senza avere
              	  	 * cliccato un ticket prima e quindi non devono avvenire cambiamenti di view o altro
                	 */
                	return;
				}
				mainStage.setScene(tic);
			}
		};
		managerview.getTableEv().setOnMouseClicked(openEvent);

		
		EventHandler<MouseEvent> subscriptionButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				SubscriptionSelectionView subView = new SubscriptionSelectionView();
				SubscriptionSelectionController subController = new SubscriptionSelectionController(mainStage, subView, managerview);
				mainStage.setScene(subView);
			}
		};
		managerview.getSubButton().setOnMouseClicked(subscriptionButton);
	}
}

