package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

    private DataManager criminalDataManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        criminalDataManager = Main.getDataManager();
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
            viewName.setText(criminalDetails[0]);
            viewLevel.setText(criminalDetails[2]);
            viewCurrentLocation.setText(criminalDetails[3]);
            message.setText("");
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
    }
}
