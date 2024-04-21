package it.unipv.insfw23.TicketWave.modelController.Controller.Payment;


import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
               PaymentDataMController paymentDataMController = new PaymentDataMController(mainStage,paymentDataMPage,paymentPage);
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


        EventHandler<MouseEvent> turnBack = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla TicketPage");
                if(isviewermanager){
                    try {
                        scene.getClass().getMethod("reSetBars");
                        System.out.println("Sei ritornato indietro alla  PaySubscriptionView");
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    mainStage.setScene(subscriptionSelectionView);
                }
               else {
                   try {
                    scene.getClass().getMethod("reSetBarsCustomer");
                       System.out.println("Sei ritornato indietro alla TicketPage");
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                    mainStage.setScene(ticketPage);
               }

            }
        };

        paymentPage.getBackButton().setOnMouseClicked(turnBack);





    }
}









