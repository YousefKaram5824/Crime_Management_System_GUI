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
    public void switchTOPoliceOfficer() throws IOException {
        switchToPage("police_officer.fxml");
    }

    @FXML
    public void switchTOPoliceChief() throws IOException {
        switchToPage("police_chief.fxml");
    }

    @FXML
    private void switchTOViewOfficer() throws IOException {
        switchToPage("view_officer.fxml");
    }

    @FXML
    private void switchTOAssignOfficer() throws IOException {
        switchToPage("assign_officer.fxml");
    }

    @FXML
    private void switchTOViewDepartment() throws IOException {
        switchToPage("view_department.fxml");
    }

    @FXML
    private void switchTOCreateDepartment() throws IOException {
        switchToPage("create_department.fxml");
    }

    @FXML
    private void switchTOAddCriminal() throws IOException {
        switchToPage("add_criminal.fxml");
    }

    @FXML
    private void switchTOViewCriminal() throws IOException {
        switchToPage("view_criminal.fxml");
    }

    @FXML
    private void switchTOViewProfileForOfficer() throws IOException {
        switchToPage("view_profile_for_officer.fxml");
    }

    @FXML
    private void switchTOViewProfileForChief() throws IOException {
        switchToPage("view_profile_for_chief.fxml");
    }
}