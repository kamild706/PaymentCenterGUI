package application.view;

import application.MainApp;
import application.model.serviceCenter.RecipientOfService;
import application.model.serviceCenter.ServiceWorkshop;
import application.model.serviceCenter.Shop;
import application.model.serviceCenter.TransportCompany;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class RecipientOverviewController {

    @FXML
    private TableView<RecipientOfService> recipientsTable;
    @FXML
    private TableColumn<RecipientOfService, String> nameColumn;


    @FXML
    public Label idLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label typeLabel;

    private MainApp mainApp;

    public RecipientOverviewController() {

    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

        showRecipientDetails(null);
        recipientsTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showRecipientDetails(newValue))
        );
    }

    private void showRecipientDetails(RecipientOfService recipient) {
        if (recipient != null) {
            idLabel.setText(String.valueOf(recipient.getId()));
            nameLabel.setText(recipient.getName());
            typeLabel.setText(String.valueOf(recipient.getClass().getSimpleName()));
        }
        else {
            nameLabel.setText("");
            idLabel.setText("");
            typeLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteRecipient() {
        int selectedIndex = recipientsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            recipientsTable.getItems().remove(selectedIndex);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Zaznacz przedsiębiorcę do usunięcia");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new bank.
     */
    @FXML
    private void handleNewRecipient() {
        RecipientOfService tmpRecipient = new RecipientOfService();
        boolean okClicked = mainApp.showRecipientEditDialog(tmpRecipient);
        if (okClicked) {
            switch (tmpRecipient.getTypeID()) {
                case 0:
                    mainApp.getRecipientData().add(new Shop(tmpRecipient.getName()));
                    break;
                case 1:
                    mainApp.getRecipientData().add(new ServiceWorkshop(tmpRecipient.getName()));
                    break;
                case 2:
                    mainApp.getRecipientData().add(new TransportCompany(tmpRecipient.getName()));
                    break;
            }
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditRecipient() {
        RecipientOfService selectedRecipient = recipientsTable.getSelectionModel().getSelectedItem();
        if (selectedRecipient != null) {
            boolean okClicked = mainApp.showRecipientEditDialog(selectedRecipient);
            if (okClicked) {
                showRecipientDetails(selectedRecipient);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Żaden przedsiębiorca nie został zaznaczony");
            alert.setContentText("Zaznacz właściwą pozycję w tabeli");

            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        recipientsTable.setItems(mainApp.getRecipientData());
    }
}
