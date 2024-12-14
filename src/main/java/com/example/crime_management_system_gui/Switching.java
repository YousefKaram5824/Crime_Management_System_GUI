package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
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

    public void switchScene(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        Stage stage = (Stage) Main.getPrimaryStage().getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchToPage(String fxmlFile) throws IOException {
        switchScene(fxmlFile);
    }

    @FXML
    private void switchToLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("login.fxml", actionEvent);
    }

    @FXML
    private void switchToRegister(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("register.fxml", actionEvent);
    }

    @FXML
    private void switchToReport(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("report.fxml", actionEvent);
    }

    @FXML
    private void switchToMain(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("main.fxml", actionEvent);
    }

    @FXML
    private void switchToForgetPassword(javafx.event.ActionEvent actionEvent) throws IOException {
        switchScene("forget_password.fxml", actionEvent);
    }

    public void switchTOPoliceOfficer() throws IOException {
        switchToPage("police_officer.fxml");
    }

    public void switchTOPoliceChief() throws IOException {
        switchToPage("police_chief.fxml");
    }

    @FXML
    private void backToPoliceChief() throws IOException {
        switchToPage("police_chief.fxml");
    }

    @FXML
    private void viewOfficer() throws IOException {
        switchToPage("view_officer.fxml");
    }

    @FXML
    private void assignOfficer() throws IOException {
        switchToPage("assign_officer.fxml");
    }

    @FXML
    private void viewDepartment() throws IOException {
        switchToPage("view_department.fxml");
    }

    @FXML
    private void createDepartment() throws IOException {
        switchToPage("create_department.fxml");
    }

    @FXML
    private void addCriminal() throws IOException {
        switchToPage("add_criminal.fxml");
    }

    @FXML
    private void viewCriminal() throws IOException {
        switchToPage("view_criminal.fxml");
    }
}