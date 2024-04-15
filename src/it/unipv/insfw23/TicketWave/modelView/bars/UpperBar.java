package it.unipv.insfw23.TicketWave.modelView.bars;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UpperBar extends HBox {
	
    // variabile che memorizza l'unica istanza
    private static UpperBar istance;
    
	private Button eventPlusButton;
    private Button statsButton;
    private Button searchButton;
    private Button profileButton;
    

    // costruttore privato per singleton
    private UpperBar() {

        DropShadow ombraSup = new DropShadow();
        ombraSup.setColor(Color.GRAY);
        setEffect(ombraSup);
        setMinHeight(60);
        setBackground(new Background(new BackgroundFill(Color.web("#2E5984"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titolo = new Label(" Ticketwave ");
        titolo.setFont(Font.font("Berlin Sans FB", FontWeight.MEDIUM, 40));
        titolo.setTextFill(Color.WHITE);

        ImageView iconLogo = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/logo.png");
        iconLogo.setFitHeight(60);
        iconLogo.setPreserveRatio(true);
        setAlignment(Pos.CENTER_LEFT);

        
        //new event button
        Button eventPlusButton = new Button();
        this.eventPlusButton = eventPlusButton;
        eventPlusButton.setStyle("-fx-background-color: #2E5984");
        ImageView eventPlusIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/plus.png");
        eventPlusIcon.setFitHeight(25);
        eventPlusIcon.setFitWidth(29);
        eventPlusButton.setGraphic(eventPlusIcon);
        
        
        //stats button
        Button statsButton = new Button();
        this.statsButton = statsButton;
        statsButton.setStyle("-fx-background-color: #2E5984");
        ImageView statsIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/statistics.png");
        statsIcon.setFitHeight(25);
        statsIcon.setFitWidth(29);
        statsButton.setGraphic(statsIcon);
        
        
        // search button
        Button searchButton = new Button();
        this.searchButton = searchButton;
        /*****************************
        statsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Cliccato statistche");
            }
        });
        */
        searchButton.setStyle("-fx-background-color: #2E5984");
        ImageView searchIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/search_glass.png");
        searchIcon.setFitHeight(25);
        searchIcon.setFitWidth(29);
        searchButton.setGraphic(searchIcon);



        Button profileButton = new Button();
        this.profileButton = profileButton;
        profileButton.setStyle("-fx-background-color: #2E5984");
        ImageView profileIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/user.png");
        profileIcon.setFitHeight(25);
        profileIcon.setFitWidth(29);
        profileButton.setGraphic(profileIcon);

        
		
		
        Region spacer = new Region();
        setHgrow(spacer, Priority.ALWAYS);
        getChildren().addAll(titolo, iconLogo, spacer, eventPlusButton, statsButton, searchButton, profileButton);
        setAlignment(Pos.CENTER_LEFT);
    }

    //Metodo statico per ottenere l'unica istanza
    public static UpperBar getIstance(){
        if(istance == null){
            istance = new UpperBar();
        }
        return istance;
    }
    
    public Button getEventPlusButton() {
    	return eventPlusButton;
    }
    
    public Button getStatsButton() {
        return statsButton;
    }

    public Button getSearchButton() { 
    	return searchButton; 
    }

    public Button getProfileButton() {
    	return profileButton;
    }
    
    
    public void setForNoLogged() {
    	eventPlusButton.setVisible(false);
    	statsButton.setVisible(false);
    	searchButton.setVisible(false);
    	profileButton.setVisible(false); 	
    }
    
    public void setForCustomer() {
    	eventPlusButton.setVisible(false);
    	statsButton.setVisible(false);
    }

    public void setForManager() {
         eventPlusButton.setVisible(true);
         statsButton.setVisible(true);
    }
}
