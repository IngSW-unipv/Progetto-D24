package it.unipv.insfw23.TicketWave.modelView.ticket;

import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;

import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;

public class TicketPageView extends Scene implements IResettableScene {

    private final Font font = Font.font("Helvetica", FontWeight.BOLD, 13);
    private static final Label eventNameLabel = new Label("Nome Evento:");
    private static final Label eventDescriptionLabel = new Label("Descrizione Evento:");
    private static final Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
    private static  Button buyButton = new Button();

    private static Label errmessage = new Label("Seleziona una tipologia di biglietto");


    private static  ToggleGroup priceselection = new ToggleGroup();

    // campi riempiti dal controller
    private static Label eventNameTextField = new Label();
    private static Label eventCityTextField=new Label();
    private static Label eventTypeOfEventTextField= new Label();
    private static Label eventLocationTextField=new Label();

    private static Label eventProvinceTextField=new Label();
    private static Label eventDateTextField=new Label();

    private static Label eventArtistTextField=new Label();
    private static Label eventDescriptionTextField = new Label();
    private static Label eventCityLabel = new Label("Città:");
    private static Label eventLocationLabel = new Label("Location:");
    private static Label eventProvinceLabel = new Label("Provincia:");
    private static Label eventDateLabel = new Label("Data:");
    private static Label eventArtistLabel = new Label("Artista:");

    private static final Label ticketBaseLabel = new Label("Base Tickets:");
    private static final Label ticketPremiumLabel = new Label("Premium Tickets:");
    private static final Label ticketVipLabel = new Label("Vip Tickets:");
    private static Label ticketBaseTextField = new Label();
    private static Label ticketPremiumTextField = new Label();
    private static Label ticketVipTextField = new Label();
    private static List<Label> text = new ArrayList<>();
    private static Label basePriceTextField = new Label();
    private static Label premiumPriceTextField = new Label();
    private static Label vipPriceTextField = new Label();

    private static final RadioButton basePricebutton = new RadioButton();
    private static final RadioButton premiumPricebutton = new RadioButton();
    private static final RadioButton vipPricebutton = new RadioButton();
    private Scene scene;
    private BorderPane layout;
    private boolean isCustomerViewer;
    private Button backButton = new Button();
    private ImageView eventPosterImage = new ImageView();




    public TicketPageView(){
        super(new BorderPane(), 1080, 600);
        //initComponents();


    }

    //evento che setta le label dipendenti dall'evento prima di inizializzare la view
    //senno inizializza la view con i campi vuoti e aggiorna il valore di una label senza reinizializzare
    public void setComponents(boolean isCustomerViewer, Type typeofevent, String name, String città, String location, Province prov, LocalDate data, String  artist,
                              int[] seatsRemainedNumberForType, double[] price, String description, Image image) {

        eventPosterImage.setImage(image);
        //settaggio dei campi dei valori per un singolo evento
        System.out.println(artist);
        System.out.println(artist.toString());
        this.isCustomerViewer = isCustomerViewer;


        //settaggio valori da mostrare
        eventNameTextField.setText(name);
        eventDescriptionTextField.setText(description);
        eventCityTextField.setText(città);
        eventLocationTextField.setText(location);
        eventProvinceTextField.setText(prov.toString());
        eventDateTextField.setText(data.toString());
        eventArtistTextField.setText(artist);


        //DA PRENDERE DAL DAO


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
        // controllo e reset delle barre
        if(isCustomerViewer) {
            buyButton.setVisible(true);
            UpperBar.getIstance().setForCustomer();
        }
        else {
            buyButton.setVisible(false);
            basePricebutton.setVisible(false);
            premiumPricebutton.setVisible(false);
            vipPricebutton.setVisible(false);
            UpperBar.getIstance().setForManager();
        }

        //fine settaggio
        initComponents();
    }

