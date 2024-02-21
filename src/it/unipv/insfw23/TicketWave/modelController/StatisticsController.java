package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.MainStageView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class StatisticsController {
    private TypeStatsView typeStats;

    public StatisticsController(TypeStatsView typeStats){
        this.typeStats=typeStats;
        initComponents();
    }

    public void initComponents(){

        EventHandler<MouseEvent> statsBarButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("PASSO AI GENERI E ARTISTI");

            }
        };


        for(XYChart.Data<Number, String> data: typeStats.getSerie().getData()){
            data.getNode().setOnMouseClicked(statsBarButtonHandler);
        }
    }
}
