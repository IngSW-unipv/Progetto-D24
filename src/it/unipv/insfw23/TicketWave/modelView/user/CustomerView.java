package it.unipv.insfw23.TicketWave.modelView.user;

import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
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

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;


public class CustomerView extends Scene implements IResettableScene {
    private BorderPane layout;
    private GridPane grid;
    private Label name;
    private Label wavePoints;
    private ListView notifyListView;
    private TableView ticketTab;
    private Button logoutButton;

    private UpperBar customerUpperBar;
    private LowerBar lowerBar;

    // private final
    public CustomerView() {
        super(new BorderPane(), 1080, 600);
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
        grid.setVgap(8);
        grid.setHgap(10);

        logoutButton = new Button("Logout");
        /*GridPane.setConstraints(logoutButton, 1, 1, 2, 1);
        GridPane.setHalignment(logoutButton, HPos.CENTER);
        GridPane.setHgrow(logoutButton, Priority.SOMETIMES);
        GridPane.setConstraints(wavePoints, 1, 0);*/
        GridPane.setConstraints(logoutButton, 2, 0);

        // Nome e Cognome
        name = new Label("Benvenuto,");
        name.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));

        GridPane.setConstraints(name, 0, 0);

        // Punti Accumulati
        wavePoints = new Label("Wave Points: 100"); // Esempio di punti accumulati
        wavePoints.setFont(Font.font("Helvetica ",FontWeight.BOLD, 20));
        GridPane.setConstraints(wavePoints, 1, 0);

        // ListView per le notifiche
        notifyListView = new ListView<>();
        notifyListView.getStylesheets().add("it/unipv/insfw23/TicketWave/css/listViewStyle.css");
        notifyListView.getItems().addAll("Notifica 1", "Notifica 2", "Notifica 3"); // Dati di esempio
        GridPane.setConstraints(notifyListView, 0, 1);

        // TableView per i biglietti acquistati
        ticketTab = new TableView<>();
        ticketTab.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css");


        TableColumn<Event, String> eventCol = new TableColumn<>("Evento");
        eventCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Event, Enum> provinceCol = new TableColumn<>("Provincia");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));

        TableColumn<Event, LocalDate> dataEventCol = new TableColumn<>("Data Evento");
        dataEventCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Event, String> artistCol = new TableColumn<>("Artista");
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artists"));

        ticketTab.getColumns().addAll(eventCol, provinceCol, dataEventCol, artistCol);
        ticketTab.setItems(getfakeEvent());
        ticketTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        GridPane.setConstraints(ticketTab, 1, 1);

        grid.getChildren().addAll(name, wavePoints, notifyListView, ticketTab,logoutButton);




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

    private ObservableList<Event> getfakeEvent() {

        LocalDate data = LocalDate.now();
        ArrayList<Event> arraylistevent = new ArrayList<>();
        Manager managerfinto = new Manager("paolo","rossi","2000-12-30","paobro@gmail.com","passwd",Province.AGRIGENTO, "23245234324", arraylistevent,5,1,data,4);
        int intvett[] = {2,5,10};
        int vett [] = {200, 3000, 20};
        double price[] = {30, 50, 10};
        Time time = null;
        ObservableList<Event> evs = FXCollections.observableArrayList(

                new Concert(2,"Rooler in tha house","ROZZANO","Laghetto", data, time, Province.MILANO, Genre.HOUSE, 500, 3, intvett, vett, price, managerfinto,"Blanco", "Nuovo Album")

        );
        return evs;
    }



/*
    public static class Biglietto {
        private String evento;
        private String tipoBiglietto;
        private String dataEvento;
        private double prezzo;

        public Biglietto(String evento, String tipoBiglietto, String dataEvento, double prezzo) {
            this.evento = evento;
            this.tipoBiglietto = tipoBiglietto;
            this.dataEvento = dataEvento;
            this.prezzo = prezzo;
        }

        public String getEvento() {
            return evento;
        }

        public String getTipoBiglietto() {
            return tipoBiglietto;
        }

        public String getDataEvento() {
            return dataEvento;
        }

        public double getPrezzo() {
            return prezzo;
        }



    }
*/

    public TableView getTicketTab() {
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
