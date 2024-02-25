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

public class UpperBar extends HBox {
    // variabile che memorizza l'unica istanza
    private Button statsButton;
    private Button searchButton;
    private static UpperBar istance;
    // costruttore privato per singleton
    private UpperBar() {

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


        /*****************************
         statsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
        System.out.println("Cliccato statistche");
        }
        });
         */








        Region spacer = new Region();
        setHgrow(spacer, Priority.ALWAYS);
        getChildren().addAll(titolo, iconLogo, spacer);
        setAlignment(Pos.CENTER_LEFT);
    }

    //Metodo statico per ottenere l'unica istanza
    public static UpperBar getIstance(){
        if(istance == null){
            istance = new UpperBar();
        }
        return istance;
    }
}

