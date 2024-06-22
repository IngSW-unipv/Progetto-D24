package it.unipv.insfw23.TicketWave.modelController.controller.subscription;

import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This controller manages all the buttons selected in {@link SubscriptionSelectionView}
 * EventHandler MouseEvent goToBuySubscription: if you click one of the subscriptionButton you go to the{@link  PaymentSelectionController}
 * EventHandler MouseEvent goBackEvent: if you click on the backButton you go back to the {@link it.unipv.insfw23.TicketWave.modelView.access.SignUpView} or
 * to the {@link it.unipv.insfw23.TicketWave.modelView.user.ManagerView}
 */
public class SubscriptionSelectionController {
    private Stage mainstage;
    private SubscriptionSelectionView subscriptionSelectionView;
    private PaymentSelectionView paymentPage;
    private IResettableScene backScene;


    public SubscriptionSelectionController(Stage mainstage, SubscriptionSelectionView subscriptionSelectionView, IResettableScene backScene) {
        this.subscriptionSelectionView=subscriptionSelectionView;
        this.mainstage=mainstage;
        this.backScene= backScene;


        initComponents();
    }

    public void initComponents() {

        EventHandler<MouseEvent> goToBuySubscription = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                System.out.println("You selected a subscription. Redirect to payment page.");
                double price = 0;
                if (actionEvent.getSource() == subscriptionSelectionView.getBottonePrimaSub()) {
                    price = subscriptionSelectionView.getPricePrimaSub();
                    ConnectedUser.getInstance().setNewSubLevel(0);

                } else if (actionEvent.getSource() == subscriptionSelectionView.getBottoneSecondaSub()) {
                    price = subscriptionSelectionView.getPriceSecondaSub();
                    ConnectedUser.getInstance().setNewSubLevel(1);

                } else if (actionEvent.getSource() == subscriptionSelectionView.getBottoneTerzaSub()) {
                    price = subscriptionSelectionView.getPriceTerzaSub();
                    ConnectedUser.getInstance().setNewSubLevel(2);
                }
                paymentPage = new PaymentSelectionView();
                paymentPage.setPriceComponent(price);
                System.out.println("Price Set");
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
                backScene.reSetBars();
                Scene backSceneCasted = (Scene) backScene;
                mainstage.setScene(backSceneCasted);

            }
        };
        subscriptionSelectionView.getBackButton().setOnMouseClicked(goBackEvent);
    }
}
