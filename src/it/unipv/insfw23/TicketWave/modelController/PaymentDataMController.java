package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
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
    private User user;
    private PaymentDataMView paymentDataMView;
    private TicketPageView ticketpage;
    private PaymentSelectionView paymentSelectionView;

    public PaymentDataMController(Stage mainStage, PaymentDataMView paymentDataMView, PaymentSelectionView paymentSelectionView) {
        this.paymentDataMView = paymentDataMView;
        this.paymentSelectionView = paymentSelectionView;
        this.mainStage = mainStage;
        initComponents();
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




        addCharacterLimit(paymentDataMView.getInsertNC(), 16);
        addCharacterLimit(paymentDataMView.getInsertMM(), 2);
        addCharacterLimit(paymentDataMView.getInsertYY(), 2);
        addCharacterLimit(paymentDataMView.getInsertcvc(), 4);


        if (!user.isCustomer()) {
            paymentDataMView.getUsePointsButton().setOpacity(0);
            paymentDataMView.getUsePointsButton().setDisable(true);
        } else {
            // Se l'utente è un Customer, lascia il bottone "Use Points" visibile
            paymentDataMView.getUsePointsButton().setOpacity(1);
        }

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
            customer.buyticket(    );


            // Chiudi la schermata corrente
            mainStage.close();
        }
    };

        paymentDataMView.getNextButton().setOnMouseClicked(buyTicketEventHandler);


*/







    }




