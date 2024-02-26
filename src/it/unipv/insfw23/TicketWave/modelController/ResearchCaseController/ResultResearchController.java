package it.unipv.insfw23.TicketWave.modelController.ResearchCaseController;

import it.unipv.insfw23.TicketWave.modelDomain.ticket.Ticket;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResearchView;
import it.unipv.insfw23.TicketWave.modelView.ResearchGUI.ResultResearchView;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/***
 *
 * NON FUNZIONA, DA COMPLETARE
 *
 */
public class ResultResearchController {
    private Stage mainStage;
    private ResultResearchView rrv;
    public ResultResearchController(Stage mainStage, ResultResearchView rrv)  { // costruttore che prende il mainstage e la ticketpage dalla MainStageView
        this.mainStage = mainStage;
        this.rrv = rrv;
        initcomponents();
    }
    public void initcomponents() {
        // quando clicco su una riga della TableView di ResultResearchView devo andare sulla scena TicketPageView
        EventHandler<MouseEvent> openeventHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) { // logica di quando schiaccio una riga sulla table
                System.out.println(rrv.getTable().getSelectionModel().getSelectedItem());
                TicketPageView tpv = new TicketPageView();
                mainStage.setScene(tpv);
            };
        };
        rrv.getTable().setOnMouseClicked(openeventHandler);
    }

}
