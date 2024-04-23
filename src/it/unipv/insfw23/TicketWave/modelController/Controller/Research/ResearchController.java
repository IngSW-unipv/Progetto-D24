package it.unipv.insfw23.TicketWave.modelController.Controller.Research;

import it.unipv.insfw23.TicketWave.modelView.research.ResearchNodesView;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.nio.channels.SelectionKey;

import javafx.event.EventHandler;

public class ResearchController {
    private final Stage mainStage;
    // le mie view
    private final ResearchView rv;

    // costruttore
    public ResearchController(Stage mainStage, ResearchView rv) {
        this.mainStage = mainStage;
        this.rv = rv;
        setResearchListener();
    }
    public void setResearchListener() {
        // click sul bottone di ricerca => appare la table con i risultati
        EventHandler<javafx.scene.input.MouseEvent> researchPressHandlerResearchView = new EventHandler<>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                System.out.println("Faccio la query di ricerca");
                System.out.println(rv.getresearchnodeview().getSearchBar().getText());
                rv.getresearchnodeview().getTable().setVisible(true);
                rv.getresearchnodeview().getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }
        };
        rv.getresearchnodeview().getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);

        // quando clicco su una riga della tabella prendo l'evento in quella riga
        EventHandler<javafx.scene.input.MouseEvent> eventPressHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(rv.getresearchnodeview().getTable().getSelectionModel().getSelectedItem()); // prendo l'elemento cliccato dalla tabella
            }
        };
        rv.getresearchnodeview().getTable().setOnMouseClicked(eventPressHandler);

        // Click filtri per il genere
        for (CheckMenuItem cmi : rv.getresearchnodeview().getGenv()){
            if (cmi.isSelected()){
                cmi.setOnAction(this::genrePressHandler); // se viene premuto il checkmenuitem va a genrePressHandler
                System.out.println("1");
            }
        }
    }
    private void genrePressHandler(ActionEvent event) {
        CheckMenuItem cmi = (CheckMenuItem) event.getSource();
        System.out.println("2");
        // click sui filtri del genere
        if (cmi.isSelected()) {
            System.out.println(cmi.getText() + " is selected");
        } else {
            System.out.println(cmi.getText() + " is deselected");
        }
    }
}
