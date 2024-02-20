package it.unipv.insfw23.TicketWave.modelView;

import com.sun.javafx.scene.control.GlobalMenuAdapter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckMenuItem;

import java.awt.*;
import java.util.Observable;

public class ResearchView extends Application{

    @Override
    public void start(Stage primaryStage) {
        // Barra dei filtri
        MenuBar barra = new MenuBar();
        Menu generi = new Menu("Generi");
        Menu province = new Menu("Provincia");

        // check menu filtri musica
        CheckMenuItem cmi = new CheckMenuItem("Rock");
        CheckMenuItem cmi1 = new CheckMenuItem("Punk");
        // check menu filtro provincia
        CheckMenuItem pr = new CheckMenuItem("PV");
        CheckMenuItem pr1 = new CheckMenuItem("PR");
        CheckMenuItem pr2 = new CheckMenuItem("MI");
        // aggiungo i miei check menu alla menu bar
        generi.getItems().addAll(cmi, cmi1);
        province.getItems().addAll(pr, pr1, pr2);
        barra.getMenus().addAll(generi, province);

        // Bottone per accedere al profilo
        ImageView imv2 = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/93-938050_png-file-transparent-white-user-icon-png-download-338969596.png.jpeg");
        imv2.setFitHeight(25);
        imv2.setFitWidth(20);

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
        ImageView imv = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/png-clipart-magnifying-glass-symbol-computer-icons-arrow-magnifier-magnifying-glass-logo-sign-987132787.png");
        imv.setFitHeight(10);
        imv.setFitWidth(20);

        // Button per l'invio della ricerca
        Button searchButton = new Button("Search", imv);
        searchButton.setOnAction(event -> {
            // Devo rimpiazzare sta roba con la logica di ricerca !!!!!!!
            String searchTerm = searchBar.getText();
            System.out.println("Searching for: " + searchTerm);
        });

        // Combo Box
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

        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.getChildren().add(searchBar);
        hb1.getChildren().add(searchButton);
        hb1.getChildren().add(barra);
        hb1.getChildren().add(cb);
        hb1.getChildren().add(cb1);

        // Creo il VBox che contiene gli HBox
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb2, hb1);
        // Allineo gli HBox nel VBox
        VBox.setMargin(hb2, new Insets(10.0d));
        VBox.setMargin(hb1, new Insets(10.0d));
        hb2.setAlignment(Pos.TOP_RIGHT);
        hb1.setAlignment(Pos.TOP_CENTER);

        // Creazione scene
        Scene scene = new Scene(vb1, 500, 400);

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
