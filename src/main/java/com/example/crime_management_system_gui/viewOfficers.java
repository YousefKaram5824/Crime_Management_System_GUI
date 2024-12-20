package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class viewOfficers extends Switching implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label salary;
    @FXML
    private TextField id;
    @FXML
    private Label rank;
    @FXML
    private Label department;
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> ranking;
    @FXML
    private ListView<String> cases;

    private DataManager dataManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = Main.getDataManager();
        ranking.getItems().addAll("First Lieutenant", "Second Lieutenant", "Captain", "Major", "lieutenant Colonel");
    }

    private void clear() {
        username.setText("");
        salary.setText("");
        rank.setText("");
        department.setText("");
        cases.getItems().clear();
    }

    @FXML
    private void viewUserData() {
        clear();
        String userId = id.getText();
        String userData = dataManager.getUserDataById(userId);
        List<String> casesIds = dataManager.getCasesIdsByOfficerId(userId);

        if (userData != null) {
            String[] userDetails = userData.split(",");
            username.setText(userDetails[0]);
            rank.setText(userDetails[2]);
            salary.setText(userDetails[3] + "$");
            department.setText(userDetails[6]);
            cases.getItems().addAll(casesIds);
            message.setText("");
        } else {
            message.setText("User id not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clear();
        }
    }

    @FXML
    private void rateUser() {
        String userId = id.getText();
        String newRank = ranking.getValue();
        if (newRank != null) {
            String userData = dataManager.getUserDataById(userId);
            if (userData != null) {
                for (int i = 0; i < dataManager.getUserData().size(); i++) {
                    String[] userDetails = dataManager.getUserData().get(i).split(",");
                    if (userDetails[1].equals(userId)) {
                        userDetails[2] = newRank;
                        dataManager.updateUserData(i, String.join(",", userDetails));
                        viewUserData();
                        break;
                    }
                }
                message.setText("User rated successfully!");
                message.setTextFill(javafx.scene.paint.Color.GREEN);
            } else {
                message.setText("User id not found!");
                message.setTextFill(javafx.scene.paint.Color.RED);
            }
        } else {
            message.setText("Please select a rating!");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
}
