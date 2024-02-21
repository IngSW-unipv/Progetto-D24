package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.MainStageView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StatisticsController {
    private Stage mainStage;
    private TypeStatsView typeView;
    private GenreStatsView genreView;

    public StatisticsController(Stage mainStage, TypeStatsView typeView, GenreStatsView genreView){
        this.mainStage=mainStage;
        this.typeView=typeView;
        this.genreView=genreView;
        initComponents();
    }

    public void initComponents(){

        EventHandler<MouseEvent> statsBarButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("PASSO AI GENERI E ARTISTI");
                mainStage.setScene(genreView);


            }
        };
        for(XYChart.Data<Number, String> data: typeView.getSerie().getData()){
            data.getNode().setOnMouseClicked(statsBarButtonHandler);
        }



    }
}
