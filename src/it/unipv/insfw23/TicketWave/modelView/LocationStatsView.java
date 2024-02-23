package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
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
    private String event;
    private Button backButton;
    private XYChart<String, Number> LocationSerie;
    private ArtistStatsView artistPane;

    public LocationStatsView() {
        super(new BorderPane(), 1080, 600);
        init();
    }

    private void init() {

        BorderPane layout = (BorderPane) getRoot();

        layout.setStyle("-fx-background-color: rgb(27,84,161)");

        // Creazioni assi X e Y (scheletro)
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Località");
        yAxis.setLabel("Eventi");

        final LineChart<String, Number> lineChart = new LineChart<String, Number> (xAxis, yAxis);
        lineChart.setStyle("-fx-bar-fill: #ea8928;");
        lineChart.setTitle("Statistiche sugli eventi nelle varie località");

        XYChart.Series<String,Number> series = new XYChart.Series<>();

        lineChart.setLegendVisible(false);

        // Aggiunta dei dati alla serie
        series.getData().add(new XYChart.Data<>("Località 1", 100));
        series.getData().add(new XYChart.Data<>("Località 2", 20));
        series.getData().add(new XYChart.Data<>("Località 3", 30));
        series.getData().add(new XYChart.Data<>("Località 4", 45));
        series.getData().add(new XYChart.Data<>("Località 5", 73));

        // Aggiunta della serie al grafico
        lineChart.getData().add(series);

        // Back button
        Button backButton = new Button();
        ImageView backbuttonicon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/back2.png");
        backButton.setStyle("-fx-background-color: rgb(27,84,161)");
        backbuttonicon.setFitHeight(28);
        backbuttonicon.setFitWidth(30);
//      backButton.setGraphic(backbuttonicon);

        // Pane che contiene il LineChart
        BorderPane paneGraph2 = new BorderPane();
        paneGraph2.setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        //paneGraph2.setPadding(new Insets(20));
        paneGraph2.setCenter(lineChart);
        paneGraph2.setMaxWidth(600);
        paneGraph2.setMaxHeight(400);

        BorderPane contenuto = new BorderPane();

        Region spacer = new Region();
        spacer.setMinHeight(40);
        contenuto.setBottom(spacer);
        contenuto.setTop(backButton);
        contenuto.setLeft(paneGraph2);
        contenuto.setPadding(new Insets(20));

        ArtistStatsView artistPane = new ArtistStatsView();
        this.artistPane=artistPane;
        contenuto.setLeft(artistPane); // o rimetto SetRight????

        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10));
        this.backButton = backButton;

        layout.setTop(ManagerUpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());

        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10));
        this.backButton = backButton;

        layout.setTop(ManagerUpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());

    }

    public XYChart<String, Number> getLocationSerie() {
        return LocationSerie;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Button getBackButton(){
        return backButton;
    }

    public ArtistStatsView getArtistPane() {
        return artistPane;
    }
}
