package it.unipv.insfw23.TicketWave.modelView.ticket;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;

public class TicketPageView extends Scene {
    private static final Label eventNameLabel = new Label("Nome Evento:");
    private static final Label eventDescriptionLabel = new Label("Descrizione Evento:");
    private static final Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
    private static  Button buyButton = new Button();
    private static  ToggleGroup priceselection = new ToggleGroup();

    // campi riempiti dal controller
    private static Label eventNameTextField = new Label();
    private static Label eventDescriptionTextField = new Label();
    private static final Label ticketBaseLabel = new Label("Base Tickets:");
    private static final Label ticketPremiumLabel = new Label("Premium Tickets:");
    private static final Label ticketVipLabel = new Label("Vip Tickets:");
    private static Label ticketBaseTextField = new Label();
    private static Label ticketPremiumTextField = new Label();
    private static Label ticketVipTextField = new Label();
    private static List<Label> labels = new ArrayList<>();
    private static Label basePriceTextField = new Label();
    private static Label premiumPriceTextField = new Label();
    private static Label vipPriceTextField = new Label();
    private static final RadioButton basePricebutton = new RadioButton();
    private static final RadioButton premiumPricebutton = new RadioButton();
    private static final RadioButton vipPricebutton = new RadioButton();
    private Scene scene;
    private BorderPane layout;
    private UpperBar upperBar;
    private BorderPane root;
    private boolean typeofviewermanager = false;


    public TicketPageView(){
        super(new BorderPane(), 1080, 600);


    }

    //evento che setta le label dipendenti dall'evento prima di inizializzare la view
    //senno inizializza la view con i campi vuoti e aggiorna il valore di una label senza reinizializzare
    public void setComponents(boolean isviewermanager, String typeofevent, String name, String città, String location, Province prov, LocalDate data,ArrayList<String> artist,
                              int[] seatsRemainedNumberForType, double[] price) {
        //settaggio dei campi dei valori per un singolo evento
        System.out.println(artist);
        System.out.println(artist.toString());
        typeofviewermanager = isviewermanager;
        eventNameTextField = new Label(name);
        eventDescriptionTextField = new Label("Il giorno "+data+" si terra un "+typeofevent+" in "+location+" a "+città+" ,in provincia di "+prov+" tenuto da "
                +artist.toString().substring(1, artist.toString().length()-1));

        switch(seatsRemainedNumberForType.length) {
            case 1:
                ticketVipTextField = new Label("Non disponibili");
                vipPricebutton.setVisible(false);
                ticketPremiumTextField = new Label("Non disponibili");
                premiumPricebutton.setVisible(false);
                ticketBaseTextField = new Label(String.valueOf(seatsRemainedNumberForType[0]));
                basePriceTextField = new Label("€"+price[0]);
                break;
            case 2:
                ticketVipTextField = new Label("Non disponibili");
                vipPricebutton.setVisible(false);
                ticketBaseTextField = new Label(String.valueOf(seatsRemainedNumberForType[0]));
                basePriceTextField = new Label("€"+price[0]);
                ticketPremiumTextField = new Label(String.valueOf(seatsRemainedNumberForType[1]));
                premiumPriceTextField = new Label("€"+price[1]);

                break;
            case 3:
                ticketBaseTextField = new Label(String.valueOf(seatsRemainedNumberForType[0]));
                basePriceTextField = new Label("€"+price[0]);
                ticketPremiumTextField = new Label(String.valueOf(seatsRemainedNumberForType[1]));
                premiumPriceTextField = new Label("€"+price[1]);
                ticketVipTextField = new Label(String.valueOf(seatsRemainedNumberForType[2]));
                vipPriceTextField = new Label("€"+price[2]);
        }

        if(typeofviewermanager) {
            buyButton.setVisible(false);
            UpperBar.getIstance().setForManager();
        }
        else {
            UpperBar.getIstance().setForCustomer();
        }



        //fine settaggio
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

        Image BuyButtonlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/NewBuyButton.png");
        buyButton.setGraphic(new ImageView(BuyButtonlogo));
        buyButton.setPrefWidth(buyButton.getWidth());
        buyButton.setPrefHeight(buyButton.getHeight());
        buyButton.setPadding(new Insets(0));
        buyButton.setStyle("-fx-background-color: rgb(255,255,255)");
        buyButton.setOpacity(1);

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
        //aggiunta dei campi riempiti dal controller
        bottomGrid.add(ticketBaseTextField, 1, 1);
        bottomGrid.add(ticketPremiumTextField, 1, 2);
        bottomGrid.add(ticketVipTextField, 1, 3);
        //aggiunta dei prezzi
        bottomGrid.add(basePriceTextField, 2, 1);
        bottomGrid.add(premiumPriceTextField, 2, 2);
        bottomGrid.add(vipPriceTextField, 2, 3);
        //aggiunta dei bottoni di selezione
        bottomGrid.add(basePricebutton, 3, 1);
        bottomGrid.add(premiumPricebutton, 3, 2);
        bottomGrid.add(vipPricebutton, 3, 3);


        basePricebutton.setToggleGroup(priceselection);
        premiumPricebutton.setToggleGroup(priceselection);
        vipPricebutton.setToggleGroup(priceselection);



        internalgrid.setBottom(bottomGrid);

        BorderPane root=new BorderPane();

        root.setCenter(internalgrid);
        root.setStyle("-fx-background-color: #91BAD6;");
        root.setBottom(buttonbox);
        BorderPane.setMargin(buttonbox, new Insets(30));
        BorderPane.setAlignment(buttonbox, Pos.BOTTOM_RIGHT);

        this.root=root;

        // Creazione e visualizzazione della scena
        scene = new Scene(root, 1080, 600);

        BorderPane layout= new BorderPane();
        this.layout=layout;
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());

        setRoot(layout);





    }

    public int getWhichPriceSelected() {
        if(vipPricebutton.isSelected()) {
            return 2;
        }else if(premiumPricebutton.isSelected()) {
            return 1;
        }else
            return 0;
    }

    public static Button getBuyButton() {
        return buyButton;
    }

    public Scene getScene() {
        return scene;
    }

        public void reSetBarsCustomer(){
            BorderPane temp = new BorderPane();
            setRoot(temp);
            UpperBar.getIstance().setForCustomer();
            layout.setTop(UpperBar.getIstance());
            layout.setBottom(LowerBar.getInstance());
            setRoot(layout);
        }


        public void reSetBarsManager(){
            BorderPane temp = new BorderPane();
            setRoot(temp);
            UpperBar.getIstance().setForManager();
            layout.setTop(UpperBar.getIstance());
            layout.setBottom(LowerBar.getInstance());
            setRoot(layout);
        }


    public Toggle getIfPriceSelected() {
        return priceselection.getSelectedToggle();
    }


}




