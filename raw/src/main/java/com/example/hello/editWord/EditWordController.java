package com.example.hello.editWord;

import com.example.hello.data.Word;
import com.example.hello.searchbar.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditWordController {
    private Word oldWord;
    @FXML
    private Label oldDes;

    @FXML
    private TextField newIpa;

    @FXML
    private ChoiceBox<String> newType;

    @FXML
    private TextField newDes;

    @FXML
    private TextField wordSource;

    @FXML
    void edit(ActionEvent event) {
        String des = oldWord.getFullDescription();
        String ipa = oldWord.getPronunciation();
        String type = oldWord.getType();
        System.out.println(des + ipa + type);
        if (newDes.getText() != null && newIpa.getText() != null && newType.getValue() != null) {
            des = newDes.getText().toLowerCase();
            ipa = newIpa.getText().toLowerCase();
            type = newType.getValue();
            Connect.updateWord(new Word(oldWord.getWord(), ipa, des, getIntType(type)));
            showAlert("Thành công", "từ '" + oldWord.getWord() + "' đã được chỉnh sửa", "assets/success.png");
        } else {
            showAlert("Không hợp lệ!", "Xin hãy điền đủ các dòng và đúng cú pháp", "assets/alert.png");
        }
    }

    @FXML
    void find(MouseEvent event) {
        String word = wordSource.getText().toLowerCase();
        oldWord = Connect.getWord(word);
        if (oldWord == null) {
            showAlert("Lỗi", "Từ không tồn tại trong từ điển, vui lòng thử lại", "assets/alert.png");
        } else {
            oldDes.setText(oldWord.getFullDescription());
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

    public static int getIntType(String type) {
        switch (type.toLowerCase()) {
            case "n":
                return 1;
            case "v":
                return 5;
            case "adj":
                return 2;
            case "adv":
                return 3;
            case "pre":
                return 4;
            case "other...":
                return 6;
            default:
                return 0;
        }
    }

    @FXML
    void initialize() {
        newType.getItems().addAll("n", "v", "adj", "adv", "pre", "other...");
    }
}
