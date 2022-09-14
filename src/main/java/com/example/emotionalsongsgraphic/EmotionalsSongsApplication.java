package com.example.emotionalsongsgraphic;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmotionalsSongsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        EmotionalsSongsController ec = new EmotionalsSongsController();
        File cookieReader = new File("src/main/resources/com/example/emotionalsongsgraphic/cookie.txt");
        Scanner cookieLine = new Scanner(cookieReader);
        String ena="";
        while(cookieLine.hasNextLine()) {
            ena = cookieLine.nextLine();
        }
        ec.setEnabled(ena.equals("true"));

        FXMLLoader fxmlLoader;
        if(!ec.isEnabled()) {
            fxmlLoader = new FXMLLoader(EmotionalsSongsApplication.class.getResource("homeForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 910, 600);
            stage.setTitle("Emotionals Songs - Benvenuti");
            stage.setScene(scene);
        }else{
            fxmlLoader = new FXMLLoader(EmotionalsSongsApplication.class.getResource("SearchForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 910, 600);
            stage.setTitle("Emotionals Songs - Search");
            stage.setScene(scene);
        }
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}