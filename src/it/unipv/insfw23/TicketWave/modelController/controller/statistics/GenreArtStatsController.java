package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapProv;
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
 * This class represents the controller that allows switching from the statistics view by {@link Genre} and artist
 * to the view with statistics by {@link Province}.
 *
 * @see StatisticsHandler
 * @see LocationStatsView
 */
public class GenreArtStatsController {

    private Stage mainStage;
    private TypeStatsView typeView;
    private GenreStatsView genreView;
    private Type typeCode;

    public GenreArtStatsController(Stage mainStage, TypeStatsView typeView, GenreStatsView genreView, Type typeCode){
        this.mainStage=mainStage;
        this.typeView = typeView;
        this.genreView=genreView;
        this.typeCode = typeCode;
        initComponents();
    }

    private void initComponents() {
        EventHandler<MouseEvent> artistBarButtonHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                Node clickedNode = (Node) actionEvent.getSource();
                System.out.println("GOING TO PLACES");
                StatisticsHandler statDominio = new StatisticsHandler();

                //mi arriva l'artista per cui fare le statistiche per località
                Pair<String, Number> pairDataClicked = (Pair)clickedNode.getUserData();

                System.out.println("Clicked on " + pairDataClicked.getKey().toString());

                WrapProv provRes = statDominio.provinceStats(typeCode, pairDataClicked.getKey().toString(), (Manager) ConnectedUser.getInstance().getUser());
                //al costruttore qui sotto passo il risultato delle località
                //System.out.println(provRes.getProvResult().get(0));
                LocationStatsView locationView = new LocationStatsView(provRes, pairDataClicked.getKey().toString());
                System.out.println("PostLocView");
                LocationStatsController locStatController = new LocationStatsController(mainStage, genreView, locationView);
                mainStage.setScene(locationView);
            }
        };

        for (XYChart.Data<String, Number> data : genreView.getArtistPane().getArtistSerie().getData()) {
            data.getNode().setUserData(new Pair(data.getXValue(), data.getYValue()));
            data.getNode().setOnMouseClicked(artistBarButtonHandler);
        }

        EventHandler<MouseEvent> backToTypeButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("BACK TO TYPES STATS");
                typeView.reSetBars();
                mainStage.setScene(typeView);
            }
        };
        genreView.getBackButton().setOnMouseClicked(backToTypeButtonHandler);

    }
}
