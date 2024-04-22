package it.unipv.insfw23.TicketWave.modelView.ticket;
import it.unipv.insfw23.TicketWave.modelController.Controller.BuyTicketController;
import it.unipv.insfw23.TicketWave.modelController.Controller.Ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelController.Controller.UpperBar.UpperBarController;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ProvaRunTicketPageView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Esempio di dati per testare la visualizzazione della TicketPageView
        boolean isViewManager = false;
        Type typeOfEvent=Type.CONCERT;
        String name = "Pippo Franco";
        String city = "New York";
        String location = "Madison Square Garden";
        LocalDate date = LocalDate.of(2024, 4, 20);
        String artists = "Artist 1, Artist 2";
        LocalTime time = LocalTime.of(18, 30, 0);
        Genre  genre=Genre.ROCK;
        int[] seatsRemainedNumberForType = {100, 50, 20};
        int [] ticketSoldNumberForType={10,20,5};// Base, Premium, VIP
        double[] prices = {50.0, 100.0, 200.0};
        private ArrayList<Event> events = new ArrayList<>();
        Manager creator=new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", 1, "1234567890123456", events, 5, 1, LocalDate.now(), 0);

        //tipo di evento esempio per il TicketPageController
        Concert concert=new Concert(1,"Concerto Pippo Franco","New York","Madison Square Garden",date,time,Province.AGRIGENTO,genre,typeOfEvent,100,2,seatsRemainedNumberForType,ticketSoldNumberForType,prices,creator,"Artist1, Artist2","description");

        // Base, Premium, VIP

        // Creazione della TicketPageView e inizializzazione dei componenti
        TicketPageView ticketPageView = new TicketPageView();
        TicketPageController ticketPageController=new TicketPageController(primaryStage,ticketPageView,)
        ticketPageView.setComponents(isViewManager, typeOfEvent, name, city, location, Province.AGRIGENTO, date, artists,
                seatsRemainedNumberForType, prices);


        // Creazione della scena e impostazione del titolo della finestra
        UpperBarController ctrl = new UpperBarController(primaryStage);
        primaryStage.setTitle("TicketWave - Event Details");
        primaryStage.setScene(ticketPageView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }
}
