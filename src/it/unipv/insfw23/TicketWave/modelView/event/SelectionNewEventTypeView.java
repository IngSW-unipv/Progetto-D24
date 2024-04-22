package it.unipv.insfw23.TicketWave.modelView.event;

import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class SelectionNewEventTypeView extends Scene{
	private Button abortbutton;
	private Button forwardbutton;
	private RadioButton concerto;
	private RadioButton festival;
	private RadioButton teatro;
	private RadioButton altro;
	private RadioButton choice;
	private BorderPane layout;
	private GridPane grid;

	
	public SelectionNewEventTypeView() {
		super(new BorderPane(), 1080, 600);
        init();
	}
	
	private void init() {
		layout = (BorderPane) getRoot();
		layout.setStyle("-fx-background-color: #def1fa;");
		
		grid = new GridPane();
		grid.setPadding(new Insets(50, 30, 50, 30));
		grid.setVgap(15);
		grid.setHgap(90);
//		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		Label title = new Label("Selezionare la tipologia del'evento che si vuole pubblicare:");
		title.setFont(Font.font("Arial", 25));
		title.setStyle("-fx-alignment: CENTER");
		GridPane.setConstraints(title, 0, 0, 6, 1);
		GridPane.setValignment(title, VPos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setHgrow(title, Priority.SOMETIMES);
		GridPane.setVgrow(title, Priority.SOMETIMES);
		
		ToggleGroup togglegroup = new ToggleGroup();
		
		concerto = new RadioButton("Concerto");
		concerto.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(concerto, 1, 1);
		concerto.setToggleGroup(togglegroup);
		GridPane.setHgrow(concerto, Priority.SOMETIMES);
		GridPane.setVgrow(concerto, Priority.SOMETIMES);
		
		festival = new RadioButton("Festival");
		festival.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(festival, 3, 1);
		festival.setToggleGroup(togglegroup);
		GridPane.setHgrow(festival, Priority.SOMETIMES);
		GridPane.setVgrow(festival, Priority.SOMETIMES);
//		GridPane.setHalignment(festival, HPos.CENTER);
		
		teatro = new RadioButton("Spettacolo teatrale");
		teatro.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(teatro, 1, 2);
		teatro.setToggleGroup(togglegroup);
		
		altro = new RadioButton("Altro");
		altro.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(altro, 3, 2);
		altro.setToggleGroup(togglegroup);
		GridPane.setHgrow(teatro, Priority.SOMETIMES);
		GridPane.setVgrow(altro, Priority.SOMETIMES);
//		GridPane.setHalignment(altro, HPos.CENTER);
		
		
//		choice = (RadioButton)togglegroup.getSelectedToggle();
		
		forwardbutton = new Button("Prosegui");
		forwardbutton.setFont(Font.font("Arial", 15));
		GridPane.setConstraints(forwardbutton, 3, 3, 2, 1);
		forwardbutton.setStyle("-fx-alignment: CENTER");
//		GridPane.setValignment(avanti, VPos.CENTER);
		GridPane.setHalignment(forwardbutton, HPos.CENTER);
		GridPane.setHgrow(forwardbutton, Priority.SOMETIMES);
		GridPane.setVgrow(forwardbutton, Priority.SOMETIMES);
		
		
		abortbutton = new Button("Annulla");
		abortbutton.setFont(Font.font("Arial", 15));
		GridPane.setConstraints(abortbutton, 1, 3);
		abortbutton.setStyle("-fx-alignment: CENTER");
		GridPane.setHgrow(abortbutton, Priority.SOMETIMES);
		GridPane.setVgrow(abortbutton, Priority.SOMETIMES);
		
		
				
		grid.getChildren().addAll(title, concerto, festival, teatro, altro, forwardbutton, abortbutton);
		
		layout.setStyle("-fx-background-color: #def1fa;");
		layout.setTop(UpperBar.getIstance());
		layout.setCenter(grid);
		layout.setBottom(LowerBar.getInstance());
	}
	
	public Button getAbortButton() {
		return abortbutton;
	}
	
	public Button getForwardButton() {
		return forwardbutton;
	}
	
	public RadioButton getConcertoRadioButton() {
		return concerto;
	}
	
	public RadioButton getTeathreRadioButton() {
		return teatro;
	}
	
	public RadioButton getFestivalRadioButton() {
		return festival;
	}
	
	public RadioButton getOtherRadioButton() {
		return altro;
	}
	
	
	
	public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(grid);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }

/*	@Override
	public void start(Stage primarystage) throws Exception {     
  

		Stage window = primarystage;
		
		BorderPane layout = new BorderPane();
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 30, 50, 30));
		grid.setVgap(15);
		grid.setHgap(90);
		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		Label title = new Label("Selezionare la tipologia del'evento che si vuole pubblicare:");
		title.setFont(Font.font("Arial", 25));
		title.setStyle("-fx-alignment: CENTER");
		GridPane.setConstraints(title, 0, 0, 6, 1);
		GridPane.setValignment(title, VPos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setHgrow(title, Priority.SOMETIMES);
		GridPane.setVgrow(title, Priority.SOMETIMES);
		
		ToggleGroup togglegroup = new ToggleGroup();
		
		RadioButton concerto = new RadioButton("Concerto");
		concerto.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(concerto, 1, 1);
		concerto.setToggleGroup(togglegroup);
		GridPane.setHgrow(concerto, Priority.SOMETIMES);
		GridPane.setVgrow(concerto, Priority.SOMETIMES);
		
		RadioButton festival = new RadioButton("Festival");
		festival.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(festival, 3, 1);
		festival.setToggleGroup(togglegroup);
		GridPane.setHgrow(festival, Priority.SOMETIMES);
		GridPane.setVgrow(festival, Priority.SOMETIMES);
//		GridPane.setHalignment(festival, HPos.CENTER);
		
		RadioButton teatro = new RadioButton("Spettacolo teatrale");
		teatro.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(teatro, 1, 2);
		teatro.setToggleGroup(togglegroup);
		
		RadioButton altro = new RadioButton("Altro");
		altro.setFont(Font.font("Arial", 18));
		GridPane.setConstraints(altro, 3, 2);
		altro.setToggleGroup(togglegroup);
		GridPane.setHgrow(teatro, Priority.SOMETIMES);
		GridPane.setVgrow(altro, Priority.SOMETIMES);
//		GridPane.setHalignment(altro, HPos.CENTER);
		
		Button avanti = new Button("Prosegui");
		avanti.setFont(Font.font("Arial", 15));
		GridPane.setConstraints(avanti, 3, 3, 2, 1);
		avanti.setStyle("-fx-alignment: CENTER");
//		GridPane.setValignment(avanti, VPos.CENTER);
		GridPane.setHalignment(avanti, HPos.CENTER);
		GridPane.setHgrow(avanti, Priority.SOMETIMES);
		GridPane.setVgrow(avanti, Priority.SOMETIMES);
		
		
		Button indietro = new Button("Annulla");
		indietro.setFont(Font.font("Arial", 15));
		GridPane.setConstraints(indietro, 1, 3);
		indietro.setStyle("-fx-alignment: CENTER");
		GridPane.setHgrow(indietro, Priority.SOMETIMES);
		GridPane.setVgrow(indietro, Priority.SOMETIMES);
		
		
				
		grid.getChildren().addAll(title, concerto, festival, teatro, altro, avanti, indietro);
		
		layout.setCenter(grid);

		//da attivare quando diventa una scena

		window.setMinHeight(400);
		window.setMinWidth(850);
		window.setHeight(900);
		window.setWidth(1200);
		
		
		Scene scene = new Scene(layout, window.getWidth(), window.getHeight());	
		
	
		
//		Scene scene = new Scene(layout, 1080, 600);
		
		window.setScene(scene);
        window.show();
        
        
		
	}

	public static void main(String[] args) {
		launch(args);
	}
*/	
}
