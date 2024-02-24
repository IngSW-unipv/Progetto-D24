package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;

import com.sun.javafx.scene.control.GlobalMenuAdapter;
import it.unipv.insfw23.TicketWave.modelView.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
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

public class ResearchView extends Scene{
    private ResearchNodesView rnv;
    // costruttore
    public ResearchView() {
        super(new Pane(), 1080, 600);
        scenaResearch();
    }

    public void scenaResearch(){
        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox contenuto = new HBox();
        contenuto.setSpacing(10);
        contenuto.getChildren().add(rnv.getSearchBar());
        contenuto.getChildren().add(rnv.getSearchButton());
       // contenuto.getChildren().add(rnv.getBarra());

        // Creo il VBox che contiene gli HBox, l'Upper Bar e la Lower Bar
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(ManagerUpperBar.getIstance(), contenuto, LowerBar.getInstance());
        // Allineo gli HBox nel VBox
        VBox.setMargin(ManagerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(contenuto, new Insets(10.0d));
        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        ManagerUpperBar.getIstance().setAlignment(Pos.TOP_CENTER);
        contenuto.setAlignment(Pos.TOP_CENTER);
        LowerBar.getInstance().setAlignment(Pos.BOTTOM_CENTER);

/*        // Barra dei filtri
        final MenuBar barra = new MenuBar();
        final Menu generi = new Menu("Generi");
        final Menu province = new Menu("Provincia");

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

        final Button profileButton = new Button("Profile", imv2);
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
        final Button searchButton = new Button("Search", imv);
        searchButton.setOnAction(event -> {
            // Devo mettere una query al DB !!!!!!! in generale tutta la logica che mi serve
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

        this.searchButton = searchButton;
        this.profileButton = profileButton;

        // Creazione scene
        Scene research = new Scene(vb1, 1080, 600);             */
    }
}

