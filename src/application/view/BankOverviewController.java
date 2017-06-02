package application.view;

import application.MainApp;
import application.model.bank.Bank;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BankOverviewController {

    @FXML
    private TableView<Bank> bankTable;
    @FXML
    private TableColumn<Bank, String> nameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label customersNumberLabel;

    private MainApp mainApp;

    public BankOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

        showBankDetails(null);
        bankTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showBankDetails(newValue))
        );
    }

    private void showBankDetails(Bank bank) {
        if (bank != null) {
            idLabel.setText(String.valueOf(bank.getBankPrefix()));
            nameLabel.setText(bank.getName());
            customersNumberLabel.setText(String.valueOf(bank.getCustomersNumber()));
        }
        else {
            nameLabel.setText("");
            idLabel.setText("");
            customersNumberLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteBank() {
        int selectedIndex = bankTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bankTable.getItems().remove(selectedIndex);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Zaznacz bank do usunięcia");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new bank.
     */
    @FXML
    private void handleNewPerson() {
        Bank tmpBank = new Bank();
        boolean okClicked = mainApp.showBankEditDialog(tmpBank);
        if (okClicked) {
            mainApp.getBankData().add(tmpBank);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditBank() {
        Bank selectedBank = bankTable.getSelectionModel().getSelectedItem();
        if (selectedBank != null) {
            boolean okClicked = mainApp.showBankEditDialog(selectedBank);
            if (okClicked) {
                showBankDetails(selectedBank);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Żaden bank nie został zaznaczony");
            alert.setContentText("Zaznacz właściwy bank w tabeli");

            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        bankTable.setItems(mainApp.getBankData());
    }
}
