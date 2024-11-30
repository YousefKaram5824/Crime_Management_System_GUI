package com.example.crime_management_system_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 0, 0);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.show();

        scene.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == javafx.scene.input.KeyCode.ESCAPE) {
                Platform.exit();  // Close the application
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}