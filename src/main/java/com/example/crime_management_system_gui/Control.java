package com.example.crime_management_system_gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Control {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // General method for switching scenes
    private void switchScene(String fxmlFile, javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
        switchScene("maindemo.fxml", actionEvent);
    }
}