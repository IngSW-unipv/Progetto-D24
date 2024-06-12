package it.unipv.insfw23.TicketWave.modelController.controller.payment;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.dao.notificationDao.NotificationDao;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.dao.ticketDao.TicketDao;
import it.unipv.insfw23.TicketWave.modelController.controller.user.CustomerController;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.NotificationHandlerFactory;
import it.unipv.insfw23.TicketWave.modelController.factory.payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelController.factory.subscription.SubscriptionHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.payment.MasterPayPayment;
import it.unipv.insfw23.TicketWave.modelDomain.payment.PayPolPayment;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
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
    
    private int numOfTickets;

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
               // Validare il campo email
               if(paymentDataPView.getInsertEmail().getText().isEmpty()){
                   paymentDataPView.getErrorLabel().setVisible(true);
                   return;
               } else {
                   paymentDataPView.getErrorLabel().setVisible(false);
               }

               // Azione da eseguire quando il pulsante avanti viene premuto
               System.out.println("Pagamento andato a buon fine, stai tornando alla pagina principale!");
               if(user.isCustomer()){
                   System.out.println("Stai andando alla vista del cliente");
                   Customer customer = (Customer) user;
                   Event currentEvent = ConnectedUser.getInstance().getEventForTicket();
                   
                   try {
                       
                       TicketDao ticketDao = new TicketDao();
                       EventDao eventDao = new EventDao();
                       ProfileDao profileDao= new ProfileDao();
                       System.out.println("TicketDao creato");

                       PayPolPayment payPolPayment = new PayPolPayment();
                       iPaymentAdapter = PaymentFactory.getPaypolAdapter(payPolPayment);
                       System.out.println("Creati PayPolPayment e interfaccia");

                       for(int i = 0; i < numOfTickets; i++) {
                    	   Ticket ticket = customer.buyticket(iPaymentAdapter, currentEvent, ConnectedUser.getInstance().getTicketType(), getUsePoint());
                           System.out.println("Biglietto associato correttamente");

                           try {
                               ticketDao.insertTicket(ticket, customer);
                               System.out.println("Inserimento biglietto eseguito");
                           } catch (SQLException e) {
                               throw new SQLException("Problema inserimento biglietto", e);
                           }
                           
                           try {
                               profileDao.updateCustomerPoints(customer);


                               System.out.println("updatepoints eseguito");
                           } catch (SQLException e) {
                               throw new SQLException("Problema aggiornamento punti", e);
                           }
                           
                           try {
                        	   eventDao.updateSeatsNumber(ConnectedUser.getInstance().getEventForTicket());
                        	   
                        	   System.out.println("updateSeats eseguito");
                           } catch (SQLException e) {
                        	   throw new SQLException("Problema aggiornamento posti");
                           }
                           
                       }
                       
                       if(currentEvent.getSeatsRemaining() == 0) {
                    	   NotificationDao notificationDao = new NotificationDao();
                    	   INotificationHandler notificationHandler = NotificationHandlerFactory.getIstance().getNotificationHandler();
                    	   notificationHandler.setCounterNotification(notificationDao.selectNotificationNumber());
                    	   Notification n = notificationHandler.sendNotificationSoldOut(currentEvent);
                    	   notificationDao.insertNotification(n);
                       }
                       
                   } catch (Exception e) {
                       throw new RuntimeException(e);
                   }

                   UpperBar.getIstance().setForCustomer();
                   home.reSetBars();
                   CustomerView customerview = (CustomerView) home;
                   customerview.updateTicketsTable(customer.getTicketsList());
                   CustomerController customerController = new CustomerController(mainStage, customerview, ConnectedUser.getInstance().getLoginView());
                   mainStage.setScene(customerview);

               } else {
                   Manager managerlogged = (Manager)user;
                   ProfileDao profiledao = new ProfileDao();
                   SubscriptionHandlerFactory.getInstance().getSubscriptionHandler().buySub(managerlogged, ConnectedUser.getInstance().getNewSubLevel(), PaymentFactory.getMasterPayAdapter(new MasterPayPayment()), paymentSelectionView.getPrice());
                   if(managerlogged.getSubscription() != -1) {
                       try {
                           profiledao.updateManagerSub(managerlogged);
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   } else {
                       System.out.println("Pagamento non andato a buon fine");
                   }

                   if(home!=null){
                       UpperBar.getIstance().setForManager();
                       home.reSetBars();
                       ManagerView managerView = (ManagerView)home;
                       managerView.updateSubLabels(managerlogged.getSubscription(), managerlogged.getCounterCreatedEvents());
                       ManagerController managerController = new ManagerController(mainStage, managerView, ConnectedUser.getInstance().getLoginView());
                       mainStage.setScene(managerView);
                   } else {
                       UpperBar.getIstance().setForManager();
                       ManagerView managerView = new ManagerView(managerlogged.getName(), managerlogged.getNotification(), managerlogged.getEventlist(),managerlogged.getSubscription(),managerlogged.getCounterCreatedEvents());
                       ConnectedUser.getInstance().setHome(managerView);
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
    
    public void setNumOfTickets(int number) {
    	numOfTickets = number;
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
