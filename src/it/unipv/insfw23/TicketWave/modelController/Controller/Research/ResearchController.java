package it.unipv.insfw23.TicketWave.modelController.Controller.Research;

//import it.unipv.insfw23.TicketWave.modelView.research.ResearchNodesView;
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
                System.out.println(rv.getSearchBar().getText());
                rv.getTable().setVisible(true);
                rv.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }
        };
        rv.getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);

        // quando clicco su una riga della tabella prendo l'evento in quella riga
        EventHandler<javafx.scene.input.MouseEvent> eventPressHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(rv.getTable().getSelectionModel().getSelectedItem()); // prendo l'elemento cliccato dalla tabella
            }
        };
        rv.getTable().setOnMouseClicked(eventPressHandler);

        // click su un genere specifico presente nel filtro generi della ricerca
        EventHandler<ActionEvent> genrePressHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CheckMenuItem cmi = (CheckMenuItem) actionEvent.getSource();
                if (cmi.isSelected()){
                    System.out.println(cmi.getText() + " is selected");
                } else {
                    System.out.println(cmi.getText() + " is deselected");
                }
            }
        };
        rv.getGenv().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(genrePressHandler));

        // click su una provincia specifica presente nel filtro delle province
        EventHandler<ActionEvent> provincePressHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CheckMenuItem cmi = (CheckMenuItem) actionEvent.getSource();
                if (cmi.isSelected()){
                    System.out.println(cmi.getText() + " is selected");
                } else {
                    System.out.println(cmi.getText() + " is deselected");
                }
            }
        };
        rv.getPrv().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(provincePressHandler));

    }
}
