package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewTheatreView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewTheatreController {
	Stage window;
	NewTheatreView newtheatreview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public NewTheatreController(Stage primarystage, NewTheatreView newtheatreview, SelectionNewEventTypeView typeselevview) {
		window = primarystage;
		this.newtheatreview = newtheatreview;
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
					newtheatreview.getPhotoView().setImage(photo);
				}
			}
		};
		newtheatreview.getPhotoButton().setOnMouseClicked(photoChooser);
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
			}
		};
		
		newtheatreview.getAbortButton().setOnMouseClicked(abortButton);
	}
}
