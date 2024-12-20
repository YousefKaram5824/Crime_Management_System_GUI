package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class Cases extends Switching implements Initializable {
    DataManager dataManager = Main.getDataManager();
    @FXML
    private TextField username;
    @FXML
    private TextField witness;
    @FXML
    private TextArea description;
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> crimeType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> departments = dataManager.getDepartmentsData();
        for (String department : departments) {
            String[] deptDetails = department.split(",");
            crimeType.getItems().add(deptDetails[0]);
        }
    }

    @FXML
    private void handleSubmit() {
        String Name = username.getText();
        String Witness = witness.getText();
        String Description = description.getText();
        String CrimeType = crimeType.getValue();
        String reportID = "Case " + generateUniqueReportId();

        if (Name.isEmpty() || Description.isEmpty() || CrimeType == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String reportData = String.join(",", reportID, Name, Witness, CrimeType, Description);
        dataManager.getCases().add(reportData);
        clearFields();
        message.setText("Report submitted successfully!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private String generateUniqueReportId() {
        Set<String> existingIds = new HashSet<>();
        for (String report : dataManager.getCases()) {
            String[] reportDetails = report.split(",");
            existingIds.add(reportDetails[0]);
        }
        Random random = new Random();
        String reportID;
        do {
            reportID = String.valueOf(random.nextInt(1000));
        } while (existingIds.contains(reportID));
        return reportID;
    }

    private void clearFields() {
        username.clear();
        witness.clear();
        description.clear();
        crimeType.setValue(null);
    }
}