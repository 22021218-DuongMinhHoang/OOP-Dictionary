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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

  @FXML
  VBox wordBox;

  @FXML
  VBox desBox1;

  @FXML
  VBox desBox2;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    optionsBox.getFocusModel().focus(1);
    // optionsBox.getItems().clear();
    optionsBox.setPrefHeight(optionsBox.getItems().size() * 24 + 2);

    optionsBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {

      @Override
      public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue) {
        currentWord = newValue;
        if (optionsBox.getItems().size() != 0) {
          // searchResult.setText(newValue.getFullDescription());
          List<String> desList = newValue.getDescriptionList();
          wordBox.getChildren().clear();
          desBox1.getChildren().clear();
          desBox2.getChildren().clear();
          Text word = new Text(desList.get(0));
          word.setFill(Color.BROWN);
          word.setFont(Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 25));
          Text pronounce = new Text(desList.get(1));
          pronounce.setFill(Color.BROWN);
          wordBox.getChildren().addAll(word, pronounce);
          System.out.println(wordBox.getChildren().size());
          for (int i = 2; i < desList.size(); i++) {
            Text t = new Text(desList.get(i));
            t.setWrappingWidth(250);
            String str = desList.get(i);
            if (str.equals("danh từ") ||
                str.equals("động từ") ||
                str.equals("tính từ") ||
                str.equals("thán từ") ||
                str.equals("mạo từ") ||
                str.equals("nội động từ") ||
                str.equals("ngoại động từ")) {
              t.setUnderline(true);
              t.setFont(Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 15));
              t.setText("\n" + str + ":");
            }
            t.setFill(Color.BROWN);
            if (desBox1.getChildren().size() < 20) {
              desBox1.getChildren().add(t);
            } else {
              desBox2.getChildren().add(t);
            }
          }
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
    if (currentWord != null) {
      new SoundPlayer(currentWord.getWord().toLowerCase(), SoundPlayer.US);
      System.out.println("US sound");
    } else {
      System.out.println("null");
    }
  }

  @FXML
  void ukSound(ActionEvent event) {
    if (currentWord != null) {
      new SoundPlayer(currentWord.getWord().toLowerCase(), SoundPlayer.UK);
      System.out.println("UK sound");
    } else {
      System.out.println("null");
    }
  }
}
