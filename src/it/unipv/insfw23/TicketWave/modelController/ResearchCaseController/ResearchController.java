package it.unipv.insfw23.TicketWave.modelController.ResearchCaseController;

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
    private final ResearchView rv;

    // costruttore
    public ResearchController(Stage mainStage, ResearchView rv) {
        this.mainStage = mainStage;
        this.rv = rv;
        setResearchListener();
    }

    public void setResearchListener(){
//        ResearchNodesView rnv = ResearchNodesView.getIstance();

        // click ricerca sulla ResearchNodesView
        EventHandler<MouseEvent> researchPressHandlerResearchView = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("Faccio la query di ricerca");
                ResultResearchView rrv = new ResultResearchView(); // vado nella scena della ResultResearchView
                ResultResearchController rrc = new ResultResearchController(mainStage, rrv);
                mainStage.setScene(rrv);
            }
        };
//        rnv.getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);
        rv.getresearchnodeview().getSearchButton().setOnMouseClicked(researchPressHandlerResearchView);

    }

}
