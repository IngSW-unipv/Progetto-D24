package it.unipv.insfw23.TicketWave.modelView.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.statistics.WrapType;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.*;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;

public class TypeStatsView extends Scene{

    private BarChart<Number, String> barChart;
    private XYChart.Series<Number, String> typeSerie;
    private BorderPane layout;
    private Pane content;
    private WrapType typeRes;


    // modifico il costruttore per ricevere i risultati (WrapType) e la classe
    // di dominio statDominio (StatisticHandlerArrayList)
    public TypeStatsView(WrapType typeRes){
        super(new BorderPane(), 1080, 600);
        this.typeRes = typeRes;
        init();
    }

    private void init(){

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #91BAD6;");


        final NumberAxis xAxis = new NumberAxis(0, 100, 10);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Categoria evento");
        xAxis.setLabel("Percentuale vendita");

        //BarChart final??
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setStyle("-fx-bar-fill: #EE6D21FC;");
        barChart.setTitle("                 Statistiche sulle categorie eventi");
        barChart.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 15px; -fx-font-weight: bold;");
        barChart.setLegendVisible(false);



        XYChart.Series<Number, String> series = new XYChart.Series<>();
        this.typeSerie=series;

        // Aggiunta dei dati alla serie
        for (int i=0; i<typeRes.getTypeArray().length; i++){
            series.getData().add(new XYChart.Data<>(typeRes.getTypeResult()[i], typeRes.getTypeArray()[i].toString()));
            System.out.println(typeRes.getTypeArray()[i].toString()+typeRes.getTypeResult()[i]);
        }

        // Aggiunta della serie al grafico
        barChart.getData().add(series);

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #F77F00;");
        }

        //Pannello 1 per grafico sui tipi
        Pane graphPane = new Pane(barChart);
        graphPane.setBackground((new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))));
        //graphPane.setPadding(new Insets(20));
        //graphPane.setPrefSize(10, 10);
        graphPane.setMaxWidth(600);
        graphPane.setMaxHeight(400);

        this.content = graphPane;

        layout.setCenter(graphPane);
        layout.setBottom(LowerBar.getInstance());
        UpperBar.getIstance().setForManager();
        layout.setTop(UpperBar.getIstance());
        this.layout = layout;
        setRoot(layout);
    }

    public BarChart<Number, String> getBarChart() {
        return barChart;
    }

    public XYChart.Series<Number, String> getTypeSerie() {
        return typeSerie;
    }

    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        UpperBar.getIstance().setForManager();
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(content);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }

}
