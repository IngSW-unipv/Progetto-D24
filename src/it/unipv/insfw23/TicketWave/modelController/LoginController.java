package it.unipv.insfw23.TicketWave.modelController;

import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.insfw23.TicketWave.modelDomain.event.Concert;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class LoginController {

    private Stage mainstage;

    // view da considerare
    private SignUpView signUpView;
    private CustomerView customerview;
    private LoginView loginView;
    private ManagerView managerView;


    public LoginController(Stage mainstage, SignUpView signUpView, CustomerView customerview, LoginView loginView,ManagerView managerView) {
        this.mainstage = mainstage;
        this.signUpView = signUpView;
        this.customerview = customerview;
        this.loginView = loginView;
        this.managerView=managerView;

        initComponents();
    }

    public void initComponents() {


        EventHandler<ActionEvent> goToSignUpView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // Azione da eseguire quando il pulsante "Registrati" viene premuto
                System.out.println("Hai cliccato il pulsante Registrati");
                signUpView = new SignUpView();
                SignUpController signUpController = new SignUpController(mainstage, signUpView, customerview, loginView);
                signUpView.reSetBars();
                mainstage.setScene(signUpView); // Imposta la scena SignUpView sulla stage principale
            }
        };

        loginView.getRegButton().setOnAction(goToSignUpView); // Imposta l'handler sull'azione del pulsante "Registrati"

        EventHandler<ActionEvent> goToCustomerorManagerView = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (loginView.getCustomerRadioButton().isSelected()) {


                    System.out.println("Hai cliccato il pulsante Login come cliente");
                    customerview = new CustomerView();
                    CustomerController customerController = new CustomerController(mainstage,customerview,loginView);
                    customerview.reSetBars();
                    mainstage.setScene(customerview); // Imposta la scena SignUpView sulla stage principale
                }
                else if (loginView.getManagerRadioButton().isSelected()) {
                	
                	/*	modifiche fatte da Loris per simulare la creazione di un manager senza eventi, simulare la creazione di un evento 
                	 * 	da parte di questo manager e simulare aggiunta di questo evento al suo arraylist di eventi
                	 * 	cosi da lavorare con un ipotetico manager che ha pubblicato degli eventi
                	 * */
                	
                	
        				
        				ArrayList<Event> arraylistevent = new ArrayList<>();
        				LocalDate datasub = LocalDate.of(2024, 02, 25);
        				Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","password1234",Province.CAGLIARI.ordinal(), "2324523432451420", arraylistevent,5,1,datasub,0);
        				
        				int[] seduteRImasteev1 = {200,100};
        				int[] vettfalsopriceev1 = {5,320};
        				LocalDate dataev1 = LocalDate.of(2025, 03, 20);
        				ArrayList<String> arrfintoartista1 = new ArrayList<>();
        				arrfintoartista1.add("califano");
        				//Concert eventofinto1 = new Concert(12,"reunion","busto arstizio",dataev1, "via dei matti ,0", Province.LIVORNO,300,2, seduteRImasteev1, vettfalsopriceev1,Genre.INDIE,managerfinto,arrfintoartista1);
        				
        				int[] seduterimasteev2 = {100,80,12};
        				int[] vettfalsopriceev2 = {11,250,500};
        				LocalDate dataev2 = LocalDate.of(2024, 10, 02);
        				ArrayList<String> arrfintoartista2 = new ArrayList<>();
        				arrfintoartista2.add("loredana berte");
        				//Concert eventofinto2 = new Concert(12,"reunion","busto arstizio",dataev2, "via dei matti ,0", Province.LIVORNO,300,2, seduterimasteev2, vettfalsopriceev2,Genre.INDIE,managerfinto,arrfintoartista2);
        				
        				try {
							managerfinto.createConcert(12, "reunion", "busto arstizio", dataev1, "via dei matti ,o", Province.LIVORNO, 300, 2, seduteRImasteev1, vettfalsopriceev1, Genre.INDIE, managerfinto, arrfintoartista1);
							managerfinto.createConcert(14, "festino", "Rozzano", dataev2, "stadio dell'albero", Province.MILANO, 10000, 3, seduterimasteev2, vettfalsopriceev2, Genre.HOUSE, managerfinto, arrfintoartista2);
        				} catch (Exception e) {
							e.printStackTrace();
						}
                	
                	/*	
                	 * 	fine modifiche di Loris
                	 * */


                    // Azione da eseguire quando il pulsante "Login" viene premuto
                    System.out.println("Hai cliccato il pulsante Login come gestore");
                    managerView = new ManagerView();
                    managerView.setEventsforTableev(managerfinto);
                    ManagerController managerController = new ManagerController(mainstage, managerView, loginView, managerfinto);
                    managerView.reSetBars();
                    mainstage.setScene(managerView); // Imposta la scena SignUpView sulla stage principale }
                }// devi mettere managerview anche all'interno del costruttore



                // Imposta l'handler sull'azione del pulsante "Registrati"
            }

        };

        loginView.getLoginButton().setOnAction(goToCustomerorManagerView);
    }

}
