package it.unipv.insfw23.TicketWave.modelView.user;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Popup;

public class NoMoreEventsPopup extends Popup {

    private static NoMoreEventsPopup istance;
    private Button backButton;
    private Button subscriptionButton;
    private Label messageLabel;


    private NoMoreEventsPopup(){

        BorderPane content = new BorderPane();

        messageLabel = new Label();
        backButton = new Button("Indietro");
        subscriptionButton = new Button("Procedi");

        content.setTop(messageLabel);
        content.setLeft(backButton);
        content.setRight(subscriptionButton);
        BorderPane.setAlignment(subscriptionButton, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);

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

    public Button getBackButton(){
        return backButton;
    }

    public Button getSubscriptionButton(){
        return subscriptionButton;
    }

    public void setMessageLabel(String messageText){
        messageLabel.setText(messageText);
    }
}
