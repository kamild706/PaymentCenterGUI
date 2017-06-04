package application.view;

import application.model.bank.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by kamil on 6/3/17.
 */
public class CustomerEditDialogController {
    @FXML
    private TextField peselField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField lastnameField;

    private Stage dialogStage;
    private Customer customer;
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
     * Sets the customer to be edited in the dialog.
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;

        nameField.setText(customer.getName());
        lastnameField.setText(customer.getLastname());
        peselField.setText(customer.getPesel());
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
            customer.setName(nameField.getText());
            customer.setLastname(lastnameField.getText());
            customer.setPesel(peselField.getText());

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
            errorMessage += "Nieprawidłowe imię!\n";
        }
        if (lastnameField.getText() == null || lastnameField.getText().length() == 0) {
            errorMessage += "Nieprawidłowe nazwisko!\n";
        }
        if (peselField.getText() == null || !peselField.getText().matches("[0-9]{11}")) {
            errorMessage += "Nieprawidłowy pesel!\n";
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
