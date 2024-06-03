package it.unipv.insfw23.TicketWave.modelController.controller.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.event.Type;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.StatisticsHandler;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapGenre;
import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapProv;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.statistics.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.LocationStatsView;
import it.unipv.insfw23.TicketWave.modelView.statistics.TypeStatsView;
import it.unipv.insfw23.TicketWave.modelController.controller.statistics.LocationStatsController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

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
                System.out.println("PASSO ALLE LOCALITA'");
                StatisticsHandler statDominio = new StatisticsHandler();

                //mi arriva l'artista per cui fare le statistiche per località
                Pair<String, Number> pairDataClicked = (Pair)clickedNode.getUserData();

                System.out.println(pairDataClicked.getKey().toString());

                WrapProv provRes = statDominio.provinceStats(typeCode, pairDataClicked.getKey().toString(), (Manager) ConnectedUser.getInstance().getUser());
                //al costruttore qui sotto passo il risultato delle località
                System.out.println(provRes.getProvResult().get(0));
                LocationStatsView locationView = new LocationStatsView(provRes);
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
                System.out.println("TORNO AI TIPI");
                typeView.reSetBars();
                mainStage.setScene(typeView);
            }
        };
        genreView.getBackButton().setOnMouseClicked(backToTypeButtonHandler);

    }
}
