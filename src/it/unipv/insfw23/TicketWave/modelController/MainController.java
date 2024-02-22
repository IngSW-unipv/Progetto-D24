package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
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
        EventHandler<ActionEvent> statsButtonHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                //System.out.println("STATISTICHE");
                TypeStatsView typeView = new TypeStatsView();
                StatisticControllerType stat = new StatisticControllerType(mainStage, typeView);
                mainStage.setScene(typeView);
            }
        };

        ManagerUpperBar.getIstance().getStatsButton().setOnAction(statsButtonHandler);
    }
}
