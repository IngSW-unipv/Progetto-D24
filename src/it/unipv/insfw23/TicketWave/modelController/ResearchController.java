package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchNodesView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResultResearchView;
import javafx.event.EventHandler;
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
                ResearchView rv = new ResearchView();
                mainStage.setScene(rv);
            }
        };
        rnv.getSearchButton().setOnAction(actionEvent -> {
            // logica di ricerca con query SQL
        });


    }

}
