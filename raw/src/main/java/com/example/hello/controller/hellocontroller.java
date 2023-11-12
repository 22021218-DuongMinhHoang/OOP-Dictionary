package com.example.hello.controller;

//raw\src\main\resources\com\example\hello\controller\resources\com\example\hello\controller

import com.example.hello.data.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class hellocontroller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuButton moreButton;

    @FXML
    private MenuItem game;
    @FXML
    private MenuItem add;

    @FXML
    private MenuItem remove;

    @FXML
    private MenuItem search;

    @FXML
    private ImageView start;
    @FXML
    private MenuItem textTranslate;


    @FXML
    void Search(ActionEvent event) {
        try {
            new SceneSwitch(pane, "fxml/search.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void add(ActionEvent event) {
        try {
            new SceneSwitch(pane, "fxml/addword-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void game(ActionEvent event) {
    }

    @FXML
    void remove(ActionEvent event) {
        try {
            new SceneSwitch(pane, "fxml/remove-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void startprogram(MouseEvent event) {
        try {
            new SceneSwitch(pane, "fxml/search.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void translateText(ActionEvent event) {
        try {
            new SceneSwitch(pane, "fxml/SentencesTranslating.fxml");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert moreButton != null : "fx:id=\"moreButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert remove != null : "fx:id=\"remove\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'hello-view.fxml'.";

    }
}
