package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class reportController extends Switching implements Initializable {

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
    private ComboBox<String> crimeType;

    private int reportId;

    @FXML
    private void handleSubmit() {
        String Name = username.getText();
        String Phone = phone.getText();
        LocalDate date = datePicker.getValue();
        String Description = description.getText();
        String CrimeType = crimeType.getValue();

        if (Name.isEmpty() || Phone.isEmpty() || date == null || Description.isEmpty() || CrimeType == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        String reportData = String.format("Report ID: %d\nName: %s\nPhone: %s\nDate: %s\nCrime Type: %s\nDescription: %s\n\n", reportId, Name, Phone, date, CrimeType, Description);

        saveToFile(reportData);
        saveLastReportId();
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
        crimeType.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        crimeType.getItems().addAll("Murder", "Robbery", "Assault");
        reportId = loadLastReportId();
    }

    private int loadLastReportId() {
        int lastId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader("lastReportId.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                lastId = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
        return lastId + 1;
    }

    private void saveLastReportId() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lastReportId.txt"))) {
            writer.write(String.valueOf(reportId));
        } catch (IOException e) {
            showAlert("Error saving last report ID: " + e.getMessage());
        }
    }
}