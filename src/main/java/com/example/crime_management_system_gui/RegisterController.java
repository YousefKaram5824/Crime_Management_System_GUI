package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        if (Name.isEmpty() || Phone.isEmpty() || Password.isEmpty() || ID.isEmpty() || PasswordChecker.isEmpty()) {
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
            message.setText("Passwords do not match");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!isUserIdUnique(ID)) {
            message.setText("User ID already exists");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String userData = String.join(",", ID, Name, Phone, Password, gender);
        saveUserData(userData);

        message.setText("Registration successful!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private boolean isUserIdUnique(String userId) {
        List<String> userData = readUserData();
        for (String data : userData) {
            String[] userDetails = data.split(",");
            if (userDetails[0].equals(userId)) {
                return false;
            }
        }
        return true;
    }

    private List<String> readUserData() {
        List<String> userData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userData.add(line);
            }
        } catch (IOException e) {
            message.setText("Error reading user data: " + e.getMessage());
        }
        return userData;
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