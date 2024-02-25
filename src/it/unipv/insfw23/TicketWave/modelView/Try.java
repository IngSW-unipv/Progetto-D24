package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelController.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Try extends Application {
    private static Label nameLabel = new Label("Nome");
    private static Label surnameLabel = new Label("Cognome");
    private static Label emailLabel = new Label("Email");
    private static Label confirmEmailLabel = new Label("Conferma Email");
    private static Label passwordLabel = new Label("Password:");
    private static Label confirmPasswordLabel = new Label("Conferma password:");
    private static Label dateLabel = new Label("Data di nascita");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();


        layout.setBottom(LowerBar.getInstance());
       layout.setTop(UpperBar.getIstance());

        GridPane grid = new GridPane();

       // layout.setCenter(grid); // posiziono griglia nel layout

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Font labelFont = Font.font("Arial", 10); // imposto font di tutta la pagina


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

        Label provinceLabel = new Label("Comune di residenza");
        provinceLabel.setFont(labelFont);
        GridPane.setConstraints(provinceLabel, 2, 2);
        ComboBox<String> residenceComboBox = new ComboBox<>();
        residenceComboBox.getItems().addAll(
                "Roma",
                "Milano",
                "Pavia",
                "Torino",
                "Firenze"
        );
        GridPane.setConstraints(residenceComboBox, 3, 2);



        ToggleGroup accountTypeToggleGroup = new ToggleGroup();

        RadioButton customerRadioButton = new RadioButton("Cliente");
        customerRadioButton.setFont(Font.font("Arial", 14));
        customerRadioButton.setToggleGroup(accountTypeToggleGroup);
        customerRadioButton.setSelected(true);
        GridPane.setConstraints(customerRadioButton, 0, 0);

        RadioButton managerRadioButton = new RadioButton("Gestore");
        managerRadioButton.setFont(Font.font("Arial", 14));
        managerRadioButton.setToggleGroup(accountTypeToggleGroup);
        GridPane.setConstraints(managerRadioButton, 1, 0);

        Button signUpButton = new Button("Registrati");
        GridPane.setConstraints(signUpButton, 1, 5);
        grid.getChildren().addAll(
                nameLabel, nameField, surnameLabel, surnameField, dateLabel, datePicker,
                emailLabel, emailField, confirmEmailLabel, confirmEmailField,
                passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField,
                provinceLabel, residenceComboBox,
                customerRadioButton, managerRadioButton, signUpButton
        );
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);


        primaryStage.setTitle("TicketWave");


        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");

       // LoginController loginController = new LoginController(primaryStage, signUpView, customerView, this);
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }
}
