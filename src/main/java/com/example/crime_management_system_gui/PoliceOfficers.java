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
    private DataManager reportDataManager;
    private DataManager assingedCriminalDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataManager criminalDataManager = Main.getDataManager();
        reportDataManager = Main.getDataManager();
        assingedCriminalDataManager = Main.getDataManager();

        List<String> Criminals = criminalDataManager.getCriminalsData();
        for (String criminal : Criminals) {
            String[] criminalDetails = criminal.split(",");
            criminals.getItems().add(criminalDetails[0]);
        }
        String userId = UserSession.getInstance().getCurrentUserId();
        List<String> casesIds = assingedCriminalDataManager.getCasesIdsByOfficerId(userId);
        for (String Cases : casesIds) {
            String[] caseDetails = Cases.split(",");
            cases.getItems().add(caseDetails[0]);
        }
    }

    @FXML
    private void getCaseInfoForSolve() {
        String CaseId = cases.getValue();
        String CaseData = reportDataManager.getCaseDataById(CaseId);
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

        if(assingedCriminalDataManager.isCaseIsSolved()){
            message.setText("Case is already solved!");
            message.setTextFill(Color.RED);
            return;
        }

        if (CaseId.isEmpty() || StartDate == null || Criminal == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(Color.RED);
            return;
        }

        if (assingedCriminalDataManager.isCaseAssignedToCriminal(CaseId, Criminal)) {
            message.setText("Criminal is already assigned to this case!");
            message.setTextFill(Color.RED);
            return;
        }

        String criminalAssigned = String.join(",", CaseId, StartDate.toString(), LastUpdate.toString(), isSolved, Criminal);
        assingedCriminalDataManager.getCriminalCasesData().add(criminalAssigned);
        message.setText("Case Solved successful!");
        message.setTextFill(Color.GREEN);
    }

    @FXML
    private void getCaseInfoForUpdate() {
        String CaseId = cases.getValue();
        String SolvedCaseData = reportDataManager.getSolvedCaseDataById(CaseId);
        String[] caseDetails = SolvedCaseData.split(",");
        viewStartDate.setText(caseDetails[1]);
        viewLastUpdate.setText(caseDetails[2]);

        List<String> criminalIds = assingedCriminalDataManager.getCriminalsIdsByCaseId(CaseId);
        assignedCriminals.getItems().addAll(criminalIds);
    }

    @FXML
    private void update() {
        String CaseId = cases.getValue();
        LocalDate LastUpdate = lastUpdate.getValue();
        String Criminal = criminals.getValue();

        if (LastUpdate == null || Criminal == null) {
            message.setText("Please fill all fields!");
            message.setTextFill(Color.RED);
            return;
        }

        if (assingedCriminalDataManager.isCaseAssignedToCriminal(CaseId, Criminal)) {
            message.setText("Criminal is already assigned to this case!");
            message.setTextFill(Color.RED);
            return;
        }

        String SolvedCaseData = reportDataManager.getSolvedCaseDataById(CaseId);

        if (SolvedCaseData == null || SolvedCaseData.isEmpty()) {
            message.setText("No data found for the given case ID!");
            message.setTextFill(Color.RED);
            return;
        }

        String[] caseDetails = SolvedCaseData.split(",");
        String[] assignedCriminals = caseDetails.length > 4 ? caseDetails[4].split(",") : new String[0];

        for (String assignedCriminal : assignedCriminals) {
            if (assignedCriminal.equals(Criminal)) {
                message.setText("This criminal is already assigned to the case!");
                message.setTextFill(Color.RED);
                return;
            }
        }

        caseDetails[3] = String.valueOf(LastUpdate);
        String updatedCriminals = caseDetails.length > 4 ? caseDetails[4] : "";

        if (!updatedCriminals.isEmpty()) {
            updatedCriminals += ",";
        }
        updatedCriminals += Criminal;

        String updatedCaseData = String.join(",", caseDetails[0], caseDetails[1], caseDetails[2], caseDetails[3], updatedCriminals);

        assingedCriminalDataManager.updateCriminalCasesData(CaseId, updatedCaseData);

        message.setText("Criminal added successfully!");
        message.setTextFill(Color.GREEN);
    }
}
