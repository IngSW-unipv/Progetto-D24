package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResultResearchView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResearchController {
    private Stage mainStage;
    // le mie view
    private ResearchView rv;
    private ResultResearchView rrv;

    // costruttore
    public ResearchController(Stage mainStage, ResearchView rv, ResultResearchView rrv) {
        this.mainStage = mainStage;
        this.rv = rv;
        this.rrv = rrv;
        setResearchListener();
    }

    public void setResearchListener(){
        // Pressione del tasto della lente di ingrandimento sul Main Stage
        EventHandler<MouseEvent> researchPressHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("Vado alla tab della ricerca");
                ResearchView rv = new ResearchView();
                mainStage.setScene(rv);
            }
        };
        rv.getSearchButton().setOnMouseClicked(researchPressHandler);
    }

}
