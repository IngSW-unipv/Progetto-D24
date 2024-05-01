package it.unipv.insfw23.TicketWave.modelController.controller.payment;

//import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
//import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import it.unipv.insfw23.TicketWave.modelDomain.user.User;
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


public class PaymentDataMController {
    private Stage mainStage;
    private Customer customer;
    private Manager manager;
    private PaymentDataMView paymentDataPage;
    private TicketPageView ticketpage;
    private PaymentSelectionView paymentSelectionPage;
    private boolean isviewermanager;
    private User user;
    private CustomerView customerView;
    private ManagerView managerView;

    public PaymentDataMController(Stage mainStage, PaymentDataMView paymentDataPage, PaymentSelectionView paymentSelectionPage, User user ) {
        this.paymentDataPage = paymentDataPage;
        this.paymentSelectionPage = paymentSelectionPage;
        this.mainStage = mainStage;
        this.user=user;
        initComponents();
        setLabelforWavePoints();
    }


    public void initComponents() {




        EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla paymentSelectionPage");
                paymentSelectionPage.reSetBars();
                mainStage.setScene(paymentSelectionPage);
            }
        };

        paymentDataPage.getBackButton().setOnMouseClicked(turnBackPaymentPage);


        //UNA VOLTA CHE VIENE CLICCATO IL PULSANTE ACQUISTA IL PAGAMENTO VA A BUON FINE(LO RIPORTO INDIETRO E RESETTO LE BARRE
        //IN BASE AL METODO DI CONTROLLO CUSTOMER O MANAGER

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

        paymentDataPage.getForwardButton().setOnMouseClicked(goToNewPage);




        addCharacterLimit(paymentDataPage.getInsertNC(), 16);
        addCharacterLimit(paymentDataPage.getInsertMM(), 2);
        addCharacterLimit(paymentDataPage.getInsertYY(), 2);
        addCharacterLimit(paymentDataPage.getInsertcvc(), 4);


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
            paymentDataPage.getUsePointsButton().setVisible(false);
        }
    }

}




