package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
	private Button abort;
	private Button confirm;
	

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
		GridPane.setConstraints(titlelabel, 0, 0, 3, 1);
		GridPane.setHalignment(titlelabel, HPos.CENTER);
		GridPane.setVgrow(titlelabel, Priority.SOMETIMES);
		GridPane.setHgrow(titlelabel, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(namelabel, 0, 2);
		GridPane.setVgrow(namelabel, Priority.SOMETIMES);
		GridPane.setHgrow(namelabel, Priority.SOMETIMES);
		
		namefield = new TextField();
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
		
		
		
		GridPane.setConstraints(addresslabel, 0, 5);
		GridPane.setVgrow(addresslabel, Priority.SOMETIMES);
		GridPane.setHgrow(addresslabel, Priority.SOMETIMES);
		
		addressfield = new TextField();
		GridPane.setConstraints(addressfield, 0, 6);
		GridPane.setVgrow(addressfield, Priority.SOMETIMES);
		GridPane.setHgrow(addressfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numtypeticketlabel, 1, 5);
		GridPane.setVgrow(numtypeticketlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numtypeticketlabel, Priority.SOMETIMES);
		
		typesticketbox = new ChoiceBox<>();
		typesticketbox.getItems().addAll("1","2","3");
		GridPane.setConstraints(typesticketbox, 1, 6);
		GridPane.setVgrow(typesticketbox, Priority.SOMETIMES);
		GridPane.setHgrow(typesticketbox, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(genlabel, 2, 5);
		GridPane.setVgrow(genlabel, Priority.SOMETIMES);
		GridPane.setHgrow(genlabel, Priority.SOMETIMES);
		
		gensbox = new ComboBox<>();
		gensbox.getItems().addAll(Genre.values());
		GridPane.setConstraints(gensbox, 2, 6);
		GridPane.setVgrow(gensbox, Priority.SOMETIMES);
		GridPane.setHgrow(gensbox, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(artistlabel, 0, 8);
		GridPane.setVgrow(artistlabel, Priority.SOMETIMES);
		GridPane.setHgrow(artistlabel, Priority.SOMETIMES);
		
		artistfield = new TextField();
		GridPane.setConstraints(artistfield, 0, 9, 2, 1);
		GridPane.setVgrow(artistfield, Priority.SOMETIMES);
		GridPane.setHgrow(artistfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numbaselabel, 0, 11);
		numbaselabel.setVisible(false);
		GridPane.setVgrow(numbaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(numbaselabel, Priority.SOMETIMES);
		
		numbasefield = new TextField();
		GridPane.setConstraints(numbasefield, 0, 12);
		numbasefield.setVisible(false);
		GridPane.setVgrow(numbasefield, Priority.SOMETIMES);
		GridPane.setHgrow(numbasefield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numviplabel, 1, 11);
		numviplabel.setVisible(false);
		GridPane.setVgrow(numviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(numviplabel, Priority.SOMETIMES);
		
		numvipfield = new TextField();
		GridPane.setConstraints(numvipfield, 1, 12);
		numvipfield.setVisible(false);
		GridPane.setVgrow(numvipfield, Priority.SOMETIMES);
		GridPane.setHgrow(numvipfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(numpremiumlabel, 2, 11);
		numpremiumlabel.setVisible(false);
		GridPane.setVgrow(numpremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumlabel, Priority.SOMETIMES);
		
		numpremiumfield = new TextField();
		GridPane.setConstraints(numpremiumfield, 2, 12);
		numpremiumfield.setVisible(false);
		GridPane.setVgrow(numpremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(pricebaselabel, 0, 14);
		pricebaselabel.setVisible(false);
		GridPane.setVgrow(pricebaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricebaselabel, Priority.SOMETIMES);
		
		pricebasefield = new TextField();
		GridPane.setConstraints(pricebasefield, 0, 15);
		pricebasefield.setVisible(false);
		GridPane.setVgrow(pricebasefield, Priority.SOMETIMES);
		GridPane.setHgrow(pricebasefield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(priceviplabel, 1, 14);
		priceviplabel.setVisible(false);
		GridPane.setVgrow(priceviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(priceviplabel, Priority.SOMETIMES);
		
		pricevipfield = new TextField();
		GridPane.setConstraints(pricevipfield, 1, 15);
		pricevipfield.setVisible(false);
		GridPane.setVgrow(pricevipfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricevipfield, Priority.SOMETIMES);
		
		
		
		GridPane.setConstraints(pricepremiumlabel, 2, 14);
		pricepremiumlabel.setVisible(false);
		GridPane.setVgrow(pricepremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumlabel, Priority.SOMETIMES);
		
		pricepremiumfield = new TextField();
		GridPane.setConstraints(pricepremiumfield, 2, 15);
		pricepremiumfield.setVisible(false);
		GridPane.setVgrow(pricepremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumfield, Priority.SOMETIMES);
		
		
		confirm = new Button("Conferma");
		GridPane.setConstraints(confirm, 2, 16);
		GridPane.setHalignment(confirm, HPos.RIGHT);
		
		abort = new Button("Annulla");
		GridPane.setConstraints(abort, 0, 16);
		
		
		
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
								    addressfield,numtypeticketlabel, typesticketbox, genlabel, gensbox, artistlabel, artistfield, 
									numbaselabel, numbasefield, numviplabel, numvipfield, numpremiumlabel, numpremiumfield,
									pricebaselabel, pricebasefield, priceviplabel, pricevipfield, pricepremiumlabel, pricepremiumfield, 
									confirm, abort);
		
		layout.setTop(ManagerUpperBar.getIstance());
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

	public int getNumbasefield() {
		return Integer.parseInt(numbasefield.getText());
	}

	public int getNumvipfield() {
		return Integer.parseInt(numvipfield.getText());
	}

	public int getNumpremiumfield() {
		return Integer.parseInt(numpremiumfield.getText());
	}

	public double getPricebasefield() throws NumberFormatException{
		return Double.parseDouble(pricebasefield.getText());
	}

	public double getPricevipfield() {
		return Double.parseDouble(pricevipfield.getText());
	}

	public double getPricepremiumfield() {
		return Double.parseDouble(pricepremiumfield.getText());
	}

	public Button getAbortButton() {
		return abort;
	}
	
	public Button getConfirmButton() {
		return confirm;
	}
	
	
	
	
	
}


	
	
	

