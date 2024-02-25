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
import javafx.scene.control.MenuBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;
import it.unipv.insfw23.TicketWave.modelView.LowerBar;



public class TypeStatsView extends Scene {

    private BarChart barChart;
    private XYChart.Series<Number, String> serie;
    private BorderPane layout;
    private Pane contenuto;


    // modifico il costruttore per ricevere i risultati (WrapType) e la classe di dominio statDominio (StatisticHandlerArrayList)
    public TypeStatsView(){
        super(new BorderPane(), 1080, 600);
        init();
    }
    private void init() {

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: rgb(27,84,161)");


        final NumberAxis xAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Categoria evento");
        xAxis.setLabel("Percentuale venduta");

        final BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        this.barChart=barChart;
        barChart.setStyle("-fx-bar-fill: #0d0d44;");
        barChart.setTitle("Statistiche sulle gategorie eventi");

        XYChart.Series<Number, String> series = new XYChart.Series<>();
        this.serie=series;
        barChart.setLegendVisible(false);


        //aggiunta da dominio
        /*
        for(int i=0; i<typeNameArray.size(); i++){
            series.getData().add(new XYChart.Data<>(results[i], typeNameArray.get(i));
        }
        */
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
        //graphPane.setPadding(new Insets(20));
        //graphPane.setPrefSize(10, 10);
        graphPane.setMaxWidth(600);
        graphPane.setMaxHeight(400);

        this.contenuto = graphPane;

        layout.setCenter(graphPane);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(ManagerUpperBar.getIstance());
        this.layout = layout;
        setRoot(layout);

    }

    public BarChart getBarChart() {
        return barChart;
    }

    public XYChart.Series<Number, String> getSerie() {
        return serie;
    }

    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(ManagerUpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }
}
