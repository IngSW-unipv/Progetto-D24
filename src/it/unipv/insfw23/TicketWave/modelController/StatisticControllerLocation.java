package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.LocationStatsView;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatisticControllerLocation {
        private Stage mainStage;
        private GenreStatsView genreView;
        private LocationStatsView locationView;

        public StatisticControllerLocation(Stage mainStage, GenreStatsView genreView, LocationStatsView locationView){
            this.mainStage=mainStage;
            this.genreView=genreView;
            this.locationView=locationView;
            initComponents();
        }

        public void initComponents(){
            EventHandler<MouseEvent> backToGenreArtButtonHandler = new EventHandler<>(){
                @Override
                public void handle(MouseEvent actionEvent){
                    System.out.println("TORNO AI GENERI E ARTISTI");
                    //mainStage.setScene(genreView);
                }
            };

            locationView.getBackButton().setOnMouseClicked(backToGenreArtButtonHandler);
        }

    }

