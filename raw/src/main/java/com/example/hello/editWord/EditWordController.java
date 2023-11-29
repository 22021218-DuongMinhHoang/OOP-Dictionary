package com.example.hello.editWord;

import com.example.hello.data.Word;
import com.example.hello.searchbar.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditWordController {
    private static final String ANHVIET = Connect.ANHVIET;
    public static final String VIETANH = Connect.VIETANH;
    private String currentLang = ANHVIET;
    private Word oldWord;
    @FXML
    private Button changLangButton;
    @FXML
    private Label oldDes;

    @FXML
    private TextField newIpa;

    @FXML
    private Label pronunLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private ChoiceBox<String> newType;

    @FXML
    private TextField newDes;

    @FXML
    private TextField wordSource;

    @FXML
    void edit(ActionEvent event) {
        if (currentLang.equals(ANHVIET)) {
            String des = oldWord.getFullDescription();
            String ipa = oldWord.getPronunciation();
            String type = oldWord.getType();
            System.out.println(des + ipa + type);
            if (newDes.getText() != null && newIpa.getText() != null && newType.getValue() != null) {
                des = newDes.getText().toLowerCase();
                ipa = newIpa.getText().toLowerCase();
                type = newType.getValue();
                Connect.updateWord(new Word(oldWord.getWord(), ipa, des, getIntType(type)), currentLang);
                showAlert("Thành công", "từ '" + oldWord.getWord() + "' đã được chỉnh sửa", "assets/success.png");
            } else {
                showAlert("Không hợp lệ!", "Xin hãy điền đủ các dòng và đúng cú pháp", "assets/alert.png");
            }
        } else {
            String des = oldWord.getFullDescription();
            System.out.println(des);
            if (newDes.getText() != null) {
                des = newDes.getText().toLowerCase();
                Connect.updateWord(new Word(oldWord.getWord(), des), currentLang);
                showAlert("Thành công", "từ '" + oldWord.getWord() + "' đã được chỉnh sửa", "assets/success.png");
            } else {
                showAlert("Không hợp lệ!", "Xin hãy điền định nghĩa mới của từ", "assets/alert.png");
            }
        }

    }

    @FXML
    void find(MouseEvent event) {
        String word = wordSource.getText().toLowerCase();
        oldWord = Connect.getWord(word, currentLang);
        if (oldWord == null) {
            showAlert("Lỗi", "Từ không tồn tại trong từ điển, vui lòng thử lại", "assets/alert.png");
        } else {
            oldDes.setText(oldWord.getFullDescription());
        }
    }

    @FXML
    void changeLang(ActionEvent event) {
        currentLang = currentLang.equals(ANHVIET) ? VIETANH : ANHVIET;
        String EntoVtoEn = changLangButton.getText();
        changLangButton.setText(EntoVtoEn.equalsIgnoreCase("EntoViet") ? "VietToEn" : "EntoViet");

        pronunLabel.setVisible(currentLang.equals(ANHVIET));
        newIpa.setVisible(currentLang.equals(ANHVIET));
        typeLabel.setVisible(currentLang.equals(ANHVIET));
        newType.setVisible(currentLang.equals(ANHVIET));
        clearAll();
        System.out.println(currentLang + " " + changLangButton.getText());
    }

    private void clearAll(){
        newIpa.clear();
        oldDes.setText(null);
        newType.setValue(null);
        newDes.clear();
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
