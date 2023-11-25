package com.example.hello.management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.hello.data.Word;

public class WordFile {
    private static final String filePath = "src/main/java/com/example/hello/deleteWord/deletedWords.txt";
    private static final List<String> deleted_words = new ArrayList<>();

    public static void writeWordsToFile(String word) {
        deleted_words.add(word);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.write(word);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   

    // private static String wordToLine(Word word) {
    //     // Assuming Word has getters for word, pronunciation, description, and type
    //     return String.format("%s;%s;%s;%s", word.getWord(), word.getPronunciation(),
    //             word.getDescription(), word.getType());
    // }

    public static List<Word> readWordsFromFile() {
        List<Word> wordList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Word word = lineToWord(line);
                wordList.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordList;
    }

    private static Word lineToWord(String line) {
        String[] parts = line.split(";");
        // Assuming Word has setters for word, pronunciation, description, and type
        Word word = new Word();
        word.setWord(parts[0].trim());
        word.setPronunciation(parts[1].trim());
        word.setDescription(parts[2].trim());
        word.setType(parts[3].trim());
        return word;
    }

    public static List<String> getWordsFile(){
        return deleted_words;
    }
    public static void emptyFile() throws IOException {
        // Use FileWriter to write an empty string to the file
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write("");
        }
    }

}
