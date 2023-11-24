package com.example.hello.helloapp;

//raw\src\main\resources\com\example\hello\controller\resources\com\example\hello\controller

import com.example.hello.management.SceneSwitch;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class hellocontroller {
    private int oldTabIndex = 1;
    @FXML
    private Tab tabsearch;

    @FXML
    private Tab tabhome;

    @FXML
    private Tab tabtranslate;

    @FXML
    private Tab tabgame;

    @FXML
    private TabPane mainPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Pane translate;
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
    private Pane search;

    @FXML
    private ImageView start;
    @FXML
    private MenuItem textTranslate;

    @FXML
    private Tab t1;

    @FXML
    void Search(ActionEvent event) {

    }

    // @FXML
    // void add(ActionEvent event) {
    // try {
    // new SceneSwitch(pane, "fxml/addword-view.fxml");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    // @FXML
    // void game(ActionEvent event) {
    // }

    // @FXML
    // void remove(ActionEvent event) {
    // try {
    // new SceneSwitch(pane, "fxml/remove-view.fxml");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    @FXML
    void startprogram(MouseEvent event) {

    }

    @FXML
    void translateText(ActionEvent event) {

    }

    @FXML
    void initialize() {

        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert moreButton != null : "fx:id=\"moreButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        // assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file
        // 'hello-view.fxml'.";
        assert remove != null : "fx:id=\"remove\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'hello-view.fxml'.";

        mainPane.getSelectionModel().select(1);

        // SceneSwitch.loadFXML(tabsearch, "searchbar/search.fxml");
        tabsearch.setOnSelectionChanged(event -> {
            if (tabsearch.isSelected()) {
                SceneSwitch.changeTab(tabsearch, "searchbar/search.fxml",
                        oldTabIndex, 0);
                oldTabIndex = 0;
            }
        });

        tabtranslate.setOnSelectionChanged(event -> {
            if (tabtranslate.isSelected()) {
                SceneSwitch.changeTab(tabtranslate, "ggtranslator/SentencesTranslating.fxml",
                        oldTabIndex, 2);
                oldTabIndex = 2;
            }
        });

        tabhome.setOnSelectionChanged(event -> {
            if (tabhome.isSelected()) {
                SceneSwitch.changeTab(tabhome, "ggtranslator/SentencesTranslating.fxml",
                        oldTabIndex, 1);
                oldTabIndex = 1;
            }
        });

        tabgame.setOnSelectionChanged(event -> {
            if (tabgame.isSelected()) {
                SceneSwitch.changeTab(tabgame, "ggtranslator/SentencesTranslating.fxml",
                        oldTabIndex, 3);
                oldTabIndex = 3;
            }
        });

    }
}
