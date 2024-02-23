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
                System.out.println("Sei ritornato indietro alla paymentPage");
                PaymentSelectionView paymentPage= new PaymentSelectionView();
                PaymentSelectionController paymentSelectionController = new PaymentSelectionController(mainStage,paymentPage);
                mainStage.setScene(paymentPage);
            }
        };

        paymentDataPage.getBackButton().setOnMouseClicked(turnBackPaymentPage);



    }


    }

