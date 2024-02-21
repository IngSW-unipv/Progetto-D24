package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.MainStageView;
import it.unipv.insfw23.TicketWave.modelView.ManagerUpperBar;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import javafx.event.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;
import javafx.scene.*;

public class MainController {

    private Stage mainStage;
    private TypeStatsView typeView;

    private ManagerUpperBar upperBar;

    public MainController(Stage mainStage, TypeStatsView typeView){
        this.mainStage = mainStage;
        this.typeView = typeView;
        upperBar.getIstance();
        initComponents();
    }

    private void initComponents(){
        EventHandler<ActionEvent> statsButtonHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                System.out.println("STATISTICHE");
                StatisticsController stat = new StatisticsController(typeView);
                mainStage.setScene(typeView);
            }
        };

        upperBar.getIstance().getStatsButton().setOnAction(statsButtonHandler);
    }
}
