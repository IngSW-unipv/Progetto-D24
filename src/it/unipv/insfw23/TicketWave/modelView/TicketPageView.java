package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TicketPageView extends Application {
    public static void main(String[] args) {launch(args);}


    @Override
    public void start(Stage primaryStage) {    // la classe contiene unicamente label poichè è solo una pagina di visualizzazione, il cliente non può scriverci sopra
        // Creazione dei nodi dell'interfaccia utente
        Label titleLabel = new Label("TicketWave");
        Label eventNameLabel = new Label("Nome Evento:");
        Label eventDescriptionLabel = new Label("Descrizione Evento:");
        Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
        Button buyButton = new Button("Acquista Ora");

        // campi riempiti dal controller
        Label eventNameTextField = new Label();
        Label eventDescriptionTextField = new Label();
        Label ticketBaseLabel = new Label("Base Tickets:");
        Label ticketPremiumLabel = new Label("Premium Tickets:");
        Label ticketVipLabel = new Label("Vip Tickets:");
        /*
        // Caricamento dell'immagine del logo
        Image logoImage = new Image("logo.png");
        ImageView logoImageView = new ImageView(logoImage);
        */

        // Layout dell'interfaccia utente
        BorderPane root = new BorderPane();
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(10);
        root.setTop(topBox);


        HBox buttonbox= new HBox(buyButton);
        buttonbox.setPadding(new Insets(10));
        buttonbox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonbox.setSpacing(10);




        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(10));
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);
        centerGrid.add(eventNameLabel, 0, 0);
        centerGrid.add(eventNameTextField, 1, 0);
        centerGrid.add(eventDescriptionLabel, 0, 1);
        centerGrid.add(eventDescriptionTextField, 1, 1);
        root.setCenter(centerGrid);

        GridPane bottomGrid = new GridPane();
        bottomGrid.setPadding(new Insets(20));
        bottomGrid.setVgap(10);
        bottomGrid.setHgap(10);
        bottomGrid.add(ticketsLabel, 0, 0);
        bottomGrid.add(ticketBaseLabel, 0, 1);
        bottomGrid.add(ticketPremiumLabel, 0, 2);
        bottomGrid.add(ticketVipLabel, 0, 3);

        //bottomGrid.add(buyButton, 45, 2);
        root.setBottom(bottomGrid);

        // StackPane per contenere root e ImageView del logo
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(root,buttonbox);
        // StackPane.setAlignment(logoImageView, Pos.TOP_RIGHT); // Ancora l'immagine in alto a destra
        StackPane.setAlignment(buyButton, Pos.BOTTOM_RIGHT);
        // Creazione e visualizzazione della scena
        Scene scene = new Scene(stackPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicketWave");
        primaryStage.show();



    }


}


