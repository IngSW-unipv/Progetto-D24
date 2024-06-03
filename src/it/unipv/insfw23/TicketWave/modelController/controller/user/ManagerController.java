package it.unipv.insfw23.TicketWave.modelController.controller.user;

import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelController.controller.event.SelectionNewEventTypeController;
import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.controller.statistics.TypeStatsController;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapType;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
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

public class ManagerController {
	Stage mainStage;
	ManagerView managerview;
	LoginView logview;
	Manager loggedmanager;
	IResettableScene backScene;
	
	public ManagerController(Stage primarystage, ManagerView managerview, LoginView logview) {
		mainStage = primarystage;
		this.managerview = managerview;
		this.logview = logview;
		this.loggedmanager = (Manager) ConnectedUser.getInstance().getUser();
		initComponents();
	}
	
	public void initComponents() {
		
		EventHandler<MouseEvent> logoutButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("logout");
//				LoginView logview = new LoginView();
//				SignUpView signupview = new SignUpView();
//				LoginController logcon = new LoginController(window, signupview, null, logview);
				logview.reSetBars();
				//
				ConnectedUser.getInstance().unlogUser();
				ConnectedUser.getInstance().setHome(null);
				ConnectedUser.getInstance().setLoginView(null);
				//
				logview.makeBlankPage();
				mainStage.setScene(logview.getScene());
			}
		};
		managerview.getLogoutButton().setOnMouseClicked(logoutButton);

		
		EventHandler<MouseEvent> newEventButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				if (loggedmanager.anotherEvents()) {
					//crea controller per typeselectionevent
					SelectionNewEventTypeView typesel = new SelectionNewEventTypeView();
					SelectionNewEventTypeController typeselectioneventview = new SelectionNewEventTypeController(mainStage, managerview, typesel);
					mainStage.setScene(typesel);

				} else {

					NoMoreEventsPopup.getIstance().setX(mainStage.getX() + mainStage.getWidth() - NoMoreEventsPopup.getIstance().getWidth() - 360);
					NoMoreEventsPopup.getIstance().setY(mainStage.getY() + 95);
					NoMoreEventsPopup.getIstance().show(mainStage);
					NoMoreEventsPopupController popupController = new NoMoreEventsPopupController(mainStage, NoMoreEventsPopup.getIstance().getBackButton(), NoMoreEventsPopup.getIstance().getSubscriptionButton(), managerview);
				}
			}
		};
		managerview.getNewEventButton().setOnMouseClicked(newEventButton);

		EventHandler<MouseEvent> searchButton = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("vai alla ricerca");
				ResearchView researchview = new ResearchView();
				ResearchController rescontroller = new ResearchController(mainStage, researchview);
				mainStage.setScene(researchview);
			}
		};
		managerview.getSearchButton().setOnMouseClicked(searchButton);



		EventHandler<ActionEvent> statsButtonHandler = new EventHandler<>(){
			@Override
			public void handle(ActionEvent actionEvent){
				//mi viene passato il manager

				//creo una nuova classe di statistiche, a cui passo il manager
				StatisticsHandler statDominio = new StatisticsHandler();
				WrapType typeRes = statDominio.typeStats(loggedmanager);
				//Al costruttore di type view, devo passare i risultati del metodo, e la classe di statistiche di dominio
				TypeStatsView typeView = new TypeStatsView(typeRes);
				TypeStatsController typeController = new TypeStatsController(mainStage, typeView);
				mainStage.setScene(typeView);
			}
		};
		UpperBar.getIstance().getStatsButton().setOnAction(statsButtonHandler);


		EventHandler<MouseEvent> profileButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				managerview.reSetBars();
				mainStage.setScene(managerview);
				
			}
		};
		
		managerview.getProfileButton().setOnMouseClicked(profileButton);


		EventHandler<MouseEvent> openevent = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
//				creo un manager finto per creare un evento finto
				
			/*	creazione di un finto manager e un finto evento
			/	int[] card = {2,6,5,1};
			/	int[] seduteRImaste = {20, 20};
			/	ArrayList<Event> arraylistevent = new ArrayList<>();
			/	LocalDate data = LocalDate.of(2024, 12, 20);
			/	Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","passwd",2, "23245234324", arraylistevent,5,1,data,4);
			/	int[] vettfalsoprice = {5};
			/	ArrayList<String> arrfintoartista = new ArrayList<>();
			/	arrfintoartista.add("califano");
			/	Concert eventofinto = new Concert(12,"reunion","busto arstizio",data, "via dei matti ,0", Province.LIVORNO,2,1, seduteRImaste, vettfalsoprice,Genre.INDIE,managerfinto,arrfintoartista);
			*/
				System.out.println(managerview.getTableEv().getSelectionModel().getSelectedItem());
				//costruttore view
				TicketPageView tic = new TicketPageView();
				//costruttore controller
				TicketPageController buyticketcontroller = new TicketPageController(mainStage, tic, managerview.getTableEv().getSelectionModel().getSelectedItem(),managerview);
				//metodo che setta upperbar manager
				//opacita
				//
				mainStage.setScene(tic);
			}
		};
		managerview.getTableEv().setOnMouseClicked(openevent);

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

