package it.unipv.insfw23.TicketWave.modelController.ResearchCaseController;

import it.unipv.insfw23.TicketWave.Dao.Research.ResearchDAO;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.event.ActionEvent;

// EventHandler per i filtri della Provincia durante la ricerca
public class ProvinceFilterController  implements EventHandler<ActionEvent> {
    private final CheckMenuItem checkMenuItem;

    public ProvinceFilterController(CheckMenuItem checkMenuItem)  {
        this.checkMenuItem = checkMenuItem;
    }

    @Override
    public void handle(ActionEvent event) {
        ResearchDAO rd = new ResearchDAO();
        // Custom logic for handling the CheckMenuItem's event
        if (checkMenuItem.isSelected()) {
            System.out.println(checkMenuItem.getText() + " is selected");
        } else {
            System.out.println(checkMenuItem.getText() + " is deselected");
        }
    }
}
