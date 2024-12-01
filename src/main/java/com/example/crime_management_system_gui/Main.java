package com.example.crime_management_system_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("maindemo.fxml")));
        Scene scene = new Scene(root);/*
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(1550);
        stage.setHeight(850);*/
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == javafx.scene.input.KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}