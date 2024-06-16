package it.unipv.insfw23.TicketWave.modelView.user;

import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class CustomerView extends Scene implements IResettableScene {
    private BorderPane layout;
    private GridPane grid;
    private Label nameLabel;
    private Label wavePoints;
    private Label notificationLabel;
    private Label ticketsLabel;
    private TableView<Notification> notificationTab;
    private TableView<Ticket> ticketTab;
    private Button logoutButton;
    private ObservableList<Ticket> tick;
    private ObservableList<Notification> nots;
    private UpperBar customerUpperBar;
    private LowerBar lowerBar;

    public CustomerView(String name, ArrayList<Notification> nots, ArrayList<Ticket> tick, int points) {
        super(new BorderPane(), 1080, 600);
        this.nameLabel = new Label("Ciao, "+name);
        this.wavePoints = new Label("WavePoints: " +points);
        this.nots = FXCollections.observableArrayList(nots);
        this.tick = FXCollections.observableArrayList(tick);
        initComponents();

    }



    private void initComponents() {


        // Creazione del bordo superiore e inferiore

        BorderPane layout = (BorderPane) getRoot();
        this.layout = layout;

        customerUpperBar = UpperBar.getIstance();
        customerUpperBar.setForCustomer();
        lowerBar = LowerBar.getInstance();

        layout.setStyle("-fx-background-color: #91bad6;");
        layout.setBottom(lowerBar);
        layout.setTop(customerUpperBar);
        layout.setCenter(grid);


        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        layout.setCenter(grid);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(15);
        logoutButton = new Button("Logout");
        GridPane.setConstraints(logoutButton, 1, 0);
        GridPane.setHalignment(logoutButton,HPos.RIGHT);
        // Nome e Cognome

        nameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));

        GridPane.setConstraints(nameLabel, 0, 0);

        // Punti Accumulati

        wavePoints.setFont(Font.font("Helvetica ",FontWeight.BOLD, 15));
        GridPane.setConstraints(wavePoints, 0, 6);

        // TableView per le notifiche
        notificationLabel = new Label("Notifiche");
        notificationLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        notificationTab = new TableView<Notification>();
        notificationTab.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css");
        
        TableColumn<Notification, LocalDate> dateCol = new TableColumn<>("Data");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn<Notification, LocalTime> timeCol = new TableColumn<>("Orario");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        TableColumn<Notification, String> msgCol = new TableColumn<>("Messaggio");
        msgCol.setCellValueFactory(new PropertyValueFactory<>("msg"));
        
        notificationTab.getColumns().addAll(dateCol, timeCol, msgCol);
        notificationTab.setMinWidth(400);

        
        if(nots != null){
            notificationTab.setItems(nots);}

        GridPane.setConstraints(notificationLabel, 0, 13);
        GridPane.setValignment(notificationTab, VPos.BOTTOM);
        GridPane.setConstraints(notificationTab, 0, 14);


        // TableView per i biglietti acquistati
        ticketsLabel = new Label("Biglietti acquistati");
        ticketsLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        ticketTab = new TableView<Ticket>();
        ticketTab.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css");


        TableColumn<Ticket, String> barcodeCol = new TableColumn<>("Barcode");
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));

        TableColumn<Ticket, TicketType> typeCol = new TableColumn<>("Tipo");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Ticket, Double> priceCol = new TableColumn<>("Prezzo");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Ticket, String> eventCol = new TableColumn<>("Evento");
        eventCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        ticketTab.getColumns().addAll(eventCol,barcodeCol, priceCol,typeCol);
        ticketTab.setMinWidth(430);
        if(tick != null) {
            ticketTab.setItems(tick);
        }
        GridPane.setConstraints(ticketsLabel, 1, 13);
        GridPane.setValignment(ticketTab, VPos.BOTTOM);
        GridPane.setHalignment(ticketTab, HPos.RIGHT);
        GridPane.setConstraints(ticketTab, 1, 14);

        grid.getChildren().addAll(nameLabel, wavePoints, notificationTab, ticketTab,logoutButton, ticketsLabel,notificationLabel);




    }
    public Button getLogoutButton() {
        return logoutButton;
    }
    public Button getProfileButton() {
        return customerUpperBar.getProfileButton();
    }
    public Button getSearchButton(){
        return customerUpperBar.getSearchButton();
    }


	public void updateTicketsTable(ArrayList<Ticket> tickets) {
		this.tick = FXCollections.observableArrayList(tickets);
		initComponents();
	}

    public void updateWavePoints(int points){
        wavePoints.setText("WavePoints: " +points);
    }

    public TableView<Ticket> getTicketTab() {
        return ticketTab;
    }

    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        customerUpperBar = UpperBar.getIstance();
        customerUpperBar.setForCustomer();
        lowerBar = LowerBar.getInstance();

        layout.setTop(customerUpperBar);
        layout.setBottom(lowerBar);
        layout.setCenter(grid);
        setRoot(layout);
    }
}