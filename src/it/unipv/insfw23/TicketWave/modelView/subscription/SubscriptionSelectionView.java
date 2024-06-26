package it.unipv.insfw23.TicketWave.modelView.subscription;

import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class SubscriptionSelectionView extends Scene implements IResettableScene{

    private BorderPane layout;
    private BorderPane contenuto;
    private StackPane bottonePrimaSub;
    private StackPane bottoneSecondaSub;
    private StackPane bottoneTerzaSub;
    private static Button backButton = new Button();
    
    private final int MAX_EVENTS_FOR_FREE_SUB = 1;
	private final int MAX_EVENTS_FOR_BASE_SUB = 5;
	

    public SubscriptionSelectionView() {
        super(new BorderPane(), 1080, 600);
        init();
    }

    public void init(){

        contenuto = new BorderPane();
        contenuto.setStyle("-fx-background-color: #91bad6;");

        Image backarrowlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
        ImageView backarrow = new ImageView(backarrowlogo);
        backarrow.setFitWidth(50);
        backarrow.setPreserveRatio(true);
        backButton.setGraphic(backarrow);
        backButton.setStyle("-fx-background-color: #91bad6;");

        BorderPane primaSub = new BorderPane();
        primaSub.setStyle("-fx-background-color: #2E5984; -fx-background-radius: 20;");
        BorderPane secondaSub = new BorderPane();
        secondaSub.setStyle("-fx-background-color: #2E5984; -fx-background-radius: 20;");
        BorderPane terzaSub = new BorderPane();
        terzaSub.setStyle("-fx-background-color: #2E5984; -fx-background-radius: 20;");

        BorderPane.setMargin(primaSub, new Insets(30));
        BorderPane.setMargin(secondaSub, new Insets(30));
        BorderPane.setMargin(terzaSub, new Insets(30));
        primaSub.setPadding(new Insets(20));
        secondaSub.setPadding(new Insets(20));
        terzaSub.setPadding(new Insets(20));


        Label nome1 = new Label("Nessun Abbonamento");
        nome1.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
        nome1.setTextFill(Color.WHITE);
        primaSub.setTop(nome1);

        Label descr1 = new Label("-Possibilità di pubblicare "+MAX_EVENTS_FOR_FREE_SUB+" evento\n"+" al mese");
        descr1.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 14));
        descr1.setTextFill(Color.WHITE);
        primaSub.setCenter(descr1);

        Label prezzo1 = new Label("0,00 €");
        prezzo1.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
        prezzo1.setTextFill(Color.WHITE);
        primaSub.setBottom(prezzo1);

        Label nome2 = new Label("Abbonamento Base");
        nome2.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
        nome2.setTextFill(Color.WHITE);
        secondaSub.setTop(nome2);

        Label descr2 = new Label("-Possibilità di pubblicare "+MAX_EVENTS_FOR_BASE_SUB+" eventi\n"+" al mese\n"+"-Invio di notifiche agli utenti\n"+" residenti nella stessa provincia\n"+" degli eventi pubblicati");
        descr2.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 14));
        descr2.setTextFill(Color.WHITE);
        secondaSub.setCenter(descr2);

        Label prezzo2 = new Label("8,99 €");
        prezzo2.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
        prezzo2.setTextFill(Color.WHITE);
        secondaSub.setBottom(prezzo2);


        Label nome3 = new Label("   Abbonamento Premium");
        nome3.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
        nome3.setTextFill(Color.WHITE);
        terzaSub.setTop(nome3);

        Label descr3 = new Label("-Possibilità di pubblicare un numero\n"+" infinito di eventi al mese\n"+"-Invio di notifiche agli utenti\n"+" residenti nella stessa provincia\n"+" degli eventi pubblicati\n"+"-Invio di notifiche agli utenti\n"+" che hanno selezionato il genere\n"+" dell'evento tra i preferiti");			
        descr3.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 14));
        descr3.setTextFill(Color.WHITE);
        terzaSub.setCenter(descr3);

        Label prezzo3 = new Label("14,99 €");
        prezzo3.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
        prezzo3.setTextFill(Color.WHITE);
        terzaSub.setBottom(prezzo3);

        BorderPane.setAlignment(nome1, Pos.TOP_CENTER);
        BorderPane.setAlignment(nome2, Pos.TOP_CENTER);
        BorderPane.setAlignment(nome3, Pos.TOP_CENTER);

        BorderPane.setAlignment(descr1, Pos.CENTER);
        BorderPane.setAlignment(descr2, Pos.CENTER);
        BorderPane.setAlignment(descr3, Pos.CENTER);

        BorderPane.setAlignment(prezzo1, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(prezzo2, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(prezzo3, Pos.BOTTOM_RIGHT);

        primaSub.setPrefSize(500, 500);
        secondaSub.setPrefSize(500, 500);
        terzaSub.setPrefSize(500, 500);

        primaSub.setMaxSize(300, 250);
        secondaSub.setMaxSize(300, 250);
        terzaSub.setMaxSize(300, 250);

       bottonePrimaSub = new StackPane(primaSub);
       bottoneSecondaSub = new StackPane(secondaSub);
       bottoneTerzaSub = new StackPane(terzaSub);

        bottonePrimaSub.setPadding(new Insets(20));
        bottoneSecondaSub.setPadding(new Insets(20));
        bottoneTerzaSub.setPadding(new Insets(20));

        contenuto.setLeft(bottonePrimaSub);
        contenuto.setCenter(bottoneSecondaSub);
        contenuto.setRight(bottoneTerzaSub);
        contenuto.setBottom(backButton);
        BorderPane.setMargin(backButton, new Insets(0, 10, 10, 10));

        BorderPane.setAlignment(primaSub, Pos.CENTER_LEFT);
        BorderPane.setAlignment(secondaSub, Pos.CENTER);
        BorderPane.setAlignment(terzaSub, Pos.CENTER_RIGHT);


        layout = (BorderPane)getRoot();
        UpperBar.getIstance().setForNoLogged();
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());

        

       
    }

    public void reSetBars(){

        BorderPane temp = new BorderPane();
        setRoot(temp);
        UpperBar.getIstance().setForNoLogged();
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);

    }

    public StackPane getBottonePrimaSub(){
        return bottonePrimaSub;
    }

    public StackPane getBottoneSecondaSub() {
        return bottoneSecondaSub;
    }

    public StackPane getBottoneTerzaSub() {
        return bottoneTerzaSub;
    }

    public Button getBackButton() {
        return backButton;
    }

    public double getPricePrimaSub() {
        return Double.parseDouble(((Label) ((BorderPane) bottonePrimaSub.getChildren().get(0)).getBottom()).getText().replace(" €", "").replace(",", "."));
    }

    public double getPriceSecondaSub() {
        return Double.parseDouble(((Label) ((BorderPane) bottoneSecondaSub.getChildren().get(0)).getBottom()).getText().replace(" €", "").replace(",", "."));
    }

    public double getPriceTerzaSub() {
        return Double.parseDouble(((Label) ((BorderPane) bottoneTerzaSub.getChildren().get(0)).getBottom()).getText().replace(" €", "").replace(",", "."));
    }
}







