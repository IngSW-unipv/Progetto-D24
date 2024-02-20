package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PaymentSelectionView extends Application {
    private static RadioButton method1Button= new RadioButton ("Paypal");//paypal
    private static  RadioButton method2Button= new RadioButton("Mastercard"); //mastercard
    private static  Button nextButton=new Button("Avanti>>");
    private static Button backButton=new Button("<<Indietro");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        //campi fissi
        Label titleLabel = new Label("TicketWave");
        Label totalStringLabel = new Label("Totale:");
        Label paySelectionLabel = new Label("Seleziona il metodo di pagamento:");

        //impostato dal controller
        Label totalAmountLabel = new Label();

        ToggleGroup paymethod = new ToggleGroup();
        method1Button.setToggleGroup(paymethod);
        method2Button.setToggleGroup(paymethod);

        Image paypalLogo = new Image("resouces/Paypal_logo.png");
        Image mastercardLogo = new Image("resouces/Mastercard_logo.png");

        // ImageView per i loghi
        ImageView paypalImage= new ImageView(paypalLogo);
        paypalImage.setFitWidth(100);
        paypalImage.setFitHeight(30);

        ImageView mastercardImage = new ImageView(mastercardLogo);
        mastercardImage.setFitWidth(80);
        mastercardImage.setFitHeight(50);

        Image backarrowlogo = new Image("resouces/next_arrow.png");
        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);
        /*
        //backarrow.setOnMouseClicked(event -> {
            // Azioni da eseguire quando si clicca sulla freccia destra
            System.out.println("Hai cliccato sulla freccia destra!");
        });
        */
        Image nextarrowlogo = new Image("resouces/back_arrow.png");
        ImageView nextarrow = new ImageView(nextarrowlogo);
        nextarrow.setFitWidth(50);
        nextarrow.setPreserveRatio(true);
        /*
        nextarrow.setOnMouseClicked(event -> {
            // Azioni da eseguire quando si clicca sulla freccia sinistra
            System.out.println("Hai cliccato sulla freccia sinistra!");
        });
        */

        HBox arrowBox = new HBox(nextarrow,backarrow);
        arrowBox.setSpacing(10);
        arrowBox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane.setAlignment(arrowBox, Pos.BOTTOM_CENTER);

        GridPane buttonGrid= new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.addRow(0,method1Button,paypalImage);
        buttonGrid.addRow(1,method2Button,mastercardImage);



        GridPane textGrid=new GridPane();
        textGrid.setPadding(new Insets(10));
        textGrid.setVgap(10);
        textGrid.setHgap(10);
        textGrid.add(totalStringLabel,0,0);
        textGrid.add(totalAmountLabel,0,1);



        //layout per bottoni di selezione
        VBox leftvbox = new VBox(titleLabel);
        leftvbox.setPadding(new Insets(10));
        leftvbox.setSpacing(10);
        leftvbox.getChildren().addAll(textGrid, paySelectionLabel, buttonGrid);
        leftvbox.setAlignment(Pos.TOP_LEFT);




        BorderPane rootPage = new BorderPane();
        rootPage.setLeft(leftvbox);
        rootPage.setBottom(arrowBox);

        rootPage.setPadding(new Insets(10));

        // Creazione e visualizzazione della scena
        Scene scene = new Scene(rootPage, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicketWave");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);

        primaryStage.show();


    }


}

