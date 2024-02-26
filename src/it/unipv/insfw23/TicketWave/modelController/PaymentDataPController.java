package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentDataPController {

    private PaymentSelectionView paymentPage;
    private Stage mainStage;
    private PaymentDataPView paymentDataPView;
    private User user;

    public PaymentDataPController(Stage mainStage, PaymentDataPView paymentDataPView, PaymentSelectionView paymentPage){
       this.paymentDataPView = paymentDataPView;
       this.paymentPage=paymentPage;
       this.mainStage=mainStage;
       initComponents();

   }

   public void initComponents(){
       EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

           @Override
           public void handle(MouseEvent actionEvent) {
               // Azione da eseguire quando il pulsante viene premuto
               System.out.println("Sei ritornato indietro alla PaySelectionPage");
               paymentPage.reSetBars();
               mainStage.setScene(paymentPage);
           }
       };

       paymentDataPView.getBackButton().setOnMouseClicked(turnBackPaymentPage);



       /*
       if (!user.isCustomer()) {
           paymentDataPView.getUsePointsButton().setOpacity(0);
           paymentDataPView.getUsePointsButton().setDisable(true);
       } else {
           // Se l'utente è un Customer, lascia il bottone "Use Points" visibile
           paymentDataPView.getUsePointsButton().setOpacity(1);
       }

       */


   }






}
