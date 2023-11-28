package com.example.hello.HangMan;

import java.io.IOException;

import com.example.hello.management.SceneSwitch;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuController {

    @FXML
    private Pane pane;
    @FXML
    void start(MouseEvent event) throws IOException {
        new SceneSwitch(pane, "HangMan/game.fxml");
        System.out.println("ok rooif");
    }

    @FXML
    void help(MouseEvent event) throws IOException {
        new SceneSwitch(pane, "HangMan/instruction.fxml");
    }

    @FXML
    void initialize(){
        pane.setStyle("-fx-background-color: bisque;");
    }

}
