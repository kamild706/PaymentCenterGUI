package application.view;

import application.MainApp;
import application.model.serviceCenter.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TransactionOverviewController {
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> dateColumn;
    @FXML
    private TableColumn<Transaction, String> amountColumn;
    @FXML
    private Label dateLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label serviceLabel;

    private MainApp mainApp;


    public TransactionOverviewController() {
    }

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().getRequestedMoneyProperty());

        showTransactionDetails(null);
        transactionTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showTransactionDetails(newValue))
        );
    }

    private void showTransactionDetails(Transaction transaction) {
        if (transaction != null) {
            dateLabel.setText(transaction.getFormattedDateAndTime());
            amountLabel.setText(transaction.getRequestedMoney().toString() + "" + transaction.getCard().getCurrencySymbol());
            cardNumberLabel.setText(transaction.getCard().getCardNumber());
            ownerLabel.setText(transaction.getCard().getCardOwner().getFullName());
            serviceLabel.setText(transaction.getRequester().getName());
        }
        else {
            dateLabel.setText("");
            amountLabel.setText("");
            cardNumberLabel.setText("");
            ownerLabel.setText("");
            serviceLabel.setText("");
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        transactionTable.setItems(mainApp.getTransactionData());
    }
}
