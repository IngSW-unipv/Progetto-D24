package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.dao.notificationDao.NotificationDao;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.NotificationHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Customer;
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
import java.util.ArrayList;
import java.util.Arrays;

public class NewConcertController {
	Stage window;
	NewConcertView view;
	SelectionNewEventTypeView typeselevview;
	Manager loggedmanager;
	ManagerView home;
	INotificationHandler notificationHandler;
	
	public NewConcertController(Stage primarystage, NewConcertView newconcview, SelectionNewEventTypeView typeselevview) {
		window = primarystage;
		this.view = newconcview;
		this.typeselevview = typeselevview;
		this.loggedmanager = (Manager) ConnectedUser.getInstance().getUser();
		this.home = (ManagerView)ConnectedUser.getInstance().getHome();
		this.notificationHandler = NotificationHandlerFactory.getIstance().getNotificationHandler();
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
				ArrayList<String> customerFavGenre = new ArrayList<>();
				ArrayList<String> customerProvince = new ArrayList<>();
				ArrayList<Notification> notifications = new ArrayList<>();
				
				try {
					EventDao eventDao = new EventDao();
					ProfileDao profileDao = new ProfileDao();
					NotificationDao notificationDao = new NotificationDao();
					//calcolo dei param da passare al metodo per la creazione nel dominio
				
					//id preso come count ella table event sul db
					int id = eventDao.selectEventNumber()+1;
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
					
					
					eventDao.insertEvent(createdConcert);
					
					profileDao.updateEventCreatedCounter(loggedmanager);
					
					customerFavGenre = profileDao.selectCustomerByGenre(createdConcert.getGenre());
					customerProvince = profileDao.selectCustomerByProvince(createdConcert.getProvince());
					
					//aggiorno il numero di notifiche presenti sul db
					notificationHandler.setCounterNotification(notificationDao.selectNotificationNumber());
					notifications = notificationHandler.sendNotificationNewEvent(createdConcert, customerProvince, customerFavGenre);
					
					for(Notification x : notifications) {
						notificationDao.insertNotification(x);
					}
					
					home.updateEvsTable(loggedmanager.getEventlist(),loggedmanager.getCounterCreatedEvents());
					home.reSetBars();
					ManagerController managerController = new ManagerController(window, home, ConnectedUser.getInstance().getLoginView());
					window.setScene(home);
					
				}catch (NumberFormatException e){
					view.getErrLabel().setVisible(true);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		};
		
		view.getConfirmButton().setOnMouseClicked(confirmButton);
	}
}
