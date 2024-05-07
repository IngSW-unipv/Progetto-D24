package it.unipv.insfw23.TicketWave.modelController.controller.user;

import it.unipv.insfw23.TicketWave.modelController.controller.subscription.SubscriptionSelectionController;
import it.unipv.insfw23.TicketWave.modelView.subscription.SubscriptionSelectionView;
import it.unipv.insfw23.TicketWave.modelView.user.NoMoreEventsPopup;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NoMoreEventsPopupController {
    private Stage mainStage;
    private Scene managerView;
    private Button backButton;
    private Button subButton;

    public NoMoreEventsPopupController(Stage mainStage, Button backButton, Button subButton, Scene managerView) {
        this.mainStage = mainStage;
        this.managerView = managerView;
        this.backButton = backButton;
        this.subButton = subButton;
        initComponents();
    }

    private void initComponents(){

        EventHandler<MouseEvent> backButtonPopupEvent = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                NoMoreEventsPopup.getIstance().hide();
            }
        };
        backButton.setOnMouseClicked(backButtonPopupEvent);

        EventHandler<MouseEvent> goToSubButtonPopupEvent = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                NoMoreEventsPopup.getIstance().hide();
                SubscriptionSelectionView subSelectionView = new SubscriptionSelectionView();
                SubscriptionSelectionController subSelectionController = new SubscriptionSelectionController(mainStage, subSelectionView,managerView);
                mainStage.setScene(subSelectionView);
            }
        };
        subButton.setOnMouseClicked(goToSubButtonPopupEvent);
    }
}
