package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Switching {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    private UserDataManager userDataManager;

    @FXML
    public void initialize() {
        userDataManager = Main.getUserDataManager(); // Access UserDataManager statically
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
                switchToPage("pages/police_officer.fxml");
            } else if (username.startsWith("chf")) {
                switchToPage("pages/chief_of_police.fxml");
            } else {
                message.setText("Invalid user type");
                message.setTextFill(javafx.scene.paint.Color.RED);
            }
        } catch (IOException e) {
            message.setText("Error loading page: " + e.getMessage());
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
}