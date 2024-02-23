package it.unipv.insfw23.TicketWave.modelView;
import it.unipv.insfw23.TicketWave.modelController.BuyTicketController;
import it.unipv.insfw23.TicketWave.modelController.PaymentDataController;
import it.unipv.insfw23.TicketWave.modelController.PaymentSelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProvaRun extends Application {

    @Override
    public void start(Stage primaryStage) {


        try {
            // Istanzia la TicketPageView
            PaymentSelectionView paymentSelectionView=new PaymentSelectionView();

            PaymentSelectionController paymentSelectionController=new PaymentSelectionController(primaryStage,paymentSelectionView);
           paymentSelectionController.initComponents(); // Inizializza i componenti del controller

            // Crea la scena principale utilizzando la TicketPageView
            Scene scene = paymentSelectionView;

            // Imposta la scena principale sullo stage e mostra lo stage
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


/*
PaymentDataM22View paymentDataPage = new PaymentDataM22View();
Scene scene = paymentDataPage;

        primaryStage.setTitle("Payment Data");
        primaryStage.setScene(scene);
        primaryStage.show();

         TicketPageView TicketPage = new TicketPageView();
        Scene scene = TicketPage;

        primaryStage.setTitle("TicketPage");
        primaryStage.setScene(scene);
        primaryStage.show();

 */