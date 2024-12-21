package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class Criminals extends Person {

    private final DataManager dataManager = Main.getDataManager();
    Person criminal = new Criminals();
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

    @FXML
    private void addCriminal() {
        String Name = criminal.name.getText();
        String ID = criminal.id.getText();
        String CurrentLocation = currentLocation.getText();
        String Level = "Low";

        if (Name.isEmpty() || ID.isEmpty() || CurrentLocation.isEmpty()) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (dataManager.isCriminalUnique(ID)) {
            message.setText("Criminal ID already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (dataManager.isCriminalUnique(ID,Name)) {
            message.setText("Criminal Name already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String criminalData = String.join(",", Name, ID, Level, CurrentLocation);
        dataManager.getCriminalData().add(criminalData);
        message.setText("Criminal added successfully!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
        clearFields();
    }

    private void clearFields() {
        name.clear();
        id.clear();
        currentLocation.clear();
    }

    @Override
    @FXML
    void view() {
        String criminalId = id.getText();
        String criminalData = dataManager.getCriminalDataById(criminalId);
        if (criminalData != null) {
            String[] criminalDetails = criminalData.split(",");
            List<String> casesAssigned = dataManager.getCasesIdsByCriminalId(criminalDetails[0]);
            int numberOfCases = dataManager.getNumberOfCasesForCriminal(criminalDetails[0]);
            viewName.setText(criminalDetails[0]);
            if (numberOfCases > 3 && numberOfCases <= 7) {
                criminalDetails[2] = "Moderate";
            } else if (numberOfCases > 7 && numberOfCases <= 10) {
                criminalDetails[2] = "High";
            } else if (numberOfCases > 10) {
                criminalDetails[2] = "Very High";
            }
            for (int i = 0; i < dataManager.getCriminalData().size(); i++) {
                String[] CriminalDetails = dataManager.getCriminalData().get(i).split(",");
                if (CriminalDetails[1].equals(criminalId)) {
                    CriminalDetails[2] = criminalDetails[2];
                    dataManager.updateCriminalData(i, String.join(",", CriminalDetails));
                    break;
                }
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
