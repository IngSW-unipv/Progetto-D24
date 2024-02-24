package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TicketPageView extends Scene {
    private static final Label eventNameLabel = new Label("Nome Evento:");
    private static final Label eventDescriptionLabel = new Label("Descrizione Evento:");
    private static final Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
    private static  Button buyButton = new Button();

    // campi riempiti dal controller
    private static final Label eventNameTextField = new Label();
    private static final Label eventDescriptionTextField = new Label();
    private static final Label ticketBaseLabel = new Label("Base Tickets:");
    private static final Label ticketPremiumLabel = new Label("Premium Tickets:");
    private static final Label ticketVipLabel = new Label("Vip Tickets:");
    private static List<Label> labels = new ArrayList<>();
    private Scene scene;
    private BorderPane layout;
    private BorderPane root;


    public TicketPageView(){
        super(new BorderPane(), 1080, 600);
        initComponents();

    }

    private void initComponents() {    // la classe contiene unicamente label poichè è solo una pagina di visualizzazione, il cliente non può scriverci sopra

        // Layout dell'interfaccia utente
        BorderPane internalgrid = new BorderPane();

        labels.add(eventNameLabel);
        labels.add(eventDescriptionLabel);
        labels.add(ticketsLabel);
        labels.add(ticketBaseLabel);
        labels.add(ticketPremiumLabel);
        labels.add(ticketVipLabel);


        for (Label label : labels) {
            label.setTextFill(Color.BLACK);
        }

        HBox buttonbox= new HBox(buyButton);
        buttonbox.setPadding(new Insets(10));
        buttonbox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonbox.setSpacing(50);

        Image BuyButtonlogo = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/buyButton.png");
        buyButton.setGraphic(new ImageView(BuyButtonlogo));
        buyButton.setPrefWidth(buyButton.getWidth());
        buyButton.setPrefHeight(buyButton.getHeight());
        buyButton.setPadding(new Insets(0));
        buyButton.setStyle("-fx-background-color: rgb(255,255,255)");

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(10));
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);
        centerGrid.add(eventNameLabel, 0, 0);
        centerGrid.add(eventNameTextField, 1, 0);
        centerGrid.add(eventDescriptionLabel, 0, 1);
        centerGrid.add(eventDescriptionTextField, 1, 1);

        internalgrid.setCenter(centerGrid);

        GridPane bottomGrid = new GridPane();
        bottomGrid.setPadding(new Insets(20));
        bottomGrid.setVgap(10);
        bottomGrid.setHgap(10);
        bottomGrid.add(ticketsLabel, 0, 0);
        bottomGrid.add(ticketBaseLabel, 0, 1);
        bottomGrid.add(ticketPremiumLabel, 0, 2);
        bottomGrid.add(ticketVipLabel, 0, 3);


        internalgrid.setBottom(bottomGrid);

        BorderPane root=new BorderPane();
        root.setCenter(internalgrid);
        root.setStyle("-fx-background-color: rgb(255,255,255)");
        root.setBottom(buttonbox);
        BorderPane.setMargin(buttonbox, new Insets(30));
        BorderPane.setAlignment(buttonbox, Pos.BOTTOM_RIGHT);

        this.root=root;

        // Creazione e visualizzazione della scena
         scene = new Scene(root, 1080, 600);

        BorderPane layout= new BorderPane();
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(CustomerUpperBar.getIstance());
        setRoot(layout);

        this.layout=layout;



    }

    public static Button getBuyButton() {
        return buyButton;
    }

    public Scene getScene() {
        return scene;
    }

    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(CustomerUpperBar.getIstance());
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }

}



