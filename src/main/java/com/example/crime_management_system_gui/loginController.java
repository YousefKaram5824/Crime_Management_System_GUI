package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController extends Switching {

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
        if ("admin".equals(username) && "admin".equals(password)) {
            messageLabel.setText("Welcome, " + username + "!");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        }
        else {
            messageLabel.setText("Incorrect username or password");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

}
