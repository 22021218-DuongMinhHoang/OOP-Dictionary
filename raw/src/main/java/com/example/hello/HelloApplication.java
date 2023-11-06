package com.example.hello;

import com.example.hello.data.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    public static Dictionary myDict = new Dictionary();
    @Override
    public void start(Stage stage) throws IOException {
        myDict.addFromFile("E:\\study_document\\JAVA\\JAVA_OOP\\OOP-Dictionary\\raw\\src\\main\\java\\com\\example\\hello\\data\\dictionaries.txt");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("HELLO DICTIONARY");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}