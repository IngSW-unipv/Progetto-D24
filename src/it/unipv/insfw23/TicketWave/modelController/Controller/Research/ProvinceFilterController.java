package it.unipv.insfw23.TicketWave.modelController.Controller.Research;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;

// EventHandler per i filtri della Provincia durante la ricerca
public class ProvinceFilterController  implements EventHandler<ActionEvent> {
    private final CheckMenuItem checkMenuItem;

    public ProvinceFilterController(CheckMenuItem checkMenuItem)  {
        this.checkMenuItem = checkMenuItem;
    }

    @Override
    public void handle(ActionEvent event) {
        // Custom logic for handling the CheckMenuItem's event
        if (checkMenuItem.isSelected()) {
            System.out.println(checkMenuItem.getText() + " is selected");
        } else {
            System.out.println(checkMenuItem.getText() + " is deselected");
        }
    }
}
