package it.unipv.insfw23.TicketWave.modelController.Controller.UpperBar;

import it.unipv.insfw23.TicketWave.modelController.Controller.Statistics.TypeStatsController;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
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

    }
}
