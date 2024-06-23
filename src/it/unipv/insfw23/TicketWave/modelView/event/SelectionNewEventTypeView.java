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
import javafx.scene.text.FontWeight;

public class SelectionNewEventTypeView extends Scene{
	private Font labelsfont = Font.font("Helvetica", FontWeight.NORMAL, 16);
	private Button abortbutton;
	private Button forwardbutton;
	private RadioButton concert;
	private RadioButton festival;
	private RadioButton theatre;
	private RadioButton other;
	private BorderPane layout;
	private GridPane grid;

	
	public SelectionNewEventTypeView() {
		super(new BorderPane(), 1080, 600);
        init();
	}
	
	private void init() {
		layout = (BorderPane) getRoot();
		layout.setStyle("-fx-background-color: #91bad6;");
		
		grid = new GridPane();
		grid.setPadding(new Insets(50, 30, 50, 30));
		grid.setVgap(15);
		grid.setHgap(90);
//		grid.setGridLinesVisible(true);
		grid.setAlignment(Pos.CENTER);
		
		
		Label title = new Label("Selezionare la tipologia del'evento che si vuole pubblicare:");
		title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
		title.setStyle("-fx-alignment: CENTER");
		GridPane.setConstraints(title, 0, 0, 6, 1);
		GridPane.setValignment(title, VPos.CENTER);
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setHgrow(title, Priority.SOMETIMES);
		GridPane.setVgrow(title, Priority.SOMETIMES);
		
		
		
		ToggleGroup togglegroup = new ToggleGroup();
		
		concert = new RadioButton("Concerto");
		concert.setFont(labelsfont);
		GridPane.setConstraints(concert, 1, 1);
		concert.setToggleGroup(togglegroup);
		GridPane.setHgrow(concert, Priority.SOMETIMES);
		GridPane.setVgrow(concert, Priority.SOMETIMES);
		
		
		festival = new RadioButton("Festival");
		festival.setFont(labelsfont);
		GridPane.setConstraints(festival, 3, 1);
		festival.setToggleGroup(togglegroup);
		GridPane.setHgrow(festival, Priority.SOMETIMES);
		GridPane.setVgrow(festival, Priority.SOMETIMES);
		
		
		theatre = new RadioButton("Spettacolo teatrale");
		theatre.setFont(labelsfont);
		GridPane.setConstraints(theatre, 1, 2);
		theatre.setToggleGroup(togglegroup);
		
		
		other = new RadioButton("Altro");
		other.setFont(labelsfont);
		GridPane.setConstraints(other, 3, 2);
		other.setToggleGroup(togglegroup);
		GridPane.setHgrow(theatre, Priority.SOMETIMES);
		GridPane.setVgrow(other, Priority.SOMETIMES);
		
		
		
		forwardbutton = new Button("Prosegui");
		forwardbutton.setFont(labelsfont);
		GridPane.setConstraints(forwardbutton, 3, 3, 2, 1);
		forwardbutton.setStyle("-fx-alignment: CENTER");
		GridPane.setHalignment(forwardbutton, HPos.CENTER);
		GridPane.setHgrow(forwardbutton, Priority.SOMETIMES);
		GridPane.setVgrow(forwardbutton, Priority.SOMETIMES);
		
		
		abortbutton = new Button("Annulla");
		abortbutton.setFont(labelsfont);
		GridPane.setConstraints(abortbutton, 1, 3);
		abortbutton.setStyle("-fx-alignment: CENTER");
		GridPane.setHgrow(abortbutton, Priority.SOMETIMES);
		GridPane.setVgrow(abortbutton, Priority.SOMETIMES);
		
		
				
		grid.getChildren().addAll(title, concert, festival, theatre, other, forwardbutton, abortbutton);
		
		
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
		return concert;
	}
	
	public RadioButton getTeathreRadioButton() {
		return theatre;
	}
	
	public RadioButton getFestivalRadioButton() {
		return festival;
	}
	
	public RadioButton getOtherRadioButton() {
		return other;
	}
	
	
	
	public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(grid);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }
	
}
