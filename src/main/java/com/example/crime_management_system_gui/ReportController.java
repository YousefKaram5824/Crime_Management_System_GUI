package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ReportController extends Switching implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField witness;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea description;
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> crimeType;

    private DataManager reportDataManager;
    private int reportId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDataManager = Main.getDataManager();
        DataManager departmentDataManager = Main.getDataManager();
        reportDataManager.loadReports();
        List<String> departments = departmentDataManager.getDepartmentsData();
        for (String department : departments) {
            String[] deptDetails = department.split(",");
            crimeType.getItems().add(deptDetails[1]);
        }
        reportId = generateRandomReportId();
    }

    private int generateRandomReportId() {
        Random random = new Random();
        return random.nextInt(100);
    }

    private void clearFields() {
        username.clear();
        witness.clear();
        datePicker.setValue(null);
        description.clear();
        crimeType.setValue(null);
    }

    @FXML
    private void handleSubmit() {
        String Name = username.getText();
        String Witness = witness.getText();
        LocalDate date = datePicker.getValue();
        String Description = description.getText();
        String CrimeType = crimeType.getValue();
        String reportID = String.valueOf(reportId);

        if (Name.isEmpty() || date == null || Description.isEmpty() || CrimeType == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        String reportData = String.join(",", reportID, Name, Witness, formattedDate, CrimeType, Description);

        try {
            reportDataManager.getReports().add(reportData);
            reportDataManager.saveReport(reportData);
            clearFields();
            message.setText("Report submitted successfully!");
            message.setTextFill(javafx.scene.paint.Color.GREEN);
            System.out.println("Report successfully saved:\n" + reportData);
        } catch (Exception e) {
            message.setText("An error occurred while saving the report.");
            message.setTextFill(javafx.scene.paint.Color.RED);
            e.printStackTrace();
        }
    }
}