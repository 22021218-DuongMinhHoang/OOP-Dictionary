package com.example.hello.management;

import com.example.hello.HelloApplication;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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

    public static void changeTab(Tab tab, String fxml, int oldTabIndex, int newTabIndex) {
        try {
            Pane root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
            int direction = 1;
            if (newTabIndex < oldTabIndex) {
                direction = -1;
            }
            root.translateXProperty().set(direction * tab.getTabPane().getWidth());

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_OUT);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.25), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            tab.setContent(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
