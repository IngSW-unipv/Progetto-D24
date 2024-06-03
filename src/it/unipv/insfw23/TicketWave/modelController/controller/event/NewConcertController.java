package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewConcertView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Blob;
import java.util.Arrays;

public class NewConcertController {
	Stage window;
	NewConcertView view;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	ManagerView home;
	
	public NewConcertController(Stage primarystage, NewConcertView newconcview, SelectionNewEventTypeView typeselevview) {
		window = primarystage;
		this.view = newconcview;
		this.typeselevview = typeselevview;
		this.loggedmanager = (Manager) ConnectedUser.getInstance().getUser();
		this.home = (ManagerView)ConnectedUser.getInstance().getHome();
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
					view.getPhotoView().setImage(photo);
				}
			}
		};
		view.getPhotoButton().setOnMouseClicked(photoChooser);


		EventHandler<MouseEvent> abortButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {
				typeselevview.reSetBars();
				window.setScene(typeselevview);
			}
		};
		
		view.getAbortButton().setOnMouseClicked(abortButton);
		
		EventHandler<MouseEvent> confirmButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event){
				view.getErrLabel().setVisible(false);
				
				//System.out.println(view.getPricebasefield());
				//System.out.println(view.getTypesticket());
				
				
				
				try {
					//calcolo dei param da passare al metodo per la creazione nel dominio
				
					//id preso come count ella table event sul db
					int id = 12;//da cambiare col numero effettivo
					int maxNumOfSeats = view.getNumbasefield()+view.getNumpremiumfield()+view.getNumvipfield();
				
					int[] seatsRemainedNumberForType = new int[view.getTypesticket()];
					double[] prices = new double[view.getTypesticket()];
					switch(view.getTypesticket()) {
					case 3:
						seatsRemainedNumberForType[2] = view.getNumvipfield();
						prices[2] = view.getPricevipfield();
					case 2:
						seatsRemainedNumberForType[1] = view.getNumpremiumfield();
						prices[1] = view.getPricepremiumfield();
					case 1:
						seatsRemainedNumberForType[0] = view.getNumbasefield();
						prices[0] = view.getPricebasefield();
					}
				
					// il vettore di biglietti venduti è a 0 dato che l'eveento sta venendo creato
					int[] ticketSoldNumberForType = new int[view.getTypesticket()];
					Arrays.fill(ticketSoldNumberForType, 0);
				
					//conversione da ImageView nella view a Image
					Image photo = view.getPhotoView().getImage();
					
					Concert createdConcert = loggedmanager.createConcert(id, view.getNamefield(), view.getCityfield(), view.getAddressfield(), view.getDatepicked(), view.getTimeSelected(), view.getProvince(),
												view.getGenre(), maxNumOfSeats, view.getTypesticket(), seatsRemainedNumberForType, ticketSoldNumberForType, prices, loggedmanager, view.getArtistfield(), view.getDescription(), photo);
					
					EventDao eventDao = new EventDao();
					eventDao.insertEvent(createdConcert);
					
					
					
				}catch (NumberFormatException e){
					view.getErrLabel().setVisible(true);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				home.updateEvsTable(loggedmanager.getEventlist());
				home.reSetBars();
				window.setScene(home);
			}
		};
		
		view.getConfirmButton().setOnMouseClicked(confirmButton);
	}
}
