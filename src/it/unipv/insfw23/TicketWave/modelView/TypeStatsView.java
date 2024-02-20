package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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



public class TypeStatsView  {
    public static StackPane createTypeStats() {

        HBox barraSup = new HBox();
        DropShadow ombraSup = new DropShadow();
        ombraSup.setColor(Color.GRAY);
        barraSup.setEffect(ombraSup);
        barraSup.setMinHeight(60);
        barraSup.setBackground(new Background(new BackgroundFill(Color.web("#80C1E2"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titolo = new Label(" TicketWave  ");
        titolo.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 40));
        titolo.setTextFill(Color.BLACK);

        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        ImageView iconLogo = new ImageView(icon);
        iconLogo.setFitHeight(60);
        iconLogo.setPreserveRatio(true);
        barraSup.getChildren().add(titolo);
        barraSup.getChildren().addAll(iconLogo);
        barraSup.setAlignment(Pos.CENTER_LEFT);

        HBox barraInf = new HBox();
        DropShadow ombraInf = new DropShadow();
        ombraInf.setColor(Color.GRAY);
        barraInf.setEffect(ombraInf);
        barraInf.setMinHeight(30);
        barraInf.setBackground(new Background(new BackgroundFill(Color.web("#80C1E2"), CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane contenuto = new StackPane();
        contenuto.setStyle("-fx-background-color: rgba(210,125,27,0.99)");

        BorderPane layout = new BorderPane();
        layout.setTop(barraSup);
        layout.setCenter(contenuto);
        layout.setBottom(barraInf);
        Scene scene = new Scene(layout, 1080, 600);

        scene.setFill(Color.web("#FFC943"));




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
        graphPane.setPrefSize(400, 300);
        graphPane.setMaxWidth(600);
        contenuto.getChildren().add(graphPane);
        contenuto.setPadding(new Insets(30));

        return contenuto;
    }


}
