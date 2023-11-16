package com.example.hello.management;

import com.example.hello.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.io.IOException;

public class SceneSwitch {
    public SceneSwitch(Pane currentPane, String fxml) throws IOException {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        // currentPane.getChildren().removeAll();
        currentPane.getChildren().add(pane);
        // pane.setPrefSize(currentPane.getWidth(), currentPane.getHeight());
        pane.setPrefSize(currentPane.getPrefWidth(), currentPane.getPrefHeight());

    }
}
