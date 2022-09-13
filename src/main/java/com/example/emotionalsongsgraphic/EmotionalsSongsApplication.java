package com.example.emotionalsongsgraphic;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class EmotionalsSongsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EmotionalsSongsApplication.class.getResource("homeForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 910, 600);
        stage.setTitle("Emotionals Songs");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}