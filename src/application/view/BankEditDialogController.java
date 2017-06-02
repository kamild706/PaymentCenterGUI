package application.view;

import application.model.bank.Bank;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;


public class BankEditDialogController {

    @FXML
    private TextField nameField;

    private Stage dialogStage;
    private Bank bank;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the bank to be edited in the dialog.
     *
     * @param bank
     */
    public void setBank(Bank bank) {
        this.bank = bank;

        nameField.setText(bank.getName());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            bank.setName(nameField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Nieprawidłowa nazwa!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Nieprawidłowe wartości");
            alert.setHeaderText("Popraw niewłaściwe dane");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
