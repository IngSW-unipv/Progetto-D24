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
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
        CheckMenuItem cmi = new CheckMenuItem("ROCK");
        CheckMenuItem cmi1 = new CheckMenuItem("PUNK");
        // check menu filtro provincia
        CheckMenuItem pr = new CheckMenuItem("PAVIA");
        CheckMenuItem pr1 = new CheckMenuItem("PARMA");
        CheckMenuItem pr2 = new CheckMenuItem("MILANO");
        // aggiungo i miei check menu alla menu bar
        generi.getItems().addAll(cmi, cmi1);
        province.getItems().addAll(pr, pr1, pr2);
        barra.getMenus().addAll(generi, province);

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
            // Devo mettere una query al DB !!!!!!!
            String searchTerm = searchBar.getText();
            System.out.println("Searching for: " + searchTerm);
        });

       // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.getChildren().add(searchBar);
        hb1.getChildren().add(searchButton);
        hb1.getChildren().add(barra);

        // Creo il VBox che contiene gli HBox
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb2, hb1);
        // Allineo gli HBox nel VBox
        VBox.setMargin(hb2, new Insets(10.0d));
        VBox.setMargin(hb1, new Insets(10.0d));
        hb2.setAlignment(Pos.TOP_RIGHT);
        hb1.setAlignment(Pos.TOP_CENTER);

        hb1.setStyle("-fx-background-color: rgb(27,84,161)");
        hb2.setStyle("-fx-background-color: rgb(27,84,161)");
        vb1.setStyle("-fx-background-color: rgb(27,84,161)");

        // Creazione scene
        Scene research = new Scene(vb1, 500, 400);
    }
}
