package application.view;

import application.MainApp;
import application.model.bank.Bank;
import application.model.bank.Customer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;


public class CustomerOverviewController {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> lastnameColumn;
    @FXML
    private TableColumn<Customer, String> peselColumn;
    @FXML
    private Label peselLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label lastnameLabel;

    private MainApp mainApp;
    private Bank bank;

    public CustomerOverviewController() {

    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        lastnameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().getPeselProperty());

        showCustomerDetails(null);
        customerTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showCustomerDetails(newValue))
        );
    }

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            peselLabel.setText(String.valueOf(customer.getPesel()));
            nameLabel.setText(customer.getName());
            lastnameLabel.setText(String.valueOf(customer.getLastname()));
        }
        else {
            nameLabel.setText("");
            peselLabel.setText("");
            lastnameLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new customer.
     */
    @FXML
    private void handleNewCustomer() {
        Customer tmpCustomer = new Customer();
        boolean okClicked = mainApp.showCustomerEditDialog(tmpCustomer);
        if (okClicked) {
            bank.addCustomer(tmpCustomer);
            mainApp.showCustomerOverview(bank);
        }
    }

    @FXML
    private void handleDeleteCustomer() {
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            customerTable.getItems().remove(selectedIndex);
            bank.setCustomers(new ArrayList<Customer>(customerTable.getItems()));
            mainApp.showCustomerOverview(bank);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Zaznacz klienta do usunięcia");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleShowCards() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            mainApp.showCardOverview(selectedCustomer);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Żaden klient nie został zaznaczony");
            alert.setContentText("Zaznacz właściwego klienta w tabeli");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            boolean okClicked = mainApp.showCustomerEditDialog(selectedCustomer);
            if (okClicked) {
                bank.setCustomers(new ArrayList<Customer>(customerTable.getItems()));
                mainApp.showCustomerOverview(bank);
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
    }

    public void addCustomers(ObservableList<Customer> customers) {
        customerTable.setItems(customers);
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
