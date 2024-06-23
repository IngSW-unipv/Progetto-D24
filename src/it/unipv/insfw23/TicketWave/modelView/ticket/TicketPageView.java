package it.unipv.insfw23.TicketWave.modelView.ticket;

import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private final Label eventNameLabel = new Label("Nome Evento:");
    private final Label eventDescriptionLabel = new Label("Descrizione Evento:");
    private final Label ticketsLabel = new Label("Biglietti disponibili per tipo:");
    private final  Button buyButton = new Button();

    private final  Label errmessage = new Label("Seleziona una tipologia di biglietto");


    private final   ToggleGroup priceselection = new ToggleGroup();

    // campi riempiti dal controller
    private final  Label eventNameField = new Label();

    private final Label typeOfEventField=new Label();
    private final Label eventCityField =new Label();
    private final Label eventLocationField =new Label();

    private final Label eventProvinceField =new Label();
    private final Label eventDateField =new Label();

    private final Label typeOfEventLabel=new Label("Tipo:");

    private final Label authorNameField=new Label();
    private final Label authorNameLabel= new Label("Autore:");

    private final Label eventArtistField =new Label();
    private static final TextArea eventDescriptionField = new TextArea();
    private final Label eventCityLabel = new Label("Città:");
    private final Label eventLocationLabel = new Label("Location:");
    private final Label eventProvinceLabel = new Label("Provincia:");
    private final Label eventDateLabel = new Label("Data:");
    private final Label eventArtistLabel = new Label("Artista:");

    private Label ticketBaseLabel = new Label("Base Tickets:");
    private Label ticketPremiumLabel = new Label("Premium Tickets:");
    private Label ticketVipLabel = new Label("Vip Tickets:");
    private Label ticketBaseField = new Label();
    private Label ticketPremiumField = new Label();
    private Label ticketVipField = new Label();
    private List<Label> text = new ArrayList<>();
    private Label basePriceField = new Label();
    private Label premiumPriceField = new Label();
    private Label vipPriceField = new Label();

    private  final RadioButton basePricebutton = new RadioButton();
    private  final RadioButton premiumPricebutton = new RadioButton();
    private  final RadioButton vipPricebutton = new RadioButton();

    private final int MAX_TICKET_BUYABLE = 4;
    private final String NOT_AVAILABLE = "Non disponibili";
    private final String SOLDOUT = "Terminati";

    private final Label quantityLabel = new Label("quantità da acquistare(max "+MAX_TICKET_BUYABLE+"): ");
    private Spinner<Integer> baseSpinner;
    private Spinner<Integer> premiumSpinner;
    private Spinner<Integer> vipSpinner;

    private Scene scene;
    private BorderPane layout;
    private boolean isCustomerViewer;
    private final Button backButton = new Button();
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
        eventNameField.setText(name);
        typeOfEventField.setText(typeofevent.toString());
        eventDescriptionField.setText(description);
        eventCityField.setText(città);
        eventLocationField.setText(location);
        eventProvinceField.setText(prov.toString());
        eventDateField.setText(data.toString());
        eventArtistField.setText(artist);




        //DA PRENDERE DAL DAO


        switch(seatsRemainedNumberForType.length) {
            case 1:
            	basePriceField = new Label("€"+price[0]);
                if(seatsRemainedNumberForType[0] == 0) {
                	ticketBaseField = new Label(SOLDOUT);
                	basePricebutton.setVisible(false);
                }else {
                	ticketBaseField = new Label(String.valueOf(seatsRemainedNumberForType[0]));
                	baseSpinner = new Spinner<>(1, Integer.min(MAX_TICKET_BUYABLE, seatsRemainedNumberForType[0]), 1);
                }
                ticketPremiumField = new Label(NOT_AVAILABLE);
                premiumPricebutton.setVisible(false);
                ticketVipField = new Label(NOT_AVAILABLE);
                vipPricebutton.setVisible(false);
                break;
            case 2:
            	basePriceField = new Label("€"+price[0]);
            	if(seatsRemainedNumberForType[0] == 0) {
            		ticketBaseField = new Label(SOLDOUT);
                	basePricebutton.setVisible(false);
            	}else {
            		ticketBaseField = new Label(String.valueOf(seatsRemainedNumberForType[0]));
            		baseSpinner = new Spinner<>(1, Integer.min(MAX_TICKET_BUYABLE, seatsRemainedNumberForType[0]), 1);
            	}
            	premiumPriceField = new Label("€"+price[1]);
            	if(seatsRemainedNumberForType[1] == 0) {
            		ticketPremiumField = new Label(SOLDOUT);
            		premiumPricebutton.setVisible(false);
            	}else {
            		ticketPremiumField = new Label(String.valueOf(seatsRemainedNumberForType[1]));
            		premiumSpinner = new Spinner<>(1, Integer.min(MAX_TICKET_BUYABLE, seatsRemainedNumberForType[1]), 1);
            	}
                ticketVipField = new Label(NOT_AVAILABLE);
                vipPricebutton.setVisible(false);
                break;
            case 3:
            	basePriceField = new Label("€"+price[0]);
                if(seatsRemainedNumberForType[0] == 0) {
                	ticketBaseField = new Label(SOLDOUT);
                	basePricebutton.setVisible(false);
                }else {
                	ticketBaseField = new Label(String.valueOf(seatsRemainedNumberForType[0]));
                	baseSpinner = new Spinner<>(1, Integer.min(MAX_TICKET_BUYABLE, seatsRemainedNumberForType[0]), 1);
                }
                premiumPriceField = new Label("€"+price[1]);
                if(seatsRemainedNumberForType[1] == 0) {
            		ticketPremiumField = new Label(SOLDOUT);
            		premiumPricebutton.setVisible(false);
            	}else {
            		ticketPremiumField = new Label(String.valueOf(seatsRemainedNumberForType[1]));
            		premiumSpinner = new Spinner<>(1, Integer.min(MAX_TICKET_BUYABLE, seatsRemainedNumberForType[1]), 1);
            	}
                vipPriceField = new Label("€"+price[2]);
                if(seatsRemainedNumberForType[2] == 0) {
                	ticketVipField = new Label(SOLDOUT);
                	vipPricebutton.setVisible(false);
                }else {
                	ticketVipField = new Label(String.valueOf(seatsRemainedNumberForType[2]));
                	vipSpinner = new Spinner<>(1, Integer.min(MAX_TICKET_BUYABLE, seatsRemainedNumberForType[2]), 1);
                }
        }
        // controllo e reset delle barre
        if(isCustomerViewer) {
            UpperBar.getIstance().setForCustomer();
        }
        else {
            UpperBar.getIstance().setForManager();
        }

        //fine settaggio
        initComponents();
    }

    private void initComponents() {// la classe contiene unicamente label poichè è solo una pagina di visualizzazione, il cliente non può scriverci sopra

        //BorderPane per struttura esterna
        this.layout=new BorderPane();
        setRoot(layout);

        eventDescriptionField.setEditable(false); // rende non editabile la textArea
        eventDescriptionField.setMouseTransparent(false); // il mouse quando va sopra la text area non cambia aspetto
        eventDescriptionField.setWrapText(true); // rende inutilizzabile lo scrolling orizzontale => fa andare a capo il testo quando sta per eccedere il box della textArea
        eventDescriptionField.getStylesheets().add("it/unipv/insfw23/TicketWave/css/eventDescriptionField.css");
        eventDescriptionField.setPrefWidth(400);
        eventDescriptionField.setPrefHeight(40);

        // BorderPane per struttura interna
        BorderPane internalStructure = new BorderPane();

        //aggiunta campi alla lista di testo
        text.add(eventNameLabel);
        text.add(typeOfEventLabel);
        text.add(authorNameField);
        text.add(authorNameLabel);
        text.add(typeOfEventField);
        text.add(eventCityLabel);
        text.add(eventLocationLabel);
        text.add(eventProvinceLabel);
        text.add(eventDateLabel);
        text.add(eventArtistLabel);
        text.add(eventDescriptionLabel);
        text.add(eventNameField);
        text.add(eventCityField);
        text.add(eventLocationField);
        text.add(eventProvinceField);
        text.add(eventDateField);
        text.add(eventArtistField);
        text.add(ticketBaseLabel);
        text.add(ticketPremiumLabel);
        text.add(ticketVipLabel);
        text.add(ticketBaseField);
        text.add(ticketPremiumField);
        text.add(ticketVipField);
        text.add(basePriceField);
        text.add(premiumPriceField);
        text.add(vipPriceField);
        text.add(ticketsLabel);
        text.add(quantityLabel);
        text.add(errmessage);


        //istruzioni per settaggio del testo
        for (Label label : text) {
            label.setTextFill(Color.BLACK);
            label.setFont(font);
        }
        eventDescriptionField.setFont(font);

        eventNameField.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 17));



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

    buttonBox.setSpacing(50); // Spazio tra i bottoni
    buttonBox.setAlignment(Pos.CENTER); // Allinea i bottoni al centro

        errmessage.setOpacity(0);
        errmessage.setStyle("-fx-text-fill: red;");

        //impostazioni relative alla grafica degli spinner
        if(baseSpinner != null) {
        	baseSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
            baseSpinner.setPrefWidth(65);
            baseSpinner.setPrefHeight(20);
            baseSpinner.setVisible(false);
        }

        if(premiumSpinner != null) {
        	premiumSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        	premiumSpinner.setPrefWidth(65);
        	premiumSpinner.setPrefHeight(20);
        	premiumSpinner.setVisible(false);
        }

        if(vipSpinner != null) {
        	vipSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        	vipSpinner.setPrefWidth(65);
        	vipSpinner.setPrefHeight(20);
        	vipSpinner.setVisible(false);
        }


        //Gridpane per sistemazione elementi centrali
        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(10));
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);
        centerGrid.add(eventNameLabel, 0, 0);
        centerGrid.add(eventNameField, 1, 0);
        centerGrid.add(typeOfEventLabel,2,0);
        centerGrid.add(typeOfEventField,3,0);
        centerGrid.add(authorNameLabel,2,1);
        centerGrid.add(authorNameField,3,1);
        centerGrid.add(eventDateLabel, 0, 1);
        centerGrid.add(eventDateField, 1, 1);
        centerGrid.add(eventLocationLabel, 0, 2);
        centerGrid.add(eventLocationField, 1, 2);
        centerGrid.add(eventCityLabel, 0, 3);
        centerGrid.add(eventCityField, 1, 3);
        centerGrid.add(eventProvinceLabel, 0, 4);
        centerGrid.add(eventProvinceField, 1, 4);
        centerGrid.add(eventArtistLabel, 0, 5);
        centerGrid.add(eventArtistField, 1, 5);
        centerGrid.add(eventDescriptionLabel, 0, 6);
        centerGrid.add(eventDescriptionField, 1, 6);
        centerGrid.add(errmessage, 0, 7);

        //authorNameLabel.setVisible(false);
        //authorNameField.setVisible(false);


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
        bottomGrid.add(ticketBaseField, 1, 2);
        bottomGrid.add(ticketPremiumField, 1, 3);
        bottomGrid.add(ticketVipField, 1, 4);
        //aggiunta dei prezzi
        bottomGrid.add(basePriceField, 2, 2);
        bottomGrid.add(premiumPriceField, 2, 3);
        bottomGrid.add(vipPriceField, 2, 4);
        //aggiunta dei bottoni di selezione
        bottomGrid.add(basePricebutton, 3, 2);
        bottomGrid.add(premiumPricebutton, 3, 3);
        bottomGrid.add(vipPricebutton, 3, 4);
        //aggiunta label quantity
        bottomGrid.add(quantityLabel, 4, 1);
        //aggiunta degli spinner per la selezione della quantità di biglietti solo se sono stati creati
        if(baseSpinner != null)
        	bottomGrid.add(baseSpinner, 4, 2);
        if(premiumSpinner != null)
        	bottomGrid.add(premiumSpinner, 4, 3);
        if(vipSpinner != null)
        	bottomGrid.add(vipSpinner, 4, 4);



        basePricebutton.setToggleGroup(priceselection);
        premiumPricebutton.setToggleGroup(priceselection);
        vipPricebutton.setToggleGroup(priceselection);

        //listener per il cambio dello spinner visibile a seconda del radiobutton
        priceselection.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) {
				if(baseSpinner != null)
                    baseSpinner.setVisible(false);
				if(premiumSpinner != null)
					premiumSpinner.setVisible(false);
				if(vipSpinner != null)
					vipSpinner.setVisible(false);

				if(priceselection.getSelectedToggle().equals(basePricebutton)) {
					baseSpinner.setVisible(true);
				}else if(priceselection.getSelectedToggle().equals(premiumPricebutton) && premiumSpinner != null) {
					premiumSpinner.setVisible(true);
				}else if(priceselection.getSelectedToggle().equals(vipPricebutton) && vipSpinner != null) {
					vipSpinner.setVisible(true);
				}
			}
		});


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
        BorderPane.setMargin(buttonBox, new Insets(-10,0,-20,0));




        //BordePane layout per upperBar e lowerbar
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(root);
        layout.setBottom(LowerBar.getInstance());
    }

    public int getWhichPriceSelected() {
        if(vipPricebutton.isSelected()) {
            return 2;
        }else if(premiumPricebutton.isSelected()) {
            return 1;
        }else
            return 0;
    }

    public Button getBuyButton() {
        return buyButton;
    }

    public Scene getScene() {
        return scene;
    }

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

    public  Label getErrmessage() {
        return errmessage;
    }



    public void setForNotBuyable(){
        buyButton.setVisible(false);
        basePricebutton.setVisible(false);
        premiumPricebutton.setVisible(false);
        vipPricebutton.setVisible(false);
        quantityLabel.setVisible(false);
    }

    public Label getAuthorNameField() {
        return authorNameField;
    }

    public Label getAuthorNameLabel() {
        return authorNameLabel;
    }

    public void setAuthorNameField(String authorName) {
        this.authorNameField.setText(authorName);
    }
    public void setAuthorNameLabel(String authorNameLabel) {
        this.authorNameLabel.setText(authorNameLabel);
    }


    public int getNumOfTickets() {
    	if(baseSpinner!= null && baseSpinner.isVisible())
    		return baseSpinner.getValue();
    	else if(premiumSpinner != null && premiumSpinner.isVisible())
    		return premiumSpinner.getValue();
    	else if(vipSpinner != null) {
            return vipSpinner.getValue();
        }
        return 0;
    }
}



