package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Switching {

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
    public void switchToLogin() throws IOException {
        switchToPage("login.fxml");
    }

    @FXML
    public void switchToRegister() throws IOException {
        switchToPage("register.fxml");
    }

    @FXML
    public void switchToReport() throws IOException {
        switchToPage("report.fxml");
    }

    @FXML
    public void switchToMain() throws IOException {
        switchToPage("main.fxml");
    }

    @FXML
    public void switchToForgetPassword() throws IOException {
        switchToPage("forget_password.fxml");
    }

    @FXML
    public void switchToPoliceOfficer() throws IOException {
        switchToPage("police_officer.fxml");
    }

    @FXML
    public void switchToPoliceChief() throws IOException {
        switchToPage("police_chief.fxml");
    }

    @FXML
    private void switchToViewOfficer() throws IOException {
        switchToPage("view_officer.fxml");
    }

    @FXML
    private void switchToAssignOfficer() throws IOException {
        switchToPage("assign.fxml");
    }

    @FXML
    private void switchToViewDepartment() throws IOException {
        switchToPage("view_department.fxml");
    }

    @FXML
    private void switchToCreateDepartment() throws IOException {
        switchToPage("create_department.fxml");
    }

    @FXML
    private void switchToAddCriminal() throws IOException {
        switchToPage("add_criminal.fxml");
    }

    @FXML
    private void switchToViewCriminal() throws IOException {
        switchToPage("view_criminal.fxml");
    }

    @FXML
    private void switchToViewProfile() throws IOException {
        switchToPage("view_profile.fxml");
    }

    @FXML
    private void switchToSolve() throws IOException {
        switchToPage("solve.fxml");
    }

    @FXML
    private void switchToUpdate() throws IOException {
        switchToPage("update.fxml");
    }
}