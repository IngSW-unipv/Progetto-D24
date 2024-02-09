package it.unipv.insfw23.TicketWave.modelView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TicketPageGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TicketWave");

        // Creazione dei componenti
        Label titleLabel = new Label("TicketWave");
        Label eventNameLabel = new Label("Nome evento:");
        Label descriptionLabel = new Label("Descrizione evento:");
        Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
        TextField eventNameField = new TextField();
        TextField descriptionField = new TextField();
        TextField ticketsField1 = new TextField();
        TextField ticketsField2 = new TextField();
        TextField ticketsField3 = new TextField();
        Button buyButton = new Button("Acquista Ora");
        ImageView posterImageView = new ImageView();

        // Caricamento dell'immagine di default
        Image defaultImage = new Image(getClass().getResourceAsStream("default_poster.jpg"));
        posterImageView.setImage(defaultImage);
        posterImageView.setFitWidth(200);
        posterImageView.setFitHeight(200);

        // Creazione del layout
        BorderPane root = new BorderPane();
        GridPane centerPane = new GridPane();
        HBox bottomPane = new HBox();
        HBox topPane = new HBox();

        centerPane.setPadding(new Insets(10));
        centerPane.setHgap(10);
        centerPane.setVgap(10);
        centerPane.add(eventNameLabel, 0, 0);
        centerPane.add(eventNameField, 1, 0);
        centerPane.add(descriptionLabel, 0, 1);
        centerPane.add(descriptionField, 1, 1);
        centerPane.add(posterImageView, 2, 0, 1, 2);
        centerPane.add(ticketsLabel, 0, 2);
        centerPane.add(ticketsField1, 0, 3);
        centerPane.add(ticketsField2, 0, 4);
        centerPane.add(ticketsField3, 0, 5);

        bottomPane.getChildren().add(buyButton);
        topPane.getChildren().add(titleLabel);

        root.setTop(topPane);
        root.setCenter(centerPane);
        root.setBottom(bottomPane);

        // Creazione della scena
        Scene scene = new Scene(root, 600, 400);

        // Impostazione della scena e visualizzazione
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

