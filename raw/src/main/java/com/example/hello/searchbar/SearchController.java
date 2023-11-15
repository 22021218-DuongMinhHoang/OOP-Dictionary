package com.example.hello.searchbar;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import com.example.hello.data.Word;

public class SearchController implements Initializable {
  Timer searchTimer;
  ObservableList<Word> hq;

  private class searchTask extends TimerTask {
    @Override
    public void run() {
      Platform.runLater(() -> {
        if (!searchBar.getText().trim().equals("")) {
          hq = Connect.findWords(searchBar.getText(), Connect.ANHVIET);
          optionsBox.setItems(hq);
          optionsBox.setPrefHeight(hq.size() * 24 + 2);
        }
      });
    }
  }

  @FXML
  TextField searchBar;

  @FXML
  ListView<Word> optionsBox;

  @FXML
  Label searchResult;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    optionsBox.getFocusModel().focus(1);

    optionsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {

      @Override
      public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue) {
        if (optionsBox.getItems().size() != 0) {
          searchResult.setText(newValue.getFullDescription());
          searchBar.setText("");
        }
      }
    });

    searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
      optionsBox.getItems().clear();
      optionsBox.setPrefHeight(0);
      if (!newValue.trim().isEmpty()) {
        searchTimer = new Timer();
        searchTimer.schedule(new searchTask(), 250);
      }
    });
  }
}
