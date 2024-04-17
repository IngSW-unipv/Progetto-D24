package it.unipv.insfw23.TicketWave.modelView.research;

/*****************************************
 QUI VA TUTTO, DEVO ABBELLIRE LA DISPOSIZIONE
 *****************************************/

import com.sun.javafx.scene.control.GlobalMenuAdapter;
// import it.unipv.insfw23.TicketWave.modelController.MainController;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
    private ResearchNodesView rnv;
    // costruttore
    public ResearchView() {
        super(new Pane(), 1080, 600);
        scenaResearch();
    }

    public void scenaResearch() {
        rnv = ResearchNodesView.getIstance();
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color: #91BAD6");
        gp.setAlignment(Pos.TOP_CENTER);
        // gp.setGridLinesVisible(true);
        // decido qual'è la distanza da tutti i bordi
        gp.setPadding(new Insets(50,50,50,50));

        // posiziono gli elementi nel gridPane
        gp.add(rnv.getBar(), 0, 0, 1, 1);
        gp.add(rnv.getSearchBar(), 1, 0, 1,1);
        gp.add(rnv.getSearchButton(),2,0,1,1);

        // decido di quanto spaziare gli elementi del gridPane
        gp.setHgap(20); // gap orizzontale
        gp.setVgap(20); // gap verticale

        // creo dei vincoli sulle colonne, in modo da decidere se una colonna deve essere più grande di un'altra
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        gp.getColumnConstraints().add(column1);
        gp.getColumnConstraints().add(column2);
        // column1.setPercentWidth(15); // Non mi interessa regolare la grandezza della menubar in base a come ridimensiono la finestra
        column2.setPercentWidth(30);

        // allineo i vari nodi all'interno delle loro celle della gridpane
        GridPane.setHalignment(rnv.getBar(), HPos.CENTER);
        GridPane.setHalignment(rnv.getSearchBar(), HPos.CENTER);
        GridPane.setHalignment(rnv.getSearchButton(), HPos.CENTER);

        gp.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);


        BorderPane layout = new BorderPane();
        layout.setCenter(gp);
        layout.setBottom(LowerBar.getInstance());
        /* if ( User == Manager) {
               layout.setTop(ManagerUpperBar.getIstance());
            }  else {
                layout.setTop(CustomerUpperBar.getIstance());
            }
         */
        layout.setTop(UpperBar.getIstance());
        setRoot(layout);
    }

    public ResearchNodesView getresearchnodeview() {
        return rnv;
    }

}
