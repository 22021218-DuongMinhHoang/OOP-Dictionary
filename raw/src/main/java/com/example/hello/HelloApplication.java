package com.example.hello;

//import com.example.hello.data.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // public static Dictionary myDict = new Dictionary();
    @Override
    public void start(Stage stage) throws IOException {
        // myDict.addFromFile("src\\main\\java\\com\\example\\hello\\data\\dictionaries.txt");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "helloapp\\hello-view.fxml"));
        // FXMLLoader fxmlLoader = new
        // FXMLLoader(HelloApplication.class.getResource("SearchBar/Search.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        String tab = getClass().getResource("helloapp\\tab.css").toExternalForm();
        String search = getClass().getResource("searchbar\\search.css").toExternalForm();
        String translate = getClass().getResource("ggtranslator\\translate.css").toExternalForm();
        // String game = getClass().getResource("game\\Game.css").toExternalForm();

        scene.getStylesheets().addAll(tab, search, translate);

        stage.setTitle("HELLO DICTIONARY");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}