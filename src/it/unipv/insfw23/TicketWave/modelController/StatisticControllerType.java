package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatisticControllerType {
    private Stage mainStage;
    private TypeStatsView typeView;

    public StatisticControllerType(Stage mainStage, TypeStatsView typeView){
        this.mainStage=mainStage;
        this.typeView=typeView;
        initComponents();
    }

    public void initComponents(){

        EventHandler<MouseEvent> statsBarButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("PASSO AI GENERI E ARTISTI");
                GenreStatsView genreView = new GenreStatsView();
                StatisticControllerGenreArt artStat = new StatisticControllerGenreArt(mainStage, typeView, genreView);
                mainStage.setScene(genreView);
            }
        };

        for(XYChart.Data<Number, String> data: typeView.getSerie().getData()){
            data.getNode().setOnMouseClicked(statsBarButtonHandler);
        }

/*
        EventHandler<ActionEvent> backToTypeHandler = new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent){
                mainStage.setScene(typeView);
            }
        };
        genreView.getBackButton().setOnAction(backToTypeHandler);
*/

    }
}
