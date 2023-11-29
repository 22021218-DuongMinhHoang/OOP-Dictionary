package com.example.hello.helloapp;

//raw\src\main\resources\com\example\hello\controller\resources\com\example\hello\controller

import com.example.hello.management.SceneSwitch;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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
    private Tab tabsetting;

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
    private ImageView background;

    @FXML
    private ImageView movingBackground;

    @FXML
    private Button startButton;

    @FXML
    private Label homeLabel;

    @FXML
    private Label searchLabel;

    @FXML
    private Label translateLabel;

    @FXML
    private Label gameLabel;

    @FXML
    private Label settingsLabel;

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
        searchLabel.setVisible(false);
        translateLabel.setVisible(false);
        homeLabel.setVisible(false);
        gameLabel.setVisible(false);
        settingsLabel.setVisible(false);

        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert moreButton != null : "fx:id=\"moreButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        // assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file
        // 'hello-view.fxml'.";
        assert remove != null : "fx:id=\"remove\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'hello-view.fxml'.";

        mainPane.getSelectionModel().select(2);

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
                        oldTabIndex, 1);
                oldTabIndex = 1;
            }
        });

        tabhome.setOnSelectionChanged(event -> {
            if (tabhome.isSelected()) {
                SceneSwitch.changeTab(tabhome, "helloapp/tabhome.fxml",
                        oldTabIndex, 2);
                oldTabIndex = 2;
            }
        });

        tabgame.setOnSelectionChanged(event -> {
            if (tabgame.isSelected()) {
                SceneSwitch.changeTab(tabgame, "game/mainGame.fxml",
                        oldTabIndex, 3);
                oldTabIndex = 3;
            }
        });

        tabsetting.setOnSelectionChanged(event -> {
            if (tabsetting.isSelected()) {
                SceneSwitch.changeTab(tabsetting, "management/setting.fxml",
                        oldTabIndex, 4);
                oldTabIndex = 4;
            }
        });
    }

    public void start() {
        // SceneSwitch.changeTabHome(tabhome);
        SceneSwitch.changeTab(tabhome, "helloapp/tabhome.fxml",
                oldTabIndex, 2);
        oldTabIndex = 2;

        startButton.setVisible(false);

        TranslateTransition home = new TranslateTransition(Duration.seconds(5), mainPane);
        home.setByY(-600);
        home.setCycleCount(1);
        home.play();

        TranslateTransition image = new TranslateTransition(Duration.seconds(5), background);
        image.setByY(304.5);
        image.setCycleCount(1);
        image.play();

        image.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                background.setVisible(false);
                movingBackground.setVisible(true);
                System.out.println("ok");
                TranslateTransition moving = new TranslateTransition(Duration.seconds(80), movingBackground);
                moving.setInterpolator(Interpolator.LINEAR);
                moving.setByX(-1070);
                moving.setCycleCount(1000);
                moving.play();
                searchLabel.setVisible(true);
                translateLabel.setVisible(true);
                homeLabel.setVisible(true);
                gameLabel.setVisible(true);
                settingsLabel.setVisible(true);
            }

        });
    }
}
