package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ForgotPassword extends Switching {
    private final DataManager dataManager = Main.getDataManager();
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

        boolean userFound = false;

        for (int i = 0; i < dataManager.getOfficerData().size(); i++) {
            String[] userDetails = dataManager.getOfficerData().get(i).split(",");
            if (userDetails[1].equals(userId)) {
                userDetails[5] = NewPassword;
                dataManager.updateOfficerData(i, String.join(",", userDetails));
                userFound = true;
                break;
            }
        }

        if (userFound) {
            message.setText("Password reset successfully!");
            message.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            message.setText("User ID not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
}