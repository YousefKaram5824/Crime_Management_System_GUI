package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class reportController extends Switching {

    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label messageLabel;


    @FXML
    private void handleSubmit() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        LocalDate date = datePicker.getValue();
        String description = descriptionArea.getText();

        if (name.isEmpty() || phone.isEmpty() || date == null || description.isEmpty()) {
            messageLabel.setText("Please fill all fields!");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        String reportData = String.format("Name: %s\tPhone: %s\tDate: %s\tDescription: %s\n\n", name, phone, date, description);

        saveToFile(reportData);
        clearFields();
        messageLabel.setText("Report submitted successfully!");
        messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
    }

    private void saveToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reports.txt", true))) {
            writer.write(data);
        } catch (IOException e) {
            showAlert("Error saving report: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        datePicker.setValue(null);
        descriptionArea.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}