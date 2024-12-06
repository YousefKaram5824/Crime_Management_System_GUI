package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController extends Switching {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    @FXML
    private void handleLogin() {
        String Name = username.getText();
        String Password = password.getText();

        if (checkCredentials(Name, Password)) {
            message.setText("Welcome, " + username + "!");
            message.setTextFill(javafx.scene.paint.Color.GREEN);
            redirectUser(Name);
        } else {
            message.setText("Incorrect username or password");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private boolean checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(username) && userData[3].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            message.setText("Error reading data: " + e.getMessage());
        }
        return false;
    }

    private void redirectUser(String username) {
        try {
            if (username.startsWith("dep")) {
                switchToPage("chief_of_department.fxml");
            } else if (username.startsWith("poc")) {
                switchToPage("police_officer.fxml");
            } else if (username.startsWith("chf")) {
                switchToPage("chief_of_police.fxml");
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