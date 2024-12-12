package com.example.crime_management_system_gui;

import javafx.fxml.FXML;

import java.io.IOException;

public class PoliceChief extends Switching {

    @FXML
    private void viewDepartment() throws IOException {
        switchToPage("view_department.fxml");
    }

    @FXML
    private void viewCriminal() throws IOException {
        switchToPage("view_criminal.fxml");
    }

    @FXML
    private void viewOfficer() throws IOException {
        switchToPage("view_officer.fxml");
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
    private void assignOfficer() throws IOException {
        switchToPage("assign_officer.fxml");
    }
}
