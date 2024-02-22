package it.unipv.insfw23.TicketWave.modelView;


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
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class PaymentDataM2View extends Scene {

    private static  Label nameLabel = new Label("Nome Intestatario Carta:");
    private static  Label surnameLabel = new Label("Cognome Intestatario Carta:");
    private static  Label ncLabel = new Label("N° Carta:");
    private static Label expirationLabel = new Label("Data Scadenza:");
    private static Label cvcLabel = new Label("CVC:");
    private static  final TextField insertName = new TextField();
    private static  final TextField insertSurname = new TextField();
    private static final TextField insertNC = new TextField();
    private static final  TextField insertMM = new TextField();
    private static final  TextField insertYY = new TextField();
    private static final  TextField insertcvc = new TextField();
    private static  List<TextField> textFields = new ArrayList<>();
    private static  List<Label> labels = new ArrayList<>();
    private Scene scene;

    public PaymentDataM2View(){
        super(new BorderPane(), 1080, 600);
        initComponents();
    }


    private void initComponents() {

        Image forwardarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/nextArrow.png");
        Button forwardButton = new Button();
        forwardButton.setGraphic(new ImageView(forwardarrowlogo));
        forwardButton.setPrefWidth(forwardarrowlogo.getWidth());
        forwardButton.setPrefHeight(forwardarrowlogo.getHeight());
        forwardButton.setPadding(new Insets(0));
        forwardButton.setStyle("-fx-background-color: rgb(255,255,255)");

        textFields.add(insertMM);
        textFields.add(insertYY);
        textFields.add(insertcvc);


        labels.add(nameLabel);
        labels.add(surnameLabel);
        labels.add(ncLabel);
        labels.add(expirationLabel);
        labels.add(cvcLabel);

        // Impostazione del colore del testo a nero per tutti i label
        for (Label label : labels) {
            label.setTextFill(Color.BLACK);
        }


        // Aggiunta di testo predefinito per alcuni text field
        insertMM.setText("MM");
        insertYY.setText("YY");
        insertcvc.setText("1234");

        GridPane dataInput = new GridPane();
        dataInput.setAlignment(Pos.TOP_LEFT);
        dataInput.setHgap(10);
        dataInput.setVgap(10);
        dataInput.setPadding(new Insets(10));
        dataInput.addRow(0, nameLabel, insertName);
        dataInput.addRow(1, surnameLabel, insertSurname);
        dataInput.addRow(2, ncLabel, insertNC);
        dataInput.addRow(3, expirationLabel, insertMM, insertYY);
        dataInput.addRow(4, cvcLabel, insertcvc);


        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: rgb(255,255,255)");
        root.setPadding(new Insets(10));
        root.setCenter(dataInput);
        root.setBottom(forwardButton);
        BorderPane.setAlignment(forwardButton, Pos.BOTTOM_RIGHT);

        BorderPane layout= new BorderPane();
        layout.setStyle("-fx-background-color: rgb(27,84,161)");
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(ManagerUpperBar.getIstance());
        setRoot(layout);



    }
}
