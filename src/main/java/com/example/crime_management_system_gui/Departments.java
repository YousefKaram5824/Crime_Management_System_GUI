package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.List;

public class Departments extends Switching {
    private final DataManager dataManager = Main.getDataManager();
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label viewId;
    @FXML
    private Label dateOfActivation;
    @FXML
    private ListView<String> officers;
    @FXML
    private ListView<String> cases;
    @FXML
    private Label message;

    @FXML
    private void createDepartment() {
        String Name = name.getText();
        String ID = id.getText();
        LocalDate date = datePicker.getValue();

        if (Name.isEmpty() || ID.isEmpty() || date == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (!dataManager.isDepartmentIdUnique(ID)) {
            message.setText("Department ID already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (!dataManager.isDepartmentNameUnique(Name)) {
            message.setText("Department name already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String departmentData = String.join(",", Name, ID, date.toString());
        dataManager.getDepartmentsData().add(departmentData);
        message.setText("Department created successfully!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
        clearFields();
    }

    private void clearFields() {
        name.clear();
        id.clear();
        datePicker.setValue(null);
    }

    @FXML
    private void viewDepartment() {
        String departmentName = name.getText();
        String departmentData = dataManager.getDepartmentDataByName(departmentName);

        if (departmentData != null) {
            String[] departmentDetails = departmentData.split(",");
            viewId.setText(departmentDetails[0]);
            dateOfActivation.setText(departmentDetails[2]);
            displayUserIds(departmentDetails[0]);
            displayCasesIds(departmentDetails[0]);
            message.setText("");
        } else {
            message.setText("Department name not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clear();
        }
    }

    private void displayUserIds(String departmentName) {
        officers.getItems().clear();
        List<String> userIds = dataManager.getUserIdsByDepartmentName(departmentName);

        if (userIds.isEmpty()) {
            message.setTextFill(Color.RED);
            message.setText("No users assigned to this department yet.");
        } else {
            officers.getItems().addAll(userIds);
        }
    }

    private void displayCasesIds(String departmentName) {
        cases.getItems().clear();
        List<String> casesIds = dataManager.getCasesIdsByDepartmentName(departmentName);

        if (casesIds.isEmpty()) {
            message.setText("No cases assigned to this department yet.");
            message.setTextFill(Color.RED);
        } else {
            cases.getItems().addAll(casesIds);
        }
    }

    private void clear() {
        viewId.setText("");
        officers.getItems().clear();
        cases.getItems().clear();
        dateOfActivation.setText("");
    }
}