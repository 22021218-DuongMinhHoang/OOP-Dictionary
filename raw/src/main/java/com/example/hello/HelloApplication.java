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
<<<<<<< Updated upstream
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "helloapp\\hello-view.fxml"));
        // FXMLLoader fxmlLoader = new
        // FXMLLoader(HelloApplication.class.getResource("SearchBar/Search.fxml"));
=======
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("helloapp/hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addWord/add_word.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("deleteWord/delete_word.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editWord/edit_word.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HangMan/menu.fxml"));
>>>>>>> Stashed changes
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("helloapp\\tab.css").toExternalForm());

        stage.setTitle("HELLO DICTIONARY");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}