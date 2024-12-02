package com.example.crime_management_system_gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Control {

    private Scene scene;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
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