package com.example.hello;

//import com.example.hello.data.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // public static Dictionary myDict = new Dictionary();
    @Override
    public void start(Stage stage) throws IOException {
        // myDict.addFromFile("src\\main\\java\\com\\example\\hello\\data\\dictionaries.txt");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("helloapp/hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addWord/add_word.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("deleteWord/delete_word.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("helloapp\\tab.css").toExternalForm());
        Image icon = new Image(getClass().getResource("resources\\logovnu.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("HELLO DICTIONARY");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}