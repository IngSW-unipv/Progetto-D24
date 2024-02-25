package it.unipv.insfw23.TicketWave.modelController.ResearchCaseController;

import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.event.ActionEvent;

public class GenreFilterController implements EventHandler<ActionEvent> {
    private final CheckMenuItem checkMenuItem;

    public GenreFilterController(CheckMenuItem checkMenuItem)  {
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
