package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchNodesView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResultResearchView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResearchController {
    private Stage mainStage;
    // le mie view
    private ManagerUpperBar mub;
    private ResearchNodesView rnv;
    private ResultResearchView rrv;

    // costruttore
    public ResearchController(Stage mainStage, ResearchNodesView rnv, ResultResearchView rrv, ManagerUpperBar mub) {
        this.mainStage = mainStage;
        this.rnv = rnv;
        this.rrv = rrv;
        this.mub = mub;
        setResearchListener();
    }

    public void setResearchListener(){
        // Pressione del tasto della lente di ingrandimento sul Main Stage
        EventHandler<MouseEvent> researchPressHandlerMainStage = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("Vado alla tab della ricerca");
                ResearchView rv = new ResearchView();
                mainStage.setScene(rv);
            }
        };
        mub.getSearchButton().setOnMouseClicked(researchPressHandlerMainStage);

        // click ricerca sulla ResearchNodesView
        EventHandler<MouseEvent> researchPressHandlerResearchView = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("Faccio la query di ricerca");
                ResultResearchView rrv = new ResultResearchView(); // vado nella scena della ResultResearchView
                ResearchNodesView rnv = ResearchNodesView.getIstance(); // prendo l'unica istanza del Singleton
                // rnv.getSearchBar()

                mainStage.setScene(rrv);
            }
        };
        rnv.getSearchButton().setOnAction(actionEvent -> {
            // logica di ricerca con query SQL
        });
        // filtri del genere musicale
        EventHandler<ActionEvent> genreFilterHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ResearchNodesView rnv = ResearchNodesView.getIstance(); // prendo l'unica istanza del Singleton
                for (CheckMenuItem cmi : rnv.getGenv()) { // checko i checkMenuItem selezionati per la query filtrata per genere
                    if (cmi.isSelected()){
                        // logica per fare la query filtrata per genere
                    }
                }
            }
        };

        // filtri per provincia
        EventHandler<ActionEvent> provinceFilterHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ResearchNodesView rnv = ResearchNodesView.getIstance(); // prendo l'unica istanza del Singleton
                for (CheckMenuItem cmi : rnv.getPrv()) { // checko i checkMenuItem selezionati per la query filtrata per provincia/e
                    if (cmi.isSelected()){
                        // logica per fare la query filtrata per provincia
                    }
                }
            }
        };

        EventHandler<ActionEvent> ResultResearchHandler = new EventHandler<ActionEvent>() {
            ObservableList<String> azzeratore = FXCollections.observableArrayList(" ");
            @Override
            public void handle(ActionEvent actionEvent) {
                ResultResearchView rrv = new ResultResearchView();
                for (String s : rrv.getResult()){
                    if (rrv.getResult() != null){ // posso ometterlo se lo fa in automatico
                        rrv.setResult(azzeratore); // se leggo che sulla List view c'è qualcosa, la ripulisco e metto 0
                    } else {
                        // logica che mette i risultati nella ListView della ResultResearchView
                    }
                }

            }
        };

    }

}
