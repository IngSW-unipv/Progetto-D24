package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapArtist;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapGenre;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.statistics.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;


/**
 * This class represents the controller that allows switching from the statistics view by {@link Type}
 * to the view with statistics by {@link Genre} and artist.
 *
 * @see StatisticsHandler
 * @see GenreStatsView
 * @see ArtistStatsView
 */
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
                Node clickedNode = (Node) actionEvent.getSource();
                System.out.println("GOING TO GENRES AND ARTISTS STATS");
                StatisticsHandler statDominio = new StatisticsHandler();

                Pair<Number, String> pairDataClicked = (Pair)clickedNode.getUserData();

                System.out.println("Clicked on " + pairDataClicked.getValue().toString());
                WrapGenre genreRes = statDominio.genreStats(Type.valueOf(pairDataClicked.getValue().toString()), (Manager) ConnectedUser.getInstance().getUser());

                WrapArtist artistRes = statDominio.artistStats(Type.valueOf(pairDataClicked.getValue().toString()), (Manager) ConnectedUser.getInstance().getUser());
                //Al costruttore qui sotto passo il risultato dei generi, degli artisti e la statdomain

                GenreStatsView genreView = new GenreStatsView(genreRes, artistRes);
                GenreArtStatsController artStats = new GenreArtStatsController(mainStage, typeView, genreView, Type.valueOf(pairDataClicked.getValue().toString()));
                mainStage.setScene(genreView);
            }
        };

        for (XYChart.Data<Number, String> data : typeView.getTypeSerie().getData()) {
            data.getNode().setUserData(new Pair<Number, String>(data.getXValue(), data.getYValue()));
            data.getNode().setOnMouseClicked(statsBarButtonHandler);
        }

    }
}
