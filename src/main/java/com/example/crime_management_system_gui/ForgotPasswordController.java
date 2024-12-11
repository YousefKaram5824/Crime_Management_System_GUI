package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ForgotPasswordController extends Switching {
    @FXML
    private TextField id;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField newPasswordConfirm;
    @FXML
    private Label message;

    private DataManager userDataManager;

    @FXML
    public void initialize() {
        userDataManager = Main.getUserDataManager();
    }

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

        for (int i = 0; i < userDataManager.getUserData().size(); i++) {
            String[] userDetails = userDataManager.getUserData().get(i).split(",");
            if (userDetails[0].equals(userId)) {
                userDetails[3] = NewPassword;
                userDataManager.updateUserData(i, String.join(",", userDetails));
                userFound = true;
                break;
            }
        }

        if (userFound) {
            message.setText("Password reset successfully!");
            message.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            message.setText("User ID not found.");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
}