package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PoliceOfficers extends Switching implements Initializable {
    @FXML
    private Label message;
    @FXML
    private Label crimeType;
    @FXML
    private Label description;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker lastUpdate;
    @FXML
    private ComboBox<String> criminals;
    @FXML
    private ComboBox<String> cases;
    @FXML
    private Label viewStartDate;
    @FXML
    private Label viewLastUpdate;
    @FXML
    private ListView<String> assignedCriminals;
    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = Main.getDataManager();

        List<String> Criminals = dataManager.getCriminalsData();
        for (String criminal : Criminals) {
            String[] criminalDetails = criminal.split(",");
            criminals.getItems().add(criminalDetails[0]);
        }
        String userId = UserSession.getInstance().getCurrentUserId();
        List<String> casesIds = dataManager.getCasesIdsByOfficerId(userId);
        for (String Cases : casesIds) {
            String[] caseDetails = Cases.split(",");
            cases.getItems().add(caseDetails[0]);
        }
    }

    @FXML
    private void getCaseInfoForSolve() {
        String CaseId = cases.getValue();
        String CaseData = dataManager.getCaseDataById(CaseId);
        String[] caseDetails = CaseData.split(",");
        crimeType.setText(caseDetails[3]);
        description.setText(caseDetails[4]);
        message.setText("");
    }

    @FXML
    private void solve() {
        String CaseId = cases.getValue();
        LocalDate StartDate = startDate.getValue();
        LocalDate LastUpdate = startDate.getValue();
        String Criminal = criminals.getValue();
        String isSolved = "yes";

        if (dataManager.isCaseIsSolved(CaseId)) {
            message.setText("Case is already solved!");
            message.setTextFill(Color.RED);
            return;
        }

        if (CaseId.isEmpty() || StartDate == null || Criminal == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(Color.RED);
            return;
        }

        if (dataManager.isCaseAssignedToCriminal(CaseId, Criminal)) {
            message.setText("Criminal is already assigned to this case!");
            message.setTextFill(Color.RED);
            return;
        }

        String criminalAssigned = String.join(",", CaseId, StartDate.toString(), LastUpdate.toString(), isSolved, Criminal);
        String UpdatedCriminalCases = String.join(",", CaseId, LastUpdate.toString(), Criminal);
        dataManager.getCriminalCasesData().add(criminalAssigned);
        dataManager.getUpdatedCriminalCases().add(UpdatedCriminalCases);
        message.setText("Case Solved successful!");
        message.setTextFill(Color.GREEN);
    }

    @FXML
    private void update() {
        String CaseId = cases.getValue();
        String LastUpdate = String.valueOf(lastUpdate.getValue());
        String Criminal = criminals.getValue();

        if (LastUpdate == null || Criminal == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(Color.RED);
            return;
        }

        if (dataManager.isCaseAssignedToCriminal(CaseId, Criminal)) {
            message.setText("Criminal is already assigned to this case!");
            message.setTextFill(Color.RED);
            return;
        }


        message.setText("Criminal added successfully!");
        message.setTextFill(Color.GREEN);
    }

    @FXML
    private void getCaseInfoForUpdate() {
        assignedCriminals.getItems().clear();
        String CaseId = cases.getValue();
        String SolvedCaseData = dataManager.getSolvedCaseDataById(CaseId);
        String[] caseDetails = SolvedCaseData.split(",");
        viewStartDate.setText(caseDetails[1]);

        String UpdatedCaseData = dataManager.getUpdatedCaseDataById(CaseId);
        String[] updatedCaseDetails = UpdatedCaseData.split(",");
        viewLastUpdate.setText(updatedCaseDetails[1]);

        List<String> criminalIds = dataManager.getCriminalsIdsByCaseId(CaseId);
        assignedCriminals.getItems().addAll(criminalIds);
    }
}
