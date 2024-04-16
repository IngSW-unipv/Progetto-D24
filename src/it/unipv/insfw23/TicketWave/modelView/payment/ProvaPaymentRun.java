package it.unipv.insfw23.TicketWave.modelView.payment;

import it.unipv.insfw23.TicketWave.modelController.Controller.Payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ProvaPaymentRun extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Creazione di una nuova istanza della classe PaymentSelectionView

        TicketPageView ticketPageView=new TicketPageView();
        PaymentSelectionView paymentView = new PaymentSelectionView();
        PaymentSelectionController paymentSelectionController=new PaymentSelectionController(primaryStage,paymentView,ticketPageView);
        PaymentDataPView paymentDataPView=new PaymentDataPView();
        primaryStage.setScene(paymentView);
        primaryStage.show();


        /*
        PaymentDataMView paymentDataMView=new PaymentDataMView();
        primaryStage.setScene(paymentDataMView);
        primaryStage.show();
        */

        // Impostazione dei componenti della vista (ad esempio, il prezzo totale

        paymentView.setComponents(175.0);

        // Impostazione della scena principale con la vista di pagamento
        //primaryStage.setScene(paymentView);

        // Impostazione del titolo della finestra
        primaryStage.setTitle("Payment Selection");

        // Mostra la finestra principale
        //primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }
}


