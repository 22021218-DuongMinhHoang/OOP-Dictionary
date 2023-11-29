package com.example.hello.markedWord;

import com.example.hello.searchbar.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MarkController {
    public static String ANHVIET = Connect.ANHVIET;
    public static String VIETANH = Connect.VIETANH;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label wordDisplay;
    private String currentLang;
    private String selectedWord;
    @FXML
    private Button langChangeBut;

    @FXML
    void changeLang(ActionEvent event) {
        currentLang = currentLang.equals(ANHVIET) ? VIETANH : ANHVIET;
        String EntoVtoEn = langChangeBut.getText();
        langChangeBut.setText(EntoVtoEn.equalsIgnoreCase("Tiếng Anh") ? "Tiếng Việt" : "Tiếng Anh");
        display();
    }

    @FXML
    void initialize() {
        currentLang = ANHVIET;
        display();
    }

    private void display() {
        ObservableList<String> observableWords = FXCollections.observableArrayList(Connect.getMarkedWords(currentLang));
        listView.setItems(observableWords);
        if (observableWords == null || observableWords.isEmpty()) {
            listView.setPlaceholder(new Label("Không có từ nào được đánh dấu yêu thích"));
        }
        listView.setOnMouseClicked(event -> {
            selectedWord = listView.getSelectionModel().getSelectedItem();
            wordDisplay.setText(Connect.getWord(selectedWord, currentLang).getFullDescription());
            System.out.println("Clicked on: " + selectedWord);
        });
    }

}

/*
 * ALTER TABLE av
 * ADD COLUMN isMarked BOOLEAN;
 * UPDATE av
 * SET isMarked = 0;
 * 
 * ALTER TABLE va
 * ADD COLUMN isMarked BOOLEAN;
 * UPDATE va
 * SET isMarked = 0;
 */