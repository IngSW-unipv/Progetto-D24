package it.unipv.insfw23.TicketWave.modelController.controller.ticket;


import it.unipv.insfw23.TicketWave.modelController.controller.payment.PaymentSelectionController;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
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
//	ticketPage = new TicketPageView();
        //assegnazione dei campi dell'evento ai campi della ticketpageview
        ticketPage.setComponents(user.isCustomer(), event.getType(), event.getName(), event.getCity(), event.getLocation(), event.getProvince(), event.getDate(),
                event.getArtists(), event.getSeatsRemainedNumberForType(), event.getPrices());
        //fine assegnazione
        //
        //cambio scena
        mainStage.setScene(ticketPage);

        EventHandler<MouseEvent> goToPSelectionViewHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent actionEvent) {

                // Azione da eseguire quando il pulsante viene premuto
                if (ticketPage.getIfPriceSelected() != null) {
                    System.out.println("Hai cliccato il bottone Acquista");
                    PaymentSelectionView paymentSelectionView = new PaymentSelectionView();
                    paymentSelectionView.setPriceComponent(event.getPrices()[ticketPage.getWhichPriceSelected()]);
                    PaymentSelectionController paymentSelectionController = new PaymentSelectionController(mainStage, paymentSelectionView, ticketPage);
                    mainStage.setScene(paymentSelectionView);
                } else{
                    ticketPage.getErrmessage().setOpacity(100);
                    System.out.println("Scegli una tipologia di biglietto");//da stampare a video o con eccezione
                }

            }

        };
        ticketPage.getBuyButton().setOnMouseClicked(goToPSelectionViewHandler);


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

