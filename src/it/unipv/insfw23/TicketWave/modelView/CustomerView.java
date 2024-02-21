package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerView extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Sezione Notifiche
        VBox notifyBox = new VBox();
        notifyBox.setSpacing(30);
        notifyBox.setPadding(new Insets(10));
        notifyBox.getChildren().add(new Label("Notifiche"));
        notifyBox.setStyle("-fx-background-color: lightblue;");


        root.setRight(notifyBox);

        // Sezione Biglietti Acquistati
        VBox ticketBox = new VBox();
        ticketBox.setSpacing(10);
        ticketBox.setPadding(new Insets(10));
        ticketBox.getChildren().add(new Label("Biglietti Acquistati"));
        ticketBox.setStyle("-fx-background-color: lightyellow;");
        // Aggiungi biglietti acquistati
        root.setCenter(ticketBox);

        // Barra di Ricerca
        TextField risearchField = new TextField();
        risearchField.setPromptText("Cerca");
        root.setTop(risearchField);

        // Sezione Punti Accumulati
        VBox pointsBox = new VBox();
        pointsBox.setSpacing(10);
        pointsBox.setPadding(new Insets(10));
        pointsBox.getChildren().add(new Label("Waves Points"));
        // Aggiungi visualizzazione dei punti
        root.setLeft(pointsBox);

        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicketWave");
        primaryStage.show();
    }
    /*
     private void addNotify(VBox notificheBox, String testoNotifica) {
    Label notifyLabel = new Label(testoNotifica);
    notifyBox.getChildren().add(notificaLabel);
}*/
    public static void main(String[] args) {
        launch(args);
    }
}

