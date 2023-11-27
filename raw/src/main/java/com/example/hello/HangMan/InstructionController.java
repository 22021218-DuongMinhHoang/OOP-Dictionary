package com.example.hello.HangMan;

import java.io.IOException;

import com.example.hello.management.SceneSwitch;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class InstructionController {
    @FXML
    private Pane pane;

    @FXML
    void back(MouseEvent event) throws IOException {
        new SceneSwitch(pane, "HangMan/menu.fxml");
    }

    @FXML
    void initialize() {
        pane.setStyle("-fx-background-color: bisque;");
    }
}
