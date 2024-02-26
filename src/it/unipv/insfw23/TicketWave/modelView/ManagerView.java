package it.unipv.insfw23.TicketWave.modelView;

import java.awt.Dimension;

import it.unipv.insfw23.TicketWave.modelController.ManagerController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.stage.Stage;



public class ManagerView extends Scene{
	
//	Stage window;
	
	public ManagerView() {
		super(new BorderPane(), 1080, 600);
		init();
	}

	private Button logoutButton;
	private ManagerUpperBar upperbar;
	private LowerBar lowerbar;
	private BorderPane layout;
	private GridPane grid;

	
	public void init(){
//		window = primarystage;
//		window.setTitle("speriamo figa");
		
		layout = new BorderPane();

		
		grid = new GridPane();
		grid.setPadding(new Insets(50, 100, 50, 100));
		grid.setVgap(15);
		grid.setHgap(90);
//		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		Label nome = new Label("Mario Rossi");
		nome.setFont(Font.font("Arial", 40));
		GridPane.setConstraints(nome, 1, 0, 2, 1);
		GridPane.setHalignment(nome, HPos.CENTER);
		GridPane.setHgrow(nome, Priority.SOMETIMES);
		
		logoutButton = new Button("Logout");
		GridPane.setConstraints(logoutButton, 1, 1, 2, 1);
		GridPane.setHalignment(logoutButton, HPos.CENTER);
		GridPane.setHgrow(logoutButton, Priority.SOMETIMES);
		
		
		
		Label not = new Label("Notifiche");
		not.setFont(Font.font("Arial", 20));
		GridPane.setConstraints(not, 1, 5);
		GridPane.setHgrow(not, Priority.SOMETIMES);

		
		Label ev = new Label("Eventi pubblicati");
		ev.setFont(Font.font("Arial", 20));		
		GridPane.setConstraints(ev, 3, 5);
		GridPane.setHgrow(ev, Priority.SOMETIMES);
		
		// tab eventi
		TableView<Event> tabev = new TableView<>();
		GridPane.setConstraints(tabev, 2, 6, 2, 1);
		GridPane.setHgrow(tabev, Priority.SOMETIMES);
		GridPane.setVgrow(tabev, Priority.SOMETIMES);
		
		TableColumn<Event, Integer> codevcol = new TableColumn<>("codice");
		codevcol.setCellValueFactory(new PropertyValueFactory<>("cod"));
		codevcol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Event, String> evnomecol = new TableColumn<>("nome");
		evnomecol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		evnomecol.setStyle("-fx-alignment: CENTER");
		
		tabev.getColumns().addAll(codevcol, evnomecol);
		
		ObservableList<Event> evs = FXCollections.observableArrayList();
//		evs.add(new Event(1,"nome1"));
//		evs.add(new Event(3,"nome2"));
		tabev.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		tabev.setItems(evs);
//		tabev.setPrefWidth(400);
		
		
		
		
		// tab notifiche
		TableView<Notification> tabnot = new TableView<>();
		GridPane.setConstraints(tabnot, 0, 6, 2,1);
		GridPane.setHgrow(tabnot, Priority.SOMETIMES);
		GridPane.setVgrow(tabnot, Priority.SOMETIMES);

		
		TableColumn<Notification, Integer> codcol = new TableColumn<>("codice");
		codcol.setCellValueFactory(new PropertyValueFactory<>("num"));
		codcol.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Notification, String> s1col = new TableColumn<>("stringa1");
		s1col.setCellValueFactory(new PropertyValueFactory<>("s1"));
		s1col.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Notification, String> s2col = new TableColumn<>("stringa2");
		s2col.setCellValueFactory(new PropertyValueFactory<>("s2"));
		s2col.setStyle("-fx-alignment: CENTER");
		
		tabnot.getColumns().addAll(codcol, s1col, s2col);
		
		ObservableList<Notification> nots = FXCollections.observableArrayList();
//		nots.add(new Notification(1,"str1","str2"));
//		nots.add(new Notification(3,"str3","str4"));
		tabnot.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		tabnot.setItems(nots);
//		tabnot.setPrefWidth(400);
		
		grid.getChildren().addAll(nome, logoutButton, tabnot, tabev, not, ev);
		
		
		
		upperbar = ManagerUpperBar.getIstance();
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
	
	public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(ManagerUpperBar.getIstance());
        layout.setCenter(grid);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }

	
}

