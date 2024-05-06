package it.unipv.insfw23.TicketWave.modelView.payment;

import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;

import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;


//CLASSE DI PROVA DI COMPILAZIONE NON UTILIZZARE
public class ProvaPaymentRun extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Event> events = new ArrayList<>();

        // Creazione di una nuova istanza della classe PaymentSelectionView
        Manager creator = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", Province.COMO, "1234567890123456", events, 5, 1, LocalDate.now(), 0);
        TicketPageView ticketPageView=new TicketPageView();
        PaymentSelectionView paymentView = new PaymentSelectionView();
        SubscriptionSelectionView subscriptionSelectionView=new SubscriptionSelectionView();
        PaymentSelectionController paymentSelectionController=new PaymentSelectionController(primaryStage,paymentView,subscriptionSelectionView,creator);
        //PaymentDataPView paymentDataPView=new PaymentDataPView();
        SubscriptionSelectionController subscriptionSelectionController=new SubscriptionSelectionController(primaryStage,subscriptionSelectionView);

        primaryStage.setScene(subscriptionSelectionView);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }
}


