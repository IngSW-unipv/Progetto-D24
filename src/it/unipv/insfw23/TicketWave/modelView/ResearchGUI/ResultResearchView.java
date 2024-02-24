package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;

import it.unipv.insfw23.TicketWave.modelView.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        // List view per elencare i risultati della ricerca FACCIO TABLE VIEW!!!!!!!!!!!
        ListView<String> list = new ListView<String>();
        ObservableList<String> result = FXCollections.observableArrayList("Festival1", "Concerto1", "Festival3"); // questo andrà tolto una volta fatta la logica nel ResearchController
        list.setPrefSize(400, 600);
        list.setEditable(false);
        list.setItems(result);

        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox box1 = new HBox();
        box1.setSpacing(10);
        box1.getChildren().add(rnv.getBar());
        box1.getChildren().add(rnv.getSearchBar());
        box1.getChildren().add(rnv.getSearchButton());

        HBox box2 = new HBox();
        box2.setSpacing(10);
        box2.getChildren().add(list);

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

