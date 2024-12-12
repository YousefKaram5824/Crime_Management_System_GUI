package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class officerProfile extends Switching implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label id;
    @FXML
    private Label salary;
    @FXML
    private Label department;

    private String userData; // Store user data string

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataManager userDataManager = Main.getDataManager();
        if (userData != null) {
            displayUserData();
        }
    }

    public void setUserData(String userData) {
        this.userData = userData; // Store the user data
    }

    private void displayUserData() {
        String[] userDetails = userData.split(",");
        id.setText(userDetails[0]);
        username.setText(userDetails[1]); // Assuming the second column is username
        salary.setText(userDetails[5]); // Assuming the sixth column is salary
    }
}