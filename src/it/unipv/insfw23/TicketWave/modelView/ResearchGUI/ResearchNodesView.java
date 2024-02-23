package it.unipv.insfw23.TicketWave.modelView.ResearchGUI;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class ResearchNodesView extends Node { //  questo mi serve per avere solo uno di ogni nodes, in tal modo ho tutti i button,... comuni tra le scene ResearchView e ResultResearchView

        // variabile che memorizza l'unica istanza
        private Button searchButton;
        private MenuBar barra;
        private Menu genre;
        private Menu province;

        private TextField searchBar;
        private static ResearchNodesView istance;
        // costruttore privato per singleton
        private ResearchNodesView() {
            TextField searchBar = new TextField();
            this.searchBar = searchBar;

            Button searchButton = new Button();
            this.searchButton = searchButton;

            MenuBar barra = new MenuBar();
            this.barra = barra;

            Menu genre = new Menu("Generi");
            this.genre = genre;

            Menu province = new Menu("Provincia");
            this.province = province;

            searchBar.setStyle("-fx-background-color: #ffffff");
            searchBar.setPromptText("Enter your search...");

            searchButton.setStyle("-fx-background-color: #80C1E2");
            ImageView searchIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/Resources/search_glass.png");
            searchIcon.setFitHeight(25);
            searchIcon.setFitWidth(29);
            searchButton.setGraphic(searchIcon);

            // creo  la MenuBar con i filtri
            // check menu filtri musica
            CheckMenuItem cmi = new CheckMenuItem("ROCK");
            CheckMenuItem cmi1 = new CheckMenuItem("PUNK");
            // check menu filtro provincia
            CheckMenuItem pr = new CheckMenuItem("PAVIA");
            CheckMenuItem pr1 = new CheckMenuItem("PARMA");
            CheckMenuItem pr2 = new CheckMenuItem("MILANO");
            // aggiungo i miei check menu alla menu bar
            genre.getItems().addAll(cmi, cmi1);
            province.getItems().addAll(pr, pr1, pr2);
            barra.getMenus().addAll(genre, province);
            barra.setStyle("-fx-background-color: #ffffff");


        }

        //Metodo statico per ottenere l'unica istanza
        public static ResearchNodesView getIstance(){
            if(istance == null){
                istance = new ResearchNodesView();
            }
            return istance;
        }

    public TextField getSearchBar() {
        return searchBar;
    }

    public Button getSearchButton() { return searchButton; } // lo chiamo nel ResearchController

    public MenuBar getBarra() {
        return barra;
    }

    public Menu getGenre() {
        return genre;
    }

    public Menu getProvince() {
        return province;
    }
}

