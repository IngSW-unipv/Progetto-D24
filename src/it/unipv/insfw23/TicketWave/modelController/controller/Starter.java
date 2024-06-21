package it.unipv.insfw23.TicketWave.modelController.controller;

import it.unipv.insfw23.TicketWave.modelController.controller.access.LoginController;
import it.unipv.insfw23.TicketWave.modelView.access.LoginView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Starter extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/logo.png");	

		LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(primaryStage, loginView);
        
        
        primaryStage.setTitle("TicketWave");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1120);
        primaryStage.setHeight(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setScene(loginView);
        primaryStage.show();
	}
	
    public static void main(String[] args) {
        launch(args);
    }

}
