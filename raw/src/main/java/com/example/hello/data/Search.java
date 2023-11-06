package com.example.hello.data;
import com.example.hello.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Search {
    private Dictionary myDict;
    public void setDict(Dictionary myDict){
        this.myDict = myDict;
    }
    //private Collection<String> possibleSuggestions = new HashSet<>(Dictionary.suggestions);
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Text meaning;

    @FXML
    private Button searchButton;

    @FXML
    private TextField textField;

    @FXML
    private Text word;

    @FXML
    void search(MouseEvent event) {
        Word myWord = myDict.findWord(textField.getText());
        word.setText(textField.getText());
        textField.clear();
        if(myWord == null){
            meaning.setText("Not Found Meaning");
        }
        else{
            meaning.setText(myWord.getMeaning());
        }
    }
    private void performSearch() {
        Word myWord = myDict.findWord(textField.getText());
        word.setText(textField.getText());
        textField.clear();
        if (myWord == null) {
            meaning.setText("Not Found Meaning");
        } else {
            meaning.setText(myWord.getMeaning());
        }
    }

    @FXML
    void initialize() {
        assert meaning != null : "fx:id=\"meaning\" was not injected: check your FXML file 'search.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'search.fxml'.";
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'search.fxml'.";
        assert word != null : "fx:id=\"word\" was not injected: check your FXML file 'search.fxml'.";
        myDict = HelloApplication.myDict;
        // Add an action listener to the searchButton
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                performSearch();
            }
        });

        // Add a key press listener to the textField
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    performSearch();
                }
            }
        });
    }
}
