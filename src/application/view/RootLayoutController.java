package application.view;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class RootLayoutController {

    private MainApp mainApp;

    public RootLayoutController() {

    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleShowBanks() {
        mainApp.showBankOverview();
    }

    @FXML
    private void handleShowTransactions() {

    }

    @FXML
    private void handleShowRoS() {
        mainApp.showRecipientOverview();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
