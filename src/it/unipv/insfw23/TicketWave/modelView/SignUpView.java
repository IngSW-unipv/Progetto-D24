package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelDomain.event.Province;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class SignUpView extends Scene {

    private static Label nameLabel = new Label("Nome");
    private static Label surnameLabel = new Label("Cognome");
    private static Label emailLabel = new Label("Email");
    private static Label confirmEmailLabel = new Label("Conferma Email");
    private static Label passwordLabel = new Label("Password:");
    private static Label confirmPasswordLabel = new Label("Conferma password:");
    private static Label dateLabel = new Label("Data di nascita");
    private static Label provinceLabel = new Label("Comune di residenza");
    private BorderPane layout ;
    private GridPane grid;
    private Button signUpButton = new Button("Registrati");
    private Button backButton = new Button("Torna indietro");
    private RadioButton managerRadioButton;
    private RadioButton customerRadioButton;



    public SignUpView(){
        super(new BorderPane(),1080,600);
        initComponents();
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

    private void initComponents() {

        // creazione borderpane e setto upper e lower bar

        BorderPane layout= (BorderPane) getRoot();
        layout.setStyle("-fx-background-color: #def1fa;");
        this.layout= layout;



        layout.setBottom(LowerBar.getInstance());
        layout.setTop(UpperBar.getIstance());

        // creazione griglia dei campi di iscrizione

        GridPane grid = new GridPane();
        this.grid=grid;

        layout.setCenter(grid); // posiziono griglia nel layout

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #def1fa;");

        Font labelFont = Font.font("Arial", 18); // imposto font di tutta la pagina


        nameLabel.setFont(labelFont);
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameField = new TextField();
        GridPane.setConstraints(nameField, 1, 1);


        surnameLabel.setFont(labelFont);
        GridPane.setConstraints(surnameLabel, 2, 1);
        TextField surnameField = new TextField();
        GridPane.setConstraints(surnameField, 3, 1);


        emailLabel.setFont(labelFont);
        GridPane.setConstraints(emailLabel, 0, 3);
        TextField emailField = new TextField();
        GridPane.setConstraints(emailField, 1, 3);


        confirmEmailLabel.setFont(labelFont);
        GridPane.setConstraints(confirmEmailLabel, 2, 3);
        TextField confirmEmailField = new TextField();
        GridPane.setConstraints(confirmEmailField, 3, 3);


        passwordLabel.setFont(labelFont);
        GridPane.setConstraints(passwordLabel, 0, 4);
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 4);


        confirmPasswordLabel.setFont(labelFont);
        GridPane.setConstraints(confirmPasswordLabel, 2, 4);
        PasswordField confirmPasswordField = new PasswordField();
        GridPane.setConstraints(confirmPasswordField, 3, 4);


        dateLabel.setFont(labelFont);
        GridPane.setConstraints(dateLabel, 0, 2);

        DatePicker datePicker = new DatePicker();
        GridPane.setConstraints(datePicker, 1, 2);


        provinceLabel.setFont(labelFont);
        GridPane.setConstraints(provinceLabel, 2, 2);
        ComboBox<Province> residenceComboBox = new ComboBox<>();
        residenceComboBox.getItems().addAll(Province.values());
        GridPane.setConstraints(residenceComboBox, 3, 2);



        ToggleGroup accountTypeToggleGroup = new ToggleGroup();

        RadioButton customerRadioButton = new RadioButton("Cliente");
        this.customerRadioButton=customerRadioButton;
        customerRadioButton.setFont(Font.font("Arial", 14));
        customerRadioButton.setToggleGroup(accountTypeToggleGroup);
        customerRadioButton.setSelected(true);
        GridPane.setConstraints(customerRadioButton, 0, 0);

        RadioButton managerRadioButton = new RadioButton("Gestore");
        this.managerRadioButton=managerRadioButton;
        managerRadioButton.setFont(Font.font("Arial", 14));
        managerRadioButton.setToggleGroup(accountTypeToggleGroup);
        GridPane.setConstraints(managerRadioButton, 1, 0);


        GridPane.setConstraints(signUpButton, 1, 5);


        GridPane.setConstraints(backButton,2,5);

        // controllo sulle password
        Label errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        GridPane.setColumnSpan(errorLabel, 2);
        GridPane.setConstraints(errorLabel, 2, 5);

       /* signUpButton.setOnAction(event -> {
            // Check if passwords match
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                errorLabel.setText("Le password non corrispondono");
            } else {
                // Perform sign-up action
                errorLabel.setText(""); // Clear error message
                // Your sign-up logic here...
            }
        });*/


        grid.getChildren().addAll(
                nameLabel, nameField, surnameLabel, surnameField, dateLabel, datePicker,
                emailLabel, emailField, confirmEmailLabel, confirmEmailField,
                passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField,
                provinceLabel, residenceComboBox,
                customerRadioButton, managerRadioButton, signUpButton,backButton
        );



        //Scene scene = new Scene(root, 800, 600);

       /* Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TicketWave");
        primaryStage.show(); */
    }
    public void reSetBars(){
        BorderPane temp = new BorderPane();
        setRoot(temp);
        layout.setTop(UpperBar.getIstance());
        layout.setCenter(grid);
        layout.setBottom(LowerBar.getInstance());
        setRoot(layout);
    }


}