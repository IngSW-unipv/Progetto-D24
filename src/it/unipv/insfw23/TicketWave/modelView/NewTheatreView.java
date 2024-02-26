package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NewTheatreView extends Scene{
	
	private Button abort;
	private Button forward;
	
	public NewTheatreView() {
		super(new BorderPane(), 1080, 600);
		init();
	}

	public void init(){
		
		
		
		BorderPane layout = (BorderPane)  getRoot();
		

		layout.setStyle("-fx-background-color: #def1fa;");
		GridPane grid = new GridPane();
		
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setVgap(10);
		grid.setHgap(60);
//		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		
		Label titlelabel = new Label("Compilare i seguenti campi per proseguire:");
		titlelabel.setFont(Font.font("Arial", 30));
		GridPane.setConstraints(titlelabel, 0, 0, 3, 1);
		GridPane.setHalignment(titlelabel, HPos.CENTER);
		GridPane.setVgrow(titlelabel, Priority.SOMETIMES);
		GridPane.setHgrow(titlelabel, Priority.SOMETIMES);
		
		
		Label namelabel = new Label("Nome: ");
		GridPane.setConstraints(namelabel, 0, 2);
		GridPane.setVgrow(namelabel, Priority.SOMETIMES);
		GridPane.setHgrow(namelabel, Priority.SOMETIMES);
		
		TextField namefield = new TextField();
		GridPane.setConstraints(namefield, 0, 3);
		GridPane.setVgrow(namefield, Priority.SOMETIMES);
		GridPane.setHgrow(namefield, Priority.SOMETIMES);
		
		
		Label citylabel = new Label("Città: ");
		GridPane.setConstraints(citylabel, 1, 2);
		GridPane.setVgrow(citylabel, Priority.SOMETIMES);
		GridPane.setHgrow(citylabel, Priority.SOMETIMES);
		
		TextField cityfield = new TextField();
		GridPane.setConstraints(cityfield, 1, 3);
		GridPane.setVgrow(cityfield, Priority.SOMETIMES);
		GridPane.setHgrow(cityfield, Priority.SOMETIMES);
		
		
		Label provincelabel = new Label("Provincia: ");
		GridPane.setConstraints(provincelabel, 2, 2);
		GridPane.setVgrow(provincelabel, Priority.SOMETIMES);
		GridPane.setHgrow(provincelabel, Priority.SOMETIMES);
		
		ChoiceBox<String> provincesbox = new ChoiceBox<>();
		provincesbox.getItems().addAll("Milano", "Pavia", "Treviso", "Roma");
		GridPane.setConstraints(provincesbox, 2, 3);
		GridPane.setVgrow(provincesbox, Priority.SOMETIMES);
		GridPane.setHgrow(provincesbox, Priority.SOMETIMES);
		
		
		Label addresslabel = new Label("Indirizzo: ");
		GridPane.setConstraints(addresslabel, 0, 5);
		GridPane.setVgrow(addresslabel, Priority.SOMETIMES);
		GridPane.setHgrow(addresslabel, Priority.SOMETIMES);
		
		TextField addressfield = new TextField();
		GridPane.setConstraints(addressfield, 0, 6);
		GridPane.setVgrow(addressfield, Priority.SOMETIMES);
		GridPane.setHgrow(addressfield, Priority.SOMETIMES);
		
		
		Label numtypeticketlabel = new Label("Tipologie di biglietti: ");
		GridPane.setConstraints(numtypeticketlabel, 1, 5);
		GridPane.setVgrow(numtypeticketlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numtypeticketlabel, Priority.SOMETIMES);
		
		ChoiceBox<String> typesticketbox = new ChoiceBox<>();
		typesticketbox.getItems().addAll("1","2","3");
		GridPane.setConstraints(typesticketbox, 1, 6);
		GridPane.setVgrow(typesticketbox, Priority.SOMETIMES);
		GridPane.setHgrow(typesticketbox, Priority.SOMETIMES);
		
		
		Label authlabel = new Label("Autore: ");
		GridPane.setConstraints(authlabel, 2, 5);
		GridPane.setVgrow(authlabel, Priority.SOMETIMES);
		GridPane.setHgrow(authlabel, Priority.SOMETIMES);
		
		TextField authfield = new TextField();
		GridPane.setConstraints(authfield, 2, 6);
		GridPane.setVgrow(authfield, Priority.SOMETIMES);
		GridPane.setHgrow(authfield, Priority.SOMETIMES);
		
		
		Label artistlabel = new Label("Compagnia teatrale: ");
		GridPane.setConstraints(artistlabel, 0, 8);
		GridPane.setVgrow(artistlabel, Priority.SOMETIMES);
		GridPane.setHgrow(artistlabel, Priority.SOMETIMES);
		
		TextField artistfield = new TextField();
		GridPane.setConstraints(artistfield, 0, 9, 2, 1);
		GridPane.setVgrow(artistfield, Priority.SOMETIMES);
		GridPane.setHgrow(artistfield, Priority.SOMETIMES);
		
		Label numbaselabel = new Label("n. biglietti base: ");
		GridPane.setConstraints(numbaselabel, 0, 11);
		numbaselabel.setVisible(false);
		GridPane.setVgrow(numbaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(numbaselabel, Priority.SOMETIMES);
		
		TextField numbasefield = new TextField();
		GridPane.setConstraints(numbasefield, 0, 12);
		numbasefield.setVisible(false);
		GridPane.setVgrow(numbasefield, Priority.SOMETIMES);
		GridPane.setHgrow(numbasefield, Priority.SOMETIMES);
		
		Label numviplabel = new Label("n. biglietti vip: ");
		GridPane.setConstraints(numviplabel, 1, 11);
		numviplabel.setVisible(false);
		GridPane.setVgrow(numviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(numviplabel, Priority.SOMETIMES);
		
		TextField numvipfield = new TextField();
		GridPane.setConstraints(numvipfield, 1, 12);
		numvipfield.setVisible(false);
		GridPane.setVgrow(numvipfield, Priority.SOMETIMES);
		GridPane.setHgrow(numvipfield, Priority.SOMETIMES);
		
		Label numpremiumlabel = new Label("n. biglietti premium: ");
		GridPane.setConstraints(numpremiumlabel, 2, 11);
		numpremiumlabel.setVisible(false);
		GridPane.setVgrow(numpremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumlabel, Priority.SOMETIMES);
		
		TextField numpremiumfield = new TextField();
		GridPane.setConstraints(numpremiumfield, 2, 12);
		numpremiumfield.setVisible(false);
		GridPane.setVgrow(numpremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(numpremiumfield, Priority.SOMETIMES);
		
		Label pricebaselabel = new Label("prezzo base: ");
		GridPane.setConstraints(pricebaselabel, 0, 14);
		pricebaselabel.setVisible(false);
		GridPane.setVgrow(pricebaselabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricebaselabel, Priority.SOMETIMES);
		
		TextField pricebasefield = new TextField();
		GridPane.setConstraints(pricebasefield, 0, 15);
		pricebasefield.setVisible(false);
		GridPane.setVgrow(pricebasefield, Priority.SOMETIMES);
		GridPane.setHgrow(pricebasefield, Priority.SOMETIMES);
		
		Label priceviplabel = new Label("prezzo vip: ");
		GridPane.setConstraints(priceviplabel, 1, 14);
		priceviplabel.setVisible(false);
		GridPane.setVgrow(priceviplabel, Priority.SOMETIMES);
		GridPane.setHgrow(priceviplabel, Priority.SOMETIMES);
		
		TextField pricevipfield = new TextField();
		GridPane.setConstraints(pricevipfield, 1, 15);
		pricevipfield.setVisible(false);
		GridPane.setVgrow(pricevipfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricevipfield, Priority.SOMETIMES);
		
		Label pricepremiumlabel = new Label("prezzo premium: ");
		GridPane.setConstraints(pricepremiumlabel, 2, 14);
		pricepremiumlabel.setVisible(false);
		GridPane.setVgrow(pricepremiumlabel, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumlabel, Priority.SOMETIMES);
		
		TextField pricepremiumfield = new TextField();
		GridPane.setConstraints(pricepremiumfield, 2, 15);
		pricepremiumfield.setVisible(false);
		GridPane.setVgrow(pricepremiumfield, Priority.SOMETIMES);
		GridPane.setHgrow(pricepremiumfield, Priority.SOMETIMES);
		
		
		forward = new Button("Conferma");
		GridPane.setConstraints(forward, 2, 16);
		GridPane.setHalignment(forward, HPos.RIGHT);
		
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
								    addressfield,numtypeticketlabel, typesticketbox, authlabel, authfield, artistlabel, artistfield, 
									numbaselabel, numbasefield, numviplabel, numvipfield, numpremiumlabel, numpremiumfield,
									pricebaselabel, pricebasefield, priceviplabel, pricevipfield, pricepremiumlabel, pricepremiumfield, 
									forward, abort);
		
		layout.setTop(ManagerUpperBar.getIstance());
		layout.setCenter(grid);
		layout.setBottom(LowerBar.getInstance());
		
	}
	
	public Button getAbortButton() {
		return abort;
	}
	
	public Button getForwardButton() {
		return forward;
	}
	
	
	

	
}
