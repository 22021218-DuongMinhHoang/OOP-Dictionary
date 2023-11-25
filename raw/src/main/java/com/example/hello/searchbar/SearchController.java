package com.example.hello.searchbar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.example.hello.data.Word;
import com.example.hello.speech.SoundPlayer;

public class SearchController implements Initializable {
  private Word currentWord;
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
        currentWord = newValue;
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

   @FXML
  void usSound(ActionEvent event) {
      if(currentWord != null){
        new SoundPlayer(currentWord.getWord().toLowerCase(), SoundPlayer.US);
        System.out.println("US sound");
      }
      else{
        System.out.println("null");
      }
  }

  @FXML
  void ukSound(ActionEvent event) {
      if(currentWord != null){
        new SoundPlayer(currentWord.getWord().toLowerCase(), SoundPlayer.UK);
        System.out.println("UK sound");
      }
      else{
        System.out.println("null");
      }
  }
}
