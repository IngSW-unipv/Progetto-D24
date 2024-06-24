package it.unipv.insfw23.TicketWave.modelView.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapArtist;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ArtistStatsView extends BorderPane{

    private XYChart.Series<String, Number> artistSerie;

    //modifico il costruttore per ricevere wrapArtist
    public ArtistStatsView(WrapArtist artistRes) {

        final NumberAxis yAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis xAxis = new CategoryAxis();

        xAxis.setTickLabelRotation(45);


        xAxis.setLabel("Artisti");
        yAxis.setLabel("Percentuale vendita");

        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setMinHeight(400);
        barChart.setStyle("-fx-bar-fill: #91BAD6;");
        barChart.setTitle("Statistiche sugli Artisti degli eventi");
        barChart.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 15px; -fx-font-weight: bold;");
        barChart.setCategoryGap(45);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        this.artistSerie = series;
        barChart.setLegendVisible(false);

        // Aggiunta dei dati alla serie
        if (artistRes.getArtistResult().size() != artistRes.getArtistNameArray().size()) {
            throw new IllegalArgumentException("Le liste devono avere la stessa dimensione");
        }
        else {
            // Aggiunta dei dati alla serie
            for (int i = 0; i < artistRes.getArtistNameArray().size(); i++) {

                series.getData().add(new XYChart.Data<>(artistRes.getArtistNameArray().get(i), artistRes.getArtistResult().get(i)));

            }
        }

        // Aggiunta della serie al grafico
        barChart.getData().add(series);

        for (Node n : barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: rgba(238,109,33,0.99);");
        }

        setCenter(barChart);
        setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        setMaxWidth(600);
        setMaxHeight(400);
    }


    public XYChart.Series<String, Number> getArtistSerie(){
        return artistSerie;
    }
}
