package application.view;

import application.MainApp;
import application.model.bank.Card;
import application.model.exceptions.CardNotFoundException;
import application.model.exceptions.FundsException;
import application.model.exceptions.PaymentRefusedException;
import application.model.serviceCenter.RecipientOfService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CardChargeDialogController {

    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField moneyField;

    private boolean okClicked;
    private Stage dialogStage;
    private MainApp mainApp;
    private RecipientOfService ros;

    public void setRecipient(RecipientOfService ros) {
        this.ros = ros;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public CardChargeDialogController() {
    }

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


    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Wystąpił problem");
            alert.setHeaderText("Popraw niewłaściwe dane");

            try {
                mainApp.chargeCard(cardNumberField.getText(), moneyField.getText(), ros);
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(dialogStage);
                alert.setTitle("Karta obciążona");
                alert.setHeaderText("Transakcja przebiegła pomyślnie");
            }
            catch (CardNotFoundException e) {
                alert.setContentText("Karta o podanym numerze nie została znaleziona");
            }
            catch (FundsException e) {
                alert.setContentText("Karta nie posiada wystarczajacych środków");
            }
            catch (PaymentRefusedException e) {
                alert.setContentText("Bank odrzucił transakcje");
            }
            finally {
                alert.showAndWait();
                okClicked = true;
                dialogStage.close();
            }
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

        if (moneyField.getText() == null || !moneyField.getText().matches("[0-9]+([.][0-9]{2})?")) {
            errorMessage += "Nieprawidłowa kwota!\n";
        }
        if (cardNumberField.getText() == null || !cardNumberField.getText().matches("[0-9]{8}")) {
            errorMessage += "Nieprawidłowy numer karty!\n";
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

