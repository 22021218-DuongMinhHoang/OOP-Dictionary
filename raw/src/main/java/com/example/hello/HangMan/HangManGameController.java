package com.example.hello.HangMan;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.hello.game.Inventory;
import com.example.hello.management.SceneSwitch;

import javafx.animation.PauseTransition;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class HangManGameController extends HangMan {
    private static final String accessKey = "FMwPe-7dJe0yabyBaUGk5Mwqg8CNYW26zIn1HuzPBQg";
    private char guessedChar;
    private Label clickedLabel;
    private List<ImageView> hearts;

    @FXML
    private ImageView h1;

    @FXML
    private ImageView h2;

    @FXML
    private ImageView h3;

    @FXML
    private ImageView h4;

    @FXML
    private ImageView h5;

    @FXML
    private ImageView h6;

    @FXML
    private ImageView h7;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView hint;

    @FXML
    private Button button;

    @FXML
    private Pane pane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label label;

    @FXML
    private Label secret;

    @FXML
    private Label meaning;

    @FXML
    void initialize() {
        setStyle();
        play();
        labelBrighten();
    }

    @FXML
    void ok(MouseEvent event) {
        clickedLabel.setDisable(true);
        clickedLabel.setStyle("-fx-background-color: #90EE90;");
        guessedChar = label.getText().toLowerCase().charAt(0);
        if (isCharInWord(guessedChar) && wrongtimes <= MAX_PLAY_TIME) {
            updateHiddenWord(guessedChar);
            secret.setText(hiddenWord);
            if (secretWord.equalsIgnoreCase(hiddenWord)) {
                button.setDisable(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(e -> {
                    secret.setText(secretWord);
                    if (showWinPane()) {
                        try {
                            new SceneSwitch(pane, "HangMan/game.fxml");
                            pane.getParent().setVisible(false);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            new SceneSwitch(pane, "HangMan/menu.fxml");
                            pane.getParent().setVisible(false);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                pause.play();
            }

        } else {
            if (wrongtimes <= MAX_PLAY_TIME) {
                if (wrongtimes == MAX_PLAY_TIME) {
                    button.setDisable(true);
                    wrongtimes = 8;
                    showHangMan(imageView);
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(e -> {

                        if (showLosePane()) {
                            try {
                                new SceneSwitch(pane, null);
                                pane.getParent().setVisible(false);
                                Inventory.getInstance().getBadDays().add(1);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                new SceneSwitch(pane, null);
                                pane.getParent().setVisible(false);
                                Inventory.getInstance().getBadDays()
                                        .set(Inventory.getInstance().getBadDays().get() + 1);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    pause.play();
                }
                hearts.get(MAX_PLAY_TIME - wrongtimes - 1).setVisible(false);
                wrongtimes++;
                if (wrongtimes >= 4 && !meaning.isVisible()) {
                    meaning.setVisible(true);
                }

                showHangMan(imageView);
            } else {
                button.setDisable(true);
                wrongtimes = 8;
                showHangMan(imageView);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(e -> {

                    if (showLosePane()) {
                        try {
                            new SceneSwitch(pane, "HangMan/game.fxml");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            new SceneSwitch(pane, "HangMan/menu.fxml");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                pause.play();
            }
        }

    }

    @FXML
    void getChar(MouseEvent event) {
        clickedLabel = (Label) event.getSource();
        if (!clickedLabel.isDisable()) {
            char ch = clickedLabel.getText().charAt(0);
            label.setText(String.valueOf(ch));

        }

    }

    private void play() {
        hearts = Arrays.asList(h1, h2, h3, h4, h5, h6, h7);
        showHangMan(imageView);
        secretWord = getScrectWord();
        // searchAndDisplayImage(secretWord);
        createHiddenWord();
        meaning.setVisible(false);
        meaning.setText(getMeaning());
        secret.setText(hiddenWord);
    }

    private void setPaneColor() {
        String greenPastelColor = "-fx-background-color: #FFDEAD;";

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setStyle(greenPastelColor);
            }
        }
    }

    private void labelBrighten() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setOnMouseEntered(event -> {
                    label.setStyle("-fx-background-color: #90EE90;");
                });

                label.setOnMouseExited(event -> {
                    label.setStyle("-fx-background-color: transparent;");
                });

                label.setOnMouseClicked(event -> {
                    getChar(event);
                });
            }
        }
    }

    public void buttonMouseEntered() {
        button.setStyle(
                "-fx-background-color: white; -fx-border-color:  rgb(201, 117, 31); -fx-border-width: 3; -fx-border-radius: 5;");
    }

    public void buttonMouseExited() {
        button.setStyle(
                "-fx-background-color: white; -fx-border-color:  rgb(201, 117, 31); -fx-border-width: 2; -fx-border-radius: 5;");
    }

    private void setStyle() {
        // pane.setStyle("-fx-background-color: bisque;");
        // setPaneColor();
        // label.setStyle("-fx-background-color: white;");
        // secret.setStyle("-fx-background-color: white; -fx-letter-spacing: 1.5em; ");
        // button.setStyle(
        // "-fx-background-color: #EEE8AA; -fx-text-fill: black; -fx-font-size: 18px;
        // -fx-font-weight: bold;");
    }

    // private void searchAndDisplayImage(String keyword) {
    // keyword = keyword.toLowerCase().replace(' ', '+');
    // try {
    // // Build the API URL with the search term
    // String apiUrl = "https://api.unsplash.com/search/photos?query=" +
    // keyword.toLowerCase() + "&client_id="
    // + accessKey;

    // // Make the API request
    // URL url = new URL(apiUrl);
    // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // connection.setRequestMethod("GET");

    // // Read API response
    // InputStream responseStream = connection.getInputStream();
    // String jsonResponse = convertStreamToString(responseStream);

    // // Parse JSON response as an object
    // JSONObject responseObject = new JSONObject(jsonResponse);

    // // Check if the response contains the "results" property
    // if (responseObject.has("results")) {
    // // If it does, get the first result's "urls" property
    // JSONArray resultsArray = responseObject.getJSONArray("results");
    // if (resultsArray.length() > 0) {
    // String imageUrl =
    // resultsArray.getJSONObject(0).getJSONObject("urls").getString("regular");

    // // Load image from the URL
    // Image image = new Image(imageUrl);
    // hint.setImage(image);
    // }
    // } else {
    // // Handle the case where "results" property is not found in the response
    // System.out.println("Invalid JSON response. 'results' property not found.");
    // }

    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    // private String convertStreamToString(InputStream inputStream) {
    // Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
    // return scanner.hasNext() ? scanner.next() : "";
    // }
}
