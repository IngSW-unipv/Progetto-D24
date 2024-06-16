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
import it.unipv.insfw23.TicketWave.modelDomain.payment.*;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;

import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;


/**
 * The class manages the {@link PaymentDataPView}
 *
 */
public class PaymentDataPController {
    private Stage mainStage;
    private PaymentDataPView paymentDataPView;
    private PaymentSelectionView paymentSelectionView;
    private User user= ConnectedUser.getInstance().getUser();  // associo l'User corrente a una variabile User
    private int numOfTickets;
    private IPaymentAdapter iPaymentAdapter;
    private IResettableScene home = ConnectedUser.getInstance().getHome();   // associo la home corrente alla variabile Home

    /**
     * the constructor uses the Current UI {@link PaymentDataPView} and the Previous view {@link PaymentSelectionView}
     *
     * calling an initialization method and a setup method for a possible logged {@link Customer} or {@link Manager}  as a {@link ConnectedUser}
     * @param mainStage
     * @param paymentDataPView
     * @param paymentSelectionView
     */
    public PaymentDataPController(Stage mainStage, PaymentDataPView paymentDataPView, PaymentSelectionView paymentSelectionView){
       this.paymentDataPView = paymentDataPView;
       this.paymentSelectionView = paymentSelectionView;
       this.mainStage=mainStage;
       initComponents();
        setLabelforWavePoints();

   }

    /**
     * This method has two EventHandlers associated with the {@link PaymentDataPView} buttons.
     * turnBackPaymentPage EventHandler: if  BackButton is clicked--return back to the paymentSelectionView.
     *
     * goToNewPage EventHandler: if the NextButton is clicked--based on the LoggedUser, if is {@link Customer} buy one or more tickets, or if is {@link Manager} buy a subscription.
     * Both options have additional input and domain controls
     *
    */
   public void initComponents(){

        //-----EventHandler passaggio alla View precedente-----//
       EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

           @Override
           public void handle(MouseEvent actionEvent) {
               // Azione da eseguire quando il pulsante viene premuto
               System.out.println("Return to the  PaySelectionPage");
               paymentSelectionView.reSetBars();
               mainStage.setScene(paymentSelectionView);
           }
       };

       paymentDataPView.getBackButton().setOnMouseClicked(turnBackPaymentPage);


       //-----EventHandler passaggio alla View successiva-----//
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


               //USER=CUSTOMER
               if(user.isCustomer()){
                   System.out.println("The User is a Customer ");
                   Customer customer = (Customer) user;  //cast del User in Customer
                   Event currentEvent = ConnectedUser.getInstance().getEventForTicket();   //associo l'Evento Corrente alla variabile Event
                   
                   try {

                       //creazione Variabili dei DAO, associo alle istanze
                       
                       TicketDao ticketDao = new TicketDao();
                       EventDao eventDao = new EventDao();
                       ProfileDao profileDao= new ProfileDao();



                       //utilizzo di un Istanza del PaymentFactor e associo a un iPaymentAdapter
                       PayPolPayment payPolPayment = new PayPolPayment();
                       iPaymentAdapter = PaymentFactory.getPaypolAdapter(payPolPayment);


                       //associazione dei tickets
                       for(int i = 0; i < numOfTickets; i++) {
                    	   Ticket ticket = customer.buyticket(iPaymentAdapter, currentEvent, ConnectedUser.getInstance().getTicketType(), getUsePoint());
                           System.out.println("ticket associated correctly");

                           try {
                               ticketDao.insertTicket(ticket, customer);
                               System.out.println("insert ticket query executed");
                           } catch (SQLException e) {
                               throw new SQLException("problem with ticket insert", e);
                           }

                           //update dei points
                           try {
                               profileDao.updateCustomerPoints(customer);


                               System.out.println("updatepoints executed");
                           } catch (SQLException e) {
                               throw new SQLException("Problems with ticket Update", e);
                           }
                           //update del numero dei posti
                           try {
                        	   eventDao.updateSeatsNumber(ConnectedUser.getInstance().getEventForTicket());
                        	   
                        	   System.out.println("updateSeats executed");
                           } catch (SQLException e) {
                        	   throw new SQLException("Problems with Update Seats Number");
                           }
                           
                       }

                       //controllo e inserimento eventuale Notifica

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
                   customerview.updateWavePoints(customer.getPoints());
                   CustomerController customerController = new CustomerController(mainStage, customerview, ConnectedUser.getInstance().getLoginView());
                   mainStage.setScene(customerview);

                   //FINE CUSTOMER

                //USER=MANAGER
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
                       System.out.println("Payment Failed ");
                   }

                   if(home!=null){  //utilizzo managerView esistente
                       UpperBar.getIstance().setForManager();
                       home.reSetBars();
                       ManagerView managerView = (ManagerView)home;
                       managerView.updateSubLabels(managerlogged.getSubscription(), managerlogged.getCounterCreatedEvents());
                       ManagerController managerController = new ManagerController(mainStage, managerView, ConnectedUser.getInstance().getLoginView());
                       mainStage.setScene(managerView);
                   } else {  //creo nuova managerView
                       UpperBar.getIstance().setForManager();
                       ManagerView managerView = new ManagerView(managerlogged.getName(), managerlogged.getNotification(), managerlogged.getEventlist(),managerlogged.getSubscription(),managerlogged.getCounterCreatedEvents());
                       ConnectedUser.getInstance().setHome(managerView);
                       ManagerController managerController = new ManagerController(mainStage, managerView, ConnectedUser.getInstance().getLoginView());
                       mainStage.setScene(managerView);
                   }
               }
               //FINE MANAGER
           }
       };

       paymentDataPView.getNextButton().setOnMouseClicked(goToNewPage);

   }

    /**
     * This method sets the Round Button inserted in the View not visible, if the user is a {@link Manager}
     */
    public void setLabelforWavePoints() {
        if(!user.isCustomer()){
            paymentDataPView.getUsePointsButton().setVisible(false);
        }
    }


    /**
     * With this Method the number of tickets is set
     * @param number
     */
    public void setNumOfTickets(int number) {
    	numOfTickets = number;
    }


    /**
     *The method is used for the BuySub method for purchasing the ticket
     * @return 1 if the User has selected the UsePointsButton. 0 Otherwise.
     */
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
