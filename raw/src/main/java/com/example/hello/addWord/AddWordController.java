package com.example.hello.addWord;

import com.example.hello.data.Word;
import com.example.hello.management.WordFile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AddWordController {
    @FXML
    private TextArea description;

    @FXML
    private TextField ipa;

    @FXML
    private Button ok;

    @FXML
    private TextField word;

    @FXML
    private ChoiceBox<String> type;

    @FXML
    void wordAction(ActionEvent event) {
        String des = description.getText();
        String w = word.getText();
        String t = type.getValue();
        String pro = ipa.getText();
        if (des == null || w == null || t == null || pro == null) {
            showAlert("Invalid input!!", "Please fill in all fields", "assets/alert.png");
        } else {
            Word newWord = new Word(w, pro, des, t);
            System.out.println(
                    newWord.getWord() + newWord.getPronunciation() + newWord.getDescription() + newWord.getType());
            WordFile.writeWordsToFile(newWord);
            clearAll();
            showAlert("Successfully", "word added", "assets/success.png");
        }
    }

    private void showAlert(String title, String content, String iconPath) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
        stage.getIcons().add(icon);
        alert.showAndWait();
    }

    private void clearAll(){
        description.clear();
        word.clear();
        ipa.clear();
        type.setValue(null);
    }
    @FXML
    void initialize() {
        type.getItems().addAll("n", "v", "adj", "adv", "pre", "other...");
    }
}
