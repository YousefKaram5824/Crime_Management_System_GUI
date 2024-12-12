package com.example.crime_management_system_gui;

import javafx.fxml.FXML;

import java.io.IOException;

public class PoliceChief extends Switching {

    @FXML
    private void createDepartment() throws IOException {
        switchToPage("pages/create_department.fxml");
    }

    @FXML
    private void viewProfile() throws IOException {
        switchToPage("pages/view_profile.fxml");
    }
}
