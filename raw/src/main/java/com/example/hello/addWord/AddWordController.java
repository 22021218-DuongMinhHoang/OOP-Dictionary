package com.example.hello.addWord;

import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.example.hello.data.Word;
import com.example.hello.searchbar.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AddWordController {
    public static String ANHVIET = Connect.ANHVIET;
    public static String VIETANH = Connect.VIETANH;
    private String currentLang;
    @FXML
    private Label ipLabel;
    @FXML
    private Label wordLabel;
    @FXML
    private Button changeLangBut;
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
        }
        return 0;
    }

    @FXML
    void wordAction(ActionEvent event) {
        String des = description.getText().toLowerCase();
        String w = word.getText().toLowerCase();

        String pro = ipa.getText().toLowerCase();
        if (currentLang.equals(ANHVIET)) {
            int t = getIntType(type.getValue());
            if (des == null || w == null || t == 0 || pro == null || validateInput(pro)) {
                showAlert("Không hợp lệ!", "Xin hãy điền đủ các dòng và đúng cú pháp", "assets/alert.png");
            } else {
                Word newWord = new Word(w, pro, des, t);
                if (Connect.doesWordExist(newWord.getWord(), currentLang)) {
                    if (showNoticePane(w)) {
                        // ở đây sẽ add cái pane mật mã để edit được từ ấy ý
                        Connect.updateWord(newWord, currentLang);
                        showAlert("Thành công", "Từ '" + newWord.getWord() + "' đã được chỉnh sửa thành công",
                                "assets/success.png");
                        System.out.println("ok");
                    }
                } else {
                    Connect.insertWord(newWord, currentLang);
                    showAlert("Thành công", "Từ mới đã được thêm vào từ điển", "assets/success.png");
                }
                clearAll();

            }
        } else {
            String t = type.getValue();
            if (des == null || w == null || t == null) {
                showAlert("Không hợp lệ!", "Xin hãy điền đủ các thông tin", "assets/alert.png");
            } else {
                des = t + " BREAK " + des;
                Word newWord = new Word(w, des);
                if (Connect.doesWordExist(newWord.getWord(), currentLang)) {
                    if (showNoticePane(w)) {
                        // ở đây sẽ add cái pane mật mã để edit được từ ấy ý
                        Connect.updateWord(newWord, currentLang);
                        showAlert("Thành công", "Từ '" + newWord.getWord() + "' đã được chỉnh sửa thành công",
                                "assets/success.png");
                        System.out.println("ok");
                    }
                } else {
                    Connect.insertWord(newWord, currentLang);
                    showAlert("Thành công", "Từ mới đã được thêm vào từ điển", "assets/success.png");
                }
                clearAll();

            }
        }
    }

    @FXML
    void changeLang(ActionEvent event) {
        currentLang = currentLang.equals(ANHVIET) ? VIETANH : ANHVIET;
        String EntoVtoEn = changeLangBut.getText();
        changeLangBut.setText(EntoVtoEn.equalsIgnoreCase("Tiếng Anh") ? "Tiếng Việt" : "Tiếng Anh");
        ipLabel.setVisible(currentLang.equals(ANHVIET));
        ipa.setVisible(currentLang.equals(ANHVIET));
        if (currentLang.equals(VIETANH)) {
            wordLabel.setText("Từ tiếng Việt");
            type.getItems().clear();
            type.getItems().addAll("Danh từ", "Động từ", "Tính từ", "Trạng từ", "Giới từ", "khác...");
        } else {
            type.getItems().clear();
            type.getItems().addAll("n", "v", "adj", "adv", "pre", "other...");
            wordLabel.setText("Từ tiếng Anh");
        }
    }

    private boolean showNoticePane(String word) {
        ImageIcon icon = new ImageIcon("resources\\logovnu.png");
        int choice = JOptionPane.showOptionDialog(
                null,
                "Từ '" + word + "' đã tồn tại trong từ điển. Bạn có muốn định nghĩa lại nó không?",
                "Từ đã tồn tại",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                new Object[] { "Có", "Không" },
                "Không");

        // Return true if the user chose "Yes", false otherwise
        return choice == JOptionPane.YES_OPTION;
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

    private void clearAll() {
        description.clear();
        word.clear();
        ipa.clear();
        type.setValue(null);
    }

    private boolean validateInput(String input) {
        String regex = "^/.*?/$";
        return !Pattern.matches(regex, input);
    }

    @FXML
    void initialize() {
        currentLang = ANHVIET;
        type.getItems().addAll("n", "v", "adj", "adv", "pre", "other...");
    }
}
