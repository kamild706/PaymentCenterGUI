package application.view;

import application.MainApp;
import application.model.bank.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;


public class CardOverviewController {

    @FXML
    private Label availableFoundsLabel;
    @FXML
    private Label cardLimitLabel;
    @FXML
    private TableView<Card> cardTable;
    @FXML
    private TableColumn<Card, String> cardNumberColumn;
    @FXML
    private TableColumn<Card, String> cardTypeColumn;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private Label cardTypeLabel;

    private MainApp mainApp;
    private Customer customer;

    public CardOverviewController() {
    }

    @FXML
    private void initialize() {
        cardNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getCardNumberProperty());
        cardTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getType());

        showCardDetail(null);
        cardTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showCardDetail(newValue))
        );
    }

    private void showCardDetail(Card card) {
        if (card != null) {
            cardNumberLabel.setText(String.valueOf(card.getCardNumber()));
            cardTypeLabel.setText(card.getType().get());
            availableFoundsLabel.setText(card.getAvailableFounds());
            cardLimitLabel.setText(card.getCardLimit());
        }
        else {
            cardNumberLabel.setText("");
            cardTypeLabel.setText("");
            availableFoundsLabel.setText("");
            cardLimitLabel.setText("");
        }
    }

    @FXML
    private void handleEditCard() {
        Card selectedCard = cardTable.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            boolean okClicked = mainApp.showCardEditDialog(selectedCard);
            if (okClicked) {
                customer.setCards(new ArrayList<Card>(cardTable.getItems()));
                mainApp.showCardOverview(customer);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Żadna karta nie została zaznaczona");
            alert.setContentText("Zaznacz właściwą kartę w tabeli");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteCard() {
        int selectedIndex = cardTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            cardTable.getItems().remove(selectedIndex);
            customer.setCards(new ArrayList<Card>(cardTable.getItems()));
            mainApp.showCardOverview(customer);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Zaznacz kartę do usunięcia");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewAtmCard() {
        Card tmpCard = new AtmCard();
        boolean okClicked = mainApp.showCardEditDialog(tmpCard);
        if (okClicked) {
            customer.addCard(tmpCard);
            mainApp.showCardOverview(customer);
        }
    }

    @FXML
    private void handleNewDebitCard() {
        Card tmpCard = new DebitCard();
        boolean okClicked = mainApp.showCardEditDialog(tmpCard);
        if (okClicked) {
            customer.addCard(tmpCard);
            mainApp.showCardOverview(customer);
        }
    }

    @FXML
    private void handleNewCreditCard() {
        Card tmpCard = new CreditCard();
        boolean okClicked = mainApp.showCardEditDialog(tmpCard);
        if (okClicked) {
            customer.addCard(tmpCard);
            mainApp.showCardOverview(customer);
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addCards(ObservableList<Card> cards) {
        cardTable.setItems(cards);
    }
}
