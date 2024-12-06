package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController extends Switching {

    @FXML
    private TextField username;
    @FXML
    private TextField phone;
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label message;

    @FXML
    public void initialize() {
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
    }

    @FXML
    private void handleRegister() {
        String Name = username.getText();
        String Phone = phone.getText();
        String ID = id.getText();
        String Password = password.getText();
        String PasswordChecker = confirmPassword.getText();
        String gender = male.isSelected() ? "Male" : "Female";

        if (Name.isEmpty() || Phone.isEmpty() || Password.isEmpty() || ID.isEmpty()) {
            message.setText("Please fill all fields");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!ID.startsWith("dep") && !ID.startsWith("chf") && !ID.startsWith("poc")) {
            message.setText("Invalid user type");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!Password.equals(PasswordChecker)) {
            message.setText("Password not match");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        String userData = String.join(",", ID, Name, Phone, Password, gender);
        saveUserData(userData);

        message.setText("Registration successful!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private void saveUserData(String userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            message.setText("Error saving data: " + e.getMessage());
        }
    }
}