package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelController.MainController;
import it.unipv.insfw23.TicketWave.modelController.StatisticsController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.stage.StageStyle;


public class MainStageView extends Application {

    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        MainController mainController= new MainController(primaryStage);

        this.primaryStage = primaryStage;
        primaryStage.show();

        primaryStage.setTitle("TicketWave");
        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);


        BorderPane contenuto = new BorderPane();
        contenuto.setStyle("-fx-background-color: rgb(27,84,161)");

        BorderPane layout = new BorderPane();
        layout.setTop(ManagerUpperBar.getIstance());

        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());
        Scene scene = new Scene(layout, 1080, 600);

        //scene.setFill(Color.web("#FFC943"));
        primaryStage.setScene(scene);

        primaryStage.show();

    }

}
