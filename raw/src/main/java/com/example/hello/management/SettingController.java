package com.example.hello.management;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingController {
  @FXML
  private Pane editPane;

  @FXML
  private VBox editBar;

  @FXML
  private Button backButton;

  public void openAddWord() {
    try {
      editPane.setVisible(true);
      SceneSwitch.changeScene(editPane, "addWord/add_word.fxml");
      editBar.setVisible(false);
      backButton.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openEditWord() {
    try {
      editPane.setVisible(true);
      SceneSwitch.changeScene(editPane, "editWord/edit_word.fxml");
      editBar.setVisible(false);
      backButton.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openDeleteWord() {
    try {
      editPane.setVisible(true);
      SceneSwitch.changeScene(editPane, "deleteWord/delete_word.fxml");
      editBar.setVisible(false);
      backButton.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openRestoreWord(ActionEvent event) {
    try {
      editPane.setVisible(true);
      SceneSwitch.changeScene(editPane, "restoreword//restore.fxml");
      editBar.setVisible(false);
      backButton.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openHistory(ActionEvent event) {
    try {
      editPane.setVisible(true);
      SceneSwitch.changeScene(editPane, "history/history.fxml");
      editBar.setVisible(false);
      backButton.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openMark(ActionEvent event) {
    try {
      editPane.setVisible(true);
      SceneSwitch.changeScene(editPane, "markedWord/mark.fxml");
      editBar.setVisible(false);
      backButton.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void back() {
    editPane.getChildren().clear();
    editBar.setVisible(true);
    editPane.setVisible(false);
    backButton.setVisible(false);
  }
}
