package com.example.crime_management_system_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class viewProfile extends Switching implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label phone;
    @FXML
    private TextField id;
    @FXML
    private Label password;
    @FXML
    private Label gender;
    @FXML
    private Label salary;
    @FXML
    private Label rate;
    @FXML
    private Label message;
    @FXML
    private ChoiceBox<String> rating;

    private DataManager userDataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataManager = Main.getDataManager();
        rating.getItems().addAll("1", "2", "3", "4", "5");
    }

    private String getUserDataById(String userId) {
        for (String data : userDataManager.getUserData()) {
            String[] userDetails = data.split(",");
            if (userDetails[0].equals(userId)) {
                return data;
            }
        }
        return null;
    }

    private void clear() {
        username.setText("");
        phone.setText("");
        password.setText("");
        gender.setText("");
        salary.setText("");
        rate.setText("");
    }

    @FXML
    private void viewUserData() {
        String userId = id.getText();
        String userData = getUserDataById(userId);

        if (userData != null) {
            String[] userDetails = userData.split(",");
            username.setText(userDetails[1]);
            phone.setText(userDetails[2]);
            password.setText(userDetails[3]);
            gender.setText(userDetails[4]);
            salary.setText(userDetails[5] + "$");
            rate.setText(userDetails[6]);
            message.setText("");
        } else {
            message.setText("User id not found!");
            message.setTextFill(javafx.scene.paint.Color.RED);
            clear();
        }
    }

    @FXML
    private void rateUser() {
        /*String userId = id.getText();
        String newRating = rating.getValue();

        if (newRating != null) {
            String userData = getUserDataById(userId);
            if (userData != null) {
                String[] userDetails = userData.split(",");
                userDetails[6] = newRating;
                String updatedUserData = String.join(",", userDetails);
                userDataManager.updateUserData(6, updatedUserData);
                userDataManager.saveUserData();
                message.setText("User rated successfully!");
                message.setTextFill(javafx.scene.paint.Color.GREEN);

            } else {
                message.setText("User id not found!");
                message.setTextFill(javafx.scene.paint.Color.RED);
            }
        } else {
            message.setText("Please select a rating!");
            message.setTextFill(javafx.scene.paint.Color.RED);
        }*/
    }

}
