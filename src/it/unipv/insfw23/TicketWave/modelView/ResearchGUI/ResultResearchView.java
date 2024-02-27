package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;

import it.unipv.insfw23.TicketWave.modelController.MainController;
import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResultResearchController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class ResultResearchView extends Scene {
    private ResearchNodesView rnv;
    private ObservableList<String> result; // dove metto i risultati della query, eseguita nel ResearchController
    private TableView<Event> table;
    private ResultResearchController rrc;
    private HBox box2;
    private HBox box1;
    private BorderPane layout;


    // costruttore
    public ResultResearchView() {
        super(new Pane(), 1080, 600);
        scenaResultResearch();
    }
    public void scenaResultResearch(){
        rnv = ResearchNodesView.getIstance();

        // Tableview per elencare i risultati della ricerca
        table = new TableView<>(); // STA ROBA E' QUELLA VERA

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, String> tcEvent = new TableColumn<>("Evento");
        tcEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEvent.setStyle("-fx-alignment: CENTER");
        tcEvent.setSortable(false);

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, String> tcCity = new TableColumn<>("Città");
        tcCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tcCity.setStyle("-fx-alignment: CENTER");
        tcCity.setSortable(false);

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, String> tcLocation = new TableColumn<>("Luogo");
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tcLocation.setStyle("-fx-alignment: CENTER");
        tcLocation.setSortable(false);

        TableColumn <it.unipv.insfw23.TicketWave.modelDomain.event.Event, Province> tcProvince = new TableColumn<>("Provincia");
        tcProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        tcProvince.setStyle("-fx-alignment: CENTER");
        tcProvince.setSortable(false);

        table.getColumns().addAll(tcEvent, tcCity, tcLocation, tcProvince);
//        table.setPlaceholder( new Label("Nessun evento trovato, sii più specifico"));

        // ESEMPIO AL VOLO DA METTERE NELL'OBSERVABLE LIST
        LocalDate data = LocalDate.now();
        ArrayList<it.unipv.insfw23.TicketWave.modelDomain.event.Event> arraylistevent = new ArrayList<>();
        Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","passwd",2, "23245234324", arraylistevent,5,1,data,4);
        int intvett[] = {2,5};
        int price[] = {30, 50};
        ArrayList<String> arrstr = new ArrayList<>();

        ObservableList<it.unipv.insfw23.TicketWave.modelDomain.event.Event> evs = FXCollections.observableArrayList(
            new Concert(23,"ER MEGLIO","PAVIA",data, "MAGAZZINI GENERALI", Province.valueOf("AGRIGENTO"),4,5,intvett,price, Genre.HOUSE,managerfinto,arrstr)
        );

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        table.setItems(evs);
//        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // posso selezionare solo una riga per volta

               /* table.setOnMouseClicked(event -> { // sta roba va ma non la posso usare
                        Event selectedPerson = table.getSelectionModel().getSelectedItem();
                        if (selectedPerson != null) {
                        System.out.println("Clicked on: " + selectedPerson.getNome());
                    }
                }); */

        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        box1 = new HBox();
        box1.setSpacing(10);
        box1.getChildren().add(rnv.getBar());
        box1.getChildren().add(rnv.getSearchBar());
        box1.getChildren().add(rnv.getSearchButton());

        box2 = new HBox();
        box2.setSpacing(10);
        box2.getChildren().add(table);

        // if ( User collegato = Manager) {
        // Creo il VBox che contiene gli HBox, l'Upper Bar e la Lower Bar
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(/*ManagerUpperBar.getIstance(),*/ box1, box2/*, LowerBar.getInstance()*/);

        // Estetica
        box1.setStyle("-fx-background-color: #def1fa");
        box2.setStyle("-fx-background-color: #def1fa");
        vb1.setStyle("-fx-background-color: #def1fa");

        // Allineo gli HBox nel VBox
//        VBox.setMargin(ManagerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(box1, new Insets(10.0d));
        VBox.setMargin(box2, new Insets(10.0d));
//        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        box1.setAlignment(Pos.CENTER); // mi serve per avere vb1 centrato nel BorderPane seguente
        box2.setAlignment(Pos.CENTER);

        layout = new BorderPane();

        layout.setCenter(vb1);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(ManagerUpperBar.getIstance());
        setRoot(layout);
        // } else {
        /*
            VBox vb1 = new VBox();
        vb1.getChildren().addAll(CustomerUpperBar.getIstance(), box1, box2, LowerBar.getInstance());

        // Estetica
        box1.setStyle("-fx-background-color: #def1fa");
        box2.setStyle("-fx-background-color: #def1fa");
        vb1.setStyle("-fx-background-color: #def1fa");

        // Allineo gli HBox nel VBox
        VBox.setMargin(CustomerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(box1, new Insets(10.0d));
        VBox.setMargin(box2, new Insets(10.0d));
        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        box1.setAlignment(Pos.CENTER); // mi serve per avere vb1 centrato nel BorderPane seguente
        box2.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();

        layout.setCenter(vb1);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(CustomerUpperBar.getIstance());
        setRoot(layout);
         */
    }
    public TableView<it.unipv.insfw23.TicketWave.modelDomain.event.Event> getTable() {
        return table;
    } // lo uso nel ResultResearchController
}

