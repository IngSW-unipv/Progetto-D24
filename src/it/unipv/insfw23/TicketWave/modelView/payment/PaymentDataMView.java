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

    private final Label nameLabel = new Label("Nome Intestatario Carta:");
    private final Label surnameLabel = new Label("Cognome Intestatario Carta:");
    private final Label ncLabel = new Label("N° Carta:");
    private final Label expirationLabel = new Label("Data Scadenza (MM/YY):");
    private final Label cvcLabel = new Label("CVC:");
    private TextField insertName = new TextField();
    private TextField insertSurname = new TextField();
    private TextField insertNC = new TextField();
    private TextField insertMM = new TextField();
    private TextField insertYY = new TextField();
    private TextField insertcvc = new TextField();
    private final Button backButton = new Button();
    private final Button nextButton = new Button();
    private final Label errorLabelNC = new Label("Inserisci una carta di credito valida ");
    private final Label errorLabelMM = new Label("MM non valido");
    private final Label errorLabelYY = new Label("YY non valido");
    private final Label errorLabelCVC = new Label("CVC non valido");

    private final RadioButton usePointsButton = new RadioButton("Utilizza i tuoi WavePoints");
    private List<TextField> textFields = new ArrayList<>();
    private final List<Label> labels = new ArrayList<>();

    public PaymentDataMView() {
        super(new BorderPane(), 1080, 600);
        initComponents();
    }

    private void initComponents() {

        //aggiunta labels a lista
        labels.add(nameLabel);
        labels.add(surnameLabel);
        labels.add(ncLabel);
        labels.add(expirationLabel);
        labels.add(cvcLabel);

        //set dei label di errore
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

        //set dei label su lista
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

        //sey dei textfield su lista

        for (TextField textField : textFields) {
            textField.setFont(font);
        }


        usePointsButton.setFont(font);

        //setting nextButton
        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/NewBuyButton.png");
        ImageView nextarrow = new ImageView(nextarrowlogo);
        nextarrow.setFitWidth(200);
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

        //logo  metodo di pagamento
        Image masterPayLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/MasterPay3.png");
        ImageView masterPayImage = new ImageView(masterPayLogo);
        masterPayImage.setFitWidth(300);
        masterPayImage.setFitHeight(300);

        //setting dataInputGrid
        GridPane dataInput = new GridPane();
        dataInput.setAlignment(Pos.CENTER);
        dataInput.setHgap(10);
        dataInput.setVgap(20);
        dataInput.setPadding(new Insets(10));
        dataInput.addRow(0, nameLabel, insertName);
        dataInput.addRow(1, surnameLabel, insertSurname);
        dataInput.addRow(2, ncLabel, insertNC, errorLabelNC);
        dataInput.addRow(3, expirationLabel, insertMM, insertYY);
        dataInput.add(errorLabelMM, 1, 4);
        dataInput.add(errorLabelYY, 2, 4);
        dataInput.addRow(5, cvcLabel, insertcvc, errorLabelCVC);
        dataInput.addRow(7, usePointsButton);

        //VBox per logo
        VBox rightBox = new VBox(10, masterPayImage);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setPadding(new Insets(10));


        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        //hBox bottoni
        HBox buttonBox = new HBox(backButton,rightSpacer,nextButton);
        buttonBox.setMargin(backButton, new Insets(10, 0, 0, 0)); // Margine a sinistra
        buttonBox.setAlignment(Pos.CENTER);

       //set struttura interna
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(dataInput);
        root.setRight(rightBox);
        root.setBottom(buttonBox);

        //set struttura esterna
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

    public Label getErrorLabelNC() {
        return errorLabelNC;
    }

    public Label getErrorLabelMM() {
        return errorLabelMM;
    }

    public Label getErrorLabelYY() {
        return errorLabelYY;
    }

    public Label getErrorLabelCVC() {
        return errorLabelCVC;
    }

    public void setInsertNCText(String text) {
        this.insertNC.setText(text);
    }
}
