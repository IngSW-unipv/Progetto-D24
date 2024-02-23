package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelController.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;


public class MainStageView extends Application {

    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        MainController mainController= new MainController(primaryStage);

        this.primaryStage = primaryStage;
        primaryStage.show();

        primaryStage.setTitle("TicketWave");
        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);

        BorderPane contenuto = new BorderPane();
        contenuto.setStyle("-fx-background-color: rgb(27,84,161)");

        BorderPane layout = new BorderPane();
        layout.setTop(ManagerUpperBar.getIstance());

        layout.setCenter(contenuto);
        layout.setBottom(LowerBar.getInstance());
        Scene scene = new Scene(layout, 1080, 600);

        //scene.setFill(Color.web("#FFC943"));
        primaryStage.setScene(scene);

        primaryStage.show();

    }

}
