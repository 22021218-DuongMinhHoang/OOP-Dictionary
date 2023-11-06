package com.example.hello.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hello.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;


public class AddWordController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Word;

    @FXML
    private Button add;

    @FXML
    private TextField meaning;

    @FXML
    private Text notifi;


    @FXML
    void addWord() {
        if (HelloApplication.myDict.findWord(Word.getText()) != null) {
            notifi.setText("Sorry, the word already exists in the dictionary");
        } else {
            String wordText = Word.getText();
            String meaningText = meaning.getText();

            if (wordText != null && !wordText.isEmpty() && meaningText != null && !meaningText.isEmpty()) {
                HelloApplication.myDict.addWord(new Word(wordText.toLowerCase(), meaningText.toLowerCase()));
                notifi.setText("Word successfully added");
            } else {
                notifi.setText("Please enter both word and meaning");
            }

        }
    }

    @FXML
    void initialize() {
        assert Word != null : "fx:id=\"Word\" was not injected: check your FXML file 'addword-view.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'addword-view.fxml'.";
        assert meaning != null : "fx:id=\"meaning\" was not injected: check your FXML file 'addword-view.fxml'.";
        assert notifi != null : "fx:id=\"notifi\" was not injected: check your FXML file 'addword-view.fxml'.";
        Word.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addWord();
                event.consume(); // Consume the event to prevent further processing
            }
        });

        meaning.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addWord();
                event.consume(); // Consume the event to prevent further processing
            }
        });

    }

}
