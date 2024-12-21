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
    private Label viewName;
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

        if (dataManager.isDepartmentIdUnique(ID)) {
            message.setText("Department ID already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (dataManager.isDepartmentNameUnique(Name)) {
            message.setText("Department name already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String departmentData = String.join(",", Name, ID, date.toString());
        dataManager.getDepartmentData().add(departmentData);
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
        String departmentId = id.getText();
        String departmentData = dataManager.getDepartmentDataById(departmentId);

        if (departmentData != null) {
            String[] departmentDetails = departmentData.split(",");
            viewName.setText(departmentDetails[0]);
            dateOfActivation.setText(departmentDetails[2]);
            displayOfficersIds(departmentDetails[0]);
            displayCasesIds(departmentDetails[0]);
            message.setText("");
        } else {
            message.setText("Department ID not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clear();
        }
    }

    private void displayOfficersIds(String departmentName) {
        officers.getItems().clear();
        List<String> officersIds = dataManager.getOfficersIdsByDepartmentName(departmentName);

        if (officersIds.isEmpty()) {
            message.setTextFill(Color.RED);
            message.setText("No officers assigned to this department yet.");
        } else {
            officers.getItems().addAll(officersIds);
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
        viewName.setText("");
        officers.getItems().clear();
        cases.getItems().clear();
        dateOfActivation.setText("");
    }
}