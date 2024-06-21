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
    private ArrayList<CheckBox> provinceCheckBoxArray; // vettore per poter gestire i CheckBox di province nel controller
    private ArrayList <CheckBox> genreCheckBoxArray; // vettore per poter gestire i CheckBox di generi nel controller
    private TextField searchBar;
    private TableView<Event> resultTable;
    private BorderPane layout;

    // CONSTRUCTOR:
    public ResearchView() {
        super(new Pane(), 1080, 600);

        // chiamo la creazione della view
        researchScene();
    }

    // PUBLIC METHODS:
    public void researchScene() {
        //__________ Istanzio gli oggetti più importanti _________//
        TextField searchBar = new TextField();
        this.searchBar = searchBar;

        Button searchButton = new Button();
        this.searchButton = searchButton;

        MenuBar bar = new MenuBar(); // barra dei menu

        Menu genre = new Menu("_Generi"); // con l'underscore davanti se premo ALT + G apre il menu dei filtri per genere
        this.genre = genre;

        Menu province = new Menu("_Provincia");
        this.province = province;

        TableView<Event> resultTable = new TableView<>();
        this.resultTable = resultTable;

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
        VBox vbGenre = new VBox(); // Vbox che contiene lo scrollpane
        ScrollPane spGenre = new ScrollPane(); // scrollpane che contiene i checkbox
        Genre[] genreValuesOfEnum = Genre.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> genreStringOfEnum = new ArrayList<>(); // stringa di generi
        for (Genre value : genreValuesOfEnum) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            if (value != Genre.START_THEATER) { // se la stringa è diversa dal separatore dei generi la metto nella successiva CheckBox, per cui la metto nell'array di stringhe
                genreStringOfEnum.add(value.toString());
            }
        }
        genreCheckBoxArray = new ArrayList<CheckBox>();  // array che contiene tutti i CheckBox da mettere nel Menu del genere

        for (String s : genreStringOfEnum) { // Arraylist di CheckMenuItems che popolo
            CheckBox cbox = new CheckBox(s);
            cbox.setStyle("-fx-background-color: #ffff");
            cbox.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px; -fx-text-fill: black;");
            genreCheckBoxArray.add(cbox);
        }
        // VBox che contiene lo ScrollPane
        vbGenre.getChildren().addAll(genreCheckBoxArray);
        vbGenre.setPrefHeight(200);
        vbGenre.setStyle("-fx-background-color: #ffff");
        vbGenre.setAlignment(Pos.CENTER_LEFT);
        // ScrollPane che contiene tutti i CheckBox
        spGenre.setContent(vbGenre);
        spGenre.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spGenre.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spGenre.setStyle("-fx-background-color: #ffff");
        // CustomMenuitem che contiene lo ScrollPane, lo devo per forza usare, poiché in un Menu vanno solo MenuItem o CustomMenuItem
        CustomMenuItem cmi1 = new CustomMenuItem();
        cmi1.setContent(spGenre);
        cmi1.setStyle("-fx-background-color: #ffff");
        // Creazione vera e propria del Menu per i filtri del genere
        genre.getItems().addAll(cmi1); // Creo il Menu con i CheckMenuItems da mettere dentro la MenuBar

        ///////**** Menu filtri provincia ****////////
        VBox vbProvince = new VBox();
        ScrollPane spProvince = new ScrollPane();
        Province[] prValuesOfEnum = Province.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> provinceStringOfEnum = new ArrayList<>(); // stringa di generi
        for (Province value : prValuesOfEnum) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            provinceStringOfEnum.add(value.toString());
        }
        provinceCheckBoxArray = new ArrayList<CheckBox>(); // vettore per poter gestire i CheckBox di province nel controller

        for (String s : provinceStringOfEnum) { // Arraylist di CheckBox che popolo
            //CheckMenuItem cmi = new CheckMenuItem(s);
            CheckBox cbox = new CheckBox(s);
            cbox.setStyle("-fx-background-color: #ffff");
            cbox.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 12px; -fx-text-fill: black;");
            provinceCheckBoxArray.add(cbox);
        }
        // VBox che contiene lo ScrollPane
        vbProvince.getChildren().addAll(provinceCheckBoxArray);
        vbProvince.setPrefHeight(200);
        vbProvince.setStyle("-fx-background-color: #ffff");
        vbProvince.setAlignment(Pos.CENTER_LEFT);
        vbProvince.setMaxHeight(400);
        // ScrollPane che contiene tutti i CheckBox
        spProvince.setContent(vbProvince);
        spProvince.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spProvince.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spProvince.setStyle("-fx-background-color: #ffff");
        // CustomMenuitem che contiene lo ScrollPane, lo devo per forza usare, poiché in un Menu vanno solo MenuItem o CustomMenuItem
        CustomMenuItem cmi = new CustomMenuItem();
        cmi.setContent(spProvince);
        cmi.setStyle("-fx-background-color: #ffff");
        // Creazione vera e propria del Menu per i filtri delle province
        province.getItems().add(cmi); // Creo il Menu con il CustomMenuItem, che contiene lo ScrollPane, che a sua volta contiene la VBox, che a sua volta contiene i vari CheckBox

        // Creazione della MenuBar che contiene i Menu genre e province
        bar.getMenus().addAll(genre, province);
        bar.setStyle("-fx-background-color: #ffff");

        //__________ Creo la TableView per visualizzare i risultati della ricerca, essa va nascosta fino alla prima ricerca; quando avviene la prima ricerca diventa visibile (quando clicco sul SearchButton) _________//
        TableColumn<Event,String> tcEvent = new TableColumn<>("Evento"); // Colonna che contiene il nome dell'evento una volta estratto dal DAO
        tcEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEvent.setStyle("-fx-alignment: CENTER");
        tcEvent.setSortable(false);
        tcEvent.setReorderable(false); // mi permette di disattivare il drag and drop delle colonne interne alla tableview

        TableColumn<Event,String> tcCity = new TableColumn<>("Città"); // Colonna che contiene la città dell'evento una volta estratto dal DAO
        tcCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tcCity.setStyle("-fx-alignment: CENTER");
        tcCity.setSortable(false);
        tcCity.setReorderable(false);

        TableColumn<Event,String> tcLocation = new TableColumn<>("Luogo"); // Colonna che contiene la location dell'evento una volta estratto dal DAO
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tcLocation.setStyle("-fx-alignment: CENTER");
        tcLocation.setSortable(false);
        tcLocation.setReorderable(false);

        TableColumn<Event,String> tcProvince = new TableColumn<>("Provincia"); // Colonna che contiene la provincia dell'evento una volta estratto dal DAO
        tcProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        tcProvince.setStyle("-fx-alignment: CENTER");
        tcProvince.setSortable(false);
        tcProvince.setReorderable(false);

        resultTable.getColumns().addAll(tcEvent, tcCity, tcLocation, tcProvince); // creazione della tabella degli eventi risultanti dalla ricerca, contiene le colonne create sopra
        resultTable.setVisible(false); // fino al primo click del searchButton deve rimanere non visibile
        resultTable.getSelectionModel().setSelectionMode(null); // fino al primo click del searchButton deve rimanere non clickabile
        resultTable.setPlaceholder(new Label("Nessun evento con quelle caratteristiche è stato trovato")); // mex che viene messo a display quando la ricerca non porta a nessun evento
        resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS); // mi permette di allargare la resultTable senza creare colonne aggiuntive
        resultTable.getStylesheets().add("it/unipv/insfw23/TicketWave/css/researchTableViewStyle.css"); // estetica della tableView
        resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        //*_____________________________________________* //
        // Inizio creazione GridPane //
        //*_____________________________________________*  //
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #91BAD6");
        gridPane.setAlignment(Pos.TOP_CENTER);
        // decido qual'è la distanza da tutti i bordi
        gridPane.setPadding(new Insets(50,50,50,50));

        // posiziono gli elementi nel gridPane
        gridPane.add(bar, 0, 0, 1, 1);
        gridPane.add(searchBar, 1, 0, 1,1);
        gridPane.add(searchButton,2,0,1,1);
        gridPane.add(resultTable,0,1,3,1);
        // decido di quanto spaziare gli elementi del gridPane
        gridPane.setHgap(20); // gap orizzontale
        gridPane.setVgap(20); // gap verticale

        // creo dei vincoli sulle colonne del GridPane, in modo da decidere se una colonna deve essere più grande di un'altra
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(35);
        column2.setMinWidth(330.40);
        gridPane.getColumnConstraints().addAll(column1, column2);

        // vincoli sulle righe del GridPane
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(100);
        row2.setPrefHeight(1000); // permette alla row2 (quella che contiene la tableview) di prendere tutta la lunghezza disponibile del GridPane
        gridPane.getRowConstraints().addAll(row1, row2);

        // allineo i vari nodi all'interno delle loro celle della gridpane
        GridPane.setHalignment(bar, HPos.CENTER);
        GridPane.setHalignment(searchBar, HPos.CENTER);
        GridPane.setHalignment(searchButton, HPos.CENTER);
        GridPane.setHalignment(resultTable, HPos.CENTER);

        gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        //*_____________________________________________* //
        // BorderPane che contiene la barra superiore ed inferiore e il GridPane //
        //*_____________________________________________*  //
        layout.setCenter(gridPane);
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

    public ArrayList<CheckBox> getProvinceCheckBoxArray() {
        return provinceCheckBoxArray;
    }

    public ArrayList<CheckBox> getGenreCheckBoxArray() {
        return genreCheckBoxArray;
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public TableView<Event> getResultTable() {
        return resultTable;
    }
}
