package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentDataM2View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Nome Intestatario Carta:");
        Label surnameLabel = new Label("Cognome Intestatario Carta:");
        Label ncLabel = new Label("N° Carta:");
        Label expirationLabel = new Label("Data Scadenza:");
        Label cvcLabel = new Label("CVC:");

        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/next_arrow.png");
        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);

        TextField insertName = new TextField();
        TextField insertSurname = new TextField();
        TextField insertNC = new TextField();
        TextField insertMM = new TextField();
        TextField insertAA = new TextField();
        TextField insertcvc = new TextField();

        // Imposta il testo predefinito per i campi di inserimento della data
        insertMM.setText("MM");
        insertAA.setText("AA");

        // Imposta lo stile CSS per il testo dei campi di inserimento di MM e AA
        insertMM.setStyle("-fx-text-fill: #A9A9A9;");
        insertAA.setStyle("-fx-text-fill: #A9A9A9;");


        // nota: questo listener va inseritp in una classe a parte!
        // Aggiungi un listener per gestire l'evento di focus sui campi di inserimento di MM e AA
        insertMM.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Quando il campo ottiene il focus, rimuovi il testo "MM"
                if (insertMM.getText().equals("MM")) {
                    insertMM.setText("");
                }
                // Imposta il colore del testo su nero quando il campo ottiene il focus
                insertMM.setStyle("-fx-text-fill: black;");
            } else {
                // Se il campo è vuoto, ripristina il testo "MM"
                if (insertMM.getText().isEmpty()) {
                    insertMM.setText("MM");
                    // Mantieni il colore del testo grigio chiaro quando il campo perde il focus e il testo è "MM"
                    insertMM.setStyle("-fx-text-fill: #A9A9A9;");
                }
            }
        });

        insertAA.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Quando il campo ottiene il focus, rimuovi il testo "AA"
                if (insertAA.getText().equals("AA")) {
                    insertAA.setText("");
                }
                // Imposta il colore del testo su nero quando il campo ottiene il focus
                insertAA.setStyle("-fx-text-fill: black;");
            } else {
                // Se il campo è vuoto, ripristina il testo "AA"
                if (insertAA.getText().isEmpty()) {
                    insertAA.setText("AA");
                    // Mantieni il colore del testo grigio chiaro quando il campo perde il focus e il testo è "AA"
                    insertAA.setStyle("-fx-text-fill: #A9A9A9;");
                }
            }
        });

        GridPane dataInput = new GridPane();
        dataInput.setAlignment(Pos.TOP_LEFT);
        dataInput.setHgap(10);
        dataInput.setVgap(10);
        dataInput.setPadding(new Insets(10));
        dataInput.addRow(0, nameLabel, insertName);
        dataInput.addRow(1, surnameLabel, insertSurname);
        dataInput.addRow(2, ncLabel, insertNC);
        dataInput.addRow(3, expirationLabel, insertMM, insertAA);
        dataInput.addRow(4, cvcLabel, insertcvc);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(dataInput);

        // Posiziona l'ImageView nell'angolo in basso a destra
        root.setBottom(backarrow);
        BorderPane.setAlignment(backarrow, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inserimento Dati Carta di Credito");
        primaryStage.show();
    }
}

