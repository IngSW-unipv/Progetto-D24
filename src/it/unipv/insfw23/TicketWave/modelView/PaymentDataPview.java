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
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;

public class PaymentDataPview extends Scene {

    private static Label emailLabel = new Label("Inserisci la tua E-mail:");
    private TextField inserEmail=new TextField();

    private Scene scene;
    private static Button nextButton =new Button("Avanti");
    private static  Button backButton = new Button();

    private BorderPane layout;

    private BorderPane root;


    public PaymentDataPview(){
        super(new BorderPane(), 1080, 600);
        initComponents();

    }

    private void initComponents(){

        VBox textBox= new VBox(emailLabel,inserEmail,nextButton);
        textBox.setStyle("-fx-font-size: 14px;");
        textBox.setPadding(new Insets(10));
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(50);
        inserEmail.setMaxWidth(200);

        HBox buttonBox= new HBox(backButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(50);


        BorderPane root= new BorderPane();
        root.setStyle("-fx-background-color: rgb(255,255,255)");
        root.setPadding(new Insets(10));
        root.setCenter(textBox);
        root.setBottom(buttonBox);


        BorderPane.setAlignment(emailLabel, Pos.TOP_CENTER);
        BorderPane.setAlignment(inserEmail, Pos.CENTER);

        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/backArrow.png");
        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);
        backButton.setGraphic(backarrow);
        backButton.setStyle("-fx-background-color: rgb(255,255,255)");


        scene = new Scene(root, 1080, 600);

        BorderPane layout= new BorderPane();
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(CustomerUpperBar.getIstance());

        setRoot(layout);

    }

    public static Button getBackButton() {
        return backButton;
    }

    public static Label getEmailLabel() {
        return emailLabel;
    }

    public TextField getInserEmail() {
        return inserEmail;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Scene getScene() {
        return scene;
    }

    public void reSetBars(){
        BorderPane temp = new BorderPane();
        scene.setRoot(temp);
        layout.setTop(CustomerUpperBar.getIstance());
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }





}
