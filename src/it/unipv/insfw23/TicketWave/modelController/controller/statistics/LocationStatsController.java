package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelView.statistics.ArtistStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.LocationStatsView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class represents the controller that allows switching from the statistics view by {@link Province}
 * to the view with statistics by {@link Genre} and artist.
 *
 * @see StatisticsHandler
 * @see GenreStatsView
 * @see ArtistStatsView
 */
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
                System.out.println("BACK TO GENRES AND ARTISTS STATS");
                genreView.reSetBars();
                mainStage.setScene(genreView);
            }
        };
        locationView.getBackButton().setOnMouseClicked(backToGenreArtButtonHandler);
    }
}
