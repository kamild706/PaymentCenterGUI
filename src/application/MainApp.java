package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import application.model.bank.Bank;
import application.view.BankEditDialogController;
import application.view.BankOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Bank> bankData = FXCollections.observableArrayList();

    public MainApp() {

        readObjects();

        /*bankData.add(new Bank("MyBank"));
        bankData.add(new Bank("MyBank2"));
        bankData.add(new Bank("MyBank3"));*/
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Centrum obsługi płatności");

        initRootLayout();
        showBankOverview();
    }

    private void readObjects() {
        try {
            FileInputStream fin = new FileInputStream("/home/kamil/Projects/Java/somegui/banks.stored");
            ObjectInputStream ois = new ObjectInputStream(fin);
            ArrayList<Bank> list = (ArrayList<Bank>) ois.readObject();
            bankData = FXCollections.observableArrayList(list);
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void saveObjects() {
        try {
            FileOutputStream fout = new FileOutputStream("/home/kamil/Projects/Java/somegui/banks.stored");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(new ArrayList<Bank>(bankData));
            oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> saveObjects());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showBankOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BankOverview.fxml"));
            AnchorPane personOverview = loader.load();
            rootLayout.setCenter(personOverview);

            BankOverviewController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showBankEditDialog(Bank bank) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BankEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edytuj bank");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            BankEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBank(bank);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     *
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Bank> getBankData() {
        return bankData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}