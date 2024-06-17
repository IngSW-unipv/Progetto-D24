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
    private final ResearchView researchView;
    private final ArrayList<String> provinceFilters, genreFilters; // sono gli arrayList che contengono i filtri selezionati, per cui le province selezionate ed i generi selezionati

    // CONSTRUCTOR:

    /**
     * The constructor of ResearchController takes 2 parameters as input and calls the serResearchListener() method to manage all the events that occur on the ResearchView
     * @param mainStage
     * @param researchView
     */
    public ResearchController(Stage mainStage, ResearchView researchView) {
        this.mainStage = mainStage;
        this.researchView = researchView;
        provinceFilters = new ArrayList<>();
        genreFilters = new ArrayList<>();
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
            ResearchDAO researchDAO = new ResearchDAO();
            ArrayList<Event> eventsResearched = new ArrayList<>();
            if (Objects.equals(researchView.getSearchBar().getText(), "") && provinceFilters.isEmpty() && genreFilters.isEmpty()){ // se non scrivo nulla nella barra di ricerca => ricerco tutti gli eventi e li stampo
                try { // CHIAMATA AL DAO
                    eventsResearched = researchDAO.getAllEvents(); // devo passarla alla tabella
                    ObservableList<Event> evs = FXCollections.observableArrayList(eventsResearched);
                    researchView.getResultTable().setItems(evs); // mostro gli eventi nella tabella dei risultati
                } catch (SQLException e) {
                    throw new RuntimeException("All events not found)");
                }
            } else { // se scrivo qualcosa sulla barra di ricerca faccio una ricerca filtrata
                try {
                    eventsResearched = researchDAO.getFilteredEvents(researchView.getSearchBar().getText(), provinceFilters, genreFilters);
                    ObservableList<Event> listOfResearchedEvents = FXCollections.observableArrayList(eventsResearched); // lista contenente gli eventi che ho ricercato, la metto nella Table dei risultati della researchView, in modo da vedere i risultati
                    researchView.getResultTable().setItems(listOfResearchedEvents); // mostro gli eventi filtrati nella tabella dei risultati
                } catch (SQLException e) {
                    throw new RuntimeException("Filtered events not found");
                }
            }
            researchView.getResultTable().setVisible(true);
            researchView.getResultTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        };
        researchView.getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);

        // quando clicco su una riga della tabella prendo l'evento in quella riga
        EventHandler<javafx.scene.input.MouseEvent> eventPressHandler = mouseEvent -> {
            if (researchView.getResultTable().getSelectionModel().getSelectedIndex() == -1){ // Se clicko una riga vuota o la tabella vuota non succede nulla, questo evento viene consumato
                mouseEvent.consume();
            } else {
                researchView.getResultTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // prendo l'elemento cliccato dalla tabella

                // Prendo lo user connesso a questa sessione dell'applicativo
                ConnectedUser cu = ConnectedUser.getInstance();
                TicketPageView ticketPageView = new TicketPageView();
                if (!cu.getUser().isCustomer() || researchView.getResultTable().getSelectionModel().getSelectedItem().getSeatsRemaining() == 0) {
                    ticketPageView.setForNotBuyable();
                }
                ticketPageView.setComponents(cu.getUser().isCustomer(), researchView.getResultTable().getSelectionModel().getSelectedItem().getType(), researchView.getResultTable().getSelectionModel().getSelectedItem().getName(), researchView.getResultTable().getSelectionModel().getSelectedItem().getCity(),
                        researchView.getResultTable().getSelectionModel().getSelectedItem().getLocation(), researchView.getResultTable().getSelectionModel().getSelectedItem().getProvince(), researchView.getResultTable().getSelectionModel().getSelectedItem().getDate(),
                        researchView.getResultTable().getSelectionModel().getSelectedItem().getArtists(), researchView.getResultTable().getSelectionModel().getSelectedItem().getSeatsRemainedNumberForType(), researchView.getResultTable().getSelectionModel().getSelectedItem().getPrices(), researchView.getResultTable().getSelectionModel().getSelectedItem().getDescription(), researchView.getResultTable().getSelectionModel().getSelectedItem().getPhoto());
                // creazione del TicketPageController
                new TicketPageController(mainStage, ticketPageView, researchView.getResultTable().getSelectionModel().getSelectedItem(), researchView);
                mainStage.setScene(ticketPageView);
            }
        };
        researchView.getResultTable().setOnMouseClicked(eventPressHandler);

        // click su un genere specifico presente nel filtro generi della ricerca
        EventHandler<ActionEvent> genrePressHandler = actionEvent -> {
            CheckBox genreChechBox = (CheckBox) actionEvent.getSource();
            if (genreChechBox.isSelected()){ // se un checkbox viene selezionato => il genere fa parte dell'array list di stringhe, altrimenti no. L'arrayList di stringhe mi serve per fare la query filtrata nel ResearchDAO
                genreFilters.add(genreChechBox.getText());
            } else {
                genreFilters.remove(genreChechBox.getText());
            }
        };
        researchView.getGenreCheckBoxArray().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(genrePressHandler));

        // click su una provincia specifica presente nel filtro delle province
        EventHandler<ActionEvent> provincePressHandler = actionEvent -> {
            CheckBox provinceCheckBox = (CheckBox) actionEvent.getSource();
            if (provinceCheckBox.isSelected()){ // se un checkbox viene selezionato => la provincia fa parte dell'array list di stringhe, altrimenti no
                provinceFilters.add(provinceCheckBox.getText());
            } else {
                provinceFilters.remove(provinceCheckBox.getText());
            }
        };
        researchView.getProvinceCheckBoxArray().forEach(CheckMenuItem -> CheckMenuItem.setOnAction(provincePressHandler));
    }
}
