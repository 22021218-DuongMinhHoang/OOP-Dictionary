package com.example.hello.management;

import java.io.IOException;

import javafx.event.ActionEvent;
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

  @FXML
  void openRestoreWord(ActionEvent event) {
    try {
      SceneSwitch.changeScene(editPane, "restoreword//restore.fxml");
      editBar.setVisible(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openHistory(ActionEvent event) {
    try {
      SceneSwitch.changeScene(editPane, "history/history.fxml");
      editBar.setVisible(false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openMark(ActionEvent event) {
    try {
      SceneSwitch.changeScene(editPane, "markedWord/mark.fxml");
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
