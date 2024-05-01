package it.unipv.insfw23.TicketWave.modelController.controller.ticket;


import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
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

    private User user;


    public TicketPageController(Stage mainStage, TicketPageView ticketPage, Event event, User user) {
        this.mainStage = mainStage;
        this.ticketPage = ticketPage;
        this.event=event;
        this.user = user;
        initComponents();

    }
public void initComponents() {
//	ticketPage = new TicketPageView();
	//assegnazione dei campi dell'evento ai campi della ticketpageview
	
	ticketPage.setComponents(user.isCustomer(), event.getType(),event.getName(),event.getCity(),event.getLocation(),event.getProvince(),event.getDate(),
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
            paymentSelectionView.setPriceComponent(event.getPrices()[ticketPage.getWhichPriceSelected()]);
            PaymentSelectionController paymentSelectionController= new PaymentSelectionController(mainStage,paymentSelectionView,ticketPage,user);
            mainStage.setScene(paymentSelectionView);
        	}
        	else
        		System.out.println("non hai scelto una tipologia di biglietto");//da stampare a video o con eccezione
        }

    };
    ticketPage.getBuyButton().setOnMouseClicked(goToPSelectionViewHandler);
}



}

