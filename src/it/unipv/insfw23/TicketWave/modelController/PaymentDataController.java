package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.PaymentDataM2View;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;



public class PaymentDataController {
    private Stage mainStage;
    private PaymentDataM2View paymentDataPage;
    private PaymentSelectionView paymentPage;

    public PaymentDataController(Stage mainStage, PaymentDataM2View paymentDataPage) {
        this.paymentDataPage = paymentDataPage;
        this.mainStage = mainStage;
        initComponents();
    }


    public void initComponents() {

        EventHandler<MouseEvent> turnBackPaymentPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla paymentSelectionPage");
                PaymentSelectionView paymentPage= new PaymentSelectionView();
                PaymentSelectionController paymentSelectionController = new PaymentSelectionController(mainStage,paymentPage);
                mainStage.setScene(paymentPage);
            }
        };

        paymentDataPage.getBackButton().setOnMouseClicked(turnBackPaymentPage);




        addCharacterLimit(paymentDataPage.getInsertNC(), 16);
        addCharacterLimit(paymentDataPage.getInsertMM(), 2);
        addCharacterLimit(paymentDataPage.getInsertYY(), 2);
        addCharacterLimit(paymentDataPage.getInsertcvc(), 4);


    }

    // Metodo per aggiungere il limite di caratteri a un TextField
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










    }




