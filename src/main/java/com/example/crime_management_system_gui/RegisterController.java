package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends Switching implements Initializable {
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

    private DataManager userDataManager;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataManager = Main.getUserDataManager();
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
        if (!ID.startsWith("chf") && !ID.startsWith("poc") && !ID.startsWith("dep")) {
            message.setText("Invalid user type");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!userDataManager.isUserIdUnique(ID)) {
            message.setText("User ID already exists");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!Password.equals(PasswordChecker)) {
            message.setText("Passwords do not match");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String userData = String.join(",", ID, Name, Phone, Password, gender);
        userDataManager.getUserData().add(userData);
        message.setText("Registration successful!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
    }
}