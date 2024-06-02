package it.unipv.insfw23.TicketWave.modelView.access;


//import it.unipv.insfw23.TicketWave.modelController.LoginController;
//import it.unipv.insfw23.TicketWave.modelController.SignUpController;
import it.unipv.insfw23.TicketWave.modelController.controller.access.LoginController;
import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
import it.unipv.insfw23.TicketWave.modelView.user.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.user.ManagerView;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.Font;





public class LoginView extends Application {
    private Button loginButton = new Button("Login");
    private Button regButton = new Button("Registrati");
    private SignUpView signUpView= new SignUpView();
    private CustomerView customerView= new CustomerView();
    private ManagerView managerView;
    private BorderPane root ;
    private Scene scene ;
    private GridPane grid ;
    private  RadioButton customerRadioButton;
    private RadioButton managerRadioButton;
    private LowerBar lowerBar;
    private UpperBar upperBar;

    private TextField mail;
    private PasswordField password;
    private Label errorLabel;
    private  ToggleGroup accountTypeToggleGroup;



    @Override
    public void start(Stage primaryStage) throws Exception{

        root= new BorderPane();

        root.setStyle("-fx-background-color: #91bad6;");


        grid= new GridPane();


        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);
        // grid.setStyle("-fx-background-color: White;");





        lowerBar = LowerBar.getInstance();
        upperBar= UpperBar.getIstance();
        upperBar.setForNoLogged();
        root.setBottom(lowerBar);
        root.setTop(upperBar);
        root.setCenter(grid);




        // imposto campo email
        Label emailnameLabel = new Label("Email:");
        emailnameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD,20));
        GridPane.setConstraints(emailnameLabel, 0, 1);
        TextField emailField = new TextField();
        this.mail=emailField;
        GridPane.setConstraints(emailField, 1, 1);

        // Imposto camp password
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        GridPane.setConstraints(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        this.password = passwordField;
        GridPane.setConstraints(passwordField, 1, 2);

        Label signupLabel = new Label("Non sei ancora iscritto ?");
        signupLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        GridPane.setConstraints(signupLabel, 0, 6);


        accountTypeToggleGroup = new ToggleGroup();

        customerRadioButton = new RadioButton("Cliente");

        customerRadioButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        customerRadioButton.setToggleGroup(accountTypeToggleGroup);
        customerRadioButton.setSelected(true);
        GridPane.setConstraints(customerRadioButton, 0, 0);

        managerRadioButton = new RadioButton("Gestore");
        managerRadioButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        managerRadioButton.setToggleGroup(accountTypeToggleGroup);

        errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        errorLabel.setText("Campi non validi o vuoti");
        errorLabel.setVisible(false);

        accountTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == customerRadioButton) {
                errorLabel.setVisible(false);

            } else if (newValue == managerRadioButton) {
                errorLabel.setVisible(false);

            }
        });

        GridPane.setConstraints(managerRadioButton, 1, 0);
        GridPane.setConstraints(loginButton, 1, 5);
        GridPane.setConstraints(regButton, 1, 6);
        GridPane.setConstraints(errorLabel, 2, 6);

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




        grid.getChildren().addAll(emailnameLabel, emailField, passwordLabel, passwordField, customerRadioButton, managerRadioButton, loginButton,signupLabel,regButton,errorLabel);




        this.scene= new Scene(root, 500, 500);
        primaryStage.setScene(scene);

        primaryStage.setTitle("TicketWave");

      Image icon = new Image("it/unipv/insfw23/TicketWave/modelView/imagesResources/logo.png");


        LoginController loginController = new LoginController(primaryStage, this);


        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(600);
        primaryStage.setMinHeight(600);
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

        lowerBar = LowerBar.getInstance();
        upperBar= UpperBar.getIstance();
        upperBar.setForNoLogged();

        root.setBottom(lowerBar);
        root.setTop(upperBar);
        root.setCenter(grid);

        scene.setRoot(root);
    }
    public Button getRegButton() {
        return regButton;
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getMail(){return mail;}

    public PasswordField getPassword(){return password;}

    public void makeBlankPage(){
        mail.setText(null);
        password.setText(null);
        errorLabel.setVisible(false);
    }
    public boolean checkEmptyFields(){
        if(mail.getText()== null || password.getText()==null){
            return true;
        }else {return false;}
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(){

        System.out.println("mail/password diverse o campi vuoti");
        errorLabel.setVisible(true);
    }
    public static void main(String[] args) {
        launch(args);
    }
}