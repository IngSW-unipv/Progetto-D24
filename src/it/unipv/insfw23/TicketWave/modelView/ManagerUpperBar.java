package it.unipv.insfw23.TicketWave.modelView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManagerUpperBar extends HBox {
    // variabile che memorizza l'unica istanza
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
        statsButton.setStyle("-fx-background-color: #80C1E2");
        ImageView statsIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/statistics.png");
        statsIcon.setFitHeight(16);
        statsIcon.setFitWidth(20);
        statsButton.setGraphic(statsIcon);

        Region spacer = new Region();
        setHgrow(spacer, Priority.ALWAYS);
        getChildren().addAll(titolo, iconLogo, spacer, statsButton);
        setAlignment(Pos.CENTER_LEFT);
    }

    //Metodo statico per ottenere l'unica istanza
    public static ManagerUpperBar getIstance(){
        if(istance == null){
            istance = new ManagerUpperBar();
        }
        return istance;
    }

}
