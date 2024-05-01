package it.unipv.insfw23.TicketWave.modelController.controller.upperBar;

import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.controller.statistics.TypeStatsController;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.statistics.TypeStatsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class UpperBarController {
    private Stage mainStage;
    private UpperBar upperBar;

    public UpperBarController(Stage mainStage){
        this.mainStage = mainStage;
        upperBar = UpperBar.getIstance();
        initComponents();
    }

    private void initComponents(){

        EventHandler<ActionEvent> statsButtonHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                //mi viene passato il manager
                //creo una nuova classe di statistiche, a cui passo il manager
                //StatisticsHandlerArrayList statDominio = new StatisticsHandlerArrayList(sessionManager);
                //WrapType typeRes = statDominio.typeStats();
                //Al costruttore di type view, devo passare i risultati del metodo, e la classe di statistiche di dominio
                TypeStatsView typeView = new TypeStatsView();
                TypeStatsController typeController = new TypeStatsController(mainStage, typeView);
                mainStage.setScene(typeView);
            }
        };
        UpperBar.getIstance().getStatsButton().setOnAction(statsButtonHandler);

        // quando clicco sulla lente di ingrandimento presente nella upperBar => vado alla researchView
        EventHandler<ActionEvent> researchButtonHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                //mi viene passato il manager
                //creo una nuova classe di statistiche, a cui passo il manager
                //StatisticsHandlerArrayList statDominio = new StatisticsHandlerArrayList(sessionManager);
                //WrapType typeRes = statDominio.typeStats();
                //Al costruttore di type view, devo passare i risultati del metodo, e la classe di statistiche di dominio
                ResearchView rv = new ResearchView();
                ResearchController rc = new ResearchController(mainStage, rv);
                mainStage.setScene(rv);
            }
        };
        UpperBar.getIstance().getSearchButton().setOnAction(researchButtonHandler);

        EventHandler<ActionEvent> creationEventHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                /*
                if (manager.anotherEvents){
                    vai alla pagina di creazione dell'evento
                }
                else {
                */

            }
        };
        UpperBar.getIstance().getEventPlusButton().setOnAction(creationEventHandler);



    }
}
