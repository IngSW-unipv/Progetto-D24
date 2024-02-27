package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.PaymentDataMView;
import it.unipv.insfw23.TicketWave.modelView.PaymentDataPView;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentSelectionController {

    private Stage mainStage;
    private PaymentSelectionView paymentPage;
    private TicketPageView ticketPage;
    private PaymentDataPView paymentDataPPage;

    private PaymentDataMView paymentDataMPage;

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
                if(paymentPage.getMastercardButton().isSelected()){
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Stai andando alla PaymentDataMPage");
                paymentDataMPage=new PaymentDataMView();
               PaymentDataMController paymentDataMController = new PaymentDataMController(mainStage,paymentDataMPage,paymentPage);
                mainStage.setScene(paymentDataMPage);
            } else if (paymentPage.getPaypalButton().isSelected()) {
                    System.out.println("Stai andando alla PaymentDataPPage");
                    paymentDataPPage=new PaymentDataPView();
                    PaymentDataPController paymentDataPController=new PaymentDataPController(mainStage,paymentDataPPage,paymentPage);
                    mainStage.setScene(paymentDataPPage);
                }else {
                    paymentPage.getErrmessage().setOpacity(100);
                    System.out.println("Seleziona un Metodo di Pagamento");

                }

                }
            };
        paymentPage.getNextButton().setOnMouseClicked(goToPaymentDataPage);


        EventHandler<MouseEvent> turnBackToTicketPage = new EventHandler<>() {

            @Override
            public void handle(MouseEvent actionEvent) {
                // Azione da eseguire quando il pulsante viene premuto
                System.out.println("Sei ritornato indietro alla TicketPage");
              ticketPage.reSetBarsCustomer();
                mainStage.setScene(ticketPage);
            }
        };

        paymentPage.getBackButton().setOnMouseClicked(turnBackToTicketPage);





    }
}









