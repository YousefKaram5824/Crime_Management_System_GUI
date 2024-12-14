package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login extends Switching {
    @FXML
    private TextField id;
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
        String ID = id.getText();
        String Password = password.getText();

        if (checkCredentials(ID, Password)) {
            redirectUser(ID);
        } else {
            message.setText("Incorrect username or password");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private boolean checkCredentials(String id, String password) {
        for (String data : userDataManager.getUserData()) {
            String[] userData = data.split(",");
            if (userData[1].equals(id) && userData[4].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void redirectUser(String id) {
        try {
            if (id.startsWith("poc")) {
                String userData = getUserDataById(id);
                if (userData != null) {
                    switchToPage("police_officer.fxml");
                }
            } else if (id.startsWith("chf")) {
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
            if (userDetails[1].equals(userId)) {
                return data;
            }
        }
        return null;
    }
}