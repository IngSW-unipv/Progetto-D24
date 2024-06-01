package it.unipv.insfw23.TicketWave.modelController.controller.payment;

import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentDataPController {

    private PaymentSelectionView paymentPage;
    private Stage mainStage;
    private PaymentDataPView paymentDataPView;

    private User user= ConnectedUser.getInstance().getUser();
    private IResettableScene home = ConnectedUser.getInstance().getHome();

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
                   if(home != null){
                       UpperBar.getIstance().setForCustomer();
                       home.reSetBars();
                       Scene nextScene = (Scene) home;
                       mainStage.setScene(nextScene);
                   }
                   else{
                       UpperBar.getIstance().setForCustomer();
                       Customer customerUser = (Customer) user;
                       CustomerView customerView = new CustomerView();
                       CustomerController customerController = new CustomerController(mainStage, customerView, ConnectedUser.getInstance().getLoginView());
                       mainStage.setScene(customerView);
                   }
               }
               else {
                   System.out.println("Stai andando alla ManagerView");
                   if(home!=null){
                       UpperBar.getIstance().setForManager();
                       home.reSetBars();
                       Scene nextScene = (Scene) home;
                       mainStage.setScene(nextScene);
                   }
                   else{
                       UpperBar.getIstance().setForManager();
                       Manager managerUser = (Manager) user;
                       ManagerView managerView = new ManagerView(managerUser.getName(), managerUser.getNotification(), managerUser.getEventlist());
                       ManagerController managerController = new ManagerController(mainStage, managerView, ConnectedUser.getInstance().getLoginView());
                       mainStage.setScene(managerView);
                   }

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
