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

        private final Font font = Font.font("Helvetica", FontWeight.BOLD, 15);

        private final  Label nameLabel = new Label("Nome Intestatario Carta:");
        private final  Label surnameLabel = new Label("Cognome Intestatario Carta:");
        private final Label ncLabel = new Label("N° Carta:");
        private final Label expirationLabel = new Label("Data Scadenza (MM/YY):");
        private final Label cvcLabel = new Label("CVC:");
        private TextField insertName = new TextField();
        private TextField insertSurname = new TextField();
        private TextField insertNC = new TextField();
        private TextField insertMM = new TextField();
        private TextField insertYY = new TextField();
        private TextField insertcvc = new TextField();
        private final  Button backButton = new Button();
        private final  Button nextButton = new Button();
        private final Label errorLabelNC = new Label("Inserisci una carta di credito valida ");
        private final Label errorLabelMM = new Label("MM non valido");
        private final Label errorLabelYY = new Label("YY non valido");
        private final Label errorLabelCVC = new Label("CVC non valido");

        private final RadioButton usePointsButton = new RadioButton("Utilizza i tuoi WavePoints");
        private  List<TextField> textFields = new ArrayList<>();
        private final List<Label> labels = new ArrayList<>();
        private Scene scene;

        public PaymentDataMView() {
            super(new BorderPane(), 1080, 600);
            initComponents();
        }

        private void initComponents() {
            labels.add(nameLabel);
            labels.add(surnameLabel);
            labels.add(ncLabel);
            labels.add(expirationLabel);
            labels.add(cvcLabel);

            errorLabelNC.setFont(font);
            errorLabelNC.setStyle("-fx-text-fill: red;");
            errorLabelNC.setVisible(false);

            errorLabelMM.setFont(font);
            errorLabelMM.setStyle("-fx-text-fill: red;");
            errorLabelMM.setVisible(false);

            errorLabelYY.setFont(font);
            errorLabelYY.setStyle("-fx-text-fill: red;");
            errorLabelYY.setVisible(false);

            errorLabelCVC.setFont(font);
            errorLabelCVC.setStyle("-fx-text-fill: red;");
            errorLabelCVC.setVisible(false);

            // Setting the text color to black for all labels
            for (Label label : labels) {
                label.setFont(font);
                label.setTextFill(Color.BLACK);
            }

            textFields.add(insertMM);
            textFields.add(insertYY);
            textFields.add(insertcvc);
            textFields.add(insertName);
            textFields.add(insertSurname);
            textFields.add(insertNC);

            // Setting the text color to black for all TextFields
            for (TextField textField : textFields) {
                textField.setFont(font);
            }

            //setting color for usePointsButton
            usePointsButton.setFont(font);

            //setting nextButton
            Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/nextArrow.png");
            ImageView nextarrow = new ImageView(nextarrowlogo);
            nextarrow.setFitWidth(50);
            nextarrow.setPreserveRatio(true);
            nextButton.setGraphic(nextarrow);
            nextButton.setStyle("-fx-background-color: #91bad6;");

            //setting backButton
            Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
            ImageView backarrow = new ImageView(backarrowlogo);
            backarrow.setFitWidth(50);
            backarrow.setPreserveRatio(true);
            backButton.setGraphic(backarrow);
            backButton.setStyle("-fx-background-color: #91bad6;");

            Image masterPayLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/MasterPay3.png");
            ImageView masterPayImage = new ImageView(masterPayLogo);
            masterPayImage.setFitWidth(300);
            masterPayImage.setFitHeight(300);

            Region leftSpacer = new Region();
            HBox.setHgrow(leftSpacer, Priority.ALWAYS); // Allows leftSpacer to expand to fill available space
            Region rightSpacer = new Region();
            HBox.setHgrow(rightSpacer, Priority.ALWAYS); // Allows rightSpacer  to expand to fill available space

            //setting dataInputGrid
            GridPane dataInput = new GridPane();
            dataInput.setAlignment(Pos.TOP_LEFT);
            dataInput.setHgap(10);
            dataInput.setVgap(30);
            dataInput.setPadding(new Insets(10));
            dataInput.addRow(0, nameLabel, insertName);
            dataInput.addRow(1, surnameLabel, insertSurname);
            dataInput.addRow(2, ncLabel, insertNC, errorLabelNC);
            dataInput.addRow(3, expirationLabel, insertMM, insertYY, errorLabelMM, errorLabelYY);
            dataInput.addRow(4, cvcLabel, insertcvc, errorLabelCVC);
            dataInput.addRow(6, usePointsButton);

            // Setting of an HBox to contain the buttons and empty Regions
            HBox buttonBox = new HBox(backButton, rightSpacer, nextButton);
            buttonBox.setMargin(backButton, new Insets(0, 0, 0, 0)); // Margine a sinistra
            buttonBox.setMargin(nextButton, new Insets(0, 0, 0, 0)); // Margine a destra

            buttonBox.setSpacing(50); // Spazio tra i bottoni
            buttonBox.setAlignment(Pos.CENTER);

            //creating external structure
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));
            root.setCenter(dataInput);
            root.setRight(masterPayImage);
            root.setBottom(buttonBox);

            //Setting external layout
            BorderPane layout = new BorderPane();
            layout.setStyle("-fx-background-color: #91bad6;");
            layout.setCenter(root);
            layout.setTop(UpperBar.getIstance());
            UpperBar.getIstance().setForNoLogged();
            layout.setBottom(LowerBar.getInstance());
            setRoot(layout);
        }

        public Button getBackButton() {
            return backButton;
        }

        public Button getNextButton() {
            return nextButton;
        }

        public TextField getInsertcvc() {
            return insertcvc;
        }

        public TextField getInsertNC() {
            return insertNC;
        }

        public TextField getInsertMM() {
            return insertMM;
        }

        public TextField getInsertYY() {
            return insertYY;
        }

        public RadioButton getUsePointsButton() {
            return usePointsButton;
        }

        public  Label getErrorLabelNC() {
            return errorLabelNC;
        }

        public  Label getErrorLabelMM() {
            return errorLabelMM;
        }

        public  Label getErrorLabelYY() {
            return errorLabelYY;
        }

        public  Label getErrorLabelCVC() {
            return errorLabelCVC;
        }


    }

