package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ForgotPasswordController extends Switching {

    @FXML
    private TextField id;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField newPasswordConfirm;
    @FXML
    private Label message;

    @FXML
    private void handleResetPassword() {
        String userId = id.getText();
        String NewPassword = newPassword.getText();
        String confirmPassword = newPasswordConfirm.getText();

        if (userId.isEmpty() || NewPassword.isEmpty() || confirmPassword.isEmpty()) {
            message.setText("Please fill in all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        if (!NewPassword.equals(confirmPassword)) {
            message.setText("Passwords do not match!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        List<String> userData = readUserData();
        boolean userFound = false;

        for (int i = 0; i < userData.size(); i++) {
            String[] userDetails = userData.get(i).split(",");
            if (userDetails[0].equals(userId)) {
                userDetails[3] = NewPassword;
                userData.set(i, String.join(",", userDetails));
                userFound = true;
                break;
            }
        }

        if (userFound) {
            writeUserData(userData);
            message.setText("Password reset successfully!");
            message.setTextFill(javafx.scene.paint.Color.GREEN);
//            clearFields();
        } else {
            message.setText("User ID not found.");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
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

    private void writeUserData(List<String> userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (String data : userData) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            message.setText("Error writing user data: " + e.getMessage());
        }
    }

    /*private void clearFields() {
        id.clear();
        newPassword.clear();
        newPasswordConfirm.clear();
    }*/
}