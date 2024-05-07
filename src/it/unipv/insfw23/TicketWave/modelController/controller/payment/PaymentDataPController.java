package it.unipv.insfw23.TicketWave.modelController.controller.payment;

import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentDataPController {

    private PaymentSelectionView paymentPage;
    private Stage mainStage;
    private PaymentDataPView paymentDataPView;
    private CustomerView customerView;
    private ManagerView managerView;
    private User user= ConnectedUser.getInstance().getUser();;

    public PaymentDataPController(Stage mainStage, PaymentDataPView paymentDataPView, PaymentSelectionView paymentPage){
       this.paymentDataPView = paymentDataPView;
       this.paymentPage=paymentPage;
       this.mainStage=mainStage;
       initComponents();
        setLabelforWavePoints();

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

       EventHandler<MouseEvent> goToNewPage = new EventHandler<>() {

           @Override
           public void handle(MouseEvent actionEvent) {
               // Azione da eseguire quando il pulsante viene premuto
               System.out.println("pagamento andato a buon fine stai tornando indietro alla home page!");
               if(user.isCustomer()){
                   System.out.println("Stai andando alla CustomerView");
                   UpperBar.getIstance().setForCustomer();
                   mainStage.setScene(customerView);
               }
               else {
                   System.out.println("Stai andando alla ManagerView");
                   UpperBar.getIstance().setForManager();
                   mainStage.setScene(managerView);
               }
           }
       };

       paymentDataPView.getForwardButtonButton().setOnMouseClicked(goToNewPage);


   }
    public void setLabelforWavePoints() {
        if(!user.isCustomer()){
            paymentDataPView.getUsePointsButton().setVisible(false);
        }
    }





}