    private void initComponents() {// la classe contiene unicamente label poichè è solo una pagina di visualizzazione, il cliente non può scriverci sopra

        //BorderPane per struttura esterna
        this.layout=new BorderPane();
        setRoot(layout);

        // BorderPane per struttura interna
        BorderPane internalStructure = new BorderPane();

        //aggiunta campi alla lista di testo
        text.add(eventNameLabel);
        text.add(eventCityLabel);
        text.add(eventLocationLabel);
        text.add(eventProvinceLabel);
        text.add(eventDateLabel);
        text.add(eventArtistLabel);
        text.add(eventDescriptionLabel);
        text.add(eventNameTextField);
        text.add(eventCityTextField);
        text.add(eventLocationTextField);
        text.add(eventProvinceTextField);
        text.add(eventDateTextField);
        text.add(eventArtistTextField);
        text.add(eventDescriptionTextField);
        text.add(ticketBaseLabel);
        text.add(ticketPremiumLabel);
        text.add(ticketVipLabel);
        text.add(ticketBaseTextField);
        text.add(ticketPremiumTextField);
        text.add(ticketVipTextField);
        text.add(basePriceTextField);
        text.add(premiumPriceTextField);
        text.add(vipPriceTextField);
        text.add(ticketsLabel);
        text.add(errmessage);


        //istruzioni per settaggio del testo
        for (Label label : text) {
            label.setTextFill(Color.BLACK);
            label.setFont(font);
        }

        eventNameTextField.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 17));



        Image backButtonLogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/backArrow.png");
        ImageView imageViewBackButton=new ImageView();
        imageViewBackButton.setFitWidth(50);
        imageViewBackButton.setPreserveRatio(true);
        imageViewBackButton.setImage(backButtonLogo);
        backButton.setGraphic(imageViewBackButton);
        backButton.setStyle("-fx-background-color: #91BAD6");

        //import dell'immagine del bottone di acquisto

        Image BuyButtonlogo = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/NewBuyButton.png");
        ImageView buyButtonImageView=new ImageView();
        buyButtonImageView.setImage(BuyButtonlogo);
        buyButtonImageView.setFitHeight(120);
        buyButtonImageView.setFitWidth(200);
        buyButton.setGraphic(buyButtonImageView);
        buyButton.setPrefWidth(buyButton.getWidth());
        buyButton.setPrefHeight(buyButton.getHeight());
        buyButton.setPadding(new Insets(0));
        buyButton.setStyle("-fx-background-color: #91BAD6");
        buyButton.setOpacity(1);

        //LA LOCANDINA DELL'EVENTO DEVE ESSERE IMPOSTATA DAL DAO METTO UNA PROVVISORIA

        eventPosterImage.setFitHeight(200);
        eventPosterImage.setFitWidth(200);



      // Creazione di Region vuote per occupare lo spazio tra i bottoni e i margini

    Region leftSpacer = new Region();
    HBox.setHgrow(leftSpacer, Priority.ALWAYS); // Consente a leftSpacer di espandersi per riempire lo spazio disponibile

    Region rightSpacer = new Region();
    HBox.setHgrow(rightSpacer, Priority.ALWAYS); // Consente a rightSpacer di espandersi per riempire lo spazio disponibile// Imposta un margine di 10 unità a destra del backButton
    // Creazione di un HBox per contenere i bottoni e le Region vuote
    HBox buttonBox = new HBox( backButton, rightSpacer, buyButton);
    HBox.setMargin(backButton, new Insets(0, 10, -40, 10));

    buttonBox.setSpacing(50); // Spazio tra i bottoni
    buttonBox.setAlignment(Pos.CENTER); // Allinea i bottoni al centro

        errmessage.setOpacity(0);
        errmessage.setStyle("-fx-text-fill: red;");


        //Gridpane per sistemazione elementi centrali
        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(10));
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);
        centerGrid.add(eventNameLabel, 0, 0);
        centerGrid.add(eventNameTextField, 1, 0);
        centerGrid.add(eventDateLabel, 0, 1);
        centerGrid.add(eventDateTextField, 1, 1);
        centerGrid.add(eventLocationLabel, 0, 2);
        centerGrid.add(eventLocationTextField, 1, 2);
        centerGrid.add(eventCityLabel, 0, 3);
        centerGrid.add(eventCityTextField, 1, 3);
        centerGrid.add(eventProvinceLabel, 0, 4);
        centerGrid.add(eventProvinceTextField, 1, 4);
        centerGrid.add(eventArtistLabel, 0, 5);
        centerGrid.add(eventArtistTextField, 1, 5);
        centerGrid.add(eventDescriptionLabel, 0, 6);
        centerGrid.add(eventDescriptionTextField, 1, 6);
        centerGrid.add(errmessage, 0, 7);




        // Gridpane per sistemazione elementi sul fine pagina

        GridPane bottomGrid = new GridPane();

        bottomGrid.setPadding(new Insets(10));
        bottomGrid.setVgap(1);
        bottomGrid.setHgap(20);
        bottomGrid.add(errmessage, 0, 0);
        bottomGrid.add(ticketsLabel, 0, 1);
        bottomGrid.add(ticketBaseLabel, 0, 2);
        bottomGrid.add(ticketPremiumLabel, 0, 3);
        bottomGrid.add(ticketVipLabel, 0, 4);
        //aggiunta dei campi riempiti dal controller
        bottomGrid.add(ticketBaseTextField, 1, 2);
        bottomGrid.add(ticketPremiumTextField, 1, 3);
        bottomGrid.add(ticketVipTextField, 1, 4);
        //aggiunta dei prezzi
        bottomGrid.add(basePriceTextField, 2, 2);
        bottomGrid.add(premiumPriceTextField, 2, 3);
        bottomGrid.add(vipPriceTextField, 2, 4);
        //aggiunta dei bottoni di selezione
        bottomGrid.add(basePricebutton, 3, 2);
        bottomGrid.add(premiumPricebutton, 3, 3);
        bottomGrid.add(vipPricebutton, 3, 4);






        basePricebutton.setToggleGroup(priceselection);
        premiumPricebutton.setToggleGroup(priceselection);
        vipPricebutton.setToggleGroup(priceselection);


        internalStructure.setCenter(centerGrid);
        internalStructure.setBottom(bottomGrid);

        internalStructure.setStyle("-fx-background-color: #91BAD6;");


        //Borderpane esterno per l'immissione di tutto al centro+ layout sopra e sotto
        BorderPane root=new BorderPane();

        root.setCenter(internalStructure);
        root.setStyle("-fx-background-color: #91BAD6;");
        BorderPane.setMargin(eventPosterImage, new Insets(30, 50, 0, 0));
        root.setRight(eventPosterImage);
        root.setBottom(buttonBox);



        //BordePane layout per upperBar e lowerbar
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());

        reSetBars();

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
        //??????
        public void reSetBars(){
            BorderPane temp = new BorderPane();
            setRoot(temp);
            //controllo per il layout inizializzato
            if (layout == null) {
                System.err.println("Layout non inizializzato correttamente!");
                return;}

            if(isCustomerViewer) {
                UpperBar.getIstance().setForCustomer();
            }
            else{
                UpperBar.getIstance().setForManager();
            }
            layout.setTop(UpperBar.getIstance());
            layout.setBottom(LowerBar.getInstance());
            setRoot(layout);
        }




    public Toggle getIfPriceSelected() {
        return priceselection.getSelectedToggle();
    }


    public Button getBackButton() {
        return backButton;
    }

    public static Label getErrmessage() {
        return errmessage;
    }


  public RadioButton getBasePricebutton(){
        return basePricebutton;
    }

    public static void setEventDescriptionTextField(Label eventDescriptionTextField) {
        TicketPageView.eventDescriptionTextField = eventDescriptionTextField;
    }
}



