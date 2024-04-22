package it.unipv.insfw23.TicketWave.modelView.user;

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


public class CustomerView extends Scene {
    private BorderPane layout;
    private GridPane grid;
    private Label name;
    private Label wavePoints;
    private ListView notifyListView;
    private TableView ticket;
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
        notifyListView.getItems().addAll("Notifica 1", "Notifica 2", "Notifica 3"); // Dati di esempio
        GridPane.setConstraints(notifyListView, 0, 1);

        // TableView per i biglietti acquistati
        ticket = new TableView<>();
        ticket.setItems(getEsempioBiglietti());
        TableColumn<Biglietto, String> eventoCol = new TableColumn<>("Evento");
        eventoCol.setCellValueFactory(new PropertyValueFactory<>("evento"));
        TableColumn<Biglietto, String> tipoBigliettoCol = new TableColumn<>("Tipo Biglietto");
        tipoBigliettoCol.setCellValueFactory(new PropertyValueFactory<>("tipoBiglietto"));
        TableColumn<Biglietto, String> dataEventoCol = new TableColumn<>("Data Evento");
        dataEventoCol.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
        TableColumn<Biglietto, Double> prezzoCol = new TableColumn<>("Prezzo");
        prezzoCol.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        ticket.getColumns().addAll(eventoCol, tipoBigliettoCol, dataEventoCol, prezzoCol);
        GridPane.setConstraints(ticket, 1, 1);

        grid.getChildren().addAll(name, wavePoints, notifyListView, ticket,logoutButton);




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

    private ObservableList<Biglietto> getEsempioBiglietti() {
        ObservableList<Biglietto> biglietti = FXCollections.observableArrayList();
        biglietti.add(new Biglietto("Concerto", "VIP", "2024-02-25", 50.0));
        biglietti.add(new Biglietto("Partita", "Standard", "2024-03-15", 30.0));
        return biglietti;
    }




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
