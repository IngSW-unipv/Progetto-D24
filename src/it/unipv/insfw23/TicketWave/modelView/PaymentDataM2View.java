package it.unipv.insfw23.TicketWave.modelView;


import it.unipv.insfw23.TicketWave.modelController.PaymentDataController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PaymentDataM2View extends Application {
    private Label nameLabel = new Label("Nome Intestatario Carta:");
    private Label surnameLabel = new Label("Cognome Intestatario Carta:");
    private Label ncLabel = new Label("N° Carta:");
    private Label expirationLabel = new Label("Data Scadenza:");
    private Label cvcLabel = new Label("CVC:");
    private final TextField insertName = new TextField();
    private final TextField insertSurname = new TextField();
    private static TextField insertNC = new TextField();
    private static TextField insertMM = new TextField();
    private static TextField insertAA = new TextField();
    private static TextField insertcvc = new TextField();
    private List<TextField> textFields = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Image forwardarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/next_arrow.png");
        Button forwardButton = new Button();
        forwardButton.setGraphic(new ImageView(forwardarrowlogo));

        textFields.add(insertName);
        textFields.add(insertSurname);
        textFields.add(insertNC);
        textFields.add(insertMM);
        textFields.add(insertAA);
        textFields.add(insertcvc);

        // Impostazione dello stile di default per i text field
        for (TextField textField : textFields) {
            textField.setStyle("-fx-text-fill: #A9A9A9;");
        }

        // Aggiunta di testo predefinito per alcuni text field
        insertMM.setText("MM");
        insertAA.setText("AA");
        insertcvc.setText("1234");

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
        root.setBottom(forwardButton);
        BorderPane.setAlignment(forwardButton, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inserimento Dati Carta di Credito");
        primaryStage.show();

        PaymentDataController.addListeners(insertMM, insertAA, insertcvc, insertNC, forwardButton);
    }
}
