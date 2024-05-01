package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewFestivalView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewFestivalController {
	Stage window;
	NewFestivalView newfestview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public NewFestivalController(Stage primarystage, NewFestivalView newfestview, SelectionNewEventTypeView typeselevview, Manager loggedmanager) {
		window = primarystage;
		this.newfestview = newfestview;
		this.typeselevview = typeselevview;
		this.loggedmanager = loggedmanager;
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
					newfestview.getPhotoView().setImage(photo);
				}
			}
		};
		newfestview.getPhotoButton().setOnMouseClicked(photoChooser);
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
				System.out.println("34");
			}
		};
		
		newfestview.getAbortButton().setOnMouseClicked(abortButton);
	}
}
