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
        // List view per elencare i risultati della ricerca FACCIO TABLE VIEW!!!!!!!!!!!
        ListView<String> list = new ListView<String>();
        ObservableList<String> result = FXCollections.observableArrayList("Festival1", "Concerto1", "Festival3"); // questo andrà tolto una volta fatta la logica nel ResearchController
        list.setPrefSize(400, 600);
        list.setEditable(false);
        list.setItems(result);

        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox contenuto = new HBox();
        contenuto.setSpacing(10);
        contenuto.getChildren().add(rnv.getSearchBar());
        contenuto.getChildren().add(rnv.getSearchButton());
        contenuto.getChildren().add(rnv.getBar());

        HBox resultResearch = new HBox();
        resultResearch.setSpacing(10);
        resultResearch.getChildren().add(list);

        // Creo il VBox che contiene gli HBox, l'Upper Bar e la Lower Bar
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(ManagerUpperBar.getIstance(), contenuto, resultResearch, LowerBar.getInstance());
        // Allineo gli HBox nel VBox
        VBox.setMargin(ManagerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(contenuto, new Insets(10.0d));
        VBox.setMargin(resultResearch, new Insets(10.0d));
        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        ManagerUpperBar.getIstance().setAlignment(Pos.TOP_CENTER);
        contenuto.setAlignment(Pos.CENTER);
        resultResearch.setAlignment(Pos.CENTER); // andrà in conflitto con il contenuto che anch'esso è CENTER ???????
        LowerBar.getInstance().setAlignment(Pos.BOTTOM_CENTER);

    /*    // Bottone per accedere al profilo
        ImageView imv2 = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/user.png");
        imv2.setFitHeight(25);
        imv2.setFitWidth(24);

        Button profileButton = new Button("Profile", imv2);
        profileButton.setOnAction(event -> {
            // mettere la logica dietro l'azione della pressione dell'immagine di profilo
        });
        // Creo la HBox che contiene l'immagine di profilo
        HBox hb2 = new HBox();
        hb2.getChildren().add(profileButton);

        // Area di testo = barra di ricerca
        TextField searchBar = new TextField();
        searchBar.setPromptText("Enter your search...");

        // Immagine lente di ingrandimento
        ImageView imv = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/search_glass.png");
        imv.setFitHeight(20);
        imv.setFitWidth(18);

        // Button per l'invio della ricerca
        Button searchButton = new Button("Search", imv);
        searchButton.setOnAction(event -> {
            // Devo rimpiazzare sta roba con la logica di ricerca !!!!!!!
            String searchTerm = searchBar.getText();
            System.out.println("Searching for: " + searchTerm);
        });

        // Combo Box per filtri
        ObservableList<String> genre = FXCollections.observableArrayList(
                "Rock",
                "Punk",
                "Metal"
        );
        final ComboBox cb = new ComboBox(genre);

        ObservableList<String> prov = FXCollections.observableArrayList(
                "PV",
                "MI",
                "PR"
        );
        final ComboBox cb1 = new ComboBox(prov);

        // List view per elencare i risultati della ricerca
        ListView<String> list = new ListView<String>();
        ObservableList<String> result = FXCollections.observableArrayList("Festival1", "Concerto1", "Festival3");
        list.setPrefSize(400, 300);
        list.setEditable(false);
        list.setItems(result);

        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.getChildren().add(searchBar);
        hb1.getChildren().add(searchButton);
        hb1.getChildren().add(cb);
        hb1.getChildren().add(cb1);

        // Creo il VBox che contiene gli HBox
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb2, hb1, list);
        // Allineo gli HBox nel VBox
        VBox.setMargin(hb2, new Insets(10.0d));
        VBox.setMargin(hb1, new Insets(10.0d));
        VBox.setMargin(list, new Insets(10.0d));
        hb2.setAlignment(Pos.TOP_RIGHT);
        hb1.setAlignment(Pos.TOP_CENTER);

        // Creazione scene
        Scene resultResearch = new Scene(vb1, 1080, 600); */
    }

    public ObservableList<String> getResult() {
        return result;
    } // lo uso nel ResearchController

    public void setResult(ObservableList<String> result) {
        this.result = result;
    } // lo uso nel ResearchController
}

