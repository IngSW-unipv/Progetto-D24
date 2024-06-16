package it.unipv.insfw23.TicketWave.modelController.controller.payment;

import java.sql.SQLException;

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
import it.unipv.insfw23.TicketWave.modelDomain.payment.MasterPayAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.payment.MasterPayPayment;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.payment.IPaymentAdapter;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The class manages the {@link PaymentDataMView}
 *
 */

public class PaymentDataMController {
    private Stage mainStage;
    private PaymentDataMView paymentDataMView;
    private PaymentSelectionView paymentSelectionView;
    private User user = ConnectedUser.getInstance().getUser();  // associo l'User corrente a una variabile User
    private int numOfTickets;

    private IPaymentAdapter iPaymentAdapter;
    private IResettableScene home = ConnectedUser.getInstance().getHome();  // associo la home corrente alla variabile Home


    /**
     * the constructor uses the Current UI {@link PaymentDataMView} and the Previous view {@link PaymentSelectionView}
     *
     * calling an initialization method and a setup method for a possible logged {@link Customer} or a {@link Manager} as a {@link ConnectedUser}
     * @param mainStage
     * @param paymentDataMView
     * @param paymentSelectionView
     */
    public PaymentDataMController(Stage mainStage, PaymentDataMView paymentDataMView, PaymentSelectionView paymentSelectionView) {
        this.paymentDataMView = paymentDataMView;
        this.paymentSelectionView = paymentSelectionView;
        this.mainStage = mainStage;
        initComponents();
        setLabelforWavePoints();
    }


