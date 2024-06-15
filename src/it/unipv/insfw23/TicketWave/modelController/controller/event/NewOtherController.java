package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewOtherView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewOtherController {
	Stage window;
	NewOtherView newotherview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public NewOtherController(Stage primarystage, NewOtherView newotherview, SelectionNewEventTypeView typeselevview) {
		window = primarystage;
		this.newotherview = newotherview;
		this.typeselevview = typeselevview;
		this.loggedmanager = (Manager) ConnectedUser.getInstance().getUser();
		initComponents();
	}
	
	public void initComponents() {

		EventHandler<MouseEvent> photoChooser = new EventHandler<>() {
			@Override
			public void handle(MouseEvent event) {
				FileChooser filechooser = new FileChooser();
				filechooser.setTitle("Seleziona un'immagine");
				File file = filechooser.showOpenDialog(window);

				if(file != null){
					Image photo = new Image(file.toURI().toString());
					newotherview.getPhotoView().setImage(photo);
				}
			}
		};
		newotherview.getPhotoButton().setOnMouseClicked(photoChooser);
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
				
			}
		};
		
		newotherview.getAbortButton().setOnMouseClicked(abortButton);
		
		EventHandler<MouseEvent> forwardButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				
			}
		};
		
		newotherview.getConfirmButton().setOnMouseClicked(forwardButton);
	}
}
