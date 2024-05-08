package it.unipv.insfw23.TicketWave.modelView.event;

import java.time.LocalDate;
import java.util.Arrays;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.FontWeight;

public class NewFestivalView extends Scene{
	private final Font lebelsfont = Font.font("Helvetica", FontWeight.NORMAL, 13);
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
	private final Label artistlabel = new Label("Artisti (separati da virgole): ");
	private TextField artistfield;
	private final Label datetimelabel = new Label("Data e ora: ");
	private DatePicker datepicker;
//	private final Label hourlabel = new Label("orario(hh mm): ");
	private Spinner<Integer> hourspinner;
	private Spinner<Integer> minutesspinner;
	private HBox hboxtime;
	private HBox hboxdatetime;
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
	

	public NewFestivalView() {
		super(new BorderPane(), 1080, 600);
        init();
	}
	
	public void init() {
		
		BorderPane layout = (BorderPane) getRoot();
		layout.setStyle("-fx-background-color: #91bad6;");
		GridPane grid = new GridPane();
		
		grid.setPadding(new Insets(20, 40, 20, 40));
		grid.setVgap(10);
		grid.setHgap(40);
//		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		
		titlelabel.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
		GridPane.setConstraints(titlelabel, 0, 0, 8, 1);
		GridPane.setHalignment(titlelabel, HPos.CENTER);
		GridPane.setVgrow(titlelabel, Priority.SOMETIMES);
		GridPane.setHgrow(titlelabel, Priority.SOMETIMES);
		
		
		
		namelabel.setFont(lebelsfont);
		GridPane.setConstraints(namelabel, 0, 2, 2, 1);
		GridPane.setVgrow(namelabel, Priority.SOMETIMES);
		GridPane.setHgrow(namelabel, Priority.SOMETIMES);
		
		namefield = new TextField();
		namefield.setPrefWidth(150);
		GridPane.setConstraints(namefield, 0, 3, 2, 1);
		GridPane.setVgrow(namefield, Priority.SOMETIMES);
		GridPane.setHgrow(namefield, Priority.SOMETIMES);
		
		
		
		citylabel.setFont(lebelsfont);
		GridPane.setConstraints(citylabel, 2, 2, 2, 1);
		GridPane.setVgrow(citylabel, Priority.SOMETIMES);
		GridPane.setHgrow(citylabel, Priority.SOMETIMES);
		
		cityfield = new TextField();
		GridPane.setConstraints(cityfield, 2, 3, 2, 1);
		GridPane.setVgrow(cityfield, Priority.SOMETIMES);
		GridPane.setHgrow(cityfield, Priority.SOMETIMES);
		
		
		
		provincelabel.setFont(lebelsfont);
		GridPane.setConstraints(provincelabel, 4, 2);
		GridPane.setVgrow(provincelabel, Priority.SOMETIMES);
		GridPane.setHgrow(provincelabel, Priority.SOMETIMES);
		
		provincesbox = new ComboBox<>();
		provincesbox.getItems().addAll(Province.values());
		provincesbox.setMaxWidth(120);
		GridPane.setConstraints(provincesbox, 4, 3);
		GridPane.setVgrow(provincesbox, Priority.SOMETIMES);
		GridPane.setHgrow(provincesbox, Priority.SOMETIMES);
		
		
		
		addresslabel.setFont(lebelsfont);
		GridPane.setConstraints(addresslabel, 5, 2, 2, 1);
		GridPane.setVgrow(addresslabel, Priority.SOMETIMES);
		GridPane.setHgrow(addresslabel, Priority.SOMETIMES);
		
		addressfield = new TextField();
		addressfield.setMinWidth(150);
		GridPane.setConstraints(addressfield, 5, 3, 2, 1);
		GridPane.setVgrow(addressfield, Priority.SOMETIMES);
		GridPane.setHgrow(addressfield, Priority.SOMETIMES);
		
		
		
		datetimelabel.setFont(lebelsfont);
		GridPane.setConstraints(datetimelabel, 0, 5, 2, 1);
		GridPane.setVgrow(datetimelabel, Priority.SOMETIMES);
		GridPane.setHgrow(datetimelabel, Priority.SOMETIMES);
		
		datepicker = new DatePicker();
		datepicker.setMaxWidth(200);
				
		hourspinner = new Spinner<>(0,23,0);
		hourspinner.setMinWidth(55);
		hourspinner.setPrefWidth(55);
		minutesspinner = new Spinner<>(0,55,0,5);
		minutesspinner.setPrefWidth(55);
		minutesspinner.setMinWidth(55);
		hboxtime = new HBox(10,hourspinner,minutesspinner);

		hboxdatetime = new HBox(20,datepicker,hboxtime);
		GridPane.setConstraints(hboxdatetime, 0, 6, 2, 1);
		GridPane.setVgrow(hboxdatetime, Priority.SOMETIMES);
		GridPane.setHgrow(hboxdatetime, Priority.SOMETIMES);
		
		
		
		genlabel.setFont(lebelsfont);
		GridPane.setConstraints(genlabel, 4, 5);
		GridPane.setVgrow(genlabel, Priority.SOMETIMES);
		GridPane.setHgrow(genlabel, Priority.SOMETIMES);
		
		gensbox = new ComboBox<>();
		gensbox.getItems().addAll(Arrays.copyOfRange(Genre.values(), 0, Genre.START_THEATER.ordinal()));
		GridPane.setConstraints(gensbox, 4, 6);
		GridPane.setVgrow(gensbox, Priority.SOMETIMES);
		GridPane.setHgrow(gensbox, Priority.SOMETIMES);
		
		
		
		artistlabel.setFont(lebelsfont);
		GridPane.setConstraints(artistlabel, 2, 5, 2, 1);
		GridPane.setVgrow(artistlabel, Priority.SOMETIMES);
		GridPane.setHgrow(artistlabel, Priority.SOMETIMES);
		
		artistfield = new TextField();
		GridPane.setConstraints(artistfield, 2, 6, 2, 1);
		GridPane.setVgrow(artistfield, Priority.SOMETIMES);
		GridPane.setHgrow(artistfield, Priority.SOMETIMES);
		
		
		
		numtypeticketlabel.setFont(lebelsfont);
		GridPane.setConstraints(numtypeticketlabel, 5, 5, 2, 1);
		GridPane.setVgrow(numtypeticketlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numtypeticketlabel, Priority.SOMETIMES);
		
		typesticketbox = new ChoiceBox<>();
		typesticketbox.getItems().addAll("1","2","3");
		GridPane.setConstraints(typesticketbox, 5, 6);
		GridPane.setVgrow(typesticketbox, Priority.SOMETIMES);
		GridPane.setHgrow(typesticketbox, Priority.SOMETIMES);
		
		
		
		numbaselabel.setFont(lebelsfont);
		numbaselabel.setMinWidth(100);
		GridPane.setConstraints(numbaselabel, 0, 8);
		numbaselabel.setVisible(false);
		GridPane.setVgrow(numbaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(numbaselabel, Priority.SOMETIMES);
		
		numbasefield = new TextField();
		numbasefield.setMaxWidth(80);
		numbasefield.setMinWidth(30);
		GridPane.setConstraints(numbasefield, 0, 9);
		numbasefield.setVisible(false);
		GridPane.setVgrow(numbasefield, Priority.SOMETIMES);
		GridPane.setHgrow(numbasefield, Priority.SOMETIMES);
		
		
		
		numviplabel.setFont(lebelsfont);
		numviplabel.setMinWidth(90);
		GridPane.setConstraints(numviplabel, 2, 8);
		numviplabel.setVisible(false);
		GridPane.setVgrow(numviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(numviplabel, Priority.SOMETIMES);
		
		numvipfield = new TextField();
		numvipfield.setMaxWidth(80);
		numvipfield.setMinWidth(30);
		GridPane.setConstraints(numvipfield, 2, 9);
		numvipfield.setVisible(false);
		GridPane.setVgrow(numvipfield, Priority.SOMETIMES);
		GridPane.setHgrow(numvipfield, Priority.SOMETIMES);
		
		
		
		numpremiumlabel.setFont(lebelsfont);
		numpremiumlabel.setMinWidth(125);
		GridPane.setConstraints(numpremiumlabel, 4, 8);
		numpremiumlabel.setVisible(false);
		GridPane.setVgrow(numpremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumlabel, Priority.SOMETIMES);
		
		numpremiumfield = new TextField();
		numpremiumfield.setMaxWidth(80);
		numpremiumfield.setMinWidth(30);
		GridPane.setConstraints(numpremiumfield, 4, 9);
		numpremiumfield.setVisible(false);
		GridPane.setVgrow(numpremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumfield, Priority.SOMETIMES);
		
		
		
		photoButton.setFont(lebelsfont);
		GridPane.setConstraints(photoButton, 7, 8, 1, 2);
		GridPane.setHalignment(photoButton, HPos.CENTER);
		GridPane.setVgrow(photoButton, Priority.SOMETIMES);
		GridPane.setHgrow(photoButton, Priority.SOMETIMES);
		
		eventPhoto = new ImageView();
		eventPhoto.isSmooth();
		eventPhoto.setFitHeight(100);
		eventPhoto.setFitWidth(100);
		GridPane.setConstraints(eventPhoto, 7, 2, 1, 5);
		GridPane.setVgrow(eventPhoto, Priority.SOMETIMES);
		GridPane.setHgrow(eventPhoto, Priority.SOMETIMES);
		
		
		
		pricebaselabel.setFont(lebelsfont);
		pricebaselabel.setMinWidth(90);
		GridPane.setConstraints(pricebaselabel, 1, 8);
		pricebaselabel.setVisible(false);
		GridPane.setVgrow(pricebaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricebaselabel, Priority.SOMETIMES);
		
		pricebasefield = new TextField();
		pricebasefield.setMaxWidth(80);
		pricebasefield.setMinWidth(30);
		GridPane.setConstraints(pricebasefield, 1, 9);
		pricebasefield.setVisible(false);
		GridPane.setVgrow(pricebasefield, Priority.SOMETIMES);
		GridPane.setHgrow(pricebasefield, Priority.SOMETIMES);
		
		
		
		priceviplabel.setFont(lebelsfont);
		priceviplabel.setMinWidth(75);
		GridPane.setConstraints(priceviplabel, 3, 8);
		priceviplabel.setVisible(false);
		GridPane.setVgrow(priceviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(priceviplabel, Priority.SOMETIMES);
		
		pricevipfield = new TextField();
		pricevipfield.setMaxWidth(80);
		pricevipfield.setMinWidth(30);
		GridPane.setConstraints(pricevipfield, 3, 9);
		pricevipfield.setVisible(false);
		GridPane.setVgrow(pricevipfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricevipfield, Priority.SOMETIMES);
		
		
		
		pricepremiumlabel.setFont(lebelsfont);
		pricepremiumlabel.setMinWidth(110);
		GridPane.setConstraints(pricepremiumlabel, 5, 8);
		pricepremiumlabel.setVisible(false);
		GridPane.setVgrow(pricepremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumlabel, Priority.SOMETIMES);
		
		pricepremiumfield = new TextField();
		pricepremiumfield.setMaxWidth(80);
		pricepremiumfield.setMinWidth(30);
		GridPane.setConstraints(pricepremiumfield, 5, 9);
		pricepremiumfield.setVisible(false);
		GridPane.setVgrow(pricepremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumfield, Priority.SOMETIMES);
		
		
		
		descriptionlabel.setFont(lebelsfont);
		GridPane.setConstraints(descriptionlabel, 0, 11, 2, 1);
		GridPane.setVgrow(descriptionlabel, Priority.SOMETIMES);
		GridPane.setHgrow(descriptionlabel, Priority.SOMETIMES);
		
		descriptionarea = new TextArea();
		descriptionarea.maxHeight(20);
		GridPane.setConstraints(descriptionarea, 0,12,4,1);
		GridPane.setVgrow(descriptionarea, Priority.SOMETIMES);
		GridPane.setHgrow(descriptionarea, Priority.SOMETIMES);
		
		
		
		abort.setFont(lebelsfont);
		GridPane.setConstraints(abort, 0, 14);
		
		
		
		GridPane.setConstraints(errlabel, 1, 14,5,1);
		errlabel.setFont(Font.font("Helvetica",FontWeight.BOLD, 20));
		errlabel.setTextFill(Color.web("#FF0400"));;
		errlabel.setVisible(false);;
		GridPane.setHalignment(errlabel, HPos.CENTER);
		GridPane.setVgrow(errlabel, Priority.SOMETIMES);
		GridPane.setHgrow(errlabel, Priority.SOMETIMES);
		
		
		
		confirm.setFont(lebelsfont);
		GridPane.setConstraints(confirm, 7, 14);
		GridPane.setHalignment(confirm, HPos.RIGHT);
		
			
		
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
								   addressfield, datetimelabel, hboxdatetime, numtypeticketlabel, typesticketbox, 
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

	public Button getPhotoButton(){
		return photoButton;
	}
	
	public ImageView getPhotoView() {
		return eventPhoto;
	}
}
