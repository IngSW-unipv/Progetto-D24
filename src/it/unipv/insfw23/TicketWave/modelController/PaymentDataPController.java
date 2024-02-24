package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.PaymentDataPview;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentDataPController {

    private PaymentSelectionView paymentPage;
    private Stage mainStage;
    private PaymentDataPview paymentDataPPage;

   public PaymentDataPController(Stage mainStage, PaymentDataPview paymentDataPPage, PaymentSelectionView paymentPage){
       this.paymentDataPPage=paymentDataPPage;
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

       paymentDataPPage.getBackButton().setOnMouseClicked(turnBackPaymentPage);

   }






}
