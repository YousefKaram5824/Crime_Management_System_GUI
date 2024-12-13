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
import java.util.ArrayList;
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
    private Label message;
    @FXML
    private Label viewName;
    @FXML
    private ListView<String> userListView;

    private DataManager departmentDataManager;
    private DataManager userDataManager;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        departmentDataManager = Main.getDataManager();
        userDataManager = Main.getDataManager();
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
        String departmentId = id.getText();
        String departmentData = getDepartmentDataById(departmentId);

        if (departmentData != null) {
            String[] departmentDetails = departmentData.split(",");
            viewName.setText(departmentDetails[0]);
            displayUserIds(departmentId);
            message.setText("");
        } else {
            message.setText("User id not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clear();
        }
    }

    private String getDepartmentDataById(String departmentId) {
        for (String data : departmentDataManager.getDepartmentsData()) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentId)) {
                return data;
            }
        }
        return null;
    }

    private void displayUserIds(String departmentId) {
        userListView.getItems().clear(); // Clear previous items
        List<String> userIds = getUserIdsByDepartmentName(departmentId); // Assume this method exists

        if (userIds.isEmpty()) {
            message.setText("No users assigned to this department.");
            message.setTextFill(Color.RED);
        } else {
            userListView.getItems().addAll(userIds); // Populate ListView with user IDs
        }
    }

    public List<String> getUserIdsByDepartmentName(String departmentId) {
        List<String> userIds = new ArrayList<>();
        for (String userData : userDataManager.getUserData()) {
            String[] userDetails = userData.split(",");
            if (userDetails[5].equals(departmentId)) { // Assuming department is the 6th element
                userIds.add(userDetails[1]); // Assuming user ID is the 2nd element
            }
        }
        return userIds;
    }

    private void clear() {
        viewName.setText("");
        userListView.getItems().clear();
    }

}