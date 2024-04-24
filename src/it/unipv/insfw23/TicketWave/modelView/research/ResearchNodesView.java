package it.unipv.insfw23.TicketWave.modelView.research;
/*****************************
 QUI VA TUTTO, DEVO ABBELLIRE I TASTI
 ****************************/
// import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.GenreFilterController;
// import it.unipv.insfw23.TicketWave.modelController.ResearchCaseController.ProvinceFilterController;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    private TableView<Event> table;
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

        Menu genre = new Menu("_Generi"); // con l'underscore davanti se premo ALT + G apre il menu dei filtri per genere
        this.genre = genre;

        Menu province = new Menu("_Provincia");
        this.province = province;

        TableView<Event> table = new TableView<>();
        this.table = table;

        // Estetica
        searchBar.setStyle("-fx-background-color: #ffff");
        searchBar.setPromptText("Enter your search...");

        searchButton.setStyle("-fx-background-color: #ffff");
        ImageView searchIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/search_glass.png");
        searchIcon.setFitHeight(18);
        searchIcon.setFitWidth(22);
        searchButton.setGraphic(searchIcon);

        bar.setStyle("-fx-background-color: #ffff");
        genre.setStyle("-fx-background-color: #ffff");
        genre.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px;");
        province.setStyle("-fx-background-color: #ffff");
        province.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px;");

        // creo  la MenuBar con i filtri
        // check menu filtri musica
        Genre [] gnValues = Genre.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> gen = new ArrayList<>(); // stringa di generi
        for (Genre value : gnValues) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            if (value != Genre.START_THEATER) { // se la stringa è diversa dal separatore dei generi la metto nella successiva CheckBox, per cui la metto nell'array di stringhe
                gen.add(value.toString());
            }
        }
        genv = new ArrayList<CheckMenuItem>();  // array che contiene tutti i checkMenuItem da mettere nel Menu del genere

        for (String s : gen) { // Arraylist di CheckMenuItems che popolo
            CheckMenuItem cmi = new CheckMenuItem(s);
            genv.add(cmi);
        }
        genre.getItems().addAll(genv); // Creo il Menu con i CheckMenuItems da mettere dentro la MenuBar

        // check menu filtri provincia
        Province [] prValues = Province.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> pr = new ArrayList<>(); // stringa di generi
        for (Province value : prValues) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            pr.add(value.toString());
        }
        prv = new ArrayList<CheckMenuItem>(); // vettore per poter gestire i CheckMenu di province nel controller

        for (String s : pr) { // Arraylist di CheckMenuItems che popolo
            CheckMenuItem cmi = new CheckMenuItem(s);
            prv.add(cmi);
        }
        province.getItems().addAll(prv); // Creo il Menu con i CheckMenuItems da mettere dentro la MenuBar

        bar.getMenus().addAll(genre, province);
        bar.setStyle("-fx-background-color: #ffff");

        // Creo la TableView per visualizzare i risultati della ricerca, essa va nascosta fino alla prima ricerca; quando avviene la prima ricerca diventa visibile (quando clicco sul SearchButton)
        TableColumn<Event,String> tcEvent = new TableColumn<>("Evento");
        tcEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEvent.setStyle("-fx-alignment: CENTER");
        tcEvent.setSortable(false);
        tcEvent.setReorderable(false); // mi permette di disattivare il drag and drop delle colonne interne alla tableview

        TableColumn<Event,String> tcCity = new TableColumn<>("Città");
        tcCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tcCity.setStyle("-fx-alignment: CENTER");
        tcCity.setSortable(false);
        tcCity.setReorderable(false);

        TableColumn<Event,String> tcLocation = new TableColumn<>("Luogo");
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tcLocation.setStyle("-fx-alignment: CENTER");
        tcLocation.setSortable(false);
        tcLocation.setReorderable(false);

        TableColumn<Event,String> tcProvince = new TableColumn<>("Provincia");
        tcProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        tcProvince.setStyle("-fx-alignment: CENTER");
        tcProvince.setSortable(false);
        tcProvince.setReorderable(false);

        table.getColumns().addAll(tcEvent, tcCity, tcLocation, tcProvince);
        table.setVisible(false); // fino al primo click del searchButton deve rimanere non visibile
        table.getSelectionModel().setSelectionMode(null); // fino al primo click del searchButton deve rimanere non clickabile
        table.setPlaceholder(new Label("Nessun evento con quelle caratteristiche è stato trovato")); // mex che viene messo a display quando la ricerca non porta a nessun evento
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS); // mi permette di allargare la table senza creare colonne aggiuntive
        table.getStylesheets().add("it/unipv/insfw23/TicketWave/CSS/researchTableViewStyle.css"); // estetica della tableView

        // esempio al volo da mettere nella table view
        LocalDate data = LocalDate.now();
        ArrayList<Event> arraylistevent = new ArrayList<>();
        Manager managerfinto = new Manager("paolo","brosio","2000-12-30","paobro@gmail.com","passwd",Province.AGRIGENTO, "23245234324", arraylistevent,5,1,data,4);
        int intvett[] = {2,5,10};
        int vett [] = {200, 3000, 20};
        double price[] = {30, 50, 10};
        Time time = null;

        ObservableList<Event> evs = FXCollections.observableArrayList(
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui"),
                new Concert(23,"ER MEGLIO","PAVIA","MAGAZZINI GENERALI", data, time, Province.PAVIA, Genre.HOUSE, Type.CONCERT,500, 3, intvett, vett, price, managerfinto,"paolo", "paolo è qui")
        );

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        table.setItems(evs);

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

    public TableView<Event> getTable() {
        return table;
    }

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

}
