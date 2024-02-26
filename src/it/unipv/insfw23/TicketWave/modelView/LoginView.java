package it.unipv.insfw23.TicketWave.modelView;

import it.unipv.insfw23.TicketWave.modelController.LoginController;
import it.unipv.insfw23.TicketWave.modelController.SignUpController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import javafx.scene.text.Font;





public class LoginView extends Application {
    private Button loginButton = new Button("Login");
    private Button regButton = new Button("Registrati");
    private SignUpView signUpView= new SignUpView();
    private CustomerView customerView= new CustomerView();
    private ManagerView managerView= new ManagerView();
    private BorderPane root ;
    private Scene scene ;
    private GridPane grid = new GridPane();
    private  RadioButton customerRadioButton;
    private RadioButton managerRadioButton;



    @Override
    public void start(Stage primaryStage) throws Exception{

        root= new BorderPane();

        root.setStyle("-fx-background-color: #def1fa;");


        GridPane grid= new GridPane();
        this.grid=grid;

        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);
       // grid.setStyle("-fx-background-color: White;");




        UpperBar upperBar= UpperBar.getIstance();
        LowerBar lowerBar = LowerBar.getInstance();
        root.setBottom(lowerBar);
        root.setTop(upperBar);
        root.setCenter(grid);




        // imposto campo email
        Label emailnameLabel = new Label("Email:");
        emailnameLabel.setFont(Font.font("Arial", 20));
        GridPane.setConstraints(emailnameLabel, 0, 1);
        TextField emailField = new TextField();
        GridPane.setConstraints(emailField, 1, 1);

        // Imposto camp password
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 20));
        GridPane.setConstraints(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 2);

        Label signupLabel = new Label("Non sei ancora iscritto ?");
        signupLabel.setFont(Font.font("Arial", 14));
        GridPane.setConstraints(signupLabel, 0, 6);


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


        GridPane.setConstraints(loginButton, 1, 5);




        GridPane.setConstraints(regButton, 1, 6);

       /* loginButton.setOnAction(e -> {

            String email = emailField.getText();
            String password = passwordField.getText();
            if (customerRadioButton.isSelected()) {
                System.out.println("Login come utente con username: " + email + " e password: " + password);
            } else if (managerRadioButton.isSelected()) {
                System.out.println("Login come gestore con username: " + email + " e password: " + password);
            }
        });*/

        // aggiungo signupview al bottone registrati
       /* loginButton.setOnAction(e -> {
            CustomerView customerView = new CustomerView();
            try {
                customerView.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });*/

       /* regButton.setOnAction(e -> {
            SignUpView signUpView = new SignUpView();
            try {
                signUpView.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });*/




        grid.getChildren().addAll(emailnameLabel, emailField, passwordLabel, passwordField, customerRadioButton, managerRadioButton, loginButton,signupLabel,regButton);




        scene= new Scene(root, 500, 500);
        primaryStage.setScene(scene);

        primaryStage.setTitle("TicketWave");

        Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/Resources/logo.png");


        LoginController loginController = new LoginController(primaryStage, signUpView, customerView, this);

        //SignUpController signUpController= new SignUpController(primaryStage,signUpView,customerView,this);

        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }

    public RadioButton getCustomerRadioButton() {
        return customerRadioButton;
    }

    public RadioButton getManagerRadioButton() {
        return managerRadioButton;
    }

    public Button getLoginButton() {
        return loginButton;
    }
    public void reSetBars(){
        BorderPane temp = new BorderPane();
        scene.setRoot(temp);
        root.setTop(UpperBar.getIstance());
        root.setCenter(grid);
        root.setBottom(LowerBar.getInstance());
        scene.setRoot(root);
    }
    public Button getRegButton() {
        return regButton;
    }

    public Scene getScene() {
        return scene;
    }
    public static void main(String[] args) {
        launch(args);
    }
}