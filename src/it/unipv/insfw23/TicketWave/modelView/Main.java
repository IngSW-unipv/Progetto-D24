package it.unipv.insfw23.TicketWave.modelView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

    Button button;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Titolo");
        button=new Button();
        button.setText("Clicca qui");

        StackPane layout =new StackPane();
        layout.getChildren().add(button);

        Scene scene =new Scene(layout,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

