package it.unipv.insfw23.TicketWave.modelView.statistics;

import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class NoMoreEventsPopup extends Popup {

    private static NoMoreEventsPopup istance;
    private Button okButton;
    private Button subscriptionButton;

    private NoMoreEventsPopup(){

        BorderPane content = new BorderPane();

        Label message = new Label("Hai raggiunto il numero massimo di eventi per \nquesto mese! \nSe non vuoi aspettare, cambia subito il tuo abbonamento!");
        okButton = new Button("Indietro");
        subscriptionButton = new Button("Procedi");

        content.setTop(message);
        content.setLeft(okButton);
        content.setRight(subscriptionButton);
        BorderPane.setAlignment(subscriptionButton, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(okButton, Pos.BOTTOM_LEFT);

        getContent().add(content);
        content.setStyle("-fx-background-color: lightblue; " + // Colore di sfondo
                         "-fx-background-radius: 10px; " + // Stondamento angoli
                         "-fx-padding: 10px;" +
                         "-fx-border-width: 1px; " +
                         "-fx-border-color: black; " +
                         "-fx-effect: dropshadow(gaussian, grey, 3, 0.5, 3, 3);" +
                         "-fx-border-radius: 10px;" +
                         "-fx-font-family: 'Helvetica'; -fx-font-size: 12px; -fx-font-weight: bold;"
                );

        content.setPrefSize(350, 125);
        setAutoFix(true);
    }

    public static NoMoreEventsPopup getIstance(){
        if(istance == null){
            istance = new NoMoreEventsPopup();
        }
        return istance;
    }
}
