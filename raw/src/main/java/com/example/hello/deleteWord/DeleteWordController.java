package com.example.hello.deleteWord;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.example.hello.management.WordFile;
import com.example.hello.searchbar.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DeleteWordController {

    @FXML
    private Button button;

    @FXML
    private TextField word;

    @FXML
    void delete(ActionEvent event) {
        handleDelete();
    }

    private void handleDelete() {
        String wordToDelete = word.getText().toLowerCase();
        word.clear();
        if (Connect.doesWordExist(wordToDelete)) {
            if (showNoticePane(wordToDelete)) {
                Connect.deleteWord(wordToDelete);
                WordFile.writeWordsToFile(wordToDelete);
                showAlert("Thành công", "Đã xóa '" + wordToDelete + "' khỏi từ điển", "assets/success.png");
            }
        } else {
            showAlert("Lỗi", "'" + wordToDelete + "' không tồn tại trong từ điển", "assets/alert.png");
        }
    }

    private boolean showNoticePane(String word) {
        ImageIcon icon = new ImageIcon("resources\\logovnu.png");
        int choice = JOptionPane.showOptionDialog(
                null,
                "Bạn có chắc chắn muốn xóa từ '" + word + "' khỏi từ điển không?",
                "Xóa vĩnh viễn",
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

    @FXML
    private void initialize() {
        // Add an event filter to handle the Enter key press
        word.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleDelete();
                event.consume(); // Consume the event to prevent it from being processed further
            }
        });
    }

}
