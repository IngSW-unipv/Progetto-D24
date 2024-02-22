package it.unipv.insfw23.TicketWave.modelView;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultResearchView extends Application {

        @Override
        public void start(Stage primaryStage) {
            // Bottone per accedere al profilo
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
            ObservableList<String> result = FXCollections.observableArrayList( "Festival1", "Concerto1", "Festival3");
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
            Scene scene = new Scene(vb1, 1080, 600);

            // Setto lo stage
            primaryStage.setTitle("Search Bar Example");
            primaryStage.setScene(scene);

            // Mostro lo stage
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
