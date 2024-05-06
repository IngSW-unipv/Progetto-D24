package it.unipv.insfw23.TicketWave.modelView.payment;


import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
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
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PaymentSelectionView extends Scene {
    private static RadioButton paypalButton = new RadioButton("Paypal");//paypal
    private static RadioButton mastercardButton = new RadioButton("Mastercard"); //mastercard
    private static Button nextButton = new Button();
    private static Button backButton = new Button();

    private static final Label titleLabel = new Label("TicketWave");
    private static final Label totalStringLabel = new Label("Totale:");
    private static final Label paySelectionLabel = new Label("Scegli un metodo con cui pagare:");
    private static Label totalAmountLabel = new Label();
    private static List<Label> labels = new ArrayList<>();

    private static Text errmessage = new Text("Devi prima selezionare un metodo di pagamento!");
    private Scene scene;
    private BorderPane layout;
    private Pane root;
    private boolean isviewermanager;

    public PaymentSelectionView() {
        super(new BorderPane(), 1080, 600);
        initComponents();
    }



    private void initComponents() {
        ToggleGroup paymethod = new ToggleGroup();
        paypalButton.setToggleGroup(paymethod);
        mastercardButton.setToggleGroup(paymethod);


        Image paypalLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/Paypal_logo.png");
        Image mastercardLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/Mastercard_logo.png");
        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/nextArrow.png");

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
         nextButton.setStyle("-fx-background-color: #91bad6;");
        backButton.setGraphic(backarrow);
         backButton.setStyle("-fx-background-color: #91bad6;");

        errmessage.setOpacity(0);
        errmessage.setFill(javafx.scene.paint.Color.RED);


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
        gridPane.add(paypalButton, 0, 3);
        gridPane.add(paypalImage, 1, 3);
        gridPane.add(mastercardButton, 0, 4);
        gridPane.add(mastercardImage, 1, 4);
        gridPane.add(errmessage, 0, 6);


        BorderPane.setMargin(backButtonBox, new Insets(0, 10, 10, 10)); // Margine a sinistra
        BorderPane.setMargin(nextButtonBox, new Insets(0, 10, 10, 10)); // Margine a destra


        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #91bad6;");
        root.setCenter(gridPane);
        root.setLeft(backButtonBox);
        root.setRight(nextButtonBox);


        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #91bad6;");
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(UpperBar.getIstance());
        UpperBar.getIstance().setForNoLogged();
        setRoot(layout);
        this.layout = layout;
        this.root = root;
    }

    public void setPriceComponent(double price) {
        totalAmountLabel = new Label(String.valueOf(price));
    }

    //NOTA per il metodo ReSetBars: compilando la loginview il metodo non riesce a valutare il parametro isviewermanager, le barre in questo codice vengono automaticamente
    //impostate su customer quando viene chiamato dai controller,se runno il codice di provaRunTicketPageView e viene impostato isviewermanager il reset delle barre funziona.
    // c'è un altro metodo per non portarsi appresso una variabile booleana per distinguere customer da manager?
    public void reSetBars() {
        BorderPane temp = new BorderPane();
        setRoot(temp);

        layout.setTop(UpperBar.getIstance());
        UpperBar.getIstance().setForNoLogged();
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }


    public static RadioButton getPaypalButton() {
        return paypalButton;
    }

    public static RadioButton getMastercardButton() {
        return mastercardButton;
    }

    public static Button getNextButton() {
        return nextButton;
    }

    public static Button getBackButton() {
        return backButton;
    }

    public static List<Label> getLabels() {
        return labels;
    }

    public Scene getScene() {
        return scene;
    }

    public static Text getErrmessage() {
        return errmessage;
    }

}
