package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.PaymentDataM2View;
import it.unipv.insfw23.TicketWave.modelView.TicketPageView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PaymentDataController {  //CLASSE DA CAMBIARE NON FARE RIFERIMENTO
    private Stage mainStage;
    private TicketPageView TicketPage;



        public static void addListeners(TextField insertMM, TextField insertAA, TextField insertcvc, TextField
        insertNC, Button forwardButton){
            insertMM.textProperty().addListener(new LimitedNumberTextFieldListener(insertMM, 2));
            insertAA.textProperty().addListener(new LimitedNumberTextFieldListener(insertAA, 2));
            insertcvc.textProperty().addListener(new LimitedNumberTextFieldListener(insertcvc, 4));
            insertNC.textProperty().addListener(new LimitedNumberTextFieldListener(insertNC, 16));


            forwardButton.setOnAction(event -> handleForwardButtonClick());
        }

        private static void handleForwardButtonClick () { // logica di controllo bottone
            // Azione da eseguire quando il pulsante viene premuto
            System.out.println("Hai cliccato il bottone Forward");
            // Aggiungi qui la logica per andare avanti
        }

        static class LimitedNumberTextFieldListener implements ChangeListener<String> {  // creo classe statica per fare in modo di avere una logica indipendente dalla classe esterna PaymentDataController
            private final TextField textField;
            private final int maxLength;

            public LimitedNumberTextFieldListener(TextField textField, int maxLength) { // metodo limitatore di caratteri
                this.textField = textField;
                this.maxLength = maxLength;
            }


            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {  // metodo per il focus di scrittura
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (textField.getText().length() > maxLength) {
                    String truncatedValue = textField.getText().substring(0, maxLength);
                    textField.setText(truncatedValue);
                }
            }
        }

        public static void setDefaultTextFieldStyle (List< TextField > textFields) {
            for (TextField textField : textFields) {
                textField.setStyle("-fx-text-fill: #A9A9A9;");
            }
        }

        public static void addTextChangeListeners (TextField insertMM, TextField insertAA, TextField insertcvc){
            addTextChangeListener(insertMM);
            addTextChangeListener(insertAA);
            addTextChangeListener(insertcvc);
        }


        private static void addTextChangeListener (TextField textField){

            AtomicBoolean isFirstClick = new AtomicBoolean(true);
            textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue && isFirstClick.get()) {
                    textField.setText("");
                    textField.setStyle("-fx-text-fill: black;");
                    isFirstClick.set(false);
                }
            });
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (textField.getText().equals("MM") || textField.getText().equals("AA") || textField.getText().equals("1234")) {
                    textField.setText("");
                }
                textField.setStyle("-fx-text-fill: black;");
            });

        }
    }
