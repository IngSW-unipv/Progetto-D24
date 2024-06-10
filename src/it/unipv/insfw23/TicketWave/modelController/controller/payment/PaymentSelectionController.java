package it.unipv.insfw23.TicketWave.modelController.controller.payment;


import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PaymentSelectionController {

    private Stage mainStage;
    private PaymentSelectionView paymentPage;
    private TicketPageView ticketPage;
    private SubscriptionSelectionView subscriptionSelectionView;
    private IResettableScene backScene;
    private PaymentDataPView paymentDataPPage;

    private boolean isviewermanager;

    private PaymentDataMView paymentDataMPage;
    private User user= ConnectedUser.getInstance().getUser();

    public PaymentSelectionController(Stage mainStage, PaymentSelectionView PaymentPage, IResettableScene backScene) {
        this.mainStage = mainStage;
        this.paymentPage = PaymentPage;
        this.backScene=backScene;

        initComponents();
    }

    public void initComponents(){

        EventHandler<MouseEvent> goToPaymentDataPage= new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                if(paymentPage.getMastercardButton().isSelected()){
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Stai andando alla PaymentDataMPage");
                paymentDataMPage=new PaymentDataMView();
               PaymentDataMController paymentDataMController = new PaymentDataMController(mainStage,paymentDataMPage,paymentPage);
               
               if(user.isCustomer()) {
            	   ticketPage = (TicketPageView)backScene;
            	   paymentDataMController.setNumOfTickets(ticketPage.getNumOfTickets());
               }            
                mainStage.setScene(paymentDataMPage);
            } else if (paymentPage.getPaypalButton().isSelected()) {
                    System.out.println("Stai andando alla PaymentDataPPage");
                    paymentDataPPage=new PaymentDataPView();
                    PaymentDataPController paymentDataPController=new PaymentDataPController(mainStage,paymentDataPPage,paymentPage);
                  //se lo user è cliente mi porto dietro il numero di biglietti che si vuole acquistare
                    if(user.isCustomer()) {
                 	   ticketPage = (TicketPageView)backScene;
                 	   paymentDataPController.setNumOfTickets(ticketPage.getNumOfTickets());
                    }
                    mainStage.setScene(paymentDataPPage);
                }else {
                    paymentPage.getErrmessage().setOpacity(100);
                    System.out.println("Seleziona un Metodo di Pagamento");

                }

                }
            };
        paymentPage.getNextButton().setOnMouseClicked(goToPaymentDataPage);



        EventHandler<MouseEvent> turnBack = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto

                backScene.reSetBars();
                Scene backSceneCasted = (Scene) backScene;
                mainStage.setScene(backSceneCasted);

            }
        };

// Imposta l'handler sull'evento di clic del pulsante di ritorno
        paymentPage.getBackButton().setOnMouseClicked(turnBack);






    }
}









