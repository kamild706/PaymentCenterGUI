package application.view;

import application.model.bank.Bank;
import application.model.serviceCenter.RecipientOfService;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class RecipientEditDialogController {

    @FXML
    public ListView typesList;
    @FXML
    private TextField nameField;

    private Stage dialogStage;
    private RecipientOfService recipient;
    private boolean okClicked = false;

    private ListProperty<String> listProperty = new SimpleListProperty<>();
    private List<String> types = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
        types.add("Sklep");
        types.add("Zakład usług");
        types.add("Firma transportowa");

        typesList.itemsProperty().bind(listProperty);
        listProperty.set(FXCollections.observableArrayList(types));
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
     * @param recipient
     */
    public void setRecipient(RecipientOfService recipient) {
        this.recipient = recipient;

        nameField.setText(recipient.getName());
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
            recipient.setName(nameField.getText());
            recipient.setTypeID(typesList.getSelectionModel().getSelectedIndex());
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
