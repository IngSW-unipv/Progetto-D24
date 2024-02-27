package it.unipv.insfw23.TicketWave.modelController;



import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import it.unipv.insfw23.TicketWave.modelView.PaymentSelectionView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;


public class BuyTicketController {
    private Stage mainStage;
    private TicketPageView ticketPage;

    private Event event;


    public BuyTicketController(Stage mainStage,TicketPageView ticketPage, Event event) {
        this.mainStage = mainStage;
        this.ticketPage = ticketPage;
        this.event=event;
        initComponents();

    }
public void initComponents() {
    EventHandler<MouseEvent> goToPSelectionViewHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent actionEvent) {

            // Azione da eseguire quando il pulsante viene premuto
            System.out.println("Hai cliccato il bottone Acquista");
            PaymentSelectionView paymentSelectionView = new PaymentSelectionView();
            PaymentSelectionController paymentSelectionController= new PaymentSelectionController(mainStage,paymentSelectionView,ticketPage);
            mainStage.setScene(paymentSelectionView);
        }

    };
    ticketPage.getBuyButton().setOnMouseClicked(goToPSelectionViewHandler);
}



}

