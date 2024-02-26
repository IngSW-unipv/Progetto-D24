package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;

import it.unipv.insfw23.TicketWave.modelController.MainController;
import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResultResearchController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelView.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class ResultResearchView extends Scene {
    private ResearchNodesView rnv;
    private ObservableList<String> result; // dove metto i risultati della query, eseguita nel ResearchController
    private TableView<Event> table;
    private ResultResearchController rrc;

    // costruttore
    public ResultResearchView() {
        super(new Pane(), 1080, 600);
        scenaResultResearch();
    }
    public void scenaResultResearch(){
        ResearchNodesView rnv = ResearchNodesView.getIstance();

        // Tableview per elencare i risultati della ricerca
        table = new TableView<>();

        TableColumn <Event, String> tcEvent = new TableColumn<>("Evento");
        tcEvent.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tcEvent.setStyle("-fx-alignment: CENTER");

        ObservableList<Event> evs = FXCollections.observableArrayList(
                new Event(1, "PAOLO")
        );
        table.getColumns().addAll(tcEvent);


        //table.setOnMouseClicked(new ResultResearchController(table));
        //table.setOnMouseClicked(event -> System.out.println(table.getSelectionModel().getSelectedItem().getNome()));

      /*  TableView<it.unipv.insfw23.TicketWave.modelDomain.event.Event> table = new TableView<>();

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, String> tcEvent = new TableColumn<>("Evento");
        tcEvent.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcEvent.setStyle("-fx-alignment: CENTER");

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, String> tcCity = new TableColumn<>("Città");
        tcCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        tcCity.setStyle("-fx-alignment: CENTER");

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, String> tcLocation = new TableColumn<>("Luogo");
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        tcLocation.setStyle("-fx-alignment: CENTER");

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, Province> tcProvince = new TableColumn<>("Provincia");
        tcProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        tcProvince.setStyle("-fx-alignment: CENTER");

        table.getColumns().addAll(tcEvent, tcCity, tcLocation, tcProvince);
        table.setPlaceholder( new Label("Nessun evento trovato, sii più specifico"));

        ObservableList<it.unipv.insfw23.TicketWave.modelDomain.event.Event> evs = FXCollections.observableArrayList();   */

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        table.setItems(evs);

        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox box1 = new HBox();
        box1.setSpacing(10);
        box1.getChildren().add(rnv.getBar());
        box1.getChildren().add(rnv.getSearchBar());
        box1.getChildren().add(rnv.getSearchButton());

        HBox box2 = new HBox();
        box2.setSpacing(10);
        box2.getChildren().add(table);

        // Creo il VBox che contiene gli HBox, l'Upper Bar e la Lower Bar
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(ManagerUpperBar.getIstance(), box1, box2, LowerBar.getInstance());

        // Estetica
        box1.setStyle("-fx-background-color: #def1fa");
        box2.setStyle("-fx-background-color: #def1fa");
        vb1.setStyle("-fx-background-color: #def1fa");

        // Allineo gli HBox nel VBox
        VBox.setMargin(ManagerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(box1, new Insets(10.0d));
        VBox.setMargin(box2, new Insets(10.0d));
        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        box1.setAlignment(Pos.CENTER); // mi serve per avere vb1 centrato nel BorderPane seguente
        box2.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();

        layout.setCenter(vb1);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(ManagerUpperBar.getIstance());
        setRoot(layout);
    }

    public TableView<Event> getTable() {
        return table;
    } // lo uso nel ResultResearchController
}

