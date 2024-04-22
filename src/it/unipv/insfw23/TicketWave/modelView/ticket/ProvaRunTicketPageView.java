package it.unipv.insfw23.TicketWave.modelView.ticket;
import it.unipv.insfw23.TicketWave.modelController.Controller.UpperBar.UpperBarController;
import it.unipv.insfw23.TicketWave.modelController.controller.ticket.TicketPageController;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProvaRunTicketPageView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Esempio di dati per testare la visualizzazione della TicketPageView
        boolean isviewerManager = false;
        Type typeOfEvent = Type.CONCERT;
        String name = "Coez,Gianna Nannini";
        String city = "New York";
        String location = "Madison Square Garden";
        Time time = Time.valueOf("18:00:00");
        LocalDate date = LocalDate.of(2024, 4, 20);
        String artists = " Artist 1, Artist 2";
        int idEvent=1;
        String description="description";
        int[] seatsRemainedNumberForType = {100, 50, 20}; // Base, Premium, VIP
        int [] ticketSoldNumberForType={80,30,10};
        double[] prices = {50.0, 100.0, 200.0}; // Base, Premium, VIP
        ArrayList<Event> events = new ArrayList<>();
        Manager creator = new Manager("Giorgio", "Mastrota", "1990-01-01", "giorgiom@example.com", "eminflex", Province.COMO, "1234567890123456", events, 5, 1, LocalDate.now(), 0);


        Concert concert=new Concert(1,name,city,location,date,time,Province.AGRIGENTO, Genre.ROCK,typeOfEvent,1,3,seatsRemainedNumberForType,
        ticketSoldNumberForType,prices,creator,artists,description);




        // Creazione della TicketPageView e inizializzazione dei componenti
        TicketPageView ticketPageView = new TicketPageView();
        ticketPageView.setComponents(isviewerManager, typeOfEvent, name, city, location, Province.AGRIGENTO, date, artists,
                seatsRemainedNumberForType, prices);

        TicketPageController ticketPageController=new TicketPageController(primaryStage,ticketPageView,concert,isviewerManager);


        // Creazione della scena e impostazione del titolo della finestra
        UpperBarController ctrl = new UpperBarController(primaryStage);
        primaryStage.setTitle("TicketWave");
        primaryStage.setScene(ticketPageView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }
}
