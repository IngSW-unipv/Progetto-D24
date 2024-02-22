package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PaymentSelectionView extends Scene {
    private static  RadioButton method1Button = new RadioButton("Paypal");//paypal
    private static  RadioButton method2Button = new RadioButton("Mastercard"); //mastercard
    private static  Button nextButton = new Button();
    private static  Button backButton = new Button();

    private static final  Label titleLabel = new Label("TicketWave");
    private static final Label totalStringLabel = new Label("Totale:");
    private static final Label paySelectionLabel = new Label("Seleziona il metodo di pagamento:");
    private static final Label totalAmountLabel = new Label();
    private static List<Label> labels = new ArrayList<>();
    private Scene scene;

    public PaymentSelectionView(){
        super(new BorderPane(), 1080, 600);
        initComponents();
    }

    private void initComponents() {
        ToggleGroup paymethod = new ToggleGroup();
        method1Button.setToggleGroup(paymethod);
        method2Button.setToggleGroup(paymethod);


        Image paypalLogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/Paypal_logo.png");
        Image mastercardLogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/Mastercard_logo.png");
        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/backArrow.png");
        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/nextArrow.png");

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

        // da mettere nei controller!!!!!!
        /*
        private void setTextFieldsTextColor(){
            // Accedi ai nodi figlio del contenitore della scena
            for (Node node : ((BorderPane) getRoot()).getChildren()) {
                // Controlla se il nodo è un'etichetta (Label)
                if (node instanceof Label) {
                    ((Label) node).setTextFill(Color.BLACK);
                }
            }

        }
*/

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

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: rgb(255,255,255)");
        root.setCenter(gridPane);
        root.setLeft(backButtonBox);
        root.setRight(nextButtonBox);

        scene = new Scene(root, 800, 400);

        BorderPane layout= new BorderPane();
        layout.setStyle("-fx-background-color: rgb(27,84,161)");
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(CustomerUpperBar.getIstance());
        setRoot(layout);
    }
}
