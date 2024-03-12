package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResultResearchController;
import it.unipv.insfw23.TicketWave.modelView.CustomerUpperBar;
import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResultResearchView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import javafx.event.*;
import javafx.stage.*;

public class MainController {

    private Stage mainStage;

    // private ManagerUpperBar upperBar;

    public MainController(Stage mainStage){
        this.mainStage = mainStage;
        ManagerUpperBar.getIstance();
        initComponents();
    }

    private void initComponents(){
//        ResultResearchView rrv = new ResultResearchView();
//        ResultResearchController rrc = new ResultResearchController(mainStage, rrv); // passo il primaryStage al ResultResearchController
        EventHandler<ActionEvent> statsButtonHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                //mi viene passato il manager
                //creo una nuova classe di statistiche, a cui passo il manager
                //StatisticsHandlerArrayList statDominio = new StatisticsHandlerArrayList(sessionManager);
                //WrapType typeRes = statDominio.typeStats();
                //Al costruttore di type view, devo passare i risultati del metodo, e la classe di statistiche di dominio
                TypeStatsView typeView = new TypeStatsView();
                StatisticControllerType stat = new StatisticControllerType(mainStage, typeView);
                mainStage.setScene(typeView);
            }
        };
        EventHandler<ActionEvent> searchButtonHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                System.out.println("RICERCA");
                ResearchView rv = new ResearchView();
                ResearchController research = new ResearchController(mainStage, rv);
                mainStage.setScene(rv);
            }
        };
        ManagerUpperBar.getIstance().getStatsButton().setOnAction(statsButtonHandler);
        ManagerUpperBar.getIstance().getSearchButton().setOnAction(searchButtonHandler);
        CustomerUpperBar.getIstance().getSearchButton().setOnAction(searchButtonHandler);
    }
}
