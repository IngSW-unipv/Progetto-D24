package it.unipv.insfw23.TicketWave.modelController.controller.research;

//import it.unipv.insfw23.TicketWave.modelView.research.ResearchNodesView;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.EventHandler;

public class ResearchController {
    private final Stage mainStage;
    // le mie view
    private final ResearchView rv;
    private User user= ConnectedUser.getInstance().getUser();

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
                //esempio di creazione customer, se no non funziona, il vero utente lo devo prendere dal sign up o dal sign in
                Genre[] favoriteGenre= {Genre.EDM,Genre.HOUSE,Genre.POP};
                Customer cs=new Customer("Gesvaldo","Pieri","2020-10-10","Ges.Pieri@gmail.com","123",Province.BARI,favoriteGenre, 100);
                //
                TicketPageView tpv = new TicketPageView();
                tpv.setComponents(user.isCustomer(), rv.getTable().getSelectionModel().getSelectedItem().getType(), rv.getTable().getSelectionModel().getSelectedItem().getName(), rv.getTable().getSelectionModel().getSelectedItem().getCity(),
                                  rv.getTable().getSelectionModel().getSelectedItem().getLocation(), rv.getTable().getSelectionModel().getSelectedItem().getProvince(), rv.getTable().getSelectionModel().getSelectedItem().getDate(),
                                  rv.getTable().getSelectionModel().getSelectedItem().getArtists(), rv.getTable().getSelectionModel().getSelectedItem().getSeatsRemainedNumberForType(), rv.getTable().getSelectionModel().getSelectedItem().getPrice());
                TicketPageController tpc =  new TicketPageController(mainStage, tpv, rv.getTable().getSelectionModel().getSelectedItem()); // creazione del TicketPageController
                mainStage.setScene(tpv);
            }
        };
        rv.getTable().setOnMouseClicked(eventPressHandler);

        // click su un genere specifico presente nel filtro generi della ricerca
        EventHandler<ActionEvent> genrePressHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CheckBox cmi = (CheckBox) actionEvent.getSource();
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
                //CheckMenuItem cmi = (CheckMenuItem) actionEvent.getSource();
                CheckBox cmi = (CheckBox) actionEvent.getSource();
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
