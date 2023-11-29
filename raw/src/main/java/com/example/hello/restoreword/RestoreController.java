package com.example.hello.restoreword;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import javax.swing.JOptionPane;

import javafx.scene.control.ListView;
import com.example.hello.searchbar.Connect;

public class RestoreController {
    public static String ANHVIET = Connect.ANHVIET;
    public static String VIETANH = Connect.VIETANH;

    @FXML
    private ListView<String> listView;
    private String currentLang;
    private String selectedWord;
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
    void restore(ActionEvent event) {
        if (selectedWord != null) {
            if (showNoticePane("Bạn muốn khôi phục từ " + selectedWord + " vào từ điền?")) {
                Connect.restoreWordToDb(selectedWord, currentLang);
                showAlert("Thành công", "Đã khôi phục lại từ", "assets/success.png");
                display();
            }
        }

    }

    @FXML
    void restoreAll(ActionEvent event) {
        if (showNoticePane("Bạn có chắc muốn khôi phục lại tất cả các từ không?")) {
            Connect.restoreAllWordsToDb(currentLang);
            showAlert("Thành công", "Đã khôi phục tất cả từ", "assets/success.png");
            display();
        }
    }

    @FXML
    void initialize() {
        currentLang = ANHVIET;
        display();
        listView.setOnMouseClicked(event -> {
            selectedWord = listView.getSelectionModel().getSelectedItem();

            System.out.println("Clicked on: " + selectedWord);
        });

    }

    private void display() {
        ObservableList<String> observableWords = FXCollections.observableArrayList(Connect.deletedWords(currentLang));
        listView.setItems(observableWords);
        if (observableWords == null || observableWords.isEmpty()) {
            listView.setPlaceholder(new Label("Không tìm thấy từ nào bị xóa"));
        }
    }

    private boolean showNoticePane(String s) {
        // ImageIcon icon = new ImageIcon("resources\\logovnu.png");
        int choice = JOptionPane.showOptionDialog(
                null,
                s,
                "Thông báo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
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
}
