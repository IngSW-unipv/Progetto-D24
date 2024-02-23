package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.LocationStatsView;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatisticControllerGenreArt {
    private Stage mainStage;
    private GenreStatsView genreView;

    public StatisticControllerGenreArt(Stage mainStage, GenreStatsView genreView){
        this.mainStage=mainStage;
        this.genreView=genreView;
        initComponents();
    }

    public void initComponents(){
        EventHandler<MouseEvent> artistBarButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("PASSO ALLE LOCALITA'");
                LocationStatsView locationView = new LocationStatsView("Lazza");
                StatisticControllerLocation locStatController = new StatisticControllerLocation(mainStage, genreView, locationView);
                mainStage.setScene(locationView);
            }
        };

        for(XYChart.Data<String, Number> data: genreView.getArtistPane().getArtistSerie().getData()){
            data.getNode().setOnMouseClicked(artistBarButtonHandler);
        }
    }

}
