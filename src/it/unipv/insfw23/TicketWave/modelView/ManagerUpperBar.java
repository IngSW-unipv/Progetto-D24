package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelController.MainController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManagerUpperBar extends HBox {
    // variabile che memorizza l'unica istanza
    private Button statsButton;
    private static ManagerUpperBar istance;
    // costruttore privato per singleton
    private ManagerUpperBar() {

        DropShadow ombraSup = new DropShadow();
        ombraSup.setColor(Color.GRAY);
        setEffect(ombraSup);
        setMinHeight(60);
        setBackground(new Background(new BackgroundFill(Color.web("#80C1E2"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titolo = new Label(" TicketWave  ");
        titolo.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 40));
        titolo.setTextFill(Color.BLACK);

        ImageView iconLogo = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        iconLogo.setFitHeight(60);
        iconLogo.setPreserveRatio(true);
        setAlignment(Pos.CENTER_LEFT);

        Button statsButton = new Button();
        this.statsButton = statsButton;
        /*****************************
        statsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Cliccato statistche");
            }
        });
        */

        Button searchButton = new Button();
        searchButton.setStyle("-fx-background-color: #80C1E2");
        ImageView searchIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/search_glass.png");
        searchIcon.setFitHeight(25);
        searchIcon.setFitWidth(29);
        searchButton.setGraphic(searchIcon);


        statsButton.setStyle("-fx-background-color: #80C1E2");
        ImageView statsIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/statistics.png");
        statsIcon.setFitHeight(25);
        statsIcon.setFitWidth(29);
        statsButton.setGraphic(statsIcon);

        Button profileButton = new Button();
        profileButton.setStyle("-fx-background-color: #80C1E2");
        ImageView profileIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/user.png");
        profileIcon.setFitHeight(25);
        profileIcon.setFitWidth(29);
        profileButton.setGraphic(profileIcon);

        Button eventPlusButton = new Button();
        eventPlusButton.setStyle("-fx-background-color: #80C1E2");
        ImageView eventPlusIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/plus.png");
        eventPlusIcon.setFitHeight(25);
        eventPlusIcon.setFitWidth(29);
        eventPlusButton.setGraphic(eventPlusIcon);

        Region spacer = new Region();
        setHgrow(spacer, Priority.ALWAYS);
        getChildren().addAll(titolo, iconLogo, spacer,searchButton, eventPlusButton, statsButton, profileButton);
        setAlignment(Pos.CENTER_LEFT);
    }

    //Metodo statico per ottenere l'unica istanza
    public static ManagerUpperBar getIstance(){
        if(istance == null){
            istance = new ManagerUpperBar();
        }
        return istance;
    }

    public Button getStatsButton() {
        return statsButton;
    }
}
