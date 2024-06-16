package it.unipv.insfw23.TicketWave.modelController.controller.research;

import it.unipv.insfw23.TicketWave.dao.research.ResearchDAO;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
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

/**
 * This controller manages all clicks made on the ResearchView.
 */
public class ResearchController {

    // ATTRIBUTES:
    private final Stage mainStage;
    private final ResearchView rv;
    private final ArrayList<String> pr, gen; // sono gli arrayList che contengono i filtri selezionati, per cui le province selezionate ed i generi selezionati

    // CONSTRUCTOR:

    /**
     * The constructor of ResearchController takes 2 parameters as input and calls the serResearchListener() method to manage all the events that occur on the ResearchView
     * @param mainStage
     * @param rv
     */
    public ResearchController(Stage mainStage, ResearchView rv) {
        this.mainStage = mainStage;
        this.rv = rv;
        pr = new ArrayList<>();
        gen = new ArrayList<>();
        setResearchListener();
    }

    // PUBLIC METHODS:

    /**
     * Method that sets all the listeners of the ResearchView.
     * <ul>
     *     <li>
     *         The first listener handles the researchButton. If the button is pressed without parameters in the text field or without putting filters, then all the events are taken from the database and placed in the table view.
     *         On the other hand, if the button is pressed and text is written in the text field or filters are imposed, then all the events that comply with the imposed filters are placed in the table view.
     *     </li>
     *     <li>
     *         The second listener handles the click on a row of the tableView. If a row is pressed within the tableView. If a row is pressed in the table, then I have to go to the TicketPageView to purchase a ticket regarding that event.
     *     </li>
     *     <li>
     *         The third listener handles the genreFilter, that is an array of checkboxes. If a checkbox (represents a genre) of the genreFilter is pressed, then i have to add it to the gen, that is an arrayList of Strings. This is useful for getting filtered events from the database.
     *     </li>
     *     <li>
     *         The fourth listener handles the provinceFilter, that is an array of checkboxes. If a checkbox (represents a province) of the provinceFilter is pressed, then i have to add it to the pr, that is an arrayList of Strings. This is useful for getting filtered events from the database.
     *     </li>
     *
     *
     * </ul>
     */
    public void setResearchListener() {
        // click sul bottone di ricerca => appare la table con i risultati
        EventHandler<javafx.scene.input.MouseEvent> researchPressHandlerResearchView = mouseEvent -> {
            ResearchDAO rd = new ResearchDAO();
            ArrayList<Event> ev = new ArrayList<>();
            if (Objects.equals(rv.getSearchBar().getText(), "") && pr.isEmpty() && gen.isEmpty()){ // se non scrivo nulla nella barra di ricerca => ricerco tutti gli eventi e li stampo
                try { // CHIAMATA AL DAO
                    ev = rd.getAllEvents(); // devo passarla alla tabella
                    ObservableList<Event> evs = FXCollections.observableArrayList(ev);
                    rv.getTable().setItems(evs); // mostro gli eventi nella tabella dei risultati
                } catch (SQLException e) {
                    throw new RuntimeException("All events not found)");
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
        };
        rv.getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);

        // quando clicco su una riga della tabella prendo l'evento in quella riga
        EventHandler<javafx.scene.input.MouseEvent> eventPressHandler = mouseEvent -> {
            if (rv.getTable().getSelectionModel().getSelectedIndex() == -1){ // Se clicko una riga vuota o la tabella vuota non succede nulla, questo evento viene consumato
                mouseEvent.consume();
            } else {
                rv.getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                System.out.println(rv.getTable().getSelectionModel().getSelectedItem()); // prendo l'elemento cliccato dalla tabella

                // Prendo lo user connesso a questa sessione dell'applicativo
                ConnectedUser cu = ConnectedUser.getInstance();
                TicketPageView tpv = new TicketPageView();
                if (!cu.getUser().isCustomer() || rv.getTable().getSelectionModel().getSelectedItem().getSeatsRemaining() == 0) {
                    tpv.setForNotBuyable();
                }
                tpv.setComponents(cu.getUser().isCustomer(), rv.getTable().getSelectionModel().getSelectedItem().getType(), rv.getTable().getSelectionModel().getSelectedItem().getName(), rv.getTable().getSelectionModel().getSelectedItem().getCity(),
                        rv.getTable().getSelectionModel().getSelectedItem().getLocation(), rv.getTable().getSelectionModel().getSelectedItem().getProvince(), rv.getTable().getSelectionModel().getSelectedItem().getDate(),
                        rv.getTable().getSelectionModel().getSelectedItem().getArtists(), rv.getTable().getSelectionModel().getSelectedItem().getSeatsRemainedNumberForType(), rv.getTable().getSelectionModel().getSelectedItem().getPrices(), rv.getTable().getSelectionModel().getSelectedItem().getDescription(), rv.getTable().getSelectionModel().getSelectedItem().getPhoto());
                // creazione del TicketPageController
                new TicketPageController(mainStage, tpv, rv.getTable().getSelectionModel().getSelectedItem(), rv);
                mainStage.setScene(tpv);
            }
        };
        rv.getTable().setOnMouseClicked(eventPressHandler);

        // click su un genere specifico presente nel filtro generi della ricerca
        EventHandler<ActionEvent> genrePressHandler = actionEvent -> {
            CheckBox cmi = (CheckBox) actionEvent.getSource();
            if (cmi.isSelected()){ // se un checkbox viene selezionato => il genere fa parte dell'array list di stringhe, altrimenti no
                System.out.println(cmi.getText() + " is selected");
                gen.add(cmi.getText());
            } else {
                System.out.println(cmi.getText() + " is deselected");
                gen.remove(cmi.getText());
            }
        };
        rv.getGenv().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(genrePressHandler));

        // click su una provincia specifica presente nel filtro delle province
        EventHandler<ActionEvent> provincePressHandler = actionEvent -> {
            CheckBox cmi = (CheckBox) actionEvent.getSource();
            if (cmi.isSelected()){ // se un checkbox viene selezionato => la provincia fa parte dell'array list di stringhe, altrimenti no
                System.out.println(cmi.getText() + " is selected");
                pr.add(cmi.getText());
            } else {
                System.out.println(cmi.getText() + " is deselected");
                pr.remove(cmi.getText());
            }
        };
        rv.getPrv().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(provincePressHandler));

    }
}
