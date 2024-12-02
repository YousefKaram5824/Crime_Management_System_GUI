package com.example.crime_management_system_gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Control {
    private final Random random = new Random();
    private Scene scene;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField ssnField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private Label messageLabel;

    private void switchScene(String fxmlFile, javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        setupKeyPressEvent();
        stage.setScene(scene);
        stage.show();
    }

    private void setupKeyPressEvent() {
        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if ("admin".equals(username) && "admin".equals(password)) {
            messageLabel.setText("Welcome, " + username + "!");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            messageLabel.setText("Incorrect username or password");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    @FXML
    private void handleRegister() {
        String name = usernameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String ssn = ssnField.getText();
        String password = passwordField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";

        if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || password.isEmpty() || ssn.isEmpty()) {
            messageLabel.setText("Please fill all fields");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        String userId = generateUserId(ssn);
        String userData = String.join(",", userId, name, address, phone, password, gender);
        saveUserData(userData);

        messageLabel.setText("Registration successful!\n ID: " + userId);
        messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);

    }

    private String generateUserId(String ssn) {
        String capitalizedSsn = ssn.toUpperCase();
        int randomDigits = 1 + random.nextInt(999);
        return capitalizedSsn + randomDigits;
    }

    private void saveUserData(String userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            messageLabel.setText("Error saving data: " + e.getMessage());
        }
    }

    public void switchToLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("login.fxml", actionEvent);
    }

    public void switchToRegister(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("register.fxml", actionEvent);
    }

    public void switchToReport(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("report.fxml", actionEvent);
    }

    public void switchToMain(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("main.fxml", actionEvent);
    }

}