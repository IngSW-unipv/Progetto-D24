package it.unipv.insfw23.TicketWave.modelView.research;

import it.unipv.insfw23.TicketWave.modelDomain.event.*;
import it.unipv.insfw23.TicketWave.modelDomain.event.Event;
import it.unipv.insfw23.TicketWave.modelDomain.user.ConnectedUser;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.ArrayList;

// Estende Scene, in maniera da poter visualizzare i nodes comuni della ResearchNodesView
public class ResearchView extends Scene implements IResettableScene {

    // ATTRIBUTES:
    private Button searchButton;
    private Menu genre;
    private Menu province;
    private ArrayList<CheckBox> prv; // vettore per poter gestire i CheckBox di province nel controller
    private ArrayList <CheckBox> genv; // vettore per poter gestire i CheckBox di generi nel controller
    private TextField searchBar;
    private TableView<Event> table;
    private BorderPane layout;

    // CONSTRUCTOR:
    public ResearchView() {
        super(new Pane(), 1080, 600);

        // chiamo la creazione del GridPane
        researchScene();
    }

    // PUBLIC METHODS:
    public void researchScene() {
        //__________ Istanzio gli oggetti più importanti _________//
        TextField searchBar = new TextField();
        this.searchBar = searchBar;

        Button searchButton = new Button();
        this.searchButton = searchButton;

        MenuBar bar = new MenuBar();

        Menu genre = new Menu("_Generi"); // con l'underscore davanti se premo ALT + G apre il menu dei filtri per genere
        this.genre = genre;

        Menu province = new Menu("_Provincia");
        this.province = province;

        TableView<Event> table = new TableView<>();
        this.table = table;

        BorderPane layout = new BorderPane();
        this.layout = layout;

        //__________ Estetica SearchBar, SearchButton, MenuBar e Menu _________//
        searchBar.setStyle("-fx-background-color: #ffff");
        searchBar.setPromptText("Enter your search...");

        searchButton.setStyle("-fx-background-color: #ffff");
        ImageView searchIcon = new ImageView("it/unipv/insfw23/TicketWave/modelView/imagesResources/search_glass.png");
        searchIcon.setPreserveRatio(true);
        searchIcon.setFitHeight(18);
        searchIcon.setFitWidth(22);
        searchButton.setGraphic(searchIcon);

        bar.setStyle("-fx-background-color: #ffff");
        genre.setStyle("-fx-background-color: #ffff");
        genre.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px;");
        province.setStyle("-fx-background-color: #ffff");
        province.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px;");

        //__________ Creo  la MenuBar con i filtri _________//
        ///////**** Menu filtri generi ****////////
        VBox vb1 = new VBox();
        ScrollPane sp1 = new ScrollPane();
        Genre[] gnValues = Genre.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> gen = new ArrayList<>(); // stringa di generi
        for (Genre value : gnValues) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            if (value != Genre.START_THEATER) { // se la stringa è diversa dal separatore dei generi la metto nella successiva CheckBox, per cui la metto nell'array di stringhe
                gen.add(value.toString());
            }
        }
        genv = new ArrayList<CheckBox>();  // array che contiene tutti i CheckBox da mettere nel Menu del genere

        for (String s : gen) { // Arraylist di CheckMenuItems che popolo
            //CheckMenuItem cmi = new CheckMenuItem(s);
            CheckBox cbox = new CheckBox(s);
            cbox.setStyle("-fx-background-color: #ffff");
            cbox.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px; -fx-text-fill: black;");
            genv.add(cbox);
        }
        // VBox che contiene lo ScrollPane
        vb1.getChildren().addAll(genv);
        vb1.setPrefHeight(200);
        vb1.setStyle("-fx-background-color: #ffff");
        vb1.setAlignment(Pos.CENTER_LEFT);
        // ScrollPane che contiene tutti i CheckBox
        sp1.setContent(vb1);
        sp1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp1.setStyle("-fx-background-color: #ffff");
        // CustomMenuitem che contiene lo ScrollPane, lo devo per forza usare, poiché in un Menu vanno solo MenuItem o CustomMenuItem
        CustomMenuItem cmi1 = new CustomMenuItem();
        cmi1.setContent(sp1);
        cmi1.setStyle("-fx-background-color: #ffff");
        // Creazione vera e propria del Menu
        genre.getItems().addAll(cmi1); // Creo il Menu con i CheckMenuItems da mettere dentro la MenuBar

