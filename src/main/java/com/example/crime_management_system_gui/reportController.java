package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class reportController extends Switching {

    @FXML
    private TextField username;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea description;
    @FXML
    private Label message;


    @FXML
    private void handleSubmit() {
        String Name = username.getText();
        String Phone = phone.getText();
        LocalDate date = datePicker.getValue();
        String Description = description.getText();

        if (Name.isEmpty() || Phone.isEmpty() || date == null || Description.isEmpty()) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String reportData = String.format("Name: %s\nPhone: %s\nDate: %s\nDescription: %s\n\n", Name, Phone, date, Description);

        saveToFile(reportData);
        clearFields();
        message.setText("Report submitted successfully!");
        message.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private void saveToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reports.txt", true))) {
            writer.write(data);
        } catch (IOException e) {
            showAlert("Error saving report: " + e.getMessage());
        }
    }

    private void clearFields() {
        username.clear();
        phone.clear();
        datePicker.setValue(null);
        description.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}