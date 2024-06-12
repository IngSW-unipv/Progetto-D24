package it.unipv.insfw23.TicketWave.modelController.controller.payment;


import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentSelectionController {

    private Stage mainStage;
    private PaymentSelectionView paymentSelectionView;
    private TicketPageView ticketPageView;
    private SubscriptionSelectionView subscriptionSelectionView;
    private IResettableScene backScene;
    private PaymentDataPView paymentDataPView;
    private Manager loggedManager;



    private PaymentDataMView paymentDataMView;
    private User user= ConnectedUser.getInstance().getUser();

    public PaymentSelectionController(Stage mainStage, PaymentSelectionView paymentSelectionView, IResettableScene backScene) {
        this.mainStage = mainStage;
        this.paymentSelectionView = paymentSelectionView;
        this.backScene=backScene;

        initComponents();
    }

    public void initComponents(){

        EventHandler<MouseEvent> goToPaymentDataPage= new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                if(paymentSelectionView.getMasterPayButton().isSelected()){
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Stai andando alla PaymentDataMPage");
                paymentDataMView =new PaymentDataMView();
               PaymentDataMController paymentDataMController = new PaymentDataMController(mainStage, paymentDataMView, paymentSelectionView);



               if(user.isCustomer()) {

            	   ticketPageView = (TicketPageView)backScene;
            	   paymentDataMController.setNumOfTickets(ticketPageView.getNumOfTickets());
               }

               //CODICE DI SET PER LA CREDIT CARD DEL MANAGER
               else{
                   loggedManager = (Manager) user;
                   paymentDataMView.setInsertNCText(loggedManager.getCreditCard());
               }

               mainStage.setScene(paymentDataMView);

            }
                else if (paymentSelectionView.getPaypolButton().isSelected()) {
                    System.out.println("Stai andando alla PaymentDataPPage");
                    paymentDataPView =new PaymentDataPView();
                    PaymentDataPController paymentDataPController=new PaymentDataPController(mainStage, paymentDataPView, paymentSelectionView);
                  //se lo user è cliente mi porto dietro il numero di biglietti che si vuole acquistare

                    if(user.isCustomer()) {
                 	   ticketPageView = (TicketPageView)backScene;
                 	   paymentDataPController.setNumOfTickets(ticketPageView.getNumOfTickets());
                    }

                    mainStage.setScene(paymentDataPView);
                }
                else {
                    paymentSelectionView.getErrmessage().setOpacity(100);
                    System.out.println("Seleziona un Metodo di Pagamento");

                }

                }
            };
        paymentSelectionView.getNextButton().setOnMouseClicked(goToPaymentDataPage);



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
        paymentSelectionView.getBackButton().setOnMouseClicked(turnBack);






    }
}









