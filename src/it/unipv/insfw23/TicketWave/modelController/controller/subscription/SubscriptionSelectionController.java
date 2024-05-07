package it.unipv.insfw23.TicketWave.modelController.controller.subscription;

import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;

public class SubscriptionSelectionController {
    private Stage mainstage;
    private SubscriptionSelectionView subscriptionSelectionView;
    private PaymentSelectionView paymentPage;
    private Scene backScene;


    public SubscriptionSelectionController(Stage mainstage, SubscriptionSelectionView subscriptionSelectionView, Scene scene) {
        this.subscriptionSelectionView=subscriptionSelectionView;
        this.mainstage=mainstage;
        this.backScene= scene;

        /*

        user = new Manager("Piero", "Antonelli", "12-10-11", "ciccio@gmail.com",
                "ciccio99", Province.ALESSANDRIA, "3926475898609800", new ArrayList<Event>(), 5, 0, LocalDate.now(), 0);

         */

        initComponents();
    }

    public void initComponents() {

        EventHandler<MouseEvent> goToBuySubscription = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                System.out.println("Hai selezionato un abbonamento. Reindirizzamento alla pagina di pagamento.");
                paymentPage = new PaymentSelectionView();
                PaymentSelectionController paymentSelectionController = new PaymentSelectionController(mainstage, paymentPage, subscriptionSelectionView);
                mainstage.setScene(paymentPage);
            }

        };

        // Imposta l'handler sull'azione del clic sui bottoni della sottoscrizione

        subscriptionSelectionView.getBottonePrimaSub().setOnMouseClicked(goToBuySubscription);
        subscriptionSelectionView.getBottoneSecondaSub().setOnMouseClicked(goToBuySubscription);
        subscriptionSelectionView.getBottoneTerzaSub().setOnMouseClicked(goToBuySubscription);


        EventHandler<MouseEvent> goBackEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                try {
                    Method method = backScene.getClass().getMethod("reSetBars");

                    // Invoca effettivamente il metodo se esiste
                    method.invoke(backScene);
                    mainstage.setScene(backScene);
                } catch (NoSuchMethodException e) {
                    // Il metodo "reSetBars" non esiste nella classe della scena
                    System.out.println("Metodo 'reSetBars' non trovato nella classe della scena");
                    e.printStackTrace();
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Gestione delle eccezioni durante l'invocazione del metodo
                    e.printStackTrace();
                }
            }
        };
        subscriptionSelectionView.getBackButton().setOnMouseClicked(goBackEvent);
    }
}
