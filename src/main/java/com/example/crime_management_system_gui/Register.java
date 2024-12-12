package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Register extends Switching implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField id;
    @FXML
    private TextField salary;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> rank;

    private DataManager userDataManager;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataManager = Main.getDataManager();
        rank.getItems().addAll("First Lieutenant", "Second Lieutenant", "Captain", "Major", "lieutenant Colonel");
    }

    @FXML
    private void handleRegister() {
        String Name = username.getText();
        String ID = id.getText();
        String Rank = rank.getValue();
        String Salary = salary.getText();
        String Password = password.getText();
        String PasswordChecker = confirmPassword.getText();

        if (Name.isEmpty() || Salary.isEmpty() || Password.isEmpty() || ID.isEmpty() || PasswordChecker.isEmpty() || Rank.isEmpty()) {
            message.setText("Please fill all fields");
            message.setTextFill(Color.RED);
            return;
        }
        if (!ID.startsWith("chf") && !ID.startsWith("poc")) {
            message.setText("Invalid user type");
            message.setTextFill(Color.RED);
            return;
        }
        if (!userDataManager.isUserIdUnique(ID)) {
            message.setText("User ID already exists");
            message.setTextFill(Color.RED);
            return;
        }
        if (!Password.equals(PasswordChecker)) {
            message.setText("Passwords do not match");
            message.setTextFill(Color.RED);
            return;
        }

        if (ID.startsWith("chf")) {
            Rank = "Colonel";
        }

        String userData = String.join(",", Name, ID, Rank, Salary, Password);
        userDataManager.getUserData().add(userData);
        message.setText("Registration successful!");
        message.setTextFill(Color.GREEN);
    }
}