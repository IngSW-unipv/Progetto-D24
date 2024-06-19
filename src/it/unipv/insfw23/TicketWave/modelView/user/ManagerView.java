package it.unipv.insfw23.TicketWave.modelView.user;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManagerView extends Scene  implements IResettableScene {
	
//	Stage window;

	private Button logoutButton;
	private UpperBar upperbar;
	private LowerBar lowerbar;
	private BorderPane layout;
	private GridPane grid;
	private TableView<Event> eventsTable;
	private ObservableList<Event> evs;
	private TableView<Notification> notificationsTable;
	private ObservableList<Notification> nots;
	private Label name;
	private int currentSub;
	private Label currentSubLabel;
	private int counterCreatedEvents;
	private Label eventRemained;
	private Button subButton;
	
	private final int MAX_EVENTS_FOR_FREE_SUB = 1;
	private final int MAX_EVENTS_FOR_BASE_SUB = 5;
	
	
	public ManagerView(String name, ArrayList<Notification> nots, ArrayList<Event> evs, int currentSub, int counterCreatedEvents) {
		super(new BorderPane(), 1080, 600);
		this.name = new Label("Benvenuto, "+name);
		this.currentSub = currentSub;
		this.counterCreatedEvents = counterCreatedEvents;
		this.nots = FXCollections.observableArrayList(nots);
		this.evs = FXCollections.observableArrayList(evs);
		init();
	}

	
	public void init(){
//		window = primarystage;
//		window.setTitle("speriamo figa");
		
		layout = (BorderPane) getRoot();
		layout.setStyle("-fx-background-color: #91bad6;");

		
		grid = new GridPane();
		grid.setPadding(new Insets(50, 100, 50, 100));
		grid.setVgap(15);
		grid.setHgap(90);
		//grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		
		name.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
		GridPane.setConstraints(name, 0, 0);
		GridPane.setHalignment(name, HPos.LEFT);
		GridPane.setHgrow(name, Priority.SOMETIMES);
		
		
		
		logoutButton = new Button("Logout");
		GridPane.setConstraints(logoutButton, 3, 0);
		GridPane.setHalignment(logoutButton, HPos.RIGHT);
		GridPane.setHgrow(logoutButton, Priority.SOMETIMES);
		
		
		
		Label not = new Label("Notifiche");
		not.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
		GridPane.setConstraints(not, 0, 6);
		GridPane.setHgrow(not, Priority.SOMETIMES);

		
		Label ev = new Label("Eventi pubblicati");
		ev.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
		GridPane.setConstraints(ev, 2, 6);
		GridPane.setHgrow(ev, Priority.SOMETIMES);
		
		// tab eventi
		eventsTable = new TableView<>();
		eventsTable.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css");
		GridPane.setConstraints(eventsTable, 2, 7, 2, 1);
		GridPane.setHgrow(eventsTable, Priority.SOMETIMES);
		GridPane.setVgrow(eventsTable, Priority.SOMETIMES);
		
		TableColumn<Event, Integer> codevcol = new TableColumn<>("ID");
		codevcol.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
		codevcol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Event, String> evnomecol = new TableColumn<>("nome");
		evnomecol.setCellValueFactory(new PropertyValueFactory<>("name"));
		evnomecol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Event, Integer> cityevcol = new TableColumn<>("città");
		cityevcol.setCellValueFactory(new PropertyValueFactory<>("city"));
		cityevcol.setStyle("-fx-alignment: CENTER");
		
		eventsTable.getColumns().addAll(codevcol, evnomecol, cityevcol);
		
//		ObservableList<Event> evs = FXCollections.observableArrayList();
//		evs.add(new it.unipv.insfw23.TicketWave.modelView.Event(1,"nome1"));
//		evs.add(new it.unipv.insfw23.TicketWave.modelView.Event(3,"nome2"));
		eventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		eventsTable.setItems(evs);
//		tabev.setPrefWidth(400);
		
		
		
		
		// tab notifiche
		notificationsTable = new TableView<>();
		notificationsTable.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css");
		GridPane.setConstraints(notificationsTable, 0, 7, 2,1);
		GridPane.setHgrow(notificationsTable, Priority.SOMETIMES);
		GridPane.setVgrow(notificationsTable, Priority.SOMETIMES);

		
		TableColumn<Notification, Integer> codcol = new TableColumn<>("codice");
		codcol.setCellValueFactory(new PropertyValueFactory<>("id"));
		codcol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Notification, String> msgcol = new TableColumn<>("Message");
		msgcol.setCellValueFactory(new PropertyValueFactory<>("msg"));
		msgcol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Notification, LocalTime> orariocol = new TableColumn<>("orario");
		orariocol.setCellValueFactory(new PropertyValueFactory<>("time"));
		orariocol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Notification, LocalDate> datacol = new TableColumn<>("data");
		datacol.setCellValueFactory(new PropertyValueFactory<>("date"));
		datacol.setStyle("-fx-alignment: CENTER");
		
		notificationsTable.getColumns().addAll(codcol, msgcol, orariocol, datacol);
		
//		ObservableList<Notification> nots = FXCollections.observableArrayList();
//		nots.add(new Notification(1,new Customer("f", "tf", "h", "d", "cf", Province.AGRIGENTO, null, 300),"str2"));
//		nots.add(new Notification(3,"str3","str4"));
		notificationsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		notificationsTable.setItems(nots);
//		tabnot.setPrefWidth(400);

		subButton = new Button("Cambia abbonamento");
		GridPane.setConstraints(subButton, 3, 2);
		GridPane.setHalignment(subButton, HPos.RIGHT);
		
		
		switch(currentSub) {
		case 0:
			currentSubLabel = new Label("Abbonamento: gratuito");
			eventRemained = new Label("Eventi mensili creati: "+counterCreatedEvents+" su "+MAX_EVENTS_FOR_FREE_SUB);
			break;
		case 1:
			currentSubLabel = new Label("Abbonamento: base");
			eventRemained = new Label("Eventi mensili creati: "+counterCreatedEvents+" su "+MAX_EVENTS_FOR_BASE_SUB);
			break;
		case 2:
			currentSubLabel = new Label("Abbonamento: premium");
			eventRemained = new Label("Eventi mensili creati: "+counterCreatedEvents+" su nessun limite");
			break;


			//AGGIUNTA CASE PER IL SET DI ABBONAMENTO SCADUTO
			case -1:
				currentSubLabel = new Label("Abbonamento: scaduto");
				eventRemained = new Label("Non puoi creare altri eventi");
				break;

		}
		
		currentSubLabel.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
		GridPane.setConstraints(currentSubLabel, 0, 1, 2, 1);
		GridPane.setHgrow(currentSubLabel, Priority.SOMETIMES);
		
		eventRemained.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
		GridPane.setConstraints(eventRemained, 0, 2, 2, 1);
		GridPane.setHgrow(eventRemained, Priority.SOMETIMES);


		
		grid.getChildren().addAll(name, logoutButton, notificationsTable, eventsTable, not, ev, subButton, currentSubLabel, eventRemained);
		
		
		
		upperbar = UpperBar.getIstance();
		upperbar.setForManager();
		lowerbar = LowerBar.getInstance();
		
		layout.setTop(upperbar);
		layout.setCenter(grid);
		layout.setBottom(lowerbar);
		
		
//		ManagerController m = new ManagerController(window, this);

//		window.sizeToScene();
//		window.setMinHeight(400);
//		window.setMinWidth(850);
//		window.setHeight(900);
//		window.setWidth(1200);
		
//		Scene scene = new Scene(layout, window.getWidth(), window.getHeight());		
		
//		window.setScene(scene);
//		window.show();
	}
	
	
	
	public Button getLogoutButton() {
		return logoutButton;
	}
	
	public Button getNewEventButton() {
		return upperbar.getEventPlusButton();
	}
	
	public Button getStatsButton() {
		return upperbar.getStatsButton();
	}
	
	public Button getSearchButton() {
		return upperbar.getSearchButton();
	}
	
	public Button getProfileButton() {
		return upperbar.getProfileButton();
	}
	public Button getSubButton(){ return subButton;}
	
	public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(UpperBar.getIstance());
        upperbar.setForManager();
        layout.setCenter(grid);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }

	public TableView<Event> getTableEv(){
//		TableViewSelectionModel<it.unipv.insfw23.TicketWave.modelView.Event> a = tabev.getSelectionModel();
//		return a.getSelectedItem();
		return eventsTable;
	}
	
	public TableView<Notification> getTableNot(){
		return notificationsTable;
	}
	
	public void updateEvsTable(ArrayList<Event> evs, int counterCreatedEvents) {
		this.evs = FXCollections.observableArrayList(evs);
		this.counterCreatedEvents = counterCreatedEvents;
		init();
	}
	
	public void updateSubLabels(int newCurrentSub, int counterCreatedEvents) {
		this.currentSub = newCurrentSub;
		this.counterCreatedEvents = counterCreatedEvents;
		init();
	}
	
	
	
//	public void setEventsforTableev(Manager manager) {
//		evs = FXCollections.observableArrayList(manager.getEventlist());
//		init();
//	}
}
