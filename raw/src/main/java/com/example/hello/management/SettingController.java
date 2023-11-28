package com.example.hello.management;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingController {
  @FXML
  private Pane editPane;

  @FXML
  private VBox editBar;

  public void openAddWord() {
    try {
      SceneSwitch.changeScene(editPane, "addWord/add_word.fxml");
      editBar.setVisible(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openEditWord() {
    try {
      SceneSwitch.changeScene(editPane, "editWord/edit_word.fxml");
      editBar.setVisible(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openDeleteWord() {
    try {
      SceneSwitch.changeScene(editPane, "deleteWord/delete_word.fxml");
      editBar.setVisible(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void back() {
    editPane.getChildren().clear();
    editBar.setVisible(true);
  }
}
