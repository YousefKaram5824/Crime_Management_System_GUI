package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class OfficerProfile extends Switching {
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label rank;
    @FXML
    private Label salary;
    @FXML
    private Label phone;
    @FXML
    private Label department;
    @FXML
    private ListView<String> cases;

    private DataManager userDataManager;
    private DataManager assignedCasesDataManager;

    public void initialize() {
        userDataManager = Main.getDataManager();
        assignedCasesDataManager = Main.getDataManager();
        loadUserProfile();
    }

    private void loadUserProfile() {
        String userId = UserSession.getInstance().getCurrentUserId();
        List<String> casesIds = assignedCasesDataManager.getCasesIdsByOfficerId(userId);
        String userData = userDataManager.getUserDataById(userId);
        String[] userDetails = userData.split(",");
        name.setText(userDetails[0]);
        id.setText(userDetails[1]);
        rank.setText(userDetails[2]);
        salary.setText(userDetails[3] + "$");
        phone.setText(userDetails[4]);
        department.setText(userDetails[6]);
        cases.getItems().addAll(casesIds);
    }
}