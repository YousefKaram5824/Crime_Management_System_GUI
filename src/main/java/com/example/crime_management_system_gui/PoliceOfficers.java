package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PoliceOfficers extends Switching implements Initializable {

    @FXML
    private ComboBox<String> assignedDepartment;
    @FXML
    private ComboBox<String> assignedOfficers;
    @FXML
    private ComboBox<String> assignedCases;
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

         List<String> departments = departmentDataManager.getDepartmentsData();
         for (String department : departments) {
             String[] deptDetails = department.split(",");
             assignedDepartment.getItems().add(deptDetails[1]);
         }
    }

    @FXML
    private void assignOfficer(){

    }
}
