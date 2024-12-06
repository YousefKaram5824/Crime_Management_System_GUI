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
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (checkCredentials(username, password)) {
            messageLabel.setText("Welcome, " + username + "!");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            redirectUser(username);
        } else {
            messageLabel.setText("Incorrect username or password");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private boolean checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(username) && userData[3].equals(password)) {
                    return true; // ID and password match
                }
            }
        } catch (IOException e) {
            messageLabel.setText("Error reading data: " + e.getMessage());
        }
        return false; // No match found
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
                messageLabel.setText("Invalid user type");
                messageLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        } catch (IOException e) {
            messageLabel.setText("Error loading page: " + e.getMessage());
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
}