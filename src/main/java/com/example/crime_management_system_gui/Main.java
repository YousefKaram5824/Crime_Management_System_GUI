package com.example.crime_management_system_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pages/main.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("photos/icon.jpeg")));
        stage.getIcons().add(icon);

        stage.setTitle("Crime Management System");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
}