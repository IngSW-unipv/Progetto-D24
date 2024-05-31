package it.unipv.insfw23.TicketWave.modelController.controller.research;

//import it.unipv.insfw23.TicketWave.modelView.research.ResearchNodesView;
import it.unipv.insfw23.TicketWave.dao.research.ResearchDAO;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.EventHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class ResearchController {
    private final Stage mainStage;
    // le mie view
    private final ResearchView rv;
    private ArrayList<String> pr, gen; // sono gli arrayList che contengono i filtri selezionati, per cui le province selezionate ed i generi selezionati

    // costruttore
    public ResearchController(Stage mainStage, ResearchView rv) {
        this.mainStage = mainStage;
        this.rv = rv;
        ResearchDAO rd = new ResearchDAO();
        try {
            rd.getFilteredEvents(rv.getSearchBar().getText(), pr.toString(), gen.toString()); // qua forse dovrei intervallare a virgole
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setResearchListener();
    }
    public void setResearchListener() {
        // click sul bottone di ricerca => appare la table con i risultati
        EventHandler<javafx.scene.input.MouseEvent> researchPressHandlerResearchView = new EventHandler<>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                System.out.println("Faccio la query di ricerca");
                System.out.println(rv.getSearchBar().getText()); // in questo modo devo prendere la searchbar e passarla al ResearchDAO
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
                // Prendo lo user connesso a questa sessione dell'applicativo
                ConnectedUser cu = ConnectedUser.getInstance();
                TicketPageView tpv = new TicketPageView();
                tpv.setComponents(cu.getUser().isCustomer(), rv.getTable().getSelectionModel().getSelectedItem().getType(), rv.getTable().getSelectionModel().getSelectedItem().getName(), rv.getTable().getSelectionModel().getSelectedItem().getCity(),
                                  rv.getTable().getSelectionModel().getSelectedItem().getLocation(), rv.getTable().getSelectionModel().getSelectedItem().getProvince(), rv.getTable().getSelectionModel().getSelectedItem().getDate(),
                                  rv.getTable().getSelectionModel().getSelectedItem().getArtists(), rv.getTable().getSelectionModel().getSelectedItem().getSeatsRemainedNumberForType(), rv.getTable().getSelectionModel().getSelectedItem().getPrice());
                // creazione del TicketPageController
                TicketPageController tpc =  new TicketPageController(mainStage, tpv, rv.getTable().getSelectionModel().getSelectedItem(), rv);
                mainStage.setScene(tpv);
            }
        };
        rv.getTable().setOnMouseClicked(eventPressHandler);

        // click su un genere specifico presente nel filtro generi della ricerca
        EventHandler<ActionEvent> genrePressHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CheckBox cmi = (CheckBox) actionEvent.getSource();
                if (cmi.isSelected()){ // se un checkbox viene selezionato => il genere fa parte dell'array list di stringhe, altrimenti no
                    System.out.println(cmi.getText() + " is selected");
                    gen.add(cmi.getText());
                } else {
                    System.out.println(cmi.getText() + " is deselected");
                    gen.remove(cmi.getText());
                }
            }
        };
        rv.getGenv().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(genrePressHandler));

        // click su una provincia specifica presente nel filtro delle province
        EventHandler<ActionEvent> provincePressHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CheckBox cmi = (CheckBox) actionEvent.getSource();
                if (cmi.isSelected()){ // se un checkbox viene selezionato => la provincia fa parte dell'array list di stringhe, altrimenti no
                    System.out.println(cmi.getText() + " is selected");
                    pr.add(cmi.getText());
                } else {
                    System.out.println(cmi.getText() + " is deselected");
                    pr.remove(cmi.getText());
                }
            }
        };
        rv.getPrv().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(provincePressHandler));

    }
}
