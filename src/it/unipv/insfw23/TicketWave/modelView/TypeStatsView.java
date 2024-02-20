package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;



public class TypeStatsView extends Application {



    public static void main(String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.show();

        primaryStage.setTitle("TicketWave");
        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        //primaryStage.setScene(scene);


        final NumberAxis xAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Categoria evento");
        xAxis.setLabel("Percentuale venduta");

        final BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-bar-fill: #0d0d44;");
        barChart.setTitle("Statistiche sulle gategorie eventi");

        XYChart.Series<Number, String> series = new XYChart.Series<>();

        barChart.setLegendVisible(false);
        //series.setName("Biglietti venduti");

        // Aggiunta dei dati alla serie
        series.getData().add(new XYChart.Data<>(0, "Categoria 1"));
        series.getData().add(new XYChart.Data<>(14, "Categoria 2"));
        series.getData().add(new XYChart.Data<>(72, "Categoria 3"));
        series.getData().add(new XYChart.Data<>(55, "Categoria 4"));
        series.getData().add(new XYChart.Data<>(100, "Categoria 5"));

        // Aggiunta della serie al grafico
        barChart.getData().add(series);

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #070779;");
        }

        Scene scene = new Scene(barChart, 600, 400);

        primaryStage.setScene(scene);

        for(XYChart.Data<Number, String> data: series.getData()) {
            data.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String yAxisLabel = data.getYValue();
                    GenreStatsView genreScene = new GenreStatsView();
                    ArtistStatsView artistScene = new ArtistStatsView();

                    genreScene.setGenre(yAxisLabel);
                    genreScene.start(primaryStage);
                    System.out.println(yAxisLabel);

                }
            });
        }


    }


}
