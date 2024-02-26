package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.GenreStatsView;
import it.unipv.insfw23.TicketWave.modelView.LocationStatsView;
import it.unipv.insfw23.TicketWave.modelView.TypeStatsView;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatisticControllerGenreArt {
    private Stage mainStage;
    private TypeStatsView typeView;
    private GenreStatsView genreView;

    public StatisticControllerGenreArt(Stage mainStage, TypeStatsView typeView, GenreStatsView genreView){
        this.mainStage=mainStage;
        this.typeView = typeView;
        this.genreView=genreView;
        initComponents();
    }

    public void initComponents(){
        EventHandler<MouseEvent> artistBarButtonHandler = new EventHandler<>(){
            @Override
            public void handle(MouseEvent actionEvent){
                System.out.println("PASSO ALLE LOCALITA'");
                //mi arriva l'arraylist di artista con l'artista per cui fare le statistiche per località
                //WrapGenreOrPorv localitaRes = statDominio.provinceStats(ArrayListArtista);
                //al costruttore qui sotto passo il risultato delle località
                LocationStatsView locationView = new LocationStatsView("Lazza");
                StatisticControllerLocation locStatController = new StatisticControllerLocation(mainStage, genreView, locationView);
                mainStage.setScene(locationView);
            }
        };

        for(XYChart.Data<String, Number> data: genreView.getArtistPane().getArtistSerie().getData()){
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
