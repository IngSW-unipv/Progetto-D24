package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.CustomerView;
import it.unipv.insfw23.TicketWave.modelView.LoginView;
import it.unipv.insfw23.TicketWave.modelView.SignUpView;
import javafx.stage.Stage;

public class SignUpController {
        private Stage mainstage;

        // view da considerare
        private SignUpView sign;


        public SignUpController(Stage mainstage,SignUpView sign){

            this.sign= sign;
            this.mainstage= mainstage;


        }
}
