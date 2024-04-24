package it.unipv.insfw23.TicketWave.modelView.research;

/*****************************************
 QUI VA TUTTO, DEVO ABBELLIRE LA DISPOSIZIONE
 *****************************************/

import com.sun.javafx.scene.control.GlobalMenuAdapter;
// import it.unipv.insfw23.TicketWave.modelController.MainController;
import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.CheckMenuItem;

import java.awt.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import static javafx.application.Application.launch;

// Estende Scene, in maniera da poter visualizzare i nodes comuni della ResearchNodesView
public class ResearchView extends Scene{
    private Button searchButton;
    private MenuBar bar;
    private Menu genre;
    private Menu province;
    private ArrayList<CheckMenuItem> prv; // vettore per poter gestire i CheckMenu di province nel controller
    private ArrayList <CheckMenuItem> genv; // vettore per poter gestire i CheckMenu di generi nel controller
    private TextField searchBar;
    private TableView<Event> table;

    // costruttore
    public ResearchView() {
        super(new Pane(), 1080, 600);

        // chiamo la creazione del GridPane
        researchScene();
    }

    public void researchScene() {
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
        Genre[] gnValues = Genre.values(); // ho un array con tutti i valori associati ai nomi della ENUM
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
        Province[] prValues = Province.values(); // ho un array con tutti i valori associati ai nomi della ENUM
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

        //*_____________________________________________* //
        // Inizio creazione GridPane //
        //*_____________________________________________*  //

        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color: #91BAD6");
        gp.setAlignment(Pos.TOP_CENTER);
        //gp.setGridLinesVisible(true);
        // decido qual'è la distanza da tutti i bordi
        gp.setPadding(new Insets(50,50,50,50));

        // posiziono gli elementi nel gridPane
        gp.add(bar, 0, 0, 1, 1);
        gp.add(searchBar, 1, 0, 1,1);
        gp.add(searchButton,2,0,1,1);
        gp.add(table,1,1,1,1);
        // decido di quanto spaziare gli elementi del gridPane
        gp.setHgap(20); // gap orizzontale
        gp.setVgap(20); // gap verticale

        // creo dei vincoli sulle colonne, in modo da decidere se una colonna deve essere più grande di un'altra
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(35);
        column2.setMinWidth(330.40);
        gp.getColumnConstraints().addAll(column1, column2);

        // vincoli sulle righe
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(100);
        row2.setPrefHeight(1000); // permette alla row2 (quella che contiene la tableview) di prendere tutta la lunghezza disponibile del GridPane
        gp.getRowConstraints().addAll(row1, row2);

        // allineo i vari nodi all'interno delle loro celle della gridpane
        GridPane.setHalignment(bar, HPos.CENTER);
        GridPane.setHalignment(searchBar, HPos.CENTER);
        GridPane.setHalignment(searchButton, HPos.CENTER);
        GridPane.setHalignment(table, HPos.CENTER);

        gp.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        BorderPane layout = new BorderPane();
        layout.setCenter(gp);
        layout.setBottom(LowerBar.getInstance());
        /* if ( User == Manager) {
               layout.setTop(ManagerUpperBar.getIstance());
            }  else {
                layout.setTop(CustomerUpperBar.getIstance());
            }
         */
        layout.setTop(UpperBar.getIstance());
        setRoot(layout);
    }

    // getter utili sia per la researchView che per il controller
    public Button getSearchButton() {
        return searchButton;
    }

    public Menu getGenre() {
        return genre;
    }

    public Menu getProvince() {
        return province;
    }

    public ArrayList<CheckMenuItem> getPrv() {
        return prv;
    }

    public ArrayList<CheckMenuItem> getGenv() {
        return genv;
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public TableView<Event> getTable() {
        return table;
    }
}
