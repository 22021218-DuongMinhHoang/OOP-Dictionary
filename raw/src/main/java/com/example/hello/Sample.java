package com.example.hello;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hello.data.Dictionary;
import com.example.hello.data.Word;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.*;

public class Sample  {
    Dictionary myDict = new Dictionary();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button esc;
    @FXML
    private AnchorPane mypane;
    @FXML
    private TextField input;

    @FXML
    private TextField meaning;

    @FXML
    private TextField word;

    @FXML
    void exit(MouseEvent event) {
            mypane.setVisible(false);
            System.out.println("hello");
    }
    @FXML
    void lookUp(MouseEvent event) {

        Word myWord = myDict.findWord(input.getText());
        if(myWord != null) {
            word.setText(input.getText());
            meaning.setText(myWord.getMeaning());
        }
        else{
            word.setText(input.getText());
            meaning.setText("Meaning not found");
        }
    }
//E:/study_document/JAVA/JAVA_OOP/Hello/src/main/java/com/example/hello/data/vocab.txt
    @FXML
    void initialize() {
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'sample.fxml'.";
        assert meaning != null : "fx:id=\"meaning\" was not injected: check your FXML file 'sample.fxml'.";
        assert word != null : "fx:id=\"word\" was not injected: check your FXML file 'sample.fxml'.";
        myDict.addFromFile("E:/study_document/JAVA/JAVA_OOP/Hello/src/main/java/com/example/hello/data/dictionaries.txt");
        mypane.setFocusTraversable(true);
        mypane.requestFocus();

    }



}
