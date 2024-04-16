package it.unipv.insfw23.TicketWave.modelView.ticket;
import it.unipv.insfw23.TicketWave.modelController.Controller.UpperBar.UpperBarController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;

public class ProvaRunTicketPageView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Esempio di dati per testare la visualizzazione della TicketPageView
        boolean isViewManager = true;
        String typeOfEvent = "Concert";
        String name = "Pippo Franco";
        String city = "New York";
        String location = "Madison Square Garden";
        LocalDate date = LocalDate.of(2024, 4, 20);
        ArrayList<String> artists = new ArrayList<>();
        artists.add("Artist 1");
        artists.add("Artist 2");
        int[] seatsRemainedNumberForType = {100, 50, 20}; // Base, Premium, VIP
        double[] prices = {50.0, 100.0, 200.0}; // Base, Premium, VIP

        // Creazione della TicketPageView e inizializzazione dei componenti
        TicketPageView ticketPageView = new TicketPageView();
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
