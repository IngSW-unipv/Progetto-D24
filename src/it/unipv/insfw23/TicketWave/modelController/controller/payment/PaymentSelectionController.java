package it.unipv.insfw23.TicketWave.modelController.controller.payment;


import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentDataMController;
import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentDataPController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PaymentSelectionController {

    private Stage mainStage;
    private PaymentSelectionView paymentPage;
    private TicketPageView ticketPage;
    private SubscriptionSelectionView subscriptionSelectionView;
    private Scene scene;
    private PaymentDataPView paymentDataPPage;

    private boolean isviewermanager;

    private PaymentDataMView paymentDataMPage;

    public PaymentSelectionController(Stage mainStage, PaymentSelectionView PaymentPage, Scene scene, boolean isviewermanager) {
        this.mainStage = mainStage;
        this.paymentPage = PaymentPage;
        this.scene=scene;
        this.isviewermanager=isviewermanager;

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
               PaymentDataMController paymentDataMController = new PaymentDataMController(mainStage,paymentDataMPage,paymentPage,isviewermanager);
                mainStage.setScene(paymentDataMPage);
            } else if (paymentPage.getPaypalButton().isSelected()) {
                    System.out.println("Stai andando alla PaymentDataPPage");
                    paymentDataPPage=new PaymentDataPView();
                    PaymentDataPController paymentDataPController=new PaymentDataPController(mainStage,paymentDataPPage,paymentPage);
                    mainStage.setScene(paymentDataPPage);
                }else {
                    paymentPage.getErrmessage().setOpacity(100);
                    System.out.println("Seleziona un Metodo di Pagamento");

                }

                }
            };
        paymentPage.getNextButton().setOnMouseClicked(goToPaymentDataPage);


        //NOTA per turnback il metodo non funziona quando si runna loginview e si prova a registrare un manager.
        //secondo me perchè non viene valutato il parametro isviewermanager (stesso discorso per il problema di resetBars della view PaymentSelectionView)
        //bioparco?

        EventHandler<MouseEvent> turnBack = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                try {
                    // Ottieni il metodo "reSetBars" dalla classe della scena
                    Method method = scene.getClass().getMethod("reSetBars");
                    // Invoca effettivamente il metodo se esiste
                    method.invoke(scene);

                    // Determina quale scena caricare in base a isviewermanager
                    if (isviewermanager) {
                        System.out.println("Sei ritornato indietro alla subscriptionSelectionView");
                        mainStage.setScene(subscriptionSelectionView);
                    } else {
                        System.out.println("Sei ritornato indietro alla TicketPage");
                        TicketPageView ticketPageView=new TicketPageView();
                        ticketPageView.reSetBars();
                        mainStage.setScene(ticketPageView);
                    }
                } catch (NoSuchMethodException e) {
                    // Il metodo "reSetBars" non esiste nella classe della scena
                    System.out.println("Metodo 'reSetBars' non trovato nella classe della scena.");
                    e.printStackTrace();
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Gestione delle eccezioni durante l'invocazione del metodo
                    e.printStackTrace();
                }
            }
        };

// Imposta l'handler sull'evento di clic del pulsante di ritorno
        paymentPage.getBackButton().setOnMouseClicked(turnBack);






    }
}









