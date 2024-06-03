package it.unipv.insfw23.TicketWave.modelController.controller.payment;

import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.dao.ticketDao.TicketDao;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import it.unipv.insfw23.TicketWave.modelController.factory.payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelController.factory.subscription.SubscriptionHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.payment.MastercardPayment;
import it.unipv.insfw23.TicketWave.modelDomain.payment.PayPalPayment;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class PaymentDataPController {

    private PaymentSelectionView paymentSelectionView;
    private Stage mainStage;
    private PaymentDataPView paymentDataPView;

    private User user= ConnectedUser.getInstance().getUser();
    private IResettableScene home = ConnectedUser.getInstance().getHome();
    private IPaymentAdapter iPaymentAdapter;

    public PaymentDataPController(Stage mainStage, PaymentDataPView paymentDataPView, PaymentSelectionView paymentSelectionView){
       this.paymentDataPView = paymentDataPView;
       this.paymentSelectionView = paymentSelectionView;
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
               paymentSelectionView.reSetBars();
               mainStage.setScene(paymentSelectionView);
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
                   System.out.println("Stai andando alla CustomerView");
                   try {
                       Customer customer = (Customer) user;
                       TicketDao ticketDao = new TicketDao();
                       System.out.println("ticketdao creato");

                       PayPalPayment payPalPayment = new PayPalPayment();
                       iPaymentAdapter = PaymentFactory.getPaypalAdapter(payPalPayment);
                       System.out.println("creati i payPalPayment payment e interfaccia");


                       Ticket ticket = customer.buyticket(iPaymentAdapter, ConnectedUser.getInstance().getEventForTicket(), ConnectedUser.getInstance().getTicketType(), getUsePoint());
                       System.out.println("Ticket associato correttamente");

                       try {
                           ticketDao.insertTicket(ticket, customer);

                           System.out.println("insert ticket eseguito");
                       } catch (SQLException e) {
                           throw new SQLException("Problema inserimento ticket", e);
                       }



                   }
                   catch (Exception e) {
                       throw new RuntimeException(e);
                   }



                   UpperBar.getIstance().setForCustomer();
                   home.reSetBars();
                   Scene nextScene = (Scene) home;
                   mainStage.setScene(nextScene);




               }
               else {
                   Manager managerlogged = (Manager)user;
                   ProfileDao profiledao = new ProfileDao();
                   SubscriptionHandlerFactory.getInstance().getSubscriptionHandler().buySub(managerlogged, ConnectedUser.getInstance().getNewSubLevel(), PaymentFactory.getMastercardAdapter(new MastercardPayment()), paymentSelectionView.getPrice());
                   if(managerlogged.getSubscription() != -1) {
                       try {
                           profiledao.updateManagerSub(managerlogged);
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   }
                   else {
                       System.out.println("pagamento non andato a buon fine");
                   }

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

       paymentDataPView.getNextButton().setOnMouseClicked(goToNewPage);

   }
    public void setLabelforWavePoints() {
        if(!user.isCustomer()){
            paymentDataPView.getUsePointsButton().setVisible(false);
        }
    }

    public int getUsePoint(){
        int usePoint;
        if(paymentDataPView.getUsePointsButton().isSelected()){
            usePoint=1;
        }
        else{
            usePoint=0;
        }
        return usePoint;
    }



}
