package com.example.hello.history;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.hello.searchbar.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HistoryController {
    public static String ANHVIET = Connect.ANHVIET;
    public static String VIETANH = Connect.VIETANH;
    public static String efilePath = "src\\main\\java\\com\\example\\hello\\history\\ehistory.txt";
    public static String vfilePath = "src\\main\\java\\com\\example\\hello\\history\\vhistory.txt";
    public static List<String> history = new ArrayList<>();
    
    @FXML
    private ListView<String> listView;

    @FXML
    private Label wordDisplay;
    private String selectedWord;
    private String currentLang;

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
        ObservableList<String> observableWords = FXCollections.observableArrayList(readWordsFromFile(currentLang));
        listView.setItems(observableWords);
        if (observableWords == null || observableWords.isEmpty()) {
            listView.setPlaceholder(new Label("Không tìm thấy lịch sử"));
        }
        listView.setOnMouseClicked(event -> {
            selectedWord = listView.getSelectionModel().getSelectedItem();
            wordDisplay.setText(Connect.getWord(selectedWord, currentLang).getFullDescription());
            System.out.println("Clicked on: " + selectedWord);
        });
    }

    public static void writeWordsToFile(String word, String currentLang) {
        String filePath = currentLang.equals(ANHVIET)? efilePath: vfilePath;
        history.add(word);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(word);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readWordsFromFile(String currentLang) {
        String filePath = currentLang.equals(ANHVIET)? efilePath: vfilePath;
        System.out.println(filePath);
        List<String> wordList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordList;
    }
}
