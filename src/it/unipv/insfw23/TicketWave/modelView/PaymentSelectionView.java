package it.unipv.insfw23.TicketWave.modelView;

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
    private RadioButton method1Button = new RadioButton("Paypal");//paypal
    private RadioButton method2Button = new RadioButton("Mastercard"); //mastercard
    private Button nextButton = new Button();
    private Button backButton = new Button();

    private Label titleLabel = new Label("TicketWave");
    private Label totalStringLabel = new Label("Totale:");
    private Label paySelectionLabel = new Label("Seleziona il metodo di pagamento:");
    private Label totalAmountLabel = new Label();
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ToggleGroup paymethod = new ToggleGroup();
        method1Button.setToggleGroup(paymethod);
        method2Button.setToggleGroup(paymethod);

        Image paypalLogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/Paypal_logo.png");
        Image mastercardLogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/Mastercard_logo.png");
        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/back_arrow.png");
        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/next_arrow.png");

        ImageView paypalImage = new ImageView(paypalLogo);
        paypalImage.setFitWidth(100);
        paypalImage.setFitHeight(30);

        ImageView mastercardImage = new ImageView(mastercardLogo);
        mastercardImage.setFitWidth(80);
        mastercardImage.setFitHeight(50);

        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);

        ImageView nextarrow = new ImageView(nextarrowlogo);
        nextarrow.setFitWidth(50);
        nextarrow.setPreserveRatio(true);

        nextButton.setGraphic(nextarrow);
        backButton.setGraphic(backarrow);

        HBox nextButtonBox = new HBox();
        nextButtonBox.getChildren().add(nextButton);
        nextButtonBox.setAlignment(Pos.BOTTOM_RIGHT);

        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().add(backButton);
        backButtonBox.setAlignment(Pos.BOTTOM_LEFT);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(titleLabel, 0, 0, 2, 1);
        gridPane.add(totalStringLabel, 0, 1);
        gridPane.add(totalAmountLabel, 1, 1);
        gridPane.add(paySelectionLabel, 0, 2, 2, 1);
        gridPane.add(method1Button, 0, 3);
        gridPane.add(paypalImage, 1, 3);
        gridPane.add(method2Button, 0, 4);
        gridPane.add(mastercardImage, 1, 4);

        BorderPane.setMargin(backButtonBox, new Insets(0, 10, 10, 10)); // Margine a sinistra
        BorderPane.setMargin(nextButtonBox, new Insets(0, 10, 10, 10)); // Margine a destra

        BorderPane rootPage = new BorderPane();
        rootPage.setCenter(gridPane);
        rootPage.setLeft(backButtonBox);
        rootPage.setRight(nextButtonBox);

        scene = new Scene(rootPage, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicketWave");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }
}
