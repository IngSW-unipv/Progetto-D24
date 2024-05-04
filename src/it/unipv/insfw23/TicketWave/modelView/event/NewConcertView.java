package it.unipv.insfw23.TicketWave.modelView.event;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NewConcertView extends Scene{
	private final Label titlelabel = new Label("Compilare i seguenti campi per proseguire:");
	private final Label namelabel = new Label("Nome: ");
	private TextField namefield;
	private final Label citylabel = new Label("Città: ");
	private TextField cityfield;
	private final Label provincelabel = new Label("Provincia: ");
	private ComboBox<Province> provincesbox;
	private final Label addresslabel = new Label("Indirizzo: ");
	private TextField addressfield;
	private final Label numtypeticketlabel = new Label("Tipologie di biglietti: ");
	private ChoiceBox<String> typesticketbox;
	private final Label genlabel = new Label("Genere: ");
	private ComboBox<Genre> gensbox;
	private final Label artistlabel = new Label("Artista: ");
	private TextField artistfield;
	private final Label datelabel = new Label("Data: ");
	private DatePicker datepicker;
	private final Label hourlabel = new Label("orario(hh mm): ");
	private Spinner<Integer> hourspinner;
	private Spinner<Integer> minutesspinner;
	private HBox hboxtime;
	private final Label numbaselabel = new Label("n. biglietti base: ");
	private TextField numbasefield;
	private final Label numviplabel = new Label("n. biglietti vip: ");
	private TextField numvipfield;
	private final Label numpremiumlabel = new Label("n. biglietti premium: ");
	private TextField numpremiumfield;
	private final Label pricebaselabel = new Label("prezzo base: ");
	private TextField pricebasefield;
	private final Label priceviplabel = new Label("prezzo vip: ");
	private TextField pricevipfield;
	private final Label pricepremiumlabel = new Label("prezzo premium: ");
	private TextField pricepremiumfield;
	private final Button photoButton = new Button("Scegli foto...");
	private ImageView eventPhoto;
	private final Label descriptionlabel = new Label("Descrizione: ");
//	private TextField descriptionfield;
	private TextArea descriptionarea;
	private final Label errlabel = new Label("Parametri non validi");	
	private Button abort = new Button("Annulla");
	private Button confirm = new Button("Conferma");
	

	public NewConcertView() {
		super(new BorderPane(), 1080, 600);
        init();
	}
	
	public void init() {
		
		BorderPane layout = (BorderPane) getRoot();
		layout.setStyle("-fx-background-color: #def1fa;");
		GridPane grid = new GridPane();
		
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setVgap(10);
		grid.setHgap(60);
//		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		
		titlelabel.setFont(Font.font("Arial", 30));
		GridPane.setConstraints(titlelabel, 0, 0, 4, 1);
		GridPane.setHalignment(titlelabel, HPos.CENTER);
		GridPane.setVgrow(titlelabel, Priority.SOMETIMES);
		GridPane.setHgrow(titlelabel, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(namelabel, 0, 2);
		GridPane.setVgrow(namelabel, Priority.SOMETIMES);
		GridPane.setHgrow(namelabel, Priority.SOMETIMES);
		
		namefield = new TextField();
		//namefield.setMaxWidth(30);
		GridPane.setConstraints(namefield, 0, 3);
		GridPane.setVgrow(namefield, Priority.SOMETIMES);
		GridPane.setHgrow(namefield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(citylabel, 1, 2);
		GridPane.setVgrow(citylabel, Priority.SOMETIMES);
		GridPane.setHgrow(citylabel, Priority.SOMETIMES);
		
		cityfield = new TextField();
		GridPane.setConstraints(cityfield, 1, 3);
		GridPane.setVgrow(cityfield, Priority.SOMETIMES);
		GridPane.setHgrow(cityfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(provincelabel, 2, 2);
		GridPane.setVgrow(provincelabel, Priority.SOMETIMES);
		GridPane.setHgrow(provincelabel, Priority.SOMETIMES);
		
		provincesbox = new ComboBox<>();
		provincesbox.getItems().addAll(Province.values());
		GridPane.setConstraints(provincesbox, 2, 3);
		GridPane.setVgrow(provincesbox, Priority.SOMETIMES);
		GridPane.setHgrow(provincesbox, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(addresslabel, 3, 2);
		GridPane.setVgrow(addresslabel, Priority.SOMETIMES);
		GridPane.setHgrow(addresslabel, Priority.SOMETIMES);
		
		addressfield = new TextField();
		GridPane.setConstraints(addressfield, 3, 3);
		GridPane.setVgrow(addressfield, Priority.SOMETIMES);
		GridPane.setHgrow(addressfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(datelabel, 0, 5);
		GridPane.setVgrow(datelabel, Priority.SOMETIMES);
		GridPane.setHgrow(datelabel, Priority.SOMETIMES);
		
		datepicker = new DatePicker();
		GridPane.setConstraints(datepicker, 0, 6);
		GridPane.setVgrow(datepicker, Priority.SOMETIMES);
		GridPane.setHgrow(datepicker, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(hourlabel, 1, 5);
		GridPane.setVgrow(hourlabel, Priority.SOMETIMES);
		GridPane.setHgrow(hourlabel, Priority.SOMETIMES);
		
		hourspinner = new Spinner<>(0,23,0);
		minutesspinner = new Spinner<>(0,55,0,5);
		hboxtime = new HBox(10,hourspinner,minutesspinner);
		GridPane.setConstraints(hboxtime, 1, 6);
		GridPane.setVgrow(hboxtime, Priority.SOMETIMES);
		GridPane.setHgrow(hboxtime, Priority.SOMETIMES);
		hboxtime.setMaxWidth(120);
		
		
		
		GridPane.setConstraints(genlabel, 2, 5);
		GridPane.setVgrow(genlabel, Priority.SOMETIMES);
		GridPane.setHgrow(genlabel, Priority.SOMETIMES);
		
		gensbox = new ComboBox<>();
		gensbox.getItems().addAll(Arrays.copyOfRange(Genre.values(), 0, Genre.START_THEATER.ordinal()));
		GridPane.setConstraints(gensbox, 2, 6);
		GridPane.setVgrow(gensbox, Priority.SOMETIMES);
		GridPane.setHgrow(gensbox, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(artistlabel, 3, 5);
		GridPane.setVgrow(artistlabel, Priority.SOMETIMES);
		GridPane.setHgrow(artistlabel, Priority.SOMETIMES);
		
		artistfield = new TextField();
		GridPane.setConstraints(artistfield, 3,6);
		GridPane.setVgrow(artistfield, Priority.SOMETIMES);
		GridPane.setHgrow(artistfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numtypeticketlabel, 0, 8);
		GridPane.setVgrow(numtypeticketlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numtypeticketlabel, Priority.SOMETIMES);
		
		typesticketbox = new ChoiceBox<>();
		typesticketbox.getItems().addAll("1","2","3");
		GridPane.setConstraints(typesticketbox, 0, 9);
		GridPane.setVgrow(typesticketbox, Priority.SOMETIMES);
		GridPane.setHgrow(typesticketbox, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numbaselabel, 1, 8);
		numbaselabel.setVisible(false);
		GridPane.setVgrow(numbaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(numbaselabel, Priority.SOMETIMES);
		
		numbasefield = new TextField();
		GridPane.setConstraints(numbasefield, 1, 9);
		numbasefield.setVisible(false);
		GridPane.setVgrow(numbasefield, Priority.SOMETIMES);
		GridPane.setHgrow(numbasefield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numviplabel, 2, 8);
		numviplabel.setVisible(false);
		GridPane.setVgrow(numviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(numviplabel, Priority.SOMETIMES);
		
		numvipfield = new TextField();
		GridPane.setConstraints(numvipfield, 2, 9);
		numvipfield.setVisible(false);
		GridPane.setVgrow(numvipfield, Priority.SOMETIMES);
		GridPane.setHgrow(numvipfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numpremiumlabel, 3, 8);
		numpremiumlabel.setVisible(false);
		GridPane.setVgrow(numpremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumlabel, Priority.SOMETIMES);
		
		numpremiumfield = new TextField();
		GridPane.setConstraints(numpremiumfield, 3, 9);
		numpremiumfield.setVisible(false);
		GridPane.setVgrow(numpremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(photoButton, 0, 11);
		GridPane.setVgrow(photoButton, Priority.SOMETIMES);
		GridPane.setHgrow(photoButton, Priority.SOMETIMES);
		
		eventPhoto = new ImageView();
		eventPhoto.isSmooth();
//		eventPhoto.maxHeight(50);
//		eventPhoto.maxWidth(50);
		eventPhoto.setFitHeight(100);
		eventPhoto.setFitWidth(100);
		GridPane.setConstraints(eventPhoto, 0, 12,3,3);
		GridPane.setVgrow(eventPhoto, Priority.SOMETIMES);
		GridPane.setHgrow(eventPhoto, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(pricebaselabel, 1, 11);
		pricebaselabel.setVisible(false);
		GridPane.setVgrow(pricebaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricebaselabel, Priority.SOMETIMES);
		
		pricebasefield = new TextField();
		GridPane.setConstraints(pricebasefield, 1, 12);
		pricebasefield.setVisible(false);
//		GridPane.setValignment(pricebasefield, VPos.TOP);
		GridPane.setVgrow(pricebasefield, Priority.SOMETIMES);
		GridPane.setHgrow(pricebasefield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(priceviplabel, 2, 11);
		priceviplabel.setVisible(false);
		GridPane.setVgrow(priceviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(priceviplabel, Priority.SOMETIMES);
		
		pricevipfield = new TextField();
		GridPane.setConstraints(pricevipfield, 2, 12);
		pricevipfield.setVisible(false);
//		GridPane.setValignment(pricevipfield, VPos.TOP);
		GridPane.setVgrow(pricevipfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricevipfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(pricepremiumlabel, 3, 11);
		pricepremiumlabel.setVisible(false);
		GridPane.setVgrow(pricepremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumlabel, Priority.SOMETIMES);
		
		pricepremiumfield = new TextField();
		GridPane.setConstraints(pricepremiumfield, 3, 12);
		pricepremiumfield.setVisible(false);
//		GridPane.setValignment(pricepremiumfield, VPos.TOP);
		GridPane.setVgrow(pricepremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(descriptionlabel, 1, 14);
		GridPane.setVgrow(descriptionlabel, Priority.SOMETIMES);
		GridPane.setHgrow(descriptionlabel, Priority.SOMETIMES);
		
//		descriptionfield = new TextField();
		descriptionarea = new TextArea();
		GridPane.setConstraints(descriptionarea, 1,15,2,1);
		GridPane.setVgrow(descriptionarea, Priority.SOMETIMES);
		GridPane.setHgrow(descriptionarea, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(abort, 0, 16);
		
		
		
		GridPane.setConstraints(errlabel, 1, 16,1,2);
		errlabel.setFont(Font.font("Arial", 20));
		errlabel.setTextFill(Color.web("#FF0400"));;
		errlabel.setVisible(false);;
		GridPane.setHalignment(errlabel, HPos.CENTER);
		GridPane.setVgrow(errlabel, Priority.SOMETIMES);
		GridPane.setHgrow(errlabel, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(confirm, 3, 16);
		GridPane.setHalignment(confirm, HPos.RIGHT);
		
		
		
		
		
		
        //INSERIMENTO FOTO (+ I METODI GET)
		
		//GridPane.setHalignment(photoButton, HPos.LEFT);
		//GridPane.setHalignment(eventPhoto, HPos.LEFT);

		//I BOTTONI CONFERMA E TORNA INDIETRO NON SONO SPARITI, SONO SOTTO,
		// SE METTI SCHERMO INTERO LI VEDI, POSIZIONALI COME VUOI TUTTI QUANTI
		// SAREBBE CARINO CHE IL BOTTONE CON LA FOTO USCISSERO AL CENTRO A DX,
		// SOPRA IL TASTO CONFERMA IN LINEA CON TUTTI GLI ALTRI CAMPI

		
		
		/* handler per il num di biglietti*/
		typesticketbox.setOnAction(new EventHandler<>() {

			@Override
			public void handle(ActionEvent eventmouse) {
				numviplabel.setVisible(false);
				numvipfield.setVisible(false);
				numpremiumlabel.setVisible(false);
				numpremiumfield.setVisible(false);
				priceviplabel.setVisible(false);
				pricevipfield.setVisible(false);
				pricepremiumlabel.setVisible(false);
				pricepremiumfield.setVisible(false);
				
				
				switch(typesticketbox.getValue()) {
				case "3":
					numpremiumlabel.setVisible(true);
					numpremiumfield.setVisible(true);
					pricepremiumlabel.setVisible(true);
					pricepremiumfield.setVisible(true);
				case "2":
					numviplabel.setVisible(true);
					numvipfield.setVisible(true);
					priceviplabel.setVisible(true);
					pricevipfield.setVisible(true);
				case "1":
					numbaselabel.setVisible(true);
					numbasefield.setVisible(true);
					pricebaselabel.setVisible(true);
					pricebasefield.setVisible(true);
				}
				
			}
			
		});
			
		
		grid.getChildren().addAll(titlelabel, namelabel, namefield,citylabel, cityfield, provincelabel, provincesbox, addresslabel, 
								    addressfield, datelabel, datepicker, hourlabel, hboxtime, numtypeticketlabel, typesticketbox, 
								    genlabel, gensbox, artistlabel, artistfield, numbaselabel, numbasefield, numviplabel, numvipfield, 
								    numpremiumlabel, numpremiumfield, photoButton, eventPhoto, pricebaselabel, pricebasefield, 
								    priceviplabel, pricevipfield, pricepremiumlabel, pricepremiumfield, descriptionlabel, descriptionarea,
								    confirm, errlabel,abort);
		
		layout.setTop(UpperBar.getIstance());
		layout.setCenter(grid);
		layout.setBottom(LowerBar.getInstance());
		
		
		
		
		
	}
	
	
	
	public String getNamefield() {
		return namefield.getText();
	}

	public String getCityfield() {
		
		return cityfield.getText();
	}

	public Province getProvince() {
		return provincesbox.getValue();
	}

	public String getAddressfield() {
		return addressfield.getText();
	}

	public int getTypesticket() {
		return Integer.parseInt(typesticketbox.getValue());
	}

	public Genre getGenre() {
		return gensbox.getValue();
	}

	public String getArtistfield() {
		return artistfield.getText();
	}
	
	public LocalDate getDatepicked() {
		return datepicker.getValue();
	}

	public int getNumbasefield() throws NumberFormatException{
		return Integer.parseInt(numbasefield.getText());
	}

	public int getNumvipfield() throws NumberFormatException{
		return Integer.parseInt(numvipfield.getText());
	}

	public int getNumpremiumfield() throws NumberFormatException{
		return Integer.parseInt(numpremiumfield.getText());
	}

	public double getPricebasefield() throws NumberFormatException{
		return Double.parseDouble(pricebasefield.getText());
	}

	public double getPricevipfield() throws NumberFormatException{
		return Double.parseDouble(pricevipfield.getText());
	}

	public double getPricepremiumfield() throws NumberFormatException{
		return Double.parseDouble(pricepremiumfield.getText());
	}

	public Button getAbortButton() {
		return abort;
	}
	
	public Button getConfirmButton() {
		return confirm;
	}
	
	public Label getErrLabel() {
		return errlabel;
	}

	public Button getPhotoButton(){return photoButton;}
	public ImageView getPhotoView() {return eventPhoto;}
}
