package com.example.crime_management_system_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = new Scene(root);
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