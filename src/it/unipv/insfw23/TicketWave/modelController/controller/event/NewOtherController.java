package it.unipv.insfw23.TicketWave.modelController.controller.event;

import it.unipv.insfw23.TicketWave.dao.eventDao.EventDao;
import it.unipv.insfw23.TicketWave.dao.notificationDao.NotificationDao;
import it.unipv.insfw23.TicketWave.dao.profileDao.ProfileDao;
import it.unipv.insfw23.TicketWave.exceptions.InvalidJpegFormatException;
import it.unipv.insfw23.TicketWave.modelController.controller.user.ManagerController;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.INotificationHandler;
import it.unipv.insfw23.TicketWave.modelController.factory.notifications.NotificationHandlerFactory;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Other;
import it.unipv.insfw23.TicketWave.modelDomain.notifications.Notification;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.event.NewOtherView;
import it.unipv.insfw23.TicketWave.modelView.event.SelectionNewEventTypeView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * This class represents the controller that manages all the {@link javafx.scene.control.Button} selected in {@link NewOtherView} and allows 
 * to continue in the {@link Event} creation process.
 */
public class NewOtherController {
	private Stage window;
	private NewOtherView view;
	private SelectionNewEventTypeView selNewEvTypeView;
	private Manager loggedManager;
	private ManagerView home;
	private INotificationHandler notificationHandler;
	
	/**
	 * This constructor takes as input the current UI , i.e. a {@link NewOtherView}, the previous UI, that is a {@link SelectionNewEventTypeView}, the program 
	 * Stage and calls an initialization method
	 * @param primaryStage the {@link Stage} of this program
	 * @param newOtherView a {@link NewOtherView}, the controlled one by this controller
	 * @param selNewEvTypeView a {@link SelectionNewEventTypeView}
	 */
	public NewOtherController(Stage primaryStage, NewOtherView newOtherView, SelectionNewEventTypeView selNewEvTypeView) {
		window = primaryStage;
		this.view = newOtherView;
		this.selNewEvTypeView = selNewEvTypeView;
		this.loggedManager = (Manager) ConnectedUser.getInstance().getUser();
		this.home = (ManagerView)ConnectedUser.getInstance().getHome();
		this.notificationHandler = NotificationHandlerFactory.getIstance().getNotificationHandler();
		initComponents();
	}
	
	/**
	 * This method has three {@link EventHandler}s associated with the {@link NewOtherView} buttons.
	 * 
	 * photoChooser EventHandler: if you click on the photo button it allows to load an image from your device 
	 * 
	 * abortButton EventHandler: if you click on the abort button you return to the {@link SelectionNewEventTypeView}
	 * 
	 * confirmButton EventHandler: if you click on the confirm button the data inserted in the {@link NewOtherView} are taken and processed in order to 
	 * create the {@link Other}. Then it is loaded in the database and the possible {@link Notification}s are created. After that you are moved to the 
	 * {@link ManagerView}.  
	 */
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
			public void handle(MouseEvent arg0) {
				selNewEvTypeView.reSetBars();
				window.setScene(selNewEvTypeView);				
			}
		};		
		view.getAbortButton().setOnMouseClicked(abortButton);
		
		
		EventHandler<MouseEvent> confirmButton = new EventHandler<>() {
			
			@Override
			public void handle(MouseEvent event) {				
				ArrayList<String> customerFavGenre = new ArrayList<>();
				ArrayList<String> customerProvince = new ArrayList<>();
				ArrayList<Notification> notifications = new ArrayList<>();
				
				try {
					isJPEG(view.getPhotoView().getImage());

					EventDao eventDao = new EventDao();
					ProfileDao profileDao = new ProfileDao();
					NotificationDao notificationDao = new NotificationDao();
					
					
					//calcolo dei param da passare al metodo per la creazione nel dominio				
					
					int id = eventDao.selectEventNumber()+1; //id preso come count ella table event sul db
					
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
					
					Other createdOther = loggedManager.createOther(id, view.getNamefield(), view.getCityfield(), view.getAddressfield(), view.getDatepicked(), view.getTimeSelected(), view.getProvince(),
												view.getGenre(), maxNumOfSeats, view.getTypesticket(), seatsRemainedNumberForType, ticketSoldNumberForType, prices, loggedManager, view.getArtistfield(), view.getDescription(), photo);
					
					try {
						eventDao.insertEvent(createdOther); //inserimento nel db
					}catch (Exception e){
						loggedManager.getEventlist().remove(createdOther); //se inserimento non va a buon fine si rimuove l'evento appena creato dal dominio
					}
					
					profileDao.updateEventCreatedCounter(loggedManager);
					
					customerFavGenre = profileDao.selectCustomerByGenre(createdOther.getGenre());
					customerProvince = profileDao.selectCustomerByProvince(createdOther.getProvince());
					
					//aggiorno il numero di notifiche presenti sul db
					notificationHandler.setCounterNotification(notificationDao.selectNotificationNumber());
					notifications = notificationHandler.sendNotificationNewEvent(createdOther, customerProvince, customerFavGenre);
					
					for(Notification x : notifications) {
						notificationDao.insertNotification(x);
					}
					
					home.updateEvsTable(loggedManager.getEventlist(),loggedManager.getCounterCreatedEvents());
					home.reSetBars();
					ManagerController managerController = new ManagerController(window, home, ConnectedUser.getInstance().getLoginView());
					window.setScene(home);
					
				}catch (NumberFormatException e){
					view.getErrLabel().setText("Parametri non validi");
					view.getErrLabel().setVisible(true);

				}catch (InvalidJpegFormatException e){
					view.getErrLabel().setText(e.getMessage());
					view.getErrLabel().setVisible(true);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};		
		view.getConfirmButton().setOnMouseClicked(confirmButton);
		
		
		
		addCharacterLimit(view.getNameTextField(), 60);
		addCharacterLimit(view.getCityTextField(), 60);
		addCharacterLimit(view.getArtistsTextField(), 250);
		view.getDescriptionTextArea().setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= 950 ? change: null));
	}
	
	/**
	 * This method allows to set a maximum value of characters that can be inserted in the view fields
	 * @param textField a particular {@link TextField} to limit
	 * @param limit the max number of character
	 */
	private void addCharacterLimit(TextField textField, int limit) {  // metodo che mi permette di avere un limite sui textfields
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null && newValue.length() > limit) {
					textField.setText(oldValue); // Revert back to old value
				}
			}
		});
	}

	/**
	 * This method controls if the {@link Image} object is in JPEG format or not
	 * @param fxImage a {@link Image} to check
	 * @return true if the fxImage format is JPEG
	 * @throws IOException
	 * @throws InvalidJpegFormatException this Exception is thrown when the format is not JPEG or when the fxImage is null
	 */
	public static boolean isJPEG(Image fxImage) throws IOException, InvalidJpegFormatException {
		if (fxImage != null) {
			// Converti Image di JavaFX in BufferedImage
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);

			// Scrivi il BufferedImage in un ByteArrayOutputStream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", baos);

			// Leggi i dati dall'array di byte
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ImageInputStream iis = ImageIO.createImageInputStream(bais);

			// Ottieni i lettori di immagini disponibili
			Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);

			// Controlla se il formato è JPEG
			while (imageReaders.hasNext()) {
				ImageReader reader = imageReaders.next();
				if (reader.getFormatName().equalsIgnoreCase("JPEG")) {
					return true;
				}
			}
			throw new InvalidJpegFormatException();
		}

		throw new InvalidJpegFormatException();
    }
}
