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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaymentDataPView extends Scene {
    private final Font font = Font.font("Helvetica", FontWeight.BOLD, 15);
    private static Label emailLabel = new Label("Inserisci la tua E-mail:");
    private TextField insertEmail =new TextField();

    private Scene scene;
    private static Button nextButton =new Button("Avanti");
    private static  Button backButton = new Button();

    private static RadioButton usePointsButton= new RadioButton("Utilizza i tuoi WavePoints");

    private BorderPane layout;

    private BorderPane root;


    public PaymentDataPView(){
        super(new BorderPane(), 1080, 600);
        initComponents();

    }

    private void initComponents(){

        emailLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        insertEmail.setFont(font);
        usePointsButton.setFont(font);

        VBox textBox= new VBox(emailLabel, insertEmail, usePointsButton,nextButton);
        textBox.setStyle("-fx-font-size: 14px;");
        textBox.setPadding(new Insets(10));
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(50);
        insertEmail.setMaxWidth(200);
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



        BorderPane root= new BorderPane();
        root.setStyle("-fx-background-color: #91bad6;");
        root.setPadding(new Insets(10));
        root.setCenter(textBox);
        BorderPane.setAlignment(backButton,Pos.BOTTOM_LEFT);
        root.setBottom(backButton);


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

    public static Button getBackButton() {
        return backButton;
    }



    public static Label getEmailLabel() {
        return emailLabel;
    }

    public TextField getInsertEmail() {
        return insertEmail;
    }

    public Button getForwardButtonButton() {
        return nextButton;
    }

    public Scene getScene() {
        return scene;
    }

    public static RadioButton getUsePointsButton() {
        return usePointsButton;
    }





}