    /**
     * the methos has two EventHandlers associated with the {@link PaymentDataMView} buttons. if  BackButton is clicked--return back to the paymentSelectionView.
     * If the NextButton is clicked--based on the LoggedUser, if is {@link Customer} buy one or more tickets, or if is {@link Manager} buy a subscription.
     * Both options have additional input and domain controls
     */
    public void initComponents() {

        //-----EventHandler per ritornare alla UI precedente(PaymentSelectionView)-----//


        EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Return to the paymentSelectionView");
                paymentSelectionView.reSetBars();  // si accede al metodo per reimpostare le barre di layout
                mainStage.setScene(paymentSelectionView);  //viene settata la scena sul MainStage principale
            }
        };

        paymentDataMView.getBackButton().setOnMouseClicked(turnBackPaymentPage);



        //-----EventHandler per Proseguire alla View Successiva(PaymentSelectionView)-----//
        EventHandler<MouseEvent> goToNewPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // controllo sull'inserimento dei campi numerici
                if (!checkNumericFields()) {
                    // Se checkNumericFields ritorna false, esci dalla funzione
                    return;
                }


                //-----Azione da eseguire quando il pulsante NextButton viene premuto-----//

                if (user.isCustomer()) {            //controllo dal valore booleano di isCustomer
                    System.out.println("The User is a Customer");
                    Customer customer = (Customer) user;   //cast del User in Customer
                    Event currentEvent = ConnectedUser.getInstance().getEventForTicket();  //associo l'Evento Corrente alla variabile Event
                    
                    try {

                        //creazione Variabili dei DAO, associo alle istanze
                        
                        TicketDao ticketDao = new TicketDao();
                        EventDao eventDao = new EventDao();
                        ProfileDao profileDao = new ProfileDao();


                        //utilizzo di un Istanza del PaymentFactor e associo a un iPaymentAdapter
                        MasterPayPayment masterPayPayment = new MasterPayPayment();
                        iPaymentAdapter = PaymentFactory.getMasterPayAdapter(masterPayPayment);


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

                                System.out.println("updatePoints executed");
                            } catch (SQLException e) {
                                throw new SQLException("Problems with ticket Update", e);
                            }
                            //update del numero dei posti
                            try {
                                eventDao.updateSeatsNumber(ConnectedUser.getInstance().getEventForTicket());

                                System.out.println("updateSeats executed");
                            } catch (SQLException e) {
                                throw new SQLException("Problems with Update Seats Number", e);
                            }
                        }
                        // controllo e inserimento eventuale Notifica
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
                    Manager managerlogged = (Manager) user;  //cast dello user in Manager

                    ProfileDao profiledao = new ProfileDao();
                    SubscriptionHandlerFactory.getInstance().getSubscriptionHandler().buySub(managerlogged, ConnectedUser.getInstance().getNewSubLevel(), PaymentFactory.getMasterPayAdapter(new MasterPayPayment()), paymentSelectionView.getPrice());

                    // Update del Valore Subcription del Manager
                    try {
                        profiledao.updateManagerCreditCard(managerlogged,paymentDataMView.getInsertNC().getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    // Controllo sull'iscrizione del manager eseguo solo se è scaduto l'abbonament e il manager vuole acquistarne uno nuovo
                    if (managerlogged.getSubscription() != -1) {
                        try {
                            profiledao.updateManagerSub(managerlogged);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Payment Failed");
                    }


                    //
                    if (home != null) {  // Utilizzo ManagerView esistente
                        UpperBar.getIstance().setForManager();
                        home.reSetBars();
                        ManagerView managerView = (ManagerView) home;
                        managerView.updateSubLabels(managerlogged.getSubscription(), managerlogged.getCounterCreatedEvents());
                        ManagerController managerController = new ManagerController(mainStage, managerView, ConnectedUser.getInstance().getLoginView());                        
                        mainStage.setScene(managerView);
                    } else {  //Creo una nuova ManagerView
                        UpperBar.getIstance().setForManager();
                        ManagerView managerView = new ManagerView(managerlogged.getName(), managerlogged.getNotification(), managerlogged.getEventlist(), managerlogged.getSubscription(), managerlogged.getCounterCreatedEvents());
                        ConnectedUser.getInstance().setHome(managerView);
                        ManagerController managerController = new ManagerController(mainStage, managerView, ConnectedUser.getInstance().getLoginView());
                        mainStage.setScene(managerView);
                    }

                }

                //FINE MANAGER
            }
        };

        paymentDataMView.getNextButton().setOnMouseClicked(goToNewPage);


        //-----Controllo inserimento valori per i campi della View-----//

        addCharacterLimit(paymentDataMView.getInsertNC(), 16);
        addCharacterLimit(paymentDataMView.getInsertMM(), 2);
        addCharacterLimit(paymentDataMView.getInsertYY(), 2);
        addCharacterLimit(paymentDataMView.getInsertcvc(), 4);


    }


    /**
     * This method allows to set a maximum value of characters that can be inserted in the view fields
     * @param textField
     * @param limit
     */
    private void addCharacterLimit(TextField textField, int limit) {  // metodo che mi permette di avere un limite sui textfields
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null && newValue.length() > limit) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    /**
     * This method sets the Round Button inserted in the View not visible, if the user is a {@link Manager}
     */
    public void setLabelforWavePoints() {
        if (!user.isCustomer()) {
            paymentDataMView.getUsePointsButton().setVisible(false);
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
     *Method that checks whether the string passed as a parameter is numeric or empty
     * @param str
     * @return {@link Boolean} true if is exist, is not empty and contains only digits
     */
    private boolean isNumericAndNotEmpty(String str) {
        return str != null && !str.isEmpty() && str.matches("\\d+");
    }

    /**
     * The method checks the characters and numbers to insert for each field and sets the error labels
     * @return true if all fields are ok.
     */
    private boolean checkNumericFields() {
        boolean valid = true;

        if (!isNumericAndNotEmpty(paymentDataMView.getInsertNC().getText()) || paymentDataMView.getInsertNC().getText().length() != 16) {
            paymentDataMView.getErrorLabelNC().setVisible(true);
            valid = false;
        } else {
            paymentDataMView.getErrorLabelNC().setVisible(false);
        }

        if (!isNumericAndNotEmpty(paymentDataMView.getInsertMM().getText()) || paymentDataMView.getInsertMM().getText().length() != 2) {
            paymentDataMView.getErrorLabelMM().setVisible(true);
            valid = false;
        } else {
            paymentDataMView.getErrorLabelMM().setVisible(false);
        }

        if (!isNumericAndNotEmpty(paymentDataMView.getInsertYY().getText()) || paymentDataMView.getInsertYY().getText().length() != 2) {
            paymentDataMView.getErrorLabelYY().setVisible(true);
            valid = false;
        } else {
            paymentDataMView.getErrorLabelYY().setVisible(false);
        }

        if (!isNumericAndNotEmpty(paymentDataMView.getInsertcvc().getText())) {
            paymentDataMView.getErrorLabelCVC().setVisible(true);
            valid = false;
        } else {
            paymentDataMView.getErrorLabelCVC().setVisible(false);
        }

       return valid;

        }


    /**
     *The method is used for the BuySub method for purchasing the ticket
     * @return 1 if the User has selected the UsePointsButton. 0 Otherwise.
     */
    public int getUsePoint() {
        int usePoint;
        if (paymentDataMView.getUsePointsButton().isSelected()) {
            usePoint = 1;
        } else {
            usePoint = 0;
        }
        return usePoint;
    }

}





