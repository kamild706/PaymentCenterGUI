package application.view;

import application.model.bank.Card;
import application.model.bank.CreditCard;
import application.model.bank.Currencies;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class CardEditDialogController {

    @FXML
    private TextField balanceField;
    @FXML
    private ListView currencyList;


    private ListProperty<String> currencyProperty = new SimpleListProperty<>();
    private List<String> currencies = new ArrayList<>();

    private Stage dialogStage;
    private Card card;
    private boolean okClicked = false;

    public void setCard(Card card) {
        this.card = card;

        balanceField.setText(card.getMoney());
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {

        currencies.add(String.valueOf(Currencies.PLN));
        currencies.add(String.valueOf(Currencies.EUR));
        currencies.add(String.valueOf(Currencies.USD));
        currencies.add(String.valueOf(Currencies.GBP));

        currencyList.itemsProperty().bind(currencyProperty);
        currencyProperty.set(FXCollections.observableArrayList(currencies));
    }


    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            card.setMoney(balanceField.getText());
            switch (currencyList.getSelectionModel().getSelectedIndex()) {
                case 0:
                    card.setCurrency(Currencies.PLN);
                    break;
                case 1:
                    card.setCurrency(Currencies.EUR);
                    break;
                case 2:
                    card.setCurrency(Currencies.USD);
                    break;
                case 3:
                    card.setCurrency(Currencies.GBP);
                    break;
                default:
                    card.setCurrency(Currencies.PLN);
            }

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

        if (balanceField.getText() == null || !balanceField.getText().matches("[0-9]+([.][0-9]{2})?")) {
            errorMessage += "Nieprawidłowa kwota!\n";
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

    public void resetCardBalance() {
        if (card instanceof CreditCard) {
            ((CreditCard) card).resetBalance();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Nieprawidłowa operacja");
            alert.setHeaderText("Niedozwolona akcja");
            alert.setContentText("Wyzerować można tylko\nocbiążenie karty kredytowej");

            alert.showAndWait();
        }
    }
}
