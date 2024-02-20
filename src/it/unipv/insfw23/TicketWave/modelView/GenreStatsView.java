package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GenreStatsView extends Application {

    Scene genreScene;
    String genre;

    Button backButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.show();

        primaryStage.setTitle("TicketWave");
        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        //primaryStage.setScene(scene);


        final NumberAxis yAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Generi");
        yAxis.setLabel("Percentuale venduta");


        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-bar-fill: #ea8928;");
        barChart.setTitle("Statistiche sui Generi dei Concerti");

        barChart.setCategoryGap(45);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        barChart.setLegendVisible(false);
        //series.setName("Biglietti venduti");

        // Aggiunta dei dati alla serie
        series.getData().add(new XYChart.Data<>("Categoria 1", 56));
        series.getData().add(new XYChart.Data<>("Categoria 2", 66));
        series.getData().add(new XYChart.Data<>("Categoria 3", 89));
        series.getData().add(new XYChart.Data<>("Categoria 4", 12));
        series.getData().add(new XYChart.Data<>("Categoria 5", 0));

        // Aggiunta della serie al grafico
        barChart.getData().add(series);

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #b381fa;");
        }

        Button backButton = new Button();
        ImageView backbuttonicon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/back.png");
        backbuttonicon.setFitHeight(16);
        backbuttonicon.setFitWidth(20);
        backButton.setGraphic(backbuttonicon);
        BorderPane layout = new BorderPane();
        layout.setTop(backButton);
        layout.setCenter(barChart);
        BorderPane.setAlignment(backButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(backButton, new Insets(10));
        this.backButton = backButton;

        Scene sceneGenre = new Scene(layout, 600, 400);
        this.genreScene = sceneGenre;
        primaryStage.setScene(sceneGenre);


    }

    public Scene getScene(){
        return genreScene;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Button getBackButton(){
        return backButton;
    }
}
