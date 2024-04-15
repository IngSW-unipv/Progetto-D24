package it.unipv.insfw23.TicketWave.modelView.statistics;

import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LocationStatsView extends Scene {

    private String artistTitle;
    private Button backButton;
    private XYChart<String, Number> LocationSerie;

    public LocationStatsView(String artistTitle) {
        super(new BorderPane(), 1080, 600);
        this.artistTitle = artistTitle;
        init();
    }

    private void init() {

        BorderPane layout = (BorderPane) getRoot();

        layout.setStyle("-fx-background-color:#91BAD6");

        // Creazioni assi X e Y (scheletro)
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Località");
        yAxis.setLabel("Percentuale");

        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setStyle("-fx-bar-fill: #EE6D21FC;");
        lineChart.setTitle("Statistiche sui biglietti venduti per località:  " + artistTitle);
        lineChart.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 15px; -fx-font-weight: bold;");
        lineChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Aggiunta dei dati alla serie
        series.getData().add(new XYChart.Data<>("Località 1", 100));
        series.getData().add(new XYChart.Data<>("Località 2", 20));
        series.getData().add(new XYChart.Data<>("Località 3", 30));
        series.getData().add(new XYChart.Data<>("Località 4", 45));
        series.getData().add(new XYChart.Data<>("Località 5", 73));

        // Aggiunta della serie al grafico
        lineChart.getData().add(series);


        // Back button
        backButton = new Button();
        ImageView backbuttonicon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/back2.png");
        backButton.setStyle("-fx-background-color: rgb(145,186,214)");
        backbuttonicon.setFitHeight(28);
        backbuttonicon.setFitWidth(30);
        backButton.setGraphic(backbuttonicon);

        // Pannello 3 per il grafico sulle province
        BorderPane paneGraph3 = new BorderPane();
        paneGraph3.setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        //paneGraph2.setPadding(new Insets(20));
        paneGraph3.setCenter(lineChart);
        paneGraph3.setMaxWidth(600);
        paneGraph3.setMaxHeight(400);

        BorderPane contenuto = new BorderPane();

        Region spacer = new Region();
        spacer.setMinHeight(40);
        contenuto.setBottom(spacer);
        contenuto.setTop(backButton);
        contenuto.setCenter(paneGraph3);

        contenuto.setPadding(new Insets(20));
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10));


        layout.setTop(UpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());
    }


    public XYChart<String, Number> getLocationSerie() {
        return LocationSerie;
    }

    public Button getBackButton(){
        return backButton;
    }

}