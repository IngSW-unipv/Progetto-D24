package it.unipv.insfw23.TicketWave.modelView.subscription;

<<<<<<< HEAD
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
=======


import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.application.Application;
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
<<<<<<< HEAD
=======
import javafx.scene.image.Image;
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
<<<<<<< HEAD
=======
import javafx.stage.Stage;
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956

public class SubscriptionSelectionView extends Scene {

    private BorderPane layout;
    private Pane contenuto;
    private StackPane bottonePrimaSub;
    private StackPane bottoneSecondaSub;
    private StackPane bottoneTerzaSub;

    public SubscriptionSelectionView() {
        super(new BorderPane(), 1080, 600);
        init();
    }

    public void init(){

        BorderPane contenuto = new BorderPane();
<<<<<<< HEAD
       contenuto.setStyle("-fx-background-color: #def1fa;");

        BorderPane primaSub = new BorderPane();
        primaSub.setStyle("-fx-background-color: rgba(103,186,255); -fx-background-radius: 20;");
        BorderPane secondaSub = new BorderPane();
        secondaSub.setStyle("-fx-background-color: rgb(103,186,255); -fx-background-radius: 20;");
        BorderPane terzaSub = new BorderPane();
        terzaSub.setStyle("-fx-background-color: rgba(103,186,255); -fx-background-radius: 20;");
=======
        contenuto.setStyle("-fx-background-color: #91bad6;");

        BorderPane primaSub = new BorderPane();
        primaSub.setStyle("-fx-background-color: #2E5984; -fx-background-radius: 20;");
        BorderPane secondaSub = new BorderPane();
        secondaSub.setStyle("-fx-background-color: #2E5984; -fx-background-radius: 20;");
        BorderPane terzaSub = new BorderPane();
        terzaSub.setStyle("-fx-background-color: #2E5984; -fx-background-radius: 20;");
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956

        BorderPane.setMargin(primaSub, new Insets(30));
        BorderPane.setMargin(secondaSub, new Insets(30));
        BorderPane.setMargin(terzaSub, new Insets(30));
        primaSub.setPadding(new Insets(20));
        secondaSub.setPadding(new Insets(20));
        terzaSub.setPadding(new Insets(20));


        Label nome1 = new Label("Nessun Abbonamento");
<<<<<<< HEAD
        nome1.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        nome1.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        nome1.setTextFill(Color.BLACK);
        primaSub.setTop(nome1);

        Label descr1 = new Label("Descrizione 1");
<<<<<<< HEAD
        descr1.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        descr1.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        descr1.setTextFill(Color.BLACK);
        primaSub.setCenter(descr1);

        Label prezzo1 = new Label("0,00 $");
<<<<<<< HEAD
        prezzo1.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        prezzo1.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        prezzo1.setTextFill(Color.BLACK);
        primaSub.setBottom(prezzo1);

        Label nome2 = new Label("Abbonamento Base");
<<<<<<< HEAD
        nome2.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        nome2.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        nome2.setTextFill(Color.BLACK);
        secondaSub.setTop(nome2);

        Label descr2 = new Label("Descrizione 2");
<<<<<<< HEAD
        descr2.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        descr2.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        descr2.setTextFill(Color.BLACK);
        secondaSub.setCenter(descr2);

        Label prezzo2 = new Label("8,99 $");
<<<<<<< HEAD
        prezzo2.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        prezzo2.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        prezzo2.setTextFill(Color.BLACK);
        secondaSub.setBottom(prezzo2);


        Label nome3 = new Label("   Abbonamento Premium");
<<<<<<< HEAD
        nome3.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        nome3.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        nome3.setTextFill(Color.BLACK);
        terzaSub.setTop(nome3);

        Label descr3 = new Label("Descrizione 3");
<<<<<<< HEAD
        descr3.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        descr3.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        descr3.setTextFill(Color.BLACK);
        terzaSub.setCenter(descr3);

        Label prezzo3 = new Label("14,99 $");
<<<<<<< HEAD
        prezzo3.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 20));
=======
        prezzo3.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
        prezzo3.setTextFill(Color.BLACK);
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

<<<<<<< HEAD
       bottonePrimaSub = new StackPane(primaSub);
       bottoneSecondaSub = new StackPane(secondaSub);
       bottoneTerzaSub = new StackPane(terzaSub);
=======
        bottonePrimaSub = new StackPane(primaSub);
        bottoneSecondaSub = new StackPane(secondaSub);
        bottoneTerzaSub = new StackPane(terzaSub);
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956

        bottonePrimaSub.setPadding(new Insets(20));
        bottoneSecondaSub.setPadding(new Insets(20));
        bottoneTerzaSub.setPadding(new Insets(20));

        contenuto.setLeft(bottonePrimaSub);
        contenuto.setCenter(bottoneSecondaSub);
        contenuto.setRight(bottoneTerzaSub);

        BorderPane.setAlignment(primaSub, Pos.CENTER_LEFT);
        BorderPane.setAlignment(secondaSub, Pos.CENTER);
        BorderPane.setAlignment(terzaSub, Pos.CENTER_RIGHT);


        BorderPane layout = new BorderPane();
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());

        this.contenuto = contenuto;
        this.layout = layout;
        this.bottonePrimaSub=bottonePrimaSub;
        this.bottoneSecondaSub=bottoneSecondaSub;
        this.bottoneTerzaSub=bottoneTerzaSub;

        setRoot(layout);
    }

    public void reSetBars(){

        BorderPane temp = new BorderPane();
        setRoot(temp);
<<<<<<< HEAD
        UpperBar.getIstance().setForManager();
=======
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
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

<<<<<<< HEAD
}



=======
}
>>>>>>> 24481fa7b4e4944187c3a3d0b9f4b46bd9ebd956
