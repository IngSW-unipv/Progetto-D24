package it.unipv.insfw23.TicketWave.modelController.controller.payment;


import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * The class manages the {@link PaymentSelectionView},
 * Allowing efficient control over switching to following views:{@link PaymentDataMView}, {@link PaymentDataPView}
 *
 */
public class PaymentSelectionController {

    private Stage mainStage;
    private PaymentSelectionView paymentSelectionView;
    private TicketPageView ticketPageView;
    private IResettableScene backScene;
    private PaymentDataPView paymentDataPView;
    private Manager loggedManager;
    private PaymentDataMView paymentDataMView;
    private User user= ConnectedUser.getInstance().getUser();


    /**
     * the constructor uses the Current UI {@link PaymentSelectionView} and the {@link IResettableScene} interface
     * @param mainStage
     * @param paymentSelectionView
     * @param backScene
     */
    public PaymentSelectionController(Stage mainStage, PaymentSelectionView paymentSelectionView, IResettableScene backScene) {
        this.mainStage = mainStage;
        this.paymentSelectionView = paymentSelectionView;
        this.backScene=backScene;

        initComponents();
    }

    /**
     * This method has two EventHandlers associated with the {@link PaymentSelectionView} buttons.
     *
     * goToPaymentDataPage EventHandler: allows passage to the next view which can be {@link PaymentDataPView } or {@link PaymentDataMView}.
     * If the connected user is a Customer, the Ticket Number is set.
     * Otherwise, if the connected user is manager, his credit card is set in the label
     * (empty string if a previous card has not already been inserted)
     *
     * the same logic is applied if the payPol button is selected, except for the manager card insertion logic
     *
     * turnBack EventHandler: the previous view is loaded
     *
     *
     */
    public void initComponents(){

        //-----EventHandler passaggio alla View successiva-----((
        EventHandler<MouseEvent> goToPaymentDataPage= new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                if(paymentSelectionView.getMasterPayButton().isSelected()){
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Redicted to PaymentDataMView");
                paymentDataMView =new PaymentDataMView();
               PaymentDataMController paymentDataMController = new PaymentDataMController(mainStage, paymentDataMView, paymentSelectionView);



               if(user.isCustomer()) {

            	   ticketPageView = (TicketPageView)backScene;
            	   paymentDataMController.setNumOfTickets(ticketPageView.getNumOfTickets());
               }

               //set  del Label della Carta di Credito del loggedManager (vuota se non è stata associata precedentemente)
               else{
                   loggedManager = (Manager) user;
                   paymentDataMView.setInsertNCText(loggedManager.getCreditCard());
               }

               mainStage.setScene(paymentDataMView);

            }
                else if (paymentSelectionView.getPaypolButton().isSelected()) {
                    System.out.println("Redicted to PaymentDataPView");
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


        //-----EventHandler  passaggio alla View precedente-----//
        EventHandler<MouseEvent> turnBack = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {

                backScene.reSetBars();
                Scene backSceneCasted = (Scene) backScene;
                mainStage.setScene(backSceneCasted);

            }
        };
        paymentSelectionView.getBackButton().setOnMouseClicked(turnBack);






    }
}









