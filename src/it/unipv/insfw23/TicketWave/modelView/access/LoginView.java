package it.unipv.insfw23.TicketWave.modelView.access;



import it.unipv.insfw23.TicketWave.modelView.bars.LowerBar;
import it.unipv.insfw23.TicketWave.modelView.bars.UpperBar;
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
import javafx.scene.text.Font;





public class LoginView extends Scene {
    private Button loginButton ;
    private Button regButton ;
    private BorderPane layout;
    private GridPane grid ;
    private  RadioButton customerRadioButton;
    private RadioButton managerRadioButton;
    private LowerBar lowerBar;
    private UpperBar upperBar;

    private Label emailLabel;
    private TextField emailField;
    private Label passwordLabel;
    private PasswordField passwordField;
    private Label signupLabel;
    private Label errorLabel;
    private  ToggleGroup accountTypeToggleGroup;


    public LoginView(){
        super(new BorderPane(),1080,600);
        initComponents();
    }
    
    
    public void initComponents(){

        layout= (BorderPane) getRoot();
        layout.setStyle("-fx-background-color: #91bad6;");
        


        grid= new GridPane();


        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);



        lowerBar = LowerBar.getInstance();
        upperBar= UpperBar.getIstance();
        upperBar.setForNoLogged();
        layout.setBottom(lowerBar);
        layout.setTop(upperBar);
        layout.setCenter(grid);



        // imposto campo email
        emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Helvetica", FontWeight.BOLD,20));
        GridPane.setConstraints(emailLabel, 0, 1);
        emailField=new TextField();
        GridPane.setConstraints(emailField, 1, 1);

        // Imposto camp password
        passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        GridPane.setConstraints(passwordLabel, 0, 2);
        passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 2);

        signupLabel = new Label("Non sei ancora iscritto ?");
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
        loginButton = new Button("Login");
        regButton = new Button("Registrati");

        errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        //errorLabel.setText("Campi non validi o vuoti");
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
        GridPane.setConstraints(errorLabel, 0, 9);



        grid.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, customerRadioButton, managerRadioButton, loginButton,signupLabel,regButton,errorLabel);




        
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
        setRoot(temp);

        lowerBar = LowerBar.getInstance();
        upperBar= UpperBar.getIstance();
        upperBar.setForNoLogged();

        layout.setBottom(lowerBar);
        layout.setTop(upperBar);
        layout.setCenter(grid);

        setRoot(layout);
    }
    public Button getRegButton() {
        return regButton;
    }
    
    public TextField getMail(){
    	return emailField;
    }

    public PasswordField getPassword(){
    	return passwordField;
    }

    public void makeBlankPage(){
        emailField.setText("");
        passwordField.setText("");
        errorLabel.setVisible(false);
    }
    public boolean checkEmptyFields(){
        if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
            return true;
        }else {
        	return false;
        }
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String message){
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

}