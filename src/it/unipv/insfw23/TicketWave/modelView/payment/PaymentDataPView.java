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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaymentDataPView extends Scene {
    private final Font font = Font.font("Helvetica", FontWeight.BOLD, 15);
    private final Label emailLabel = new Label("Inserisci la tua E-mail:");
    private TextField insertEmail =new TextField();

    private Scene scene;
    private final  Button nextButton =new Button();
    private  final  Button backButton = new Button();

    private final   RadioButton usePointsButton= new RadioButton("Utilizza i tuoi WavePoints");


    private final Label errorLabel=new Label("Inserisci una mail valida");


    public PaymentDataPView(){
        super(new BorderPane(), 1080, 600);
        initComponents();

    }

    private void initComponents(){

        emailLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        insertEmail.setFont(font);
        errorLabel.setFont(font);
        errorLabel.setStyle("-fx-text-fill: red;");

        usePointsButton.setFont(font);

        VBox textBox= new VBox(emailLabel, insertEmail, errorLabel, usePointsButton);
        textBox.setStyle("-fx-font-size: 14px;");
        textBox.setPadding(new Insets(40));
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(50);
        insertEmail.setMaxWidth(200);
        errorLabel.setVisible(false);

        usePointsButton.setAlignment(Pos.BOTTOM_CENTER);

        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);
        backButton.setGraphic(backarrow);
        backButton.setStyle("-fx-background-color: #91bad6;");

        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/nextArrow.png");
        ImageView nextarrow = new ImageView(nextarrowlogo);
        nextarrow.setFitWidth(50);
        nextarrow.setPreserveRatio(true);
        nextButton.setGraphic(nextarrow);
        nextButton.setStyle("-fx-background-color: #91bad6;");

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS); // Consente a leftSpacer di espandersi per riempire lo spazio disponibile

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS); // Consente a rightSpacer di espandersi per riempire lo spazio disponibile// Imposta un margine di 10 unità a destra del backButton
        // Creazione di un HBox per contenere i bottoni e le Region vuote
        HBox buttonBox = new HBox(backButton, rightSpacer, nextButton);
        buttonBox.setMargin(backButton, new Insets(0, 0, 0, 0)); // Margine a sinistra
        buttonBox.setMargin(nextButton, new Insets(0, 0, 0, 0)); // Margine a destra

        buttonBox.setSpacing(50); // Spazio tra i bottoni
        buttonBox.setAlignment(Pos.CENTER);

        Image payPolLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/PayPol.png");
        ImageView payPolImage = new ImageView(payPolLogo);
        payPolImage.setFitWidth(300);
        payPolImage.setFitHeight(300);



        BorderPane root= new BorderPane();
        root.setStyle("-fx-background-color: #91bad6;");
        root.setPadding(new Insets(10));
        root.setCenter(textBox);
        root.setRight(payPolImage);
        root.setBottom(buttonBox);
        BorderPane.setAlignment(backButton,Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(nextButton,Pos.BOTTOM_RIGHT);

        // root.setBottom(backButton);



        BorderPane.setAlignment(emailLabel, Pos.TOP_CENTER);
        BorderPane.setAlignment(insertEmail, Pos.CENTER);




        //Setting external layout
        BorderPane layout= new BorderPane();
        layout.setCenter(root);
        layout.setTop(UpperBar.getIstance());
        UpperBar.getIstance().setForNoLogged();
        layout.setBottom(LowerBar.getInstance());


        setRoot(layout);

    }

    public  Button getBackButton() {
        return backButton;
    }



    public  Label getEmailLabel() {
        return emailLabel;
    }

    public TextField getInsertEmail() {
        return insertEmail;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Scene getScene() {
        return scene;
    }

    public  RadioButton getUsePointsButton() {
        return usePointsButton;
    }


    public  Label getErrorLabel() {
        return errorLabel;
    }
}