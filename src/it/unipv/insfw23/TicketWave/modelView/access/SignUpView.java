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

import java.util.ArrayList;

public class SignUpView extends Scene implements IResettableScene {

    private static Label nameLabel = new Label("Nome");
    private static Label surnameLabel = new Label("Cognome");
    private static Label emailLabel = new Label("Email");
    private static Label confirmEmailLabel = new Label("Conferma Email");
    private static Label passwordLabel = new Label("Password:");
    private static Label confirmPasswordLabel = new Label("Conferma password:");
    private static Label dateLabel = new Label("Data di nascita");
    private static TextField surnameField = new TextField();
    private static TextField confirmEmailField = new TextField();
    private static PasswordField passwordField = new PasswordField();
    private static PasswordField confirmPasswordField = new PasswordField();
    private static DatePicker datePicker = new DatePicker();
    private static Label provinceLabel = new Label("Comune di residenza");
    private static TextField nameField = new TextField();
    private static TextField emailField = new TextField();
    private static ComboBox<Province> residenceComboBox = new ComboBox<>();
    private Label genreLbel;
    private static final int MAX_SELECTIONS = 5;  // Numero massimo di selezioni consentite
    private int currentSelections = 0;            // Numero attuale di selezioni

    private BorderPane layout ;
    private GridPane grid;
    private Button signUpButton = new Button("Registrati");
    private Button backButton = new Button("Torna indietro");
    private RadioButton managerRadioButton;
    private RadioButton customerRadioButton;
   private  ToggleGroup accountTypeToggleGroup;
    private UpperBar upperBar;
    private LowerBar lowerBar;
    private Label errorLabel;
    //private CheckBox favoriteGenre;
    private ScrollPane genreScrollP;



    public SignUpView(){
        super(new BorderPane(),1080,600);
        initComponents();
    }




    private void initComponents() {

        // creazione borderpane e setto upper e lower bar

        BorderPane layout= (BorderPane) getRoot();
        layout.setStyle("-fx-background-color: #91bad6;");
        this.layout= layout;

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
        nameLabel.setFont(labelFont);
        GridPane.setConstraints(nameLabel, 0, 1);
        GridPane.setConstraints(nameField, 1, 1);

        // COGNOME UTENTE
        surnameLabel.setFont(labelFont);
        GridPane.setConstraints(surnameLabel, 2, 1);
        GridPane.setConstraints(surnameField, 3, 1);


        genreLbel = new Label(" Generi Preferiti ");
        genreLbel.setFont(labelFont);
        // Creare caselle di controllo per i generi
        VBox vb1 = new VBox();
        genreScrollP = new ScrollPane();
        Genre[] gnValues = Genre.values(); // ho un array con tutti i valori associati ai nomi della ENUM
        ArrayList<String> gen = new ArrayList<>(); // stringa di generi
        for (Genre value : gnValues) { // popolo la mia lista di generi (stringa) partendo dalla ENUM
            if (value != Genre.START_THEATER) { // se la stringa è diversa dal separatore dei generi la metto nella successiva CheckBox, per cui la metto nell'array di stringhe
                gen.add(value.toString());
            }
        }
       ArrayList<CheckBox> genv = new ArrayList<CheckBox>();  // array che contiene tutti i CheckBox da mettere nel Menu del genere

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
                updateCheckBoxesState(genv);
            });
            genv.add(favoriteGenre);
        }
        // VBox che contiene lo ScrollPane
        vb1.getChildren().addAll(genv);
        vb1.setPrefHeight(100);


        genreScrollP.setContent(vb1);
        genreScrollP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        GridPane.setConstraints(genreLbel, 4, 1);
        GridPane.setConstraints(genreScrollP, 5, 1,1,2);
        // EMAIL
        emailLabel.setFont(labelFont);
        GridPane.setConstraints(emailLabel, 0, 3);
        GridPane.setConstraints(emailField, 1, 3);

        //CONFERMA EMAIL
        confirmEmailLabel.setFont(labelFont);
        GridPane.setConstraints(confirmEmailLabel, 2, 3);
        GridPane.setConstraints(confirmEmailField, 3, 3);

        // PASSWORD
        passwordLabel.setFont(labelFont);
        GridPane.setConstraints(passwordLabel, 0, 4);
        GridPane.setConstraints(passwordField, 1, 4);

        //CONFERMA PASSWORD
        confirmPasswordLabel.setFont(labelFont);
        GridPane.setConstraints(confirmPasswordLabel, 2, 4);
        GridPane.setConstraints(confirmPasswordField, 3, 4);

        // DATA DI NASCITA
        dateLabel.setFont(labelFont);
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(datePicker, 1, 2);

        // PROVINCIA DI RESIDENZA
        provinceLabel.setFont(labelFont);
        GridPane.setConstraints(provinceLabel, 2, 2);
        residenceComboBox.getItems().addAll(Province.values());
        GridPane.setConstraints(residenceComboBox, 3, 2);



        accountTypeToggleGroup = new ToggleGroup();

        customerRadioButton = new RadioButton("Cliente");
        customerRadioButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        customerRadioButton.setToggleGroup(accountTypeToggleGroup);
        customerRadioButton.setSelected(true);
        GridPane.setConstraints(customerRadioButton, 0, 0);

        managerRadioButton = new RadioButton("Gestore");
        managerRadioButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        managerRadioButton.setToggleGroup(accountTypeToggleGroup);
        GridPane.setConstraints(managerRadioButton, 1, 0);


        GridPane.setConstraints(signUpButton, 2, 5);


        GridPane.setConstraints(backButton,1,5);


        // controllo sulle password
        errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setFont(Font.font("Helvetica", FontWeight.NORMAL, 14));
        GridPane.setColumnSpan(errorLabel, 2);
        GridPane.setConstraints(errorLabel, 3, 5);

        signUpButton.setOnAction(event -> {
            // Check se password e email corrispondono
            if (!passwordField.getText().equals(confirmPasswordField.getText()) ||!emailField.getText().equals(confirmEmailField.getText()) ) {
                errorLabel.setText("Email o password non corrispondono");
            }
        });


        grid.getChildren().addAll(
                nameLabel, nameField, surnameLabel, surnameField, dateLabel, datePicker,
                emailLabel, emailField, confirmEmailLabel, confirmEmailField,
                passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField,
                provinceLabel, residenceComboBox,
                customerRadioButton, managerRadioButton, backButton, signUpButton, genreLbel, genreScrollP,errorLabel
        );




    }
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

    public static TextField getSurnameField() {
        return surnameField;
    }

    public static TextField getConfirmEmailField() {
        return confirmEmailField;
    }

    public static PasswordField getPasswordField() {
        return passwordField;
    }

    public static PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public static DatePicker getDatePicker() {
        return datePicker; //restituisco la local date direttamente, per semplificarmi il codice nel controller
    }

    public static TextField getNameField() {
        return nameField;
    }

    public static TextField getEmailField() {
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

    public static Province getSelectedProvince() {
        return residenceComboBox.getValue();
    }

    public ScrollPane getGenreScrollP() {
        return genreScrollP;
    }
}