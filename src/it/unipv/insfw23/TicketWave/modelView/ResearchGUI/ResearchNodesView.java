package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;
/*****************************
        QUI VA TUTTO, DEVO ABBELLIRE I TASTI
 ****************************/
import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.GenreFilterController;
import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ProvinceFilterController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ResearchNodesView extends Node { //  questo mi serve per avere solo uno di ogni nodes, in tal modo ho tutti i button,... comuni tra le scene ResearchView e ResultResearchView

        // variabile che memorizza l'unica istanza
        private Button searchButton;
        private MenuBar bar;
        private Menu genre;
        private Menu province;
        private ArrayList <CheckMenuItem> prv; // vettore per poter gestire i CheckMenu di province nel controller
        private ArrayList <CheckMenuItem> genv; // vettore per poter gestire i CheckMenu di generi nel controller
        private TextField searchBar;
        private static ResearchNodesView istance;
        // costruttore privato per singleton
        private ResearchNodesView() {
            // Creazione bottoni,... in comune per la ResearchView
            TextField searchBar = new TextField();
            this.searchBar = searchBar;

            Button searchButton = new Button();
            this.searchButton = searchButton;

            MenuBar bar = new MenuBar();
            this.bar = bar;

            Menu genre = new Menu("Generi");
            this.genre = genre;

            Menu province = new Menu("Provincia");
            this.province = province;

            // Estetica
            searchBar.setStyle("-fx-background-color: #ffff");
            searchBar.setPromptText("Enter your search...");

            searchButton.setStyle("-fx-background-color: #ffff");
            ImageView searchIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/search_glass.png");
            searchIcon.setFitHeight(25);
            searchIcon.setFitWidth(29);
            searchButton.setGraphic(searchIcon);

            bar.setStyle("-fx-background-color: #ffff");
            genre.setStyle("-fx-background-color: #80C1E2");
            province.setStyle("-fx-background-color: #80C1E2");

            // creo  la MenuBar con i filtri
            // check menu filtri musica
            genv = new ArrayList<CheckMenuItem>(); // vettore per poter gestire i CheckMenu di generi nel controller
            String [] gen = {"ROCK", "PUNK"}; // stringa di generi

            for (String s : gen) { // Arraylist di CheckMenuItems che popolo
                CheckMenuItem cmi = new CheckMenuItem(s);

                GenreFilterController gfc = new GenreFilterController(cmi); // Controller per vedere se viene selezionato o meno un certo CheckMenuBox, cioè un genere in questo caso
                cmi.setOnAction(gfc);

                genv.add(cmi);
            }
            genre.getItems().addAll(genv); // Creo il Menu con i CheckMenuItems da mettere dentro la MenuBar

            // check menu filtri provincia
            prv = new ArrayList<CheckMenuItem>(); // vettore per poter gestire i CheckMenu di province nel controller
            String [] pr = {"PAVIA", "PARMA", "MILANO"}; // stringa delle province

            for (String s : pr) { // Arraylist di CheckMenuItems che popolo
                CheckMenuItem cmi = new CheckMenuItem(s);

                ProvinceFilterController pfc = new ProvinceFilterController(cmi); // Controller per vedere se viene selezionato o meno un certo CheckMenuBox, cioè una provincia in questo caso
                cmi.setOnAction(pfc);

                prv.add(cmi);
            }
            province.getItems().addAll(prv); // Creo il Menu con i CheckMenuItems da mettere dentro la MenuBar

            bar.getMenus().addAll(genre, province);
            bar.setStyle("-fx-background-color: #ffff");
        }

        //Metodo statico per ottenere l'unica istanza
        public static ResearchNodesView getIstance(){
            if(istance == null){
                istance = new ResearchNodesView();
            }
            return istance;
        }

    public MenuBar getBar() {
        return bar;
    } // usato dalla ResultResearchView
    public TextField getSearchBar() {
        return searchBar;
    } // lo chiamo nel ResearchController
    public Button getSearchButton() { return searchButton; } // lo chiamo nel ResearchController

    public ArrayList<CheckMenuItem> getPrv() {
        return prv;
    } // lo chiamo nel ResearchController

    public ArrayList<CheckMenuItem> getGenv() {
        return genv;
    } // lo chiamo nel ResearchController

    public Menu getGenre() {
        return genre;
    }

    public Menu getProvince() {
        return province;
    }

    /*
    private void handleCheckMenuItem(CheckMenuItem checkMenuItem) {
        // Custom logic for handling the CheckMenuItem's event
        if (checkMenuItem.isSelected()) {
            System.out.println(checkMenuItem.getText() + " is selected");
        } else {
            System.out.println(checkMenuItem.getText() + " is deselected");
        }
    }

     */
}

