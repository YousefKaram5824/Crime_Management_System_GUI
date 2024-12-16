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
    private DataManager userDataManager;
    private DataManager assignDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDataManager = Main.getDataManager();
        departmentDataManager = Main.getDataManager();
        userDataManager = Main.getDataManager();
        assignDataManager = Main.getDataManager();
    }

    @FXML
    private void getCases() {
        String departmentName = nameOfDepartment.getText();
        String departmentData = departmentDataManager.getDepartmentDataByName(departmentName);

        if (departmentData != null) {
            String[] departmentDetails = departmentData.split(",");
            displayCasesIds(departmentDetails[0]);
            message.setText("");
        } else {
            message.setText("Department name not found!");
            message.setTextFill(Color.RED);
            clear();
        }
    }

    @FXML
    private void assignOfficers() {
        String departmentName = nameOfDepartment.getText();
        String departmentOfficers = officersOfDepartment.getText();
        String selectedCase = assignedCases.getValue();
        String departmentData = departmentDataManager.getDepartmentDataByName(departmentName);


        if (selectedCase == null || selectedCase.isEmpty()) {
            message.setText("Please select a case to assign.");
            message.setTextFill(Color.RED);
            return;
        }

        if (departmentOfficers.isEmpty()) {
            message.setText("Officer name cannot be empty!");
            message.setTextFill(Color.RED);
            return;
        }

        if (departmentData == null) {
            message.setText("Department name not found!");
            message.setTextFill(Color.RED);
            return;
        }

        String[] departmentDetails = departmentData.split(",");
        List<String> userIds = userDataManager.getUserIdsByDepartmentName(departmentDetails[0]);

        if (!userIds.contains(departmentOfficers)) {
            message.setText("Officer ID cannot be found!");
            message.setTextFill(Color.RED);
            return;
        }

        List<String> assignedCasesList = assignDataManager.getAssignedCases();
        for (String assignment : assignedCasesList) {
            String[] parts = assignment.split(",");
            if (parts[0].equals(selectedCase) && parts[1].equals(departmentOfficers)) {
                message.setText("This officer is already assigned to the selected case!");
                message.setTextFill(Color.RED);
                return;
            }
        }

        String assignData = String.join(",", selectedCase, departmentOfficers);
        assignDataManager.getAssignedCases().add(assignData);
        message.setText("Officer assigned to case successfully.");
        message.setTextFill(Color.GREEN);
        officersOfDepartment.clear();
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
        assignedCases.getItems().clear();
    }
}