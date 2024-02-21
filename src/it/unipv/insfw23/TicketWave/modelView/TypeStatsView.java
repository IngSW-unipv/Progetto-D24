package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;



public class TypeStatsView extends Scene {

    public TypeStatsView(){
        super(new BorderPane(), 1080, 600);
        init();
    }
    private void init() {

        BorderPane layout = (BorderPane) getRoot();

        layout.setTop(ManagerUpperBar.getIstance());
        layout.setBottom(LowerBar.getInstance());
        layout.setStyle("-fx-background-color: rgb(27,84,161)");
        //setFill(Color.web("#FFC943"));

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

        Pane graphPane = new Pane(barChart);
        graphPane.setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        graphPane.setPadding(new Insets(20));
        //graphPane.setPrefSize(10, 10);
        graphPane.setMaxWidth(600);
        layout.setCenter(graphPane);
        layout.setPadding(new Insets(30));
    }


}
