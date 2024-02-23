package it.unipv.insfw23.TicketWave.modelController;


import it.unipv.insfw23.TicketWave.modelView.MainStageView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BuyTicketController {
    private Stage mainStage;
    private TicketPageView ticketPage;
    private Scene PaymentSelectionView;

    public BuyTicketController(Stage mainStage,TicketPageView ticketPage ) {
        this.mainStage = mainStage;
        this.ticketPage = ticketPage;
        addButtonListener();

    }

    private void addButtonListener() {
        Button buyButton = ticketPage.getBuyButton();
        buyButton.setOnAction(event -> {
            // Azione da eseguire quando il pulsante viene premuto
            System.out.println("Hai cliccato il bottone Acquista");
            // Cambia la scena per passare a PaymentSelectionView
            goToPaymentSelectionView();
        });
    }

     private void goToPaymentSelectionView(){
         PaymentSelectionView paymentSelectionView = new PaymentSelectionView();
         // Crea il controller per la vista di selezione del pagamento
         mainStage.setScene(paymentSelectionView);
        }
    }