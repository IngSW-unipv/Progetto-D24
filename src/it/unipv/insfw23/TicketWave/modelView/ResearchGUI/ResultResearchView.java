package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import static javafx.application.Application.launch;

public class ResultResearchView extends Scene {
    private ResearchNodesView rnv;
    private ObservableList<String> result; // dove metto i risultati della query, eseguita nel ResearchController

    // costruttore
    public ResultResearchView() {
        super(new Pane(), 1080, 600);
        scenaResultResearch();
    }
    public void scenaResultResearch(){
        ResearchNodesView rnv = ResearchNodesView.getIstance();

        // Tableview per elencare i risultati della ricerca
        TableView<it.unipv.insfw23.TicketWave.modelDomain.event.Event> table = new TableView<>();

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

        ObservableList<it.unipv.insfw23.TicketWave.modelDomain.event.Event> evs = FXCollections.observableArrayList();   // ESEMPIO CHE HO USATO PER VEDERE IL FUNZIONAMENTO */

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        table.setItems(evs);
        table.setEditable(true);

        // Rendo Le righe cliccabili (mando alla pagina dell'evento giusto, rimango su UI, per cui non creo un controller)
        table.setRowFactory(tv -> {
            javafx.scene.control.TableRow<it.unipv.insfw23.TicketWave.modelDomain.event.Event> row = new javafx.scene.control.TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    it.unipv.insfw23.TicketWave.modelDomain.event.Event rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getName() + " " + rowData.getCity() + " " + rowData.getLocation() + " " + rowData.getProvince() );
                    // Add your logic for handling the row click here
                }
            });
            return row;
        });

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
        box1.setStyle("-fx-background-color: rgb(27,84,161)");
        box2.setStyle("-fx-background-color: rgb(27,84,161)");
        vb1.setStyle("-fx-background-color: rgb(27,84,161)");

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

    public ObservableList<String> getResult() {
        return result;
    } // lo uso nel ResearchController

    public void setResult(ObservableList<String> result) {
        this.result = result;
    } // lo uso nel ResearchController
}

