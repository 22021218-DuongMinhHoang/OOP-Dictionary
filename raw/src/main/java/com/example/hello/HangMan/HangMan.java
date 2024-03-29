package com.example.hello.HangMan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

//import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.example.hello.data.Word;
import com.example.hello.searchbar.Connect;
import com.example.hello.speech.SoundPlayer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HangMan {
    public static int MAX_PLAY_TIME = 7;
    protected Word dbWord;
    protected String secretWord;
    protected String hiddenWord;
    protected int wrongtimes;
    protected List<String> wordSource;
    protected Image image;

    public HangMan() {
        wrongtimes = 0;
        try {
            readWordsFromFile("src/main/java/com/example/hello/HangMan/wordSources.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getScrectWord() {

        // Cách 1:return getRandomWord();
        // Cách 2:
        dbWord = Connect.getRandomWord(Connect.ANHVIET);
        return dbWord.getWord().toLowerCase();
    }

    public String getMeaning() {

        return dbWord.getDescription();
    }

    protected void readWordsFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        wordSource = Files.readAllLines(path);
    }

    // private String getRandomWord() {
    // Random random = new Random();
    // int randomIndex = random.nextInt(wordSource.size());
    // return wordSource.get(randomIndex);
    // }

    protected void createHiddenWord() {
        char[] charArray = new char[secretWord.length()];
        Arrays.fill(charArray, '_');
        hiddenWord = new String(charArray);
    }

    protected void showHangMan(ImageView imageView) {
        if (imageView != null) {
            String imagePath = "assets/" + (wrongtimes + 1) + ".png";

            image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        }
        System.out.println("Wrong times: " + wrongtimes);
    }

    protected boolean isCharInWord(char s) {
        boolean check = SoundPlayer.playMp3(secretWord.contains(String.valueOf(s).toLowerCase()));
        return check;
    }

    protected void updateHiddenWord(char guessedChar) {
        StringBuilder updatedHiddenWord = new StringBuilder(hiddenWord);
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guessedChar) {
                updatedHiddenWord.setCharAt(i, guessedChar);
            } else if (secretWord.charAt(i) < 'a' || secretWord.charAt(i) > 'z') {
                updatedHiddenWord.setCharAt(i, secretWord.charAt(i));
            }
        }
        hiddenWord = updatedHiddenWord.toString();
    }

    protected boolean showWinPane() {
        // ImageIcon icon = new ImageIcon("HangMan/assets/happy.png");
        int choice = JOptionPane.showOptionDialog(
                null,
                "Từ đó là '" + secretWord.toUpperCase() + "'\n Đêm qua không có thêm cây nào chết (chắc vậy)",
                "Xin chúc mừng",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "Oke", "Okay" },
                "Không");

        // Return true if the user chose "Yes", false otherwise
        return choice == JOptionPane.YES_OPTION;
    }

    protected boolean showLosePane() {
        // ImageIcon icon = new ImageIcon("HangMan/assets/sad.png");
        int choice = JOptionPane.showOptionDialog(
                null,
                "Từ đó là '" + secretWord.toUpperCase() + "'\n Đêm qua có thêm 1 cây chết",
                "Bạn đã thua",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "Oke", "Okay" },
                "Không");

        // Return true if the user chose "Yes", false otherwise
        return choice == JOptionPane.YES_OPTION;
    }

    public static void main(String[] args) {
        HangMan hm = new HangMan();
        System.out.println(hm.wordSource.size());
        // String a = "----";
    }

}
