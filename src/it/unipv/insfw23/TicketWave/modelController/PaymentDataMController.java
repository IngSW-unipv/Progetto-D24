package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
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
    private PaymentSelectionView paymentPage;

    public PaymentDataMController(Stage mainStage, PaymentDataMView paymentDataPage, PaymentSelectionView paymentPage) {
        this.paymentDataPage = paymentDataPage;
        this.paymentPage=paymentPage;
        this.mainStage = mainStage;
        initComponents();
    }


    public void initComponents() {

        EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla paymentSelectionPage");
                paymentPage.reSetBars();
                mainStage.setScene(paymentPage);
            }
        };

        paymentDataPage.getBackButton().setOnMouseClicked(turnBackPaymentPage);




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








}




