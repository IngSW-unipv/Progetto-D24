package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;
/*****************************************
        QUI VA TUTTO, DEVO ABBELLIRE LA DISPOSIZIONE
 *****************************************/

import com.sun.javafx.scene.control.GlobalMenuAdapter;
import it.unipv.insfw23.TicketWave.modelController.MainController;
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

import static javafx.application.Application.launch;

// Estende Scene, in maniera da poter visualizzare i nodes comuni della ResearchNodesView
public class ResearchView extends Scene{
    // costruttore
    public ResearchView() {
        super(new Pane(), 1080, 600);
        scenaResearch();
    }

    public void scenaResearch(){
        ResearchNodesView rnv = ResearchNodesView.getIstance();
        // if ( User collegato = Manager ) {
        // Creo un'HBOX che contiene barra + bottone di ricerca HBox = disposizione orizzontale
        HBox box1 = new HBox();
        box1.setSpacing(10);
        box1.getChildren().add(rnv.getBar());
        box1.getChildren().add(rnv.getSearchBar());
        box1.getChildren().add(rnv.getSearchButton());

        // Creo il VBox che contiene gli HBox, l'Upper Bar e la Lower Bar
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(ManagerUpperBar.getIstance(), box1, LowerBar.getInstance());

        // Estetica
        box1.setStyle("-fx-background-color: #def1fa");
        vb1.setStyle("-fx-background-color: #def1fa");

        // Allineo gli HBox nel VBox
        VBox.setMargin(ManagerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(box1, new Insets(10.0d));
        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        box1.setAlignment(Pos.CENTER); // mi serve per avere vb1 centrato nel BorderPane seguente

        BorderPane layout = new BorderPane();

        layout.setCenter(vb1);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(ManagerUpperBar.getIstance());
        setRoot(layout);
        // } else {
        /*
        HBox box1 = new HBox();
        box1.setSpacing(10);
        box1.getChildren().add(rnv.getBar());
        box1.getChildren().add(rnv.getSearchBar());
        box1.getChildren().add(rnv.getSearchButton());

        // Creo il VBox che contiene gli HBox, l'Upper Bar e la Lower Bar
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(CustomerUpperBar.getIstance(), box1, LowerBar.getInstance());

        // Estetica
        box1.setStyle("-fx-background-color: #def1fa");
        vb1.setStyle("-fx-background-color: #def1fa");

        // Allineo gli HBox nel VBox
        VBox.setMargin(CustomerUpperBar.getIstance(), new Insets(10.0d));
        VBox.setMargin(box1, new Insets(10.0d));
        VBox.setMargin(LowerBar.getInstance(), new Insets(10.0d));

        box1.setAlignment(Pos.CENTER); // mi serve per avere vb1 centrato nel BorderPane seguente

        BorderPane layout = new BorderPane();

        layout.setCenter(vb1);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(CustomerUpperBar.getIstance());
        setRoot(layout);
         */
    }
}

