package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewConcertController {
	Stage window;
	NewConcertView newconcview;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	
	public NewConcertController(Stage primarystage, NewConcertView newconcview, SelectionNewEventTypeView typeselevview, Manager loggedmanager) {
		window = primarystage;
		this.newconcview = newconcview;
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
					newconcview.getPhotoView().setImage(photo);
				}
			}
		};
		newconcview.getPhotoButton().setOnMouseClicked(photoChooser);
		
		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
			}
		};
		
		newconcview.getAbortButton().setOnMouseClicked(abortButton);
		
		EventHandler<MouseEvent> confirmButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event){
				newconcview.getErrLabel().setVisible(false);
				try {
					System.out.println(newconcview.getPricebasefield());
					//Event newev = new (param presi dalla view)
					//EventDao daoevent = new EventDao
					//daoevent.insert(newev)
					//loggedmanager.addev(newev )
				
				}catch (NumberFormatException e){
					newconcview.getErrLabel().setVisible(true);
					
					
				}
			}
		};
		
		newconcview.getConfirmButton().setOnMouseClicked(confirmButton);
	}
}
