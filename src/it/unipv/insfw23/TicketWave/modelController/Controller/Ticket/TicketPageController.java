package it.unipv.insfw23.TicketWave.modelController.Controller.Ticket;


import it.unipv.insfw23.TicketWave.modelController.Controller.Payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;

import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TicketPageController {
    private Stage mainStage;
    private TicketPageView ticketPage;

    private Event event;
    private boolean isviewermanager;


    public TicketPageController(Stage mainStage, TicketPageView ticketPage, Event event, boolean isviewermanager) {
        this.mainStage = mainStage;
        this.ticketPage = ticketPage;
        this.event=event;
        this.isviewermanager = isviewermanager;
        initComponents();

    }
public void initComponents() {
//	ticketPage = new TicketPageView();
	//assegnazione dei campi dell'evento ai campi della ticketpageview
	
	ticketPage.setComponents(isviewermanager,event.getType(),event.getName(),event.getCity(),event.getLocation(),event.getProvince(),event.getDate(),
			event.getArtists(),event.getSeatsRemainedNumberForType(),event.getPrices());
	//fine assegnazione
	//
	//cambio scena
	mainStage.setScene(ticketPage);
	
    EventHandler<MouseEvent> goToPSelectionViewHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent actionEvent) {

            // Azione da eseguire quando il pulsante viene premuto
        	if(ticketPage.getIfPriceSelected() != null) {
            System.out.println("Hai cliccato il bottone Acquista");
            PaymentSelectionView paymentSelectionView = new PaymentSelectionView();
            paymentSelectionView.setComponents(event.getPrices()[ticketPage.getWhichPriceSelected()]);
            PaymentSelectionController paymentSelectionController= new PaymentSelectionController(mainStage,paymentSelectionView,ticketPage);
            mainStage.setScene(paymentSelectionView);
        	}
        	else
        		System.out.println("non hai scelto una tipologia di biglietto");//da stampare a video o con eccezione
        }

    };
    ticketPage.getBuyButton().setOnMouseClicked(goToPSelectionViewHandler);
}



}

