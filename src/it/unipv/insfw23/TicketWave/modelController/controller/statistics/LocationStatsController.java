package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelView.statistics.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.LocationStatsView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LocationStatsController {

    private Stage mainStage;
    private GenreStatsView genreView;
    private LocationStatsView locationView;

    public LocationStatsController(Stage mainStage, GenreStatsView genreView, LocationStatsView locationView){
        this.mainStage=mainStage;
        this.genreView=genreView;
        this.locationView=locationView;
        initComponents();
    }

    private void initComponents(){
        EventHandler<MouseEvent> backToGenreArtButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("TORNO AI GENERI E ARTISTI");
                genreView.reSetBars();
                mainStage.setScene(genreView);
            }
        };
        locationView.getBackButton().setOnMouseClicked(backToGenreArtButtonHandler);
    }
}
