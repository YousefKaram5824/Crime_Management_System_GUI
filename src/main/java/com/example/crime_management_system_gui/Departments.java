package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class Departments extends Switching implements Initializable {
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

    private DataManager departmentDataManager;
    private DataManager userDataManager;
    private DataManager reportDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentDataManager = Main.getDataManager();
        userDataManager = Main.getDataManager();
        reportDataManager = Main.getDataManager();
    }

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

        if (!departmentDataManager.isDepartmentIdUnique(ID)) {
            message.setText("Department ID already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (!departmentDataManager.isDepartmentNameUnique(Name)) {
            message.setText("Department name already exists!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String departmentData = String.join(",", Name, ID, date.toString());
        departmentDataManager.getDepartmentsData().add(departmentData);
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
        String departmentData = departmentDataManager.getDepartmentDataByName(departmentName);

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
        List<String> userIds = userDataManager.getUserIdsByDepartmentName(departmentName);

        if (userIds.isEmpty()) {
            message.setTextFill(Color.RED);
            message.setText("No users assigned to this department yet.");
        } else {
            officers.getItems().addAll(userIds);
        }
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

    private void clear() {
        viewId.setText("");
        officers.getItems().clear();
        cases.getItems().clear();
        dateOfActivation.setText("");
    }
}