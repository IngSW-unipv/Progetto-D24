package it.unipv.insfw23.TicketWave.modelView.statistics;

import it.unipv.insfw23.TicketWave.modelController.Controller.Research.ResearchController;
import it.unipv.insfw23.TicketWave.modelController.Controller.Statistics.TypeStatsController;
import it.unipv.insfw23.TicketWave.modelController.Controller.UpperBar.UpperBarController;
import it.unipv.insfw23.TicketWave.modelView.research.ResearchView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class StarterDavide extends Application {

    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        primaryStage.show();

        primaryStage.setTitle("TicketWave");
        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);

        /*
        BorderPane contenuto = new BorderPane();
        contenuto.setStyle("-fx-background-color: #def1fa");

        BorderPane layout = new BorderPane();
        layout.setTop(UpperBar.getIstance());

        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());
        Scene scene = new Scene(layout, 1080, 600);

        //scene.setFill(Color.web("#FFC943"));

         */
        UpperBarController ctrl = new UpperBarController(primaryStage);
        TypeStatsView scene = new TypeStatsView();
        TypeStatsController controller = new TypeStatsController(primaryStage, scene);
        primaryStage.setScene(scene);


        primaryStage.show();

    }
}
