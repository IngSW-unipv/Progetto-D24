package it.unipv.insfw23.TicketWave.modelView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProvaRun extends Application {

    @Override
    public void start(Stage primaryStage) {


        PaymentDataM2View paymentDataPage = new PaymentDataM2View();
        Scene scene = paymentDataPage;

        primaryStage.setTitle("Payment Data");
        primaryStage.setScene(scene);
        primaryStage.show();
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