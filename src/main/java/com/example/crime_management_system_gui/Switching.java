package com.example.crime_management_system_gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Switching {

    private void switchScene(String fxmlFile, javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Overloaded method to switch scenes without ActionEvent
    public void switchScene(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        Stage stage = (Stage) Main.getPrimaryStage().getScene().getWindow(); // Assuming you have a method to get the primary stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void switchToForgetPassword(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("forget_password.fxml", actionEvent);
    }

    // Adjusted to use the new switchScene method
    public void switchToPage(String fxmlFile) throws IOException {
        switchScene(fxmlFile);
    }
}