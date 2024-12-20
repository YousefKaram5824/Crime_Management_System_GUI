package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login extends Switching {
    private final DataManager dataManager = Main.getDataManager();
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    @FXML
    private void handleLogin() {
        String ID = id.getText();
        String Password = password.getText();

        if (dataManager.checkCredentials(ID, Password)) {
            UserSession.getInstance().setCurrentUserId(ID);
            redirectUser(ID);
        } else {
            message.setText("Incorrect username or password");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private void redirectUser(String id) {
        try {
            if (id.startsWith("poc")) {
                switchToPoliceOfficer();
            } else if (id.startsWith("chf")) {
                switchToPoliceChief();
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