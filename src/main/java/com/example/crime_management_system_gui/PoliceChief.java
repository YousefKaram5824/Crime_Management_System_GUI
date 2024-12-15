package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PoliceChief extends Switching implements Initializable {

    @FXML
    private TextField nameOfDepartment;
    @FXML
    private TextField officersOfDepartment;
    @FXML
    private ComboBox<String> assignedCases;
    @FXML
    private Label message;

    private DataManager reportDataManager;
    private DataManager departmentDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDataManager = Main.getDataManager();
        departmentDataManager = Main.getDataManager();
    }

    @FXML
    private void getCases() {
        String departmentName = nameOfDepartment.getText();
        String departmentData = departmentDataManager.getDepartmentDataByName(departmentName);

        if (departmentData != null) {
            displayCasesIds(departmentName);
            message.setText("");
        } else {
            message.setText("Department name not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clear();
        }
    }

    @FXML
    private void assignOfficers() {
        String DepartmentOfficers = officersOfDepartment.getText();

    }

    private void displayCasesIds(String departmentName) {
        assignedCases.getItems().clear();
        List<String> casesIds = reportDataManager.getCasesIdsByDepartmentName(departmentName);

        if (casesIds.isEmpty()) {
            message.setText("No cases assigned to this department yet.");
            message.setTextFill(Color.RED);
        } else {
            assignedCases.getItems().addAll(casesIds);
        }
    }

    private void clear() {
        assignedCases.getItems().clear(); // Clear previous cases
    }
}