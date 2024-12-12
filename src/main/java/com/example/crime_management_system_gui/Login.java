package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login extends Switching {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    private DataManager userDataManager;

    @FXML
    public void initialize() {
        userDataManager = Main.getDataManager();
    }

    @FXML
    private void handleLogin() {
        String Name = username.getText();
        String Password = password.getText();

        if (checkCredentials(Name, Password)) {
            message.setText("Welcome, " + Name + "!");
            message.setTextFill(javafx.scene.paint.Color.GREEN);
            redirectUser(Name);
        } else {
            message.setText("Incorrect username or password");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private boolean checkCredentials(String username, String password) {
        for (String data : userDataManager.getUserData()) {
            String[] userData = data.split(",");
            if (userData[0].equals(username) && userData[3].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void redirectUser(String username) {
        try {
            if (username.startsWith("poc")) {
                String userData = getUserDataById(username);
                if (userData != null) {
                    switchToPage("viewProfile.fxml");
                }
            } else if (username.startsWith("chf")) {
                switchToPage("police_chief.fxml");
            } else {
                message.setText("Invalid user type");
                message.setTextFill(javafx.scene.paint.Color.RED);
            }
        } catch (IOException e) {
            message.setText("Error loading page: " + e.getMessage());
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private String getUserDataById(String userId) {
        for (String data : userDataManager.getUserData()) {
            String[] userDetails = data.split(",");
            if (userDetails[0].equals(userId)) {
                return data;
            }
        }
        return null;
    }
}