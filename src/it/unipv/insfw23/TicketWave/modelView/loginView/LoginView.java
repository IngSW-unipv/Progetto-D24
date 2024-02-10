package it.unipv.insfw23.TicketWave.modelView.loginView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class LoginView extends Application {
    Button button;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TicketWave");
        button= new Button();
        button.setText("click");

        StackPane layout= new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 200);
    }
}
