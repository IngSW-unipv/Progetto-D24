package it.unipv.insfw23.TicketWave.modelView;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

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

        HBox barraSup = new HBox();
        DropShadow ombraSup = new DropShadow();
        ombraSup.setColor(Color.GRAY);
        barraSup.setEffect(ombraSup);
        barraSup.setMinHeight(60);
        barraSup.setBackground(new Background(new BackgroundFill(Color.web("#80C1E2"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titolo = new Label(" TicketWave  ");
        titolo.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 40));
        titolo.setTextFill(Color.BLACK);

        ImageView iconLogo = new ImageView(icon);
        iconLogo.setFitHeight(60);
        iconLogo.setPreserveRatio(true);
        barraSup.setAlignment(Pos.CENTER_LEFT);

        Button statsButton = new Button();
        statsButton.setStyle("-fx-background-color: #80C1E2");
        ImageView statsIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/statistics.png");
        statsIcon.setFitHeight(16);
        statsIcon.setFitWidth(20);
        statsButton.setGraphic(statsIcon);

        Region spacer = new Region();
        barraSup.setHgrow(spacer, Priority.ALWAYS);
        barraSup.getChildren().addAll(titolo, iconLogo, spacer, statsButton);
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
        primaryStage.setScene(scene);

        primaryStage.show();



    }
}
