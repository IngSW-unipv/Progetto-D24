package it.unipv.insfw23.TicketWave.modelView.research;

//import it.unipv.insfw23.TicketWave.modelController.controller.upperBar.UpperBarController;
import it.unipv.insfw23.TicketWave.modelController.controller.research.ResearchController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StarterNicholas extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

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

        //UpperBarController ctrl = new UpperBarController(primaryStage);
        ResearchView scene = new ResearchView();
        ResearchController controller = new ResearchController(primaryStage, scene);
        primaryStage.setScene(scene);


        primaryStage.show();

    }
}
