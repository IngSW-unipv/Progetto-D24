package it.unipv.insfw23.TicketWave.modelController;

import it.unipv.insfw23.TicketWave.modelView.PaymentDataM2View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PaymentDataController {
    public static void addListeners(TextField insertMM, TextField insertAA, TextField insertcvc, TextField insertNC, Button forwardButton) {
        insertMM.textProperty().addListener(new LimitedNumberTextFieldListener(insertMM, 2));
        insertAA.textProperty().addListener(new LimitedNumberTextFieldListener(insertAA, 2));
        insertcvc.textProperty().addListener(new LimitedNumberTextFieldListener(insertcvc, 4));
        insertNC.textProperty().addListener(new LimitedNumberTextFieldListener(insertNC, 16));

        addFocusListener(insertMM, insertAA, insertcvc);

        forwardButton.setOnAction(event -> handleForwardButtonClick());
    }

    private static void handleForwardButtonClick() { // logica di controllo bottone
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

    private static void addFocusListener(TextField insertMM, TextField insertAA, TextField insertcvc) {
        insertMM.focusedProperty().addListener((observable, oldValue, newValue) -> handleFocus(insertMM, newValue));
        insertAA.focusedProperty().addListener((observable, oldValue, newValue) -> handleFocus(insertAA, newValue));
        insertcvc.focusedProperty().addListener((observable, oldValue, newValue) -> handleFocus(insertcvc, newValue));
    }

    private static void handleFocus(TextField textField, boolean hasFocus) {
        if (hasFocus) {
            if (textField.getText().equals("MM") || textField.getText().equals("AA") || textField.getText().equals("1234")) {
                textField.setText("");
            }
            textField.setStyle("-fx-text-fill: black;");
        } else {
            if (textField.getText().isEmpty()) {
                if (textField.getId().equals("insertMM")) {
                    textField.setText("MM");
                } else if (textField.getId().equals("insertAA")) {
                    textField.setText("AA");
                } else if (textField.getId().equals("insertcvc")) {
                    textField.setText("1234");
                }
                textField.setStyle("-fx-text-fill: #A9A9A9;");
            }
        }
    }
}
