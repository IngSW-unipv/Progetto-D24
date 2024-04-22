package it.unipv.insfw23.TicketWave.modelController.controller.subscription;

import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SubscriptionSelectionController {
    private Stage mainstage;
    private TicketPageView ticketPage;
    private PaymentSelectionView PaymentSelectionView;
    private SubscriptionSelectionView subscriptionSelectionView;
    private PaymentSelectionView paymentPage;
    boolean isviewermanager=true;


    public SubscriptionSelectionController(Stage mainstage,SubscriptionSelectionView subscriptionSelectionView,PaymentSelectionView paymentPage) {
        this.subscriptionSelectionView=subscriptionSelectionView;
        this.paymentPage=paymentPage;
        this.mainstage=mainstage;
        initComponents();
    }

    public void initComponents() {

        EventHandler<MouseEvent> goToBuySubscription = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                System.out.println("Hai selezionato un abbonamento. Reindirizzamento alla pagina di pagamento.");
                paymentPage= new PaymentSelectionView();
                PaymentSelectionController paymentSelectionController= new PaymentSelectionController(mainstage,paymentPage,subscriptionSelectionView,isviewermanager);
                mainstage.setScene(paymentPage);
            }

        };

        // Imposta l'handler sull'azione del clic sui bottoni della sottoscrizione

        subscriptionSelectionView.getBottonePrimaSub().setOnMouseClicked(goToBuySubscription);
        subscriptionSelectionView.getBottoneSecondaSub().setOnMouseClicked(goToBuySubscription);
        subscriptionSelectionView.getBottoneTerzaSub().setOnMouseClicked(goToBuySubscription);
    }
}
