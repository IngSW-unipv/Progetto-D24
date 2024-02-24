package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.PaymentDataM2View;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PaymentSelectionController {

    private Stage mainStage;
    private PaymentSelectionView paymentPage;
    private TicketPageView ticketPage;

    private PaymentDataM2View paymentDataM2View;

    public PaymentSelectionController(Stage mainStage, PaymentSelectionView PaymentPage,TicketPageView ticketPage) {
        this.mainStage = mainStage;
        this.paymentPage = PaymentPage;
        this.ticketPage=ticketPage;
        initComponents();
    }

    public void initComponents(){

        EventHandler<MouseEvent> goToPaymentDataPage= new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Stai andando alla PaymentDataPage");
                PaymentDataM2View paymentDataPage=new PaymentDataM2View();
                PaymentDataController paymentDataController = new PaymentDataController(mainStage,paymentDataPage,paymentPage);
                mainStage.setScene(paymentDataPage);
            }
        };
        paymentPage.getNextButton().setOnMouseClicked(goToPaymentDataPage);
        EventHandler<MouseEvent> turnBackToTicketPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla TicketPage");
              ticketPage.reSetBars();
                mainStage.setScene(ticketPage);
            }
        };

        paymentPage.getBackButton().setOnMouseClicked(turnBackToTicketPage);




    }
}









