package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TicketPageView extends Application {

    @Override
    public void start(Stage primaryStage) {


        // Creazione dei nodi dell'interfaccia utente
        Label titleLabel = new Label("TicketWave");
        Label eventNameLabel = new Label("Nome Evento:");
        Label eventDescriptionLabel = new Label("Descrizione Evento:");
        Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
        Button buyButton = new Button("Acquista Ora");

        // campi riempiti dal controller
        Label eventNameTextField = new Label();
        Label eventDescriptionTextField = new Label();
        Label ticketBaseLabel = new Label();
        Label ticketVipLabel = new Label();
        Label ticketPremiumLabel = new Label();


        /* Caricamento dell'immagine per il poster (puoi modificare il percorso dell'immagine)
        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        */
        // Layout dell'interfaccia utente
        BorderPane root = new BorderPane();
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(10);
        root.setTop(topBox);

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
        bottomGrid.setPadding(new Insets(10));
        bottomGrid.setVgap(10);
        bottomGrid.setHgap(10);
        bottomGrid.add(ticketsLabel, 0, 0);
        bottomGrid.add(ticketBaseLabel, 0, 1);
        bottomGrid.add(ticketVipLabel, 0, 2);
        bottomGrid.add(ticketPremiumLabel, 0, 3);
        bottomGrid.add(buyButton, 45, 2);
        root.setBottom(bottomGrid);

        // Creazione e visualizzazione della scena
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicketWave");
        primaryStage.show();
/*
        // Creazione del controller e passaggio dei nodi dell'interfaccia utente
        Controller controller = new Controller(eventNameTextField,eventDescriptionTextField, ticketBaseLabel, ticketVipLabel, ticketPremiumLabel);
        controller.initialize();
    }
*/
    }
    public static void main (String[]args){
        launch(args);
    }
}
