package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelView.statistics.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.NoMoreEventsPopup;
import it.unipv.insfw23.TicketWave.modelView.statistics.TypeStatsView;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TypeStatsController {
    private Stage mainStage;
    private TypeStatsView typeView;

    public TypeStatsController(Stage mainStage, TypeStatsView typeView){
        this.mainStage=mainStage;
        this.typeView=typeView;
        initComponents();
    }

    private void initComponents() {

        EventHandler<MouseEvent> statsBarButtonHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                System.out.println("PASSO AI GENERI E ARTISTI");

                //WrapGenreOrProv genreRes = statDominio.genreStats();
                //WrapArtist artistRes = statDominio.artistStats();
                //Al costruttore qui sotto passo il risultato dei generi, degli artisti e la statdomain

                //GenreStatsView genreView = new GenreStatsView();
                //GenreArtStatsController artStats = new GenreArtStatsController(mainStage, typeView, genreView);


                NoMoreEventsPopup.getIstance().setX(mainStage.getX() + mainStage.getWidth() - NoMoreEventsPopup.getIstance().getWidth() - 360);
                NoMoreEventsPopup.getIstance().setY(mainStage.getY()+95);
                NoMoreEventsPopup.getIstance().show(mainStage);


                //mainStage.setScene(genreView);
            }
        };

        for (XYChart.Data<Number, String> data : typeView.getTypeSerie().getData()) {
            data.getNode().setOnMouseClicked(statsBarButtonHandler);
        }

    }
}
