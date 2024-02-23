package it.unipv.insfw23.TicketWave.modelView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LocationStatsView extends Scene {
    private String genre;
    private Button backButton;
    private XYChart<String, Number> genreSerie;
    private ArtistStatsView artistPane;

    public LocationStatsView() {
        super(new BorderPane(), 1080, 600);
        init();
    }

    private void init() {

        BorderPane layout = (BorderPane) getRoot();

        layout.setStyle("-fx-background-color: rgb(27,84,161)");

        final NumberAxis yAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Località");
        yAxis.setLabel("Eventi");


        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-bar-fill: #ea8928;");
        barChart.setTitle("Statistiche sugli eventi nelle varie località");

        barChart.setCategoryGap(45);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        barChart.setLegendVisible(false);

        // Aggiunta dei dati alla serie
        series.getData().add(new XYChart.Data<>("Categoria 1", 56));
        series.getData().add(new XYChart.Data<>("Categoria 2", 66));
        series.getData().add(new XYChart.Data<>("Categoria 3", 89));
        series.getData().add(new XYChart.Data<>("Categoria 4", 12));
        series.getData().add(new XYChart.Data<>("Categoria 5", 0));

        // Aggiunta della serie al grafico
        barChart.getData().add(series);

        for (Node n : barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: rgba(238,109,33,0.99);");
        }


        Button backButton = new Button();
        ImageView backbuttonicon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/back2.png");
        backButton.setStyle("-fx-background-color: rgb(27,84,161)");
        backbuttonicon.setFitHeight(28);
        backbuttonicon.setFitWidth(30);
//      backButton.setGraphic(backbuttonicon);


        BorderPane paneGraph2 = new BorderPane();
        paneGraph2.setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        //paneGraph2.setPadding(new Insets(20));
        paneGraph2.setCenter(barChart);

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
        this.artistPane = artistPane;
        contenuto.setRight(artistPane);

        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10));
        this.backButton = backButton;

        layout.setTop(ManagerUpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());

    }
}
