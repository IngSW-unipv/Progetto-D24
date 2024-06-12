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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PaymentSelectionView extends Scene {

    private final Font font = Font.font("Helvetica", FontWeight.BOLD, 16);
    private final RadioButton paypolButton = new RadioButton("PayPol");//paypal
    private final RadioButton masterPayButton = new RadioButton("MasterPay"); //mastercard
    private final Button nextButton = new Button();
    private final  Button backButton = new Button();


    private  final Label totalStringLabel = new Label("Totale:");
    private  final Label paySelectionLabel = new Label("Scegli un metodo con cui pagare:");
    private final Label totalAmountLabel=new Label();
    private final Label numberOfTicketLabel = new Label();
    private final List<Label> labels = new ArrayList<>();

    private final Text errmessage = new Text("Devi prima selezionare un metodo di pagamento!");
    private Scene scene;
    private BorderPane layout;
    private Pane root;

    public PaymentSelectionView() {
        super(new BorderPane(), 1080, 600);
        initComponents();
    }



    private void initComponents() {
        ToggleGroup paymethod = new ToggleGroup();
        paypolButton.setToggleGroup(paymethod);
        masterPayButton.setToggleGroup(paymethod);

        
        numberOfTicketLabel.setFont(font);
        totalStringLabel.setFont(font);
        totalAmountLabel.setFont(font);
        paySelectionLabel.setFont(font);
        errmessage.setFont(font);
        paypolButton.setFont(font);
        masterPayButton.setFont(font);


        Image payPolLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/PayPol.png");
        Image masterPayLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/MasterPay3.png");
        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
        Image nextarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/nextArrow.png");

        ImageView payPolImage = new ImageView(payPolLogo);
        payPolImage.setFitWidth(100);
        payPolImage.setFitHeight(100);

        ImageView masterPayImage = new ImageView(masterPayLogo);
        masterPayImage.setFitWidth(120);
        masterPayImage.setFitHeight(120);

        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);
        backButton.setGraphic(backarrow);
        backButton.setStyle("-fx-background-color: #91bad6;");


        ImageView nextarrow = new ImageView(nextarrowlogo);
        nextarrow.setFitWidth(50);
        nextarrow.setPreserveRatio(true);
        nextButton.setGraphic(nextarrow);
        nextButton.setStyle("-fx-background-color: #91bad6;");



        errmessage.setOpacity(0);
        errmessage.setFill(javafx.scene.paint.Color.RED);


        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS); // Consente a leftSpacer di espandersi per riempire lo spazio disponibile

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS); // Consente a rightSpacer di espandersi per riempire lo spazio disponibile// Imposta un margine di 10 unità a destra del backButton
        // Creazione di un HBox per contenere i bottoni e le Region vuote
        HBox buttonBox = new HBox( backButton, rightSpacer, nextButton);
        buttonBox.setMargin(backButton, new Insets(0, 10, 10, 10)); // Margine a sinistra
        buttonBox.setMargin(nextButton, new Insets(0, 10, 10, 10)); // Margine a destra

        buttonBox.setSpacing(50); // Spazio tra i bottoni
        buttonBox.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(totalStringLabel, 0, 1);
        gridPane.add(totalAmountLabel, 0, 2);
        gridPane.add(numberOfTicketLabel, 1, 2);
        gridPane.add(paySelectionLabel, 0, 3);
        gridPane.add(paypolButton, 0, 4);
        gridPane.add(payPolImage, 1, 4);
        gridPane.add(masterPayButton, 0, 5);
        gridPane.add(masterPayImage, 1, 5);
        gridPane.add(errmessage, 0, 6);





        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #91bad6;");
        root.setCenter(gridPane);
        root.setBottom(buttonBox);

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
        totalAmountLabel.setText("€ " + String.valueOf(price));
    }
    
    public void setTicketNumber(int number) {
    	numberOfTicketLabel.setText("("+number+((number == 1)?" biglietto)":" biglietti)"));
    }
    
    public double getPrice() {
    	String pricestring = totalAmountLabel.getText().substring(2);
    	return Double.parseDouble(pricestring);
    }

    public void reSetBars() {
        BorderPane temp = new BorderPane();
        setRoot(temp);

        layout.setTop(UpperBar.getIstance());
        UpperBar.getIstance().setForNoLogged();
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }


    public  RadioButton getPaypolButton() {
        return paypolButton;
    }

    public RadioButton getMasterPayButton() {
        return masterPayButton;
    }

    public  Button getNextButton() {
        return nextButton;
    }

    public  Button getBackButton() {
        return backButton;
    }

    public  List<Label> getLabels() {
        return labels;
    }

    public Scene getScene() {
        return scene;
    }

    public  Text getErrmessage() {
        return errmessage;
    }

}
