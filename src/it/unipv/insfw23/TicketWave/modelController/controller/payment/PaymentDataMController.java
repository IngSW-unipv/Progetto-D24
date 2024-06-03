package it.unipv.insfw23.TicketWave.modelController.controller.payment;

import java.sql.SQLException;

import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.dao.ticketDao.TicketDao;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import it.unipv.insfw23.TicketWave.modelController.factory.payment.PaymentFactory;
import it.unipv.insfw23.TicketWave.modelController.factory.subscription.SubscriptionHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.payment.MastercardPayment;
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
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class PaymentDataMController {
    private Stage mainStage;
    private PaymentDataMView paymentDataMView;
    private TicketPageView ticketpage;
    private PaymentSelectionView paymentSelectionView;
    private boolean isviewermanager;
    private User user= ConnectedUser.getInstance().getUser();

   private  IPaymentAdapter iPaymentAdapter;


    private IResettableScene home = ConnectedUser.getInstance().getHome();

    public PaymentDataMController(Stage mainStage, PaymentDataMView paymentDataMView, PaymentSelectionView paymentSelectionView) {
        this.paymentDataMView = paymentDataMView;
        this.paymentSelectionView = paymentSelectionView;
        this.mainStage = mainStage;
        initComponents();
        setLabelforWavePoints();
    }


    public void initComponents() {




        EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla paymentSelectionPage");
                paymentSelectionView.reSetBars();
                mainStage.setScene(paymentSelectionView);
            }
        };

        paymentDataMView.getBackButton().setOnMouseClicked(turnBackPaymentPage);


        //UNA VOLTA CHE VIENE CLICCATO IL PULSANTE ACQUISTA IL PAGAMENTO VA A BUON FINE(LO RIPORTO INDIETRO E RESETTO LE BARRE
        //IN BASE AL METODO DI CONTROLLO CUSTOMER O MANAGER

        EventHandler<MouseEvent> goToNewPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("pagamento andato a buon fine stai tornando indietro alla home page!");
                if(user.isCustomer()){
                    System.out.println("Stai andando alla CustomerView");
                    try {
                        Customer customer = (Customer) user;
                        TicketDao ticketDao = new TicketDao();
                        System.out.println("ticketdao creato");

                        MastercardPayment mastercardPayment = new MastercardPayment();
                        iPaymentAdapter = PaymentFactory.getMastercardAdapter(mastercardPayment);
                        System.out.println("creati i mastercardPayment payment e interfaccia");


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

        paymentDataMView.getNextButton().setOnMouseClicked(goToNewPage);




        addCharacterLimit(paymentDataMView.getInsertNC(), 16);
        addCharacterLimit(paymentDataMView.getInsertMM(), 2);
        addCharacterLimit(paymentDataMView.getInsertYY(), 2);
        addCharacterLimit(paymentDataMView.getInsertcvc(), 4);


    }

    private void addCharacterLimit(TextField textField, int limit) {  // metodo che mi permette di avere un limite sui textfields
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > limit) {
                    textField.setText(oldValue); // Revert back to old value
                }
            }
        });
    }
/*
    EventHandler<MouseEvent> buyTicketEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent actionEvent) {
            // Azione da eseguire quando il pulsante "Next" viene premuto
            System.out.println("Hai Acquistato il biglietto");

            // Esegui l'acquisto del biglietto
            customer.buyticket(); // ???? cosa dovrei passare a questo punto??

            // Chiudi la schermata corrente
            mainStage.close();
        }
    };

        paymentDataPage.getNextButton().setOnMouseClicked(buyTicketEventHandler);

*/


    public void setLabelforWavePoints(){
        if(!user.isCustomer()){
            paymentDataMView.getUsePointsButton().setVisible(false);
        }
    }

    public int getUsePoint(){
        int usePoint;
        if(paymentDataMView.getUsePointsButton().isSelected()){
             usePoint=1;
        }
        else{
            usePoint=0;
        }
          return usePoint;
    }

}




