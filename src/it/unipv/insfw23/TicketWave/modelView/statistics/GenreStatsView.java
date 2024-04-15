package it.unipv.insfw23.TicketWave.modelView.statistics;

import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class GenreStatsView extends Scene {

    private String genre;
    private Button backButton;
    private XYChart<String, Number> genreSerie;
    private ArtistStatsView artistPane;
    private BorderPane layout;
    private BorderPane content;

    //modifico il costruttore per ricevere i due risultati WrapArtist e WrapGenreOrPorv e la classe statDominio
    public GenreStatsView(){
        super(new BorderPane(), 1080, 600);
        init();
    }
    private void init() {

        layout = (BorderPane) getRoot();

        layout.setStyle("-fx-background-color:#91BAD6");

        final NumberAxis yAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Generi");
        yAxis.setLabel("Percentuale venduta");

        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-bar-fill: #EE6D21FC;");
        barChart.setTitle("Statistiche sui Generi dei Concerti");
        barChart.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 15px; -fx-font-weight: bold;");
        barChart.setCategoryGap(45);
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();


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


        backButton = new Button();
        ImageView backbuttonicon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/back2.png");
        backButton.setStyle("-fx-background-color: rgb(145,186,214)");
        backbuttonicon.setFitHeight(28);
        backbuttonicon.setFitWidth(30);
        backButton.setGraphic(backbuttonicon);


        //Pannello 2 per grafico sui generi
        BorderPane paneGraph2 = new BorderPane();
        paneGraph2.setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        //paneGraph2.setPadding(new Insets(20));
        paneGraph2.setCenter(barChart);

        paneGraph2.setMaxWidth(600);
        paneGraph2.setMaxHeight(400);

        BorderPane content = new BorderPane();

        Region spacer = new Region();
        spacer.setMinHeight(40);
        content.setBottom(spacer);
        content.setTop(backButton);
        content.setLeft(paneGraph2);
        content.setPadding(new Insets(20));

        this.content = content;


        //passo i risultati artistRes
        ArtistStatsView artistPane = new ArtistStatsView();
        this.artistPane=artistPane;
        content.setRight(artistPane);

        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10));
        //this.backButton = backButton;

        layout.setTop(UpperBar.getIstance());
        layout.setCenter(content);
        layout.setBottom(LowerBar.getInstance());
        //this.layout = layout;


    }

    public XYChart<String, Number> getGenreSerie() {
        return genreSerie;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Button getBackButton(){
        return backButton;
    }

    public ArtistStatsView getArtistPane() {
        return artistPane;
    }

    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        UpperBar.getIstance().setForManager();
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(content);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }


}
