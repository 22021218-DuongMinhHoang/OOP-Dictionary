
package com.example.hello.data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class Dictionary {

    private HashSet<Word> dictionary;
    public static Set<String> suggestions;
    private Scanner sc;
    public Dictionary() {
        dictionary = new HashSet<>();
        sc = new Scanner(System.in);
        suggestions = new HashSet<>();

    }

    public void addWord(Word word) {
        Word existingWord = findWord(word.getWord());

        if (existingWord == null) { 
            dictionary.add(word);
            suggestions.add(word.getWord().toLowerCase());
        }
    }
    public void addFromFile(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String word = null;
            String meaning ;
            boolean isWordLine = true;

            while ((line = reader.readLine()) != null) {
                if (isWordLine) {
                    word = line;
                    isWordLine = false;
                } else {
                    meaning = line;
                    isWordLine = true;
                    dictionary.add(new Word(word, meaning));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public Word findWord(String word) {
        for (Word existingWord : dictionary) {
            if (existingWord.getWord().equalsIgnoreCase(word)) {
                return existingWord;
            }
        }
        return null;
    }

    public void display() {
        System.out.printf("|%-5s|%-15s|%-20s|\n", "No", "English", "Vietnamese");
        int count = 0;

        for (Word word : dictionary) {
            System.out.printf("|%-5d|%-15s|%-20s|\n", ++count, word.getWord(), word.getMeaning());
        }
    }
    
    public void remove(String word){
        if(dictionary.contains(findWord(word))){
            dictionary.remove(findWord(word));
        }
        else{
            System.out.println("Sorry, " + word +" does not exist in the dictionary.");
        }
    }
    
    public void lookup(String word){
        if(dictionary.contains(findWord(word))){
            System.out.println(findWord(word).getMeaning());
        }
        else{
            System.out.println("Sorry, " + word + "has not been defined in the the dictionary yet."
                    + " Would you like to add its meaning? (1: yes | 0: no)");
            if(Integer.parseInt(sc.nextLine()) == 1){
                String meaning = sc.nextLine();
                dictionary.add(new Word(word, meaning));
            }
        }
    }
    
    public void search(String word){
        Word w = findWord(word);
        if(dictionary.contains(w)){
            System.out.println("Result: " + w.toString() );
        }
        else{
            System.out.println("not found!");
        }
    }
    
}
