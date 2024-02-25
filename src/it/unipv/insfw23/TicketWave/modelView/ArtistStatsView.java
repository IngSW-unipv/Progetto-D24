package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ArtistStatsView extends BorderPane {

    private XYChart.Series<String, Number> artistSerie;

    //modifico il costruttore per ricevere wrapArtist
    public ArtistStatsView() {

        final NumberAxis yAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Artisti");
        yAxis.setLabel("Percentuale venduta");

        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-bar-fill: #ea8928;");
        barChart.setTitle("Statistiche sugli Artisti degli eventi");

        barChart.setCategoryGap(45);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        this.artistSerie=series;
        barChart.setLegendVisible(false);

        //aggiunta da dominio
        /*
        for (int i=0; i<artistNameArray.size(); i++){
            series.getData().add(new XYChart.Data<>("artistNameArray.get(i).concatenaStringhe()", artistResult.get(i));
        }
        Altrimenti
        while (artistNamearrayIterator.hasNext() && artistResultIterator.hasNext()){
            series.getData().add(new XYChart.Data<>("artistNameArrayIterator.next().concatenaStringhe()", artistResultIterator.next());
        }
         */

        // Aggiunta dei dati alla serie
        series.getData().add(new XYChart.Data<>("Categoria 1", 56));
        series.getData().add(new XYChart.Data<>("Categoria 2", 66));
        series.getData().add(new XYChart.Data<>("Categoria 3", 89));
        series.getData().add(new XYChart.Data<>("Categoria 4", 12));
        series.getData().add(new XYChart.Data<>("Categoria 5", 0));

        // Aggiunta della serie al grafico
        barChart.getData().add(series);

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
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
