package it.unipv.insfw23.TicketWave.modelController.controller.research;

//import it.unipv.insfw23.TicketWave.modelView.research.ResearchNodesView;
import it.unipv.insfw23.TicketWave.dao.research.ResearchDAO;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.EventHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ResearchController {
    private final Stage mainStage;
    // le mie view
    private final ResearchView rv;
    private ArrayList<String> pr, gen; // sono gli arrayList che contengono i filtri selezionati, per cui le province selezionate ed i generi selezionati
    private ResearchDAO rd;

    // costruttore
    public ResearchController(Stage mainStage, ResearchView rv) {
        this.mainStage = mainStage;
        this.rv = rv;
        pr = new ArrayList<>();
        gen = new ArrayList<>();
        setResearchListener();
    }
    public void setResearchListener() {
        // click sul bottone di ricerca => appare la table con i risultati
        EventHandler<javafx.scene.input.MouseEvent> researchPressHandlerResearchView = new EventHandler<>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                ResearchDAO rd = new ResearchDAO();
                ArrayList<Event> ev = new ArrayList<>();
                if (Objects.equals(rv.getSearchBar().getText(), "")){ // se non scrivo nulla nella barra di ricerca => ricerco tutti gli eventi e li stampo
                    try { // CHIAMATA AL DAO
                        ev = rd.getAllEvents(); // devo passarla alla tabella
                        ObservableList<Event> evs = FXCollections.observableArrayList(ev);
                        rv.getTable().setItems(evs); // mostro gli eventi nella tabella dei risultati
                    } catch (SQLException e) {
                        throw new RuntimeException("Tutti gli eventi non trovati (ResearchController riga 56)");
                    }
                } else { // se scrivo qualcosa sulla barra di ricerca faccio una ricerca filtrata
                    try {
                        ev = rd.getFilteredEvents(rv.getSearchBar().getText(), pr, gen);
                        ObservableList<Event> evs = FXCollections.observableArrayList(ev);
                        rv.getTable().setItems(evs); // mostro gli eventi filtrati nella tabella dei risultati
                    } catch (SQLException e) {
                        throw new RuntimeException("Eventi filtrati non trovati (ResearchController riga 64)");
                    }
                }
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
                if (rv.getTable().getItems() == null){ // CERCARE METODO PER DISABILITARE IL CLICK SU RIGHE VUOTE O SULL'INTERA TABELLA VUOTA
                    rv.getTable().getSelectionModel().clearSelection();
                    rv.getTable().setSelectionModel(null);
                } else {
                    rv.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    System.out.println(rv.getTable().getSelectionModel().getSelectedItem()); // prendo l'elemento cliccato dalla tabella

                    // Prendo lo user connesso a questa sessione dell'applicativo
                    ConnectedUser cu = ConnectedUser.getInstance();
                    TicketPageView tpv = new TicketPageView();
                    tpv.setComponents(cu.getUser().isCustomer(), rv.getTable().getSelectionModel().getSelectedItem().getType(), rv.getTable().getSelectionModel().getSelectedItem().getName(), rv.getTable().getSelectionModel().getSelectedItem().getCity(),
                            rv.getTable().getSelectionModel().getSelectedItem().getLocation(), rv.getTable().getSelectionModel().getSelectedItem().getProvince(), rv.getTable().getSelectionModel().getSelectedItem().getDate(),
                            rv.getTable().getSelectionModel().getSelectedItem().getArtists(), rv.getTable().getSelectionModel().getSelectedItem().getSeatsRemainedNumberForType(), rv.getTable().getSelectionModel().getSelectedItem().getPrices(), rv.getTable().getSelectionModel().getSelectedItem().getPhoto());
                    // creazione del TicketPageController
                    TicketPageController tpc =  new TicketPageController(mainStage, tpv, rv.getTable().getSelectionModel().getSelectedItem(), rv);
                    mainStage.setScene(tpv);
                }
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
