package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class Cases extends Switching implements Initializable {
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

    private DataManager reportDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDataManager = Main.getDataManager();
        DataManager departmentDataManager = Main.getDataManager();
        List<String> departments = departmentDataManager.getDepartmentsData();
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
        reportDataManager.getCases().add(reportData);
        clearFields();
        message.setText("Report submitted successfully!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private String generateUniqueReportId() {
        Set<String> existingIds = new HashSet<>();
        for (String report : reportDataManager.getCases()) {
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