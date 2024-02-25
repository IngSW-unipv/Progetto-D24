package it.unipv.insfw23.TicketWave.modelView;
import it.unipv.insfw23.TicketWave.modelController.BuyTicketController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProvaRun extends Application {

    @Override
    public void start(Stage primaryStage) {


        try {
            // Istanzia la TicketPageView

            TicketPageView ticketPageView=new TicketPageView();
            BuyTicketController buyTicketController=new BuyTicketController(primaryStage,ticketPageView);

            buyTicketController.initComponents();

           //buyTicketController.initComponents(); // Inizializza i componenti del controller

            // Crea la scena principale utilizzando la TicketPageView
            Scene scene = ticketPageView;

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


                    TicketPageView ticketPageView=new TicketPageView();
            BuyTicketController buyTicketController=new BuyTicketController(primaryStage,ticketPageView);

           buyTicketController.initComponents(); // Inizializza i componenti del controller


 */