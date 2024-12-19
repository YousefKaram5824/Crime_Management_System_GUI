package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PoliceOfficers extends Switching implements Initializable {
    @FXML
    private TextField caseId;
    @FXML
    private Label message;
    @FXML
    private Label crimeType;
    @FXML
    private Label description;
    @FXML
    private DatePicker startDate;
    @FXML
    private ComboBox<String> criminals;

    private DataManager reportDataManager;
    private DataManager assignDataManager;
    private DataManager assingedCriminalDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportDataManager = Main.getDataManager();
        assignDataManager = Main.getDataManager();
        DataManager criminalDataManager = Main.getDataManager();
        assingedCriminalDataManager = Main.getDataManager();
        List<String> Criminals = criminalDataManager.getCriminalsData();
        for (String criminal : Criminals) {
            String[] criminalDetails = criminal.split(",");
            criminals.getItems().add(criminalDetails[0]);
        }
    }

    @FXML
    private void getCaseInfo() {
        String CaseId = caseId.getText();
        String UserId = UserSession.getInstance().getCurrentUserId();
        if (!assignDataManager.isCaseAssignedToOfficer(CaseId, UserId)) {
            String CaseData = reportDataManager.getCaseDataById(CaseId);
            if (CaseData != null) {
                String[] caseDetails = CaseData.split(",");
                crimeType.setText(caseDetails[3]);
                description.setText(caseDetails[4]);
                message.setText("");
            } else {
                message.setText("No data for this case!");
                message.setTextFill(Color.RED);
            }
        } else {
            message.setText("Case id not assigned to you!");
            message.setTextFill(Color.RED);
            clearCaseFields();
        }
    }

    private void clearCaseFields() {
        crimeType.setText("");
        description.setText("");
    }

    @FXML
    private void solve() {
        String CaseId = caseId.getText();
        LocalDate StartDate = startDate.getValue();
        LocalDate LastUpdate = startDate.getValue();
        String Criminal = criminals.getValue();

        if (CaseId.isEmpty() || StartDate == null || Criminal == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(Color.RED);
            return;
        }

        if (!assingedCriminalDataManager.isCaseAssignedToCriminal(CaseId, Criminal)) {
            message.setText("Criminal is already assigned to this case!");
            message.setTextFill(Color.RED);
            return;
        }

        String criminalAssigned = String.join(",", CaseId, Criminal, StartDate.toString(), LastUpdate.toString());
        assingedCriminalDataManager.getCriminalCasesData().add(criminalAssigned);
        message.setText("Case Solved successful!");
        message.setTextFill(Color.GREEN);
    }
}
