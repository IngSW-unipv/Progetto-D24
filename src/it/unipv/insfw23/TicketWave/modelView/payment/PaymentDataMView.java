package it.unipv.insfw23.TicketWave.modelView.payment;


import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class PaymentDataMView extends Scene {
    private final Font font = Font.font("Helvetica", FontWeight.NORMAL, 13);

    private static  Label nameLabel = new Label("Nome Intestatario Carta:");
    private static  Label surnameLabel = new Label("Cognome Intestatario Carta:");
    private static  Label ncLabel = new Label("N° Carta:");
    private static Label expirationLabel = new Label("Data Scadenza:");
    private static Label cvcLabel = new Label("CVC:");
    private static  final TextField insertName = new TextField();
    private static  final TextField insertSurname = new TextField();
    private static TextField insertNC = new TextField();
    private static  TextField insertMM = new TextField();
    private static   TextField insertYY = new TextField();
    private static  TextField insertcvc = new TextField();
    private static  Button backButton = new Button();
    private static Button nextButton = new Button();

    private static RadioButton usePointsButton= new RadioButton("Utilizza i tuoi WavePoints");
    private static  List<TextField> textFields = new ArrayList<>();
    private static  List<Label> labels = new ArrayList<>();
    private Scene scene;

    public PaymentDataMView(){
        super(new BorderPane(), 1080, 600);
        initComponents();
    }


    private void initComponents() {



        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/nextArrow.png");
        ImageView nextarrow = new ImageView(nextarrowlogo);
        nextarrow.setFitWidth(50);
        nextarrow.setPreserveRatio(true);
        nextButton.setGraphic(nextarrow);
        nextButton.setStyle("-fx-background-color: #91bad6;");


        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);
        backButton.setGraphic(backarrow);
        backButton.setStyle("-fx-background-color: #91bad6;");

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS); // Consente a leftSpacer di espandersi per riempire lo spazio disponibile

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS); // Consente a rightSpacer di espandersi per riempire lo spazio disponibile// Imposta un margine di 10 unità a destra del backButton
        // Creazione di un HBox per contenere i bottoni e le Region vuote
        HBox buttonBox = new HBox( backButton, rightSpacer, nextButton);
        buttonBox.setMargin(backButton, new Insets(0, 0, 0, 0)); // Margine a sinistra
        buttonBox.setMargin(nextButton, new Insets(0, 0, 0, 0)); // Margine a destra

        buttonBox.setSpacing(50); // Spazio tra i bottoni
        buttonBox.setAlignment(Pos.CENTER);

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
            label.setFont(font);
            label.setTextFill(Color.BLACK);
        }

        for (TextField textField : textFields) {
            textField.setFont(font);

        }
        usePointsButton.setFont(font);



        
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
        dataInput.addRow(6,usePointsButton);


        BorderPane root = new BorderPane();
        //root.setStyle("-fx-background-color: #def1fa;");
        root.setPadding(new Insets(10));
        root.setCenter(dataInput);
        //root.setRight(nextButton);
        //root.setLeft(backButton);
        root.setBottom(buttonBox);
        //BorderPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
        //BorderPane.setAlignment(backButton,Pos.BOTTOM_LEFT);


        BorderPane layout= new BorderPane();
        layout.setStyle("-fx-background-color: #91bad6;");
        layout.setCenter(root);
        layout.setTop(UpperBar.getIstance());
        UpperBar.getIstance().setForNoLogged();
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);



    }


    public static Button getBackButton() {
        return backButton;
    }

    public static Label getCvcLabel() {
        return cvcLabel;
    }

    public static Label getNameLabel() {
        return nameLabel;
    }

    public static Label getSurnameLabel() {
        return surnameLabel;
    }

    public static Label getNcLabel() {
        return ncLabel;
    }

    public static Label getExpirationLabel() {
        return expirationLabel;
    }

    public static Button getNextButton() {
        return nextButton;
    }

    public static List<TextField> getTextFields() {
        return textFields;
    }

    public static List<Label> getLabels() {
        return labels;
    }

    public Scene getScene() {
        return scene;
    }

    public static TextField getInsertcvc() {
        return insertcvc;
    }

    public static TextField getInsertNC() {
        return insertNC;
    }

    public static TextField getInsertMM() {
        return insertMM;
    }

    public static TextField getInsertYY() {
        return insertYY;
    }

    public static RadioButton getUsePointsButton() {
        return usePointsButton;
    }
}
