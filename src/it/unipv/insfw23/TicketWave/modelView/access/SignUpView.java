package it.unipv.insfw23.TicketWave.modelView.access;

import it.unipv.insfw23.TicketWave.modelDomain.event.Genre;
import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.IResettableScene;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SignUpView extends Scene implements IResettableScene {

    private Label nameLabel ;
    private  Label surnameLabel;
    private  Label emailLabel;
    private  Label confirmEmailLabel;
    private  Label passwordLabel;
    private  Label confirmPasswordLabel;
    private  Label dateLabel;
    private  TextField surnameField;
    private  TextField confirmEmailField;
    private  PasswordField passwordField;
    private  PasswordField confirmPasswordField;
    private  DatePicker datePicker;
    private  Label provinceLabel;
    private  TextField nameField;
    private  TextField emailField;
    private  ComboBox<Province> residenceComboBox;
    private Label genreLbel;
    private  final int MAX_SELECTIONS = 5;  // Numero massimo di selezioni consentite
    private int currentSelections = 0;            // Numero attuale di selezioni
    private final LocalDate minDate = LocalDate.now().minus(18, ChronoUnit.YEARS);
    private BorderPane layout ;
    private GridPane grid;
    private Button signUpButton;
    private Button backButton;
    private RadioButton managerRadioButton;
    private RadioButton customerRadioButton;
   private  ToggleGroup accountTypeToggleGroup;
    private UpperBar upperBar;
    private LowerBar lowerBar;
    private Label errorLabel;
    //private CheckBox favoriteGenre;
    private ScrollPane genreScrollP;

    private ArrayList<CheckBox> choiceGenre;



    public SignUpView(){
        super(new BorderPane(),1080,600);
        initComponents();
    }




    private void initComponents() {

        // creazione borderpane e setto upper e lower bar

        layout= (BorderPane) getRoot();
        layout.setStyle("-fx-background-color: #91bad6;");
        

        // posizione le barre sopra e sotto
        lowerBar=LowerBar.getInstance();
        layout.setBottom(lowerBar);

        upperBar=UpperBar.getIstance();
        upperBar.setForNoLogged();
        layout.setTop(upperBar);

        // creazione griglia dei campi di iscrizione

        GridPane grid = new GridPane();
        this.grid=grid;

        layout.setCenter(grid); // posiziono griglia nel layout

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #91bad6;");

        Font labelFont = Font.font("Helvetica", FontWeight.BOLD, 18); // imposto font di tutta la pagina

        // NOME UTENTE
        nameLabel = new Label("Nome");
        nameLabel.setFont(labelFont);
        nameField = new TextField();
        GridPane.setConstraints(nameLabel, 0, 1);
        GridPane.setConstraints(nameField, 1, 1);

        // COGNOME UTENTE
        surnameLabel = new Label("Cognome");
        surnameLabel.setFont(labelFont);
        surnameField = new TextField();
        GridPane.setConstraints(surnameLabel, 2, 1);
        GridPane.setConstraints(surnameField, 3, 1);

        // GENERI PREFERITI
        genreLbel = new Label(" Generi Preferiti ");
        genreLbel.setFont(labelFont);
        // Creare caselle di controllo per i generi
        VBox vb1 = new VBox();
        genreScrollP = new ScrollPane();
        Genre[] gnValues = Genre.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> gen = new ArrayList<>(); // stringa di generi
        for (Genre value : gnValues) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            if ( value != Genre.START_OTHER && value != Genre.START_THEATER ) { // se la stringa è diversa dal separatore dei generi la metto nella successiva CheckBox, per cui la metto nell'array di stringhe
                gen.add(value.toString());
            }
        }
        choiceGenre = new ArrayList<CheckBox>();  // array che contiene tutti i CheckBox da mettere nel Menu del genere

        for (String s : gen) {  // Arraylist di CheckMenuItems che popolo
            CheckBox favoriteGenre = new CheckBox(s);
            favoriteGenre.setOnAction(event -> {
                if (favoriteGenre.isSelected()) {
                    if (currentSelections < MAX_SELECTIONS) {
                        currentSelections++;
                    } else {
                        favoriteGenre.setSelected(false);
                    }
                } else {
                    currentSelections--;
                }
                updateCheckBoxesState(choiceGenre);
            });
            choiceGenre.add(favoriteGenre);
        }
        // VBox che contiene lo ScrollPane
        vb1.getChildren().addAll(choiceGenre);
        vb1.setPrefHeight(100);


        genreScrollP.setContent(vb1);
        genreScrollP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // SETTO POSIZIONE SCROLLPANE E LABEL ASSOCIATO
        GridPane.setConstraints(genreLbel, 4, 1);
        GridPane.setConstraints(genreScrollP, 5, 1,1,2);

        // EMAIL
        emailLabel = new Label("Email");
        emailLabel.setFont(labelFont);
        emailField = new TextField();
        GridPane.setConstraints(emailLabel, 0, 3);
        GridPane.setConstraints(emailField, 1, 3);

        //CONFERMA EMAIL
        confirmEmailLabel = new Label("Conferma Email");
        confirmEmailLabel.setFont(labelFont);
        confirmEmailField = new TextField();
        GridPane.setConstraints(confirmEmailLabel, 2, 3);
        GridPane.setConstraints(confirmEmailField, 3, 3);

        // PASSWORD
        passwordLabel = new Label("Password:");
        passwordLabel.setFont(labelFont);
        passwordField = new PasswordField();
        GridPane.setConstraints(passwordLabel, 0, 4);
        GridPane.setConstraints(passwordField, 1, 4);

        //CONFERMA PASSWORD
        confirmPasswordLabel = new Label("Conferma password:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordField = new PasswordField();
        GridPane.setConstraints(confirmPasswordLabel, 2, 4);
        GridPane.setConstraints(confirmPasswordField, 3, 4);

        // DATA DI NASCITA
        dateLabel = new Label("Data di nascita");
        dateLabel.setFont(labelFont);
        datePicker = new DatePicker();
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(datePicker, 1, 2);

        //METODO PER SETTARE VISIBILI SOLE LE DATE VALIDE
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(minDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.setValue(minDate);

        // PROVINCIA DI RESIDENZA
        provinceLabel = new Label("Comune di residenza");
        provinceLabel.setFont(labelFont);
        GridPane.setConstraints(provinceLabel, 2, 2);
        residenceComboBox = new ComboBox<>();
        residenceComboBox.getItems().addAll(Province.values());
        GridPane.setConstraints(residenceComboBox, 3, 2);

        //BOTTONI DEL CLIENTE DEL GESTORE
        accountTypeToggleGroup = new ToggleGroup();

        //BOTTONE CLIENTE
        customerRadioButton = new RadioButton("Cliente");
        customerRadioButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        customerRadioButton.setToggleGroup(accountTypeToggleGroup);
        customerRadioButton.setSelected(true);
        GridPane.setConstraints(customerRadioButton, 0, 0);

        //VOTTONE GESTORE
        managerRadioButton = new RadioButton("Gestore");
        managerRadioButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        managerRadioButton.setToggleGroup(accountTypeToggleGroup);
        GridPane.setConstraints(managerRadioButton, 1, 0);

        // setto visibilità dei generi in base al tipo di utente
        accountTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == customerRadioButton) {
                genreScrollP.setVisible(true);
                genreLbel.setVisible(true);
            } else if (newValue == managerRadioButton) {
                getGenreScrollP().setVisible(false);
                genreLbel.setVisible(false);
            }
        });

        // Inizialmente la ScrollPane è visibile poiché "Cliente" è selezionato
        genreScrollP.setVisible(true);

        // Bottone registrazione
        signUpButton = new Button("Registrati");
        GridPane.setConstraints(signUpButton, 2, 5);

        //bottone ritorno
        backButton = new Button("Torna indietro");
        GridPane.setConstraints(backButton,1,5);

        // controllo sulle password
        errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        errorLabel.setVisible(false);
        GridPane.setColumnSpan(errorLabel, 2);
        GridPane.setConstraints(errorLabel, 3, 5);

        //AGGIUNGO ELEMNTI ALLA GRID
        grid.getChildren().addAll(
                nameLabel, nameField, surnameLabel, surnameField, dateLabel, datePicker,
                emailLabel, emailField, confirmEmailLabel, confirmEmailField,
                passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField,
                provinceLabel, residenceComboBox,
                customerRadioButton, managerRadioButton, backButton, signUpButton, genreLbel, genreScrollP,errorLabel
        );




    }
    // CONTROLLO IL NUMERO DI BOX SELEZIONATI
    private void updateCheckBoxesState(ArrayList<CheckBox> checkBoxes) {
        if (currentSelections >= MAX_SELECTIONS) {
            for (CheckBox checkBox : checkBoxes) {
                if (!checkBox.isSelected()) {
                    checkBox.setDisable(true);
                }
            }
        } else {
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setDisable(false);
            }
        }
    }
    // RESETTO LE BARRE
    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);

        lowerBar=LowerBar.getInstance();
        upperBar=UpperBar.getIstance();
        upperBar.setForNoLogged();

        layout.setTop(upperBar);
        layout.setBottom(lowerBar);
        layout.setCenter(grid);

        setRoot(layout);
    }

    public  TextField getSurnameField() {
        return surnameField;
    }

    public  TextField getConfirmEmailField() {
        return confirmEmailField;
    }

    public  PasswordField getPasswordField() {
        return passwordField;
    }

    public  PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public  DatePicker getDatePicker() {
        return datePicker; //restituisco la local date direttamente, per semplificarmi il codice nel controller
    }

    public  TextField getNameField() {
        return nameField;
    }

    public  TextField getEmailField() {
        return emailField;
    }
    public Button getSignUpButton() {
        return signUpButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public RadioButton getManagerRadioButton() {
        return managerRadioButton;
    }

    public RadioButton getCustomerRadioButton() {
        return customerRadioButton;
    }

    public  Province getSelectedProvince() {
        return residenceComboBox.getValue();
    }

    public ScrollPane getGenreScrollP() {
        return genreScrollP;
    }
    public Genre[] getSelectedGenres() {
        ArrayList<Genre> selectedGenres = new ArrayList<>();  //creo un arraylist tempraneo per i generi selezionati
        for (CheckBox checkBox : choiceGenre) {  //itero lungo tutti gli elementi della choiceGenre
            if (checkBox.isSelected()) {
                selectedGenres.add(Genre.valueOf(checkBox.getText()));  // se è selezionato inserisco nell'array prendendone il valore dalla checkbox e convertendone il valore in genere
            }
        }
        return selectedGenres.toArray(new Genre[0]);  // converto l'arraylist in un array di generi
    }
    // CHECK SU CORRETTEZZA CHE I TEXTFIELDS SIANO UGUALI
    public boolean checkEqualEmailAndPassword(){
        if (!passwordField.getText().equals(confirmPasswordField.getText()) || !emailField.getText().equals(confirmEmailField.getText())){
            return false;
        }else{
            return true ;
        }
    }
    public void setErrorLabel(String message){
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
    // CONTROLLO SE ALMENO UN CHECKBOX è SELEZIONATO
    private boolean isAnyCheckBoxSelected() {
        for (CheckBox checkBox : choiceGenre) {
            if (checkBox.isSelected() && customerRadioButton.isSelected()) {
                return true;
            }
        }
        return false;
    }
    // CHECK SE I CAMPI SONO VUOTI
    public boolean checkFieldsEmpty(){
        if(getNameField().getText()== null || getSurnameField().getText()== null || getEmailField().getText()==null || getConfirmEmailField().getText()== null ||
                getPasswordField().getText()== null || getConfirmPasswordField().getText()==null || getDatePicker().getValue() == null || getSelectedProvince()==null  ){
            return true;
        }else{
            return  false;
        }
    }

}

