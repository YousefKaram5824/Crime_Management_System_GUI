package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    private DataManager userDataManager;

    public void initialize() {
        userDataManager = Main.getDataManager();
        loadUserProfile();
    }

    private void loadUserProfile() {
        String userId = UserSession.getInstance().getCurrentUserId();
        String userData = userDataManager.getUserDataById(userId);
        String[] userDetails = userData.split(",");
        name.setText(userDetails[0]);
        id.setText(userDetails[1]);
        rank.setText(userDetails[2]);
        salary.setText(userDetails[3] + "$");
        phone.setText(userDetails[4]);
        department.setText(userDetails[6]);
    }
}