package it.unipv.insfw23.TicketWave.modelView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Prova extends Scene {

    public Prova() {

        super(new BorderPane(), 400, 400);
        BorderPane pannello = (BorderPane) getRoot();
        pannello.setPrefSize(400, 400);
        pannello.setTop(ManagerUpperBar.getIstance());
        Pane pannello1 = new Pane();
        pannello1.setStyle("-fx-background-color: rgb(179,129,250)");
        pannello.setCenter(pannello1);



    }
}
