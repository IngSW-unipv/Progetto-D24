package it.unipv.insfw23.TicketWave.modelView.payment;

import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;

import javafx.application.Application;
import javafx.stage.Stage;


//CLASSE DI PROVA DI COMPILAZIONE NON UTILIZZARE
public class ProvaPaymentRun extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Creazione di una nuova istanza della classe PaymentSelectionView
        boolean isviewermanager=true;
        TicketPageView ticketPageView=new TicketPageView();
        PaymentSelectionView paymentView = new PaymentSelectionView();
        SubscriptionSelectionView subscriptionSelectionView=new SubscriptionSelectionView();
        PaymentSelectionController paymentSelectionController=new PaymentSelectionController(primaryStage,paymentView,subscriptionSelectionView,isviewermanager);
        PaymentDataPView paymentDataPView=new PaymentDataPView();
        SubscriptionSelectionController subscriptionSelectionController=new SubscriptionSelectionController(primaryStage,subscriptionSelectionView,paymentView);

        primaryStage.setScene(subscriptionSelectionView);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }
}


