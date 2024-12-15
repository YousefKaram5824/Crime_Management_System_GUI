package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PoliceChief extends Switching   {
    /*@FXML
    private TextField name;
    @FXML
    private ComboBox<String> casesListView;
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
    private void getDepartment(){
        String departmentName = name.getText();
        String departmentData = departmentDataManager.getDepartmentDataByName(departmentName);

        if (departmentData != null) {
            String[] departmentDetails = departmentData.split(",");
            message.setText("");
        } else {
            message.setText("Department name not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }*/

}
