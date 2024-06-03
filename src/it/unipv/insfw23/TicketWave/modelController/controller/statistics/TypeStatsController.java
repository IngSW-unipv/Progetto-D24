package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapArtist;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapGenre;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.statistics.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.TypeStatsView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

public class TypeStatsController {
    private Stage mainStage;
    private TypeStatsView typeView;
    private Manager loggedManager;

    public TypeStatsController(Stage mainStage, TypeStatsView typeView){
        this.mainStage=mainStage;
        this.typeView=typeView;
        this.loggedManager = (Manager) ConnectedUser.getInstance().getUser();
        initComponents();
    }

    private void initComponents() {

        EventHandler<MouseEvent> statsBarButtonHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                Node clickedNode = (Node) actionEvent.getSource();
                System.out.println("PASSO AI GENERI E ARTISTI");
                StatisticsHandler statDominio = new StatisticsHandler();

                Pair<Number, String> pairDataClicked = (Pair)clickedNode.getUserData();

                System.out.println(pairDataClicked.getValue().toString());
                WrapGenre genreRes = statDominio.genreStats(Type.valueOf(pairDataClicked.getValue().toString()), (Manager) ConnectedUser.getInstance().getUser());

                WrapArtist artistRes = statDominio.artistStats(Type.valueOf(pairDataClicked.getValue().toString()), (Manager) ConnectedUser.getInstance().getUser());
                //Al costruttore qui sotto passo il risultato dei generi, degli artisti e la statdomain

                GenreStatsView genreView = new GenreStatsView(genreRes, artistRes);
                GenreArtStatsController artStats = new GenreArtStatsController(mainStage, typeView, genreView, Type.valueOf(pairDataClicked.getValue().toString()));
                mainStage.setScene(genreView);
            }
        };

        for (XYChart.Data<Number, String> data : typeView.getTypeSerie().getData()) {
            data.getNode().setUserData(new Pair(data.getXValue(), data.getYValue()));
            data.getNode().setOnMouseClicked(statsBarButtonHandler);
        }

    }
}
