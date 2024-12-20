package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Criminals extends Switching implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField currentLocation;
    @FXML
    private Label message;
    @FXML
    private Label viewName;
    @FXML
    private Label viewLevel;
    @FXML
    private Label viewCurrentLocation;
    @FXML
    private ListView<String> assignedCases;

    private DataManager criminalDataManager;
    private DataManager assingedCriminalDataManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        criminalDataManager = Main.getDataManager();
        assingedCriminalDataManager = Main.getDataManager();
    }

    @FXML
    private void addCriminal() {
        String Name = name.getText();
        String ID = id.getText();
        String Level = "Low";
        String CurrentLocation = currentLocation.getText();

        if (Name.isEmpty() || ID.isEmpty() || CurrentLocation.isEmpty()) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (!criminalDataManager.isCriminalIdUnique(ID)) {
            message.setText("Criminal ID already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String criminalData = String.join(",", Name, ID, Level, CurrentLocation);
        criminalDataManager.getCriminalsData().add(criminalData);
        message.setText("Criminal added successfully!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
        clearFields();
    }

    private void clearFields() {
        name.clear();
        id.clear();
        currentLocation.clear();
    }

    @FXML
    private void viewCriminal() {
        String criminalId = id.getText();
        String criminalData = criminalDataManager.getCriminalDataById(criminalId);
        if (criminalData != null) {
            String[] criminalDetails = criminalData.split(",");
            List<String> casesAssigned = assingedCriminalDataManager.getCasesIdsByCriminalId(criminalDetails[0]);
            int numberOfCases = assingedCriminalDataManager.getNumberOfCasesForCriminal(criminalDetails[0]);
            viewName.setText(criminalDetails[0]);
            if (numberOfCases > 3 && numberOfCases <= 7) {
                criminalDetails[2] = "Moderate";
            } else if (numberOfCases > 7 && numberOfCases <= 10) {
                criminalDetails[2] = "High";
            } else if (numberOfCases > 10) {
                criminalDetails[2] = "Very High";
            }
            viewLevel.setText(criminalDetails[2]);
            viewCurrentLocation.setText(criminalDetails[3]);
            message.setText("");
            assignedCases.getItems().addAll(casesAssigned);
        } else {
            message.setText("Criminal id not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clearLabels();
        }
    }

    private void clearLabels() {
        viewName.setText("");
        viewLevel.setText("");
        viewCurrentLocation.setText("");
        id.clear();
        assignedCases.getItems().clear();
    }
}
