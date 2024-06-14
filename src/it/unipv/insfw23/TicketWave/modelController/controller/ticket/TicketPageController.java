package it.unipv.insfw23.TicketWave.modelController.controller.ticket;


import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.ticket.TicketType;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
import it.unipv.insfw23.TicketWave.modelDomain.user.User;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.payment.PaymentSelectionView;
import it.unipv.insfw23.TicketWave.modelView.ticket.TicketPageView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TicketPageController {
    private Stage mainStage;
    private TicketPageView ticketPage;

    private Event event;
    private boolean isviewermanager;
    private IResettableScene backScene;

    private User user = ConnectedUser.getInstance().getUser();


    public TicketPageController(Stage mainStage, TicketPageView ticketPage, Event event, IResettableScene backScene) {
        this.mainStage = mainStage;
        this.ticketPage = ticketPage;
        this.event = event;
        this.backScene = backScene;
        initComponents();

    }

    public void initComponents() {

        //assegnazione dei parametri dell'evento(dominio) ai campi della ticketpageview(GUI)
        ticketPage.setComponents(user.isCustomer(), event.getType(), event.getName(), event.getCity(), event.getLocation(), event.getProvince(), event.getDate(),
                event.getArtists(), event.getSeatsRemainedNumberForType(), event.getPrices(), event.getDescription(), event.getPhoto());

        ticketPage.getAuthorNameField().setVisible(false);
        ticketPage.getAuthorNameLabel().setVisible(false);

        //contrutto di controllo per far vedere attributi di teatro
        if(event.getType().toString()=="THEATER"){
            ticketPage.getAuthorNameField().setVisible(true);
            ticketPage.getAuthorNameLabel().setVisible(true);
            Theater theater=(Theater) event;
            ticketPage.setAuthorNameField(theater.getAuthorName());
        }
        //fine assegnazione
        //cambio scena
        mainStage.setScene(ticketPage);


        //EventHandler---passaggio view successiva
        EventHandler<MouseEvent> goToPSelectionViewHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent actionEvent) {
            	int numberOfTickets = ticketPage.getNumOfTickets();

                // Azione da eseguire quando il pulsante viene premuto
                if (ticketPage.getIfPriceSelected() != null) {
                    System.out.println("Hai cliccato il bottone Acquista");
                    PaymentSelectionView paymentSelectionView = new PaymentSelectionView();
                    paymentSelectionView.setPriceComponent((event.getPrices()[ticketPage.getWhichPriceSelected()])*numberOfTickets);
                    paymentSelectionView.setTicketNumber(numberOfTickets);
                    PaymentSelectionController paymentSelectionController = new PaymentSelectionController(mainStage, paymentSelectionView, ticketPage);
                    mainStage.setScene(paymentSelectionView);

                    ConnectedUser.getInstance().setEventForTicket(event);

                    TicketType ticketType = null;

                    if (ticketPage.getWhichPriceSelected() == 0) {
                        ticketType = TicketType.BASE;
                    } 
                    else if (ticketPage.getWhichPriceSelected() == 1) {
                        ticketType = TicketType.PREMIUM;
                    } else if (ticketPage.getWhichPriceSelected() == 2) {
                        ticketType = TicketType.VIP;
                    }
                    ConnectedUser.getInstance().setTicketType(ticketType);


                }
                 else{
                    ticketPage.getErrmessage().setOpacity(100);
                    System.out.println("Scegli una tipologia di biglietto");//stampa a video l'errore
                }

            }

        };
        ticketPage.getBuyButton().setOnMouseClicked(goToPSelectionViewHandler);

        // EventHandler---di ritorno alla scena precedente
        EventHandler<MouseEvent> goBackEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                backScene.reSetBars();
                Scene backSceneCasted = (Scene) backScene;
                mainStage.setScene(backSceneCasted);


            }
        };
        ticketPage.getBackButton().setOnMouseClicked(goBackEvent);





    }
}