        ///////**** Menu filtri provincia ****////////
        VBox vb = new VBox();
        ScrollPane sp = new ScrollPane();
        Province[] prValues = Province.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> pr = new ArrayList<>(); // stringa di generi
        for (Province value : prValues) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            pr.add(value.toString());
        }
        prv = new ArrayList<CheckBox>(); // vettore per poter gestire i CheckBox di province nel controller

        for (String s : pr) { // Arraylist di CheckBox che popolo
            //CheckMenuItem cmi = new CheckMenuItem(s);
            CheckBox cbox = new CheckBox(s);
            cbox.setStyle("-fx-background-color: #ffff");
            cbox.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px; -fx-text-fill: black;");
            prv.add(cbox);
        }
        // VBox che contiene lo ScrollPane
        vb.getChildren().addAll(prv);
        vb.setPrefHeight(200);
        vb.setStyle("-fx-background-color: #ffff");
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setMaxHeight(400);
        // ScrollPane che contiene tutti i CheckBox
        sp.setContent(vb);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setStyle("-fx-background-color: #ffff");
        // CustomMenuitem che contiene lo ScrollPane, lo devo per forza usare, poiché in un Menu vanno solo MenuItem o CustomMenuItem
        CustomMenuItem cmi = new CustomMenuItem();
        cmi.setContent(sp);
        cmi.setStyle("-fx-background-color: #ffff");
        // Creazione vera e propria del Menu
        province.getItems().add(cmi); // Creo il Menu con il CustomMenuItem, che contiene lo ScrollPane, che a sua volta contiene la VBox, che a sua volta contiene i vari CheckBox

        // Creazione della MenuBar con i Menu genre e province
        bar.getMenus().addAll(genre, province);
        bar.setStyle("-fx-background-color: #ffff");

        //__________ Creo la TableView per visualizzare i risultati della ricerca, essa va nascosta fino alla prima ricerca; quando avviene la prima ricerca diventa visibile (quando clicco sul SearchButton) _________//
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
        table.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css"); // estetica della tableView
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        //*_____________________________________________* //
        // Inizio creazione GridPane //
        //*_____________________________________________*  //
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color: #91BAD6");
        gp.setAlignment(Pos.TOP_CENTER);
        // decido qual'è la distanza da tutti i bordi
        gp.setPadding(new Insets(50,50,50,50));

        // posiziono gli elementi nel gridPane
        gp.add(bar, 0, 0, 1, 1);
        gp.add(searchBar, 1, 0, 1,1);
        gp.add(searchButton,2,0,1,1);
        gp.add(table,0,1,3,1);
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

        //*_____________________________________________* //
        // BorderPane che contiene le barre e il GridPane //
        //*_____________________________________________*  //
        layout.setCenter(gp);
        layout.setBottom(LowerBar.getInstance());
        layout.setTop(UpperBar.getIstance());
        setRoot(layout);
    }

    @Override
    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        //controllo per il layout inizializzato
        if (layout == null) {
            System.err.println("Layout non inizializzato correttamente!");
            return;}

        ConnectedUser cu = ConnectedUser.getInstance();
        if(cu.getUser().isCustomer()) {
            UpperBar.getIstance().setForCustomer();
        }
        else{
            UpperBar.getIstance().setForManager();
        }
        layout.setTop(UpperBar.getIstance());
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }

    // GETTER:
    public Button getSearchButton() {
        return searchButton;
    }

    public Menu getGenre() {
        return genre;
    }

    public Menu getProvince() {
        return province;
    }

    public ArrayList<CheckBox> getPrv() {
        return prv;
    }

    public ArrayList<CheckBox> getGenv() {
        return genv;
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public TableView<Event> getTable() {
        return table;
    }
}
