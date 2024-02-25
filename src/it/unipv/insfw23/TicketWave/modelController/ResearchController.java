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
    private final Stage mainStage;
    // le mie view
    private ResultResearchView rrv;
    private final ResearchView rv;

    // costruttore
    public ResearchController(Stage mainStage, ResearchView rv) {
        this.mainStage = mainStage;
        this.rv = rv;
        setResearchListener();
    }

    public void setResearchListener(){
        ManagerUpperBar mub = ManagerUpperBar.getIstance();
        ResearchNodesView rnv = ResearchNodesView.getIstance();
 /*       // Pressione del tasto della lente di ingrandimento sul Main Stage
        EventHandler<MouseEvent> researchPressHandlerMainStage = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("Vado alla tab della ricerca");
                ResearchView rv = new ResearchView();
                mainStage.setScene(rv);
            }
        };
        mub.getSearchButton().setOnMouseClicked(researchPressHandlerMainStage); */

        // click ricerca sulla ResearchNodesView
        EventHandler<MouseEvent> researchPressHandlerResearchView = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("Faccio la query di ricerca");
                ResultResearchView rrv = new ResultResearchView(); // vado nella scena della ResultResearchView
                mainStage.setScene(rrv);
            }
        };
        rnv.getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);
        // filtri del genere musicale
        EventHandler<ActionEvent> genreFilterHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ResearchNodesView rnv = ResearchNodesView.getIstance(); // prendo l'unica istanza del Singleton
                for (CheckMenuItem cmi : rnv.getGenv()) { // checko i checkMenuItem selezionati per la query filtrata per genere
                    if (cmi.isSelected()){
                        // logica per fare la query filtrata per genere
                        System.out.println("Filtro di genere musicale selezionato");
                    }
                }
            }
        };

        // filtri per provincia
        EventHandler<ActionEvent> provinceFilterHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ResearchNodesView rnv = ResearchNodesView.getIstance(); // prendo l'unica istanza del Singleton
                int i = 0;
                for (CheckMenuItem cmi : rnv.getPrv()) { // checko i checkMenuItem selezionati per la query filtrata per provincia/e
                    cmi = rnv.getPrv().get(i);
                    if (cmi.isSelected()){
                        // logica per fare la query filtrata per provincia
                        System.out.println("Filtro sulla provincia selezionato");
                    }
                    i++;
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
