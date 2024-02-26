package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ResearchController;
import it.unipv.insfw23.TicketWave.modelView.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.LoginView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResultResearchView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CustomerController {
    private CustomerView customerView;

    public CustomerController() {

    }

 /*   private void initcomponents(){

        EventHandler<MouseEvent> searchButton = new EventHandler<>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("vai alla ricerca");
                ResearchView researchview = new ResearchView();
                ResearchController rescontroller = new ResearchController(primaryStage, researchview);
                primaryStage.setScene(researchview);
            }
        };

        customerview.getSearchButton().setOnMouseClicked(searchButton);
    } */

}
