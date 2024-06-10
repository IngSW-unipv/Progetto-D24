package it.unipv.insfw23.TicketWave.modelView.user;


import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;


public class CustomerView extends Scene implements IResettableScene {
    private BorderPane layout;
    private GridPane grid;
    private Label nameLabel;
    private Label wavePoints;
    private ListView notifyListView;
    private TableView ticketTab;
    private Button logoutButton;
    private ObservableList<Ticket> tick;
    private ObservableList<Notification> nots;
    private UpperBar customerUpperBar;
    private LowerBar lowerBar;


    // private final
    public CustomerView(String name, ArrayList<Notification> nots, ArrayList<Ticket> tick, int points) {
        super(new BorderPane(), 1080, 600);
        this.nameLabel = new Label("Benvenuto, "+name);
        this.wavePoints = new Label("WavePoints: " +points);
        this.nots = FXCollections.observableArrayList(nots);
        this.tick = FXCollections.observableArrayList(tick);
        initComponents();

    }



    private void initComponents() {

        BorderPane layout = (BorderPane) getRoot();
        this.layout = layout;

        // setto barra di sopra e di sotto
        customerUpperBar = UpperBar.getIstance();
        customerUpperBar.setForCustomer();
        lowerBar = LowerBar.getInstance();
        layout.setStyle("-fx-background-color: #91bad6;");
        layout.setBottom(lowerBar);
        layout.setTop(customerUpperBar);
        layout.setCenter(grid);

        // inizializzo griglia nella quale metto gli elementi
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        layout.setCenter(grid);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        // bottone logout
        logoutButton = new Button("Logout");
        GridPane.setConstraints(logoutButton, 2, 0);

        // Nome e Cognome
        nameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        GridPane.setConstraints(nameLabel, 0, 0);

        // Punti Accumulati

        wavePoints.setFont(Font.font("Helvetica ",FontWeight.BOLD, 20));
        GridPane.setConstraints(wavePoints, 1, 0);

        // ListView per le notifiche
        notifyListView = new ListView<>();
        notifyListView.getStylesheets().add("it/unipv/insfw23/TicketWave/css/listViewStyle.css");
        if(nots != null){
            notifyListView.setItems(nots);}
       // notifyListView.getItems().addAll("Notifica 1", "Notifica 2", "Notifica 3"); // Dati di esempio
        GridPane.setConstraints(notifyListView, 0, 1);

        // TableView per i biglietti acquistati
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
        ticketTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        if(tick != null) {
            ticketTab.setItems(tick);
        }
        GridPane.setConstraints(ticketTab, 1, 1);

        grid.getChildren().addAll(nameLabel, wavePoints, notifyListView, ticketTab,logoutButton);




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