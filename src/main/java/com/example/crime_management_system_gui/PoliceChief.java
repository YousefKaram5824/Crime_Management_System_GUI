package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PoliceChief extends Switching implements Initializable {

    @FXML
    private ComboBox<String> cases;
    @FXML
    private ComboBox<String> dep;
    @FXML
    private ComboBox<String> officer;
    @FXML
    private Label message;

    private DataManager reportDataManager;
    private DataManager userDataManager;
    private DataManager assignDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDataManager = Main.getDataManager();
        userDataManager = Main.getDataManager();
        assignDataManager = Main.getDataManager();

        DataManager departmentDataManager = Main.getDataManager();
        List<String> departments = departmentDataManager.getDepartmentsData();
        for (String department : departments) {
            String[] deptDetails = department.split(",");
            dep.getItems().add(deptDetails[0]);
        }
    }

    @FXML
    private void getCases() {
        String Dep = dep.getValue();
        displayCasesIds(Dep);
        displayOfficersIds(Dep);
    }

    private void displayCasesIds(String departmentName) {
        cases.getItems().clear();
        List<String> casesIds = reportDataManager.getCasesIdsByDepartmentName(departmentName);

        if (casesIds.isEmpty()) {
            message.setText("No cases assigned to this department yet.");
            message.setTextFill(Color.RED);
        } else {
            cases.getItems().addAll(casesIds);
        }
    }

    private void displayOfficersIds(String departmentName) {
        officer.getItems().clear();
        List<String> officersIds = userDataManager.getUserIdsByDepartmentName(departmentName);

        if (officersIds.isEmpty()) {
            message.setText("No cases assigned to this department yet.");
            message.setTextFill(Color.RED);
        } else {
            officer.getItems().addAll(officersIds);
            message.setText("");
        }
    }

    @FXML
    private void assignOfficers() {
        String selectedCase = cases.getValue();
        String selectedOfficer = officer.getValue();

        if (selectedCase == null || selectedCase.isEmpty()) {
            message.setText("Please select a case to assign.");
            message.setTextFill(Color.RED);
            return;
        }

        if (selectedOfficer == null || selectedOfficer.isEmpty()) {
            message.setText("Please select officer to assign.");
            message.setTextFill(Color.RED);
            return;
        }

        List<String> assignedCasesList = assignDataManager.getAssignedCases();
        for (String assignment : assignedCasesList) {
            String[] parts = assignment.split(",");
            if (parts[0].equals(selectedCase) && parts[1].equals(selectedOfficer)) {
                message.setText("This officer is already assigned to the selected case!");
                message.setTextFill(Color.RED);
                return;
            }
        }

        String assignData = String.join(",", selectedCase, selectedOfficer);
        assignDataManager.getAssignedCases().add(assignData);
        message.setText("Officer assigned to case successfully.");
        message.setTextFill(Color.GREEN);
    }
}