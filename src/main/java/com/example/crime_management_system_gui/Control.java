package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Control extends Switching {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField idField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordCheckerField;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
    }

    @FXML
    private void handleRegister() {
        String name = usernameField.getText();
        String phone = phoneField.getText();
        String id = idField.getText();
        String password = passwordField.getText();
        String passwordChecker = passwordCheckerField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || id.isEmpty()) {
            messageLabel.setText("Please fill all fields");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!password.equals(passwordChecker)) {
            messageLabel.setText("Password not match");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        String userData = String.join(",", id, name, phone, password, gender);
        saveUserData(userData);

        messageLabel.setText("Registration successful!\n ID: " + id);
        messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private void saveUserData(String userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            messageLabel.setText("Error saving data: " + e.getMessage());
        }
    }
}