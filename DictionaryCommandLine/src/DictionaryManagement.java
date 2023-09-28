import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
  private Dictionary wordDictionary = new Dictionary();

  public DictionaryManagement(Dictionary wd) {
    this.wordDictionary = wd;
  }

  public DictionaryManagement() {
  }

  public Dictionary getDictionary() {
    return this.wordDictionary;
  }

  public void insertFromCommandline() {
    Scanner sc = new Scanner(System.in);
    int number = 0;
    System.out.print("Enter word number: ");
    number = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < number; i++) {
      System.out.println("Word " + (i + 1) + ": ");
      System.out.print("Word target: ");
      String wt = sc.nextLine();
      System.out.print("Word explain: ");
      String we = sc.nextLine();
      wordDictionary.addWord(wt, we);
    }
  }

  public void insertFormFile() {
    try {
      File f = new File("C:\\Users\\STMTLS\\Desktop\\IT\\OOP-Dictionary\\DictionaryCommandLine\\src\\dictionary.txt");
      Scanner sc = new Scanner(f);
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String wt = "";
        String we = "";
        boolean readWord = true;
        for (int i = 0; i < line.length(); i++) {
          if ((line.charAt(i) == ' ' && line.charAt(i - 1) == ' ')
              || (line.charAt(i) == ' ' && line.charAt(i + 1) == ' ' && line.charAt(i - 1) >= 'a'
                  && line.charAt(i - 1) <= 'z')) {
            readWord = false;
            continue;
          }
          if (readWord) {
            wt += line.charAt(i);
          } else {
            we += line.charAt(i);
          }
        }
        wordDictionary.addWord(wt, we);
      }
      System.out.println("Inported successfully");
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void insertFormFile(String path) {
    try {
      File f = new File(path);
      Scanner sc = new Scanner(f);
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String wt = "";
        String we = "";
        boolean readWord = true;
        for (int i = 0; i < line.length(); i++) {
          if ((line.charAt(i) == ' ' && line.charAt(i - 1) == ' ')
              || (line.charAt(i) == ' ' && line.charAt(i + 1) == ' ' && line.charAt(i - 1) >= 'a'
                  && line.charAt(i - 1) <= 'z')) {
            readWord = false;
            continue;
          }
          if (readWord) {
            wt += line.charAt(i);
          } else {
            we += line.charAt(i);
          }
        }
        wordDictionary.addWord(wt, we);
      }
      System.out.println("Inported successfully");
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void dictionarySearcher() {
    ArrayList<Word> l = wordDictionary.getListWord();
    String s2 = "";
    System.out.print("Enter the word you want to find: ");
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    for (int i = 0; i < str.length(); i++) {
      s2 += Character.toLowerCase(str.charAt(i));
    }
    boolean found = false;
    System.out.println("Words that contains your word: ");
    for (int i = 0; i < l.size(); i++) {
      String w = l.get(i).getWordTarget();
      String w2 = "";
      for (int j = 0; j < w.length(); j++) {
        w2 += Character.toLowerCase(w.charAt(j));
      }
      if (w2.contains((CharSequence) s2)) {
        System.out.println(w + ": " + l.get(i).getWordExplain());
        found = true;
      }
    }
    if (!found) {
      System.out.println("None\nCan't find that word");
    }
  }

  public void dictionaryLookup() {
    String word = "";
    System.out.print("Enter the word you want to find: ");
    Scanner sc = new Scanner(System.in);
    word = sc.nextLine();
    int n = wordDictionary.findWord(word, 0, wordDictionary.getListWord().size() - 1);
    if (n != -1) {
      System.out.println(word + ": " + wordDictionary.getListWord().get(n).getWordExplain());
      return;
    }
    System.out.println("Can't find that word.");
  }

  public void changeWordExplain() {
    String word = "";
    System.out.print("Enter the word you want to change explain: ");
    Scanner sc = new Scanner(System.in);
    word = sc.nextLine();
    int n = wordDictionary.findWord(word, 0, wordDictionary.getListWord().size() - 1);
    if (n != -1) {
      System.out.print("Enter the explain: ");
      wordDictionary.getListWord().get(n).setWordExplain(sc.nextLine());
      return;
    }
    System.out.println("Can't find that word.");
  }

  public void eraseWord() {
    System.out.print("Enter the word you want to erase: ");
    Scanner sc = new Scanner(System.in);
    String word = sc.nextLine();
    int n = wordDictionary.findWord(word, 0, wordDictionary.getListWord().size() - 1);
    System.out.println(n);
    if (n != -1) {
      wordDictionary.erase(n);
    }
  }

  public void exportFile() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter file name: ");
    String path = sc.nextLine() + ".txt";
    try {
      File f = new File(path);
      if (f.createNewFile()) {
        System.out.println("Created file " + path);
        try {
          FileWriter fw = new FileWriter(path);
          for (int i = 0; i < wordDictionary.getListWord().size(); i++) {
            if (i > 0) {
              fw.write("\n");
            }
            Word w = wordDictionary.getListWord().get(i);
            fw.write(w.getWordTarget() + "   " + w.getWordExplain());
          }
          fw.close();
          System.out.println("Successfully wrote the file.");
        } catch (IOException e) {
          System.out.println("An error occured.");
          e.printStackTrace();
        }
      } else {
        System.out.println("File " + path + " already exists");
      }
    } catch (IOException e) {
      System.out.println("An error occured.");
      e.printStackTrace();
    }
  }

  public void insertFile() {
    System.out.println("Which file you want to import:\n[1] Default file: dictionary.txt\n[2] My own file");
    Scanner sc = new Scanner(System.in);
    System.out.print("Your action: ");
    int ans = sc.nextInt();
    sc.nextLine();
    if (ans != 1 && ans != 2) {
      System.out.println("Action not supported");
    } else if (ans == 1) {
      insertFormFile();
    } else if (ans == 2) {
      System.out.print("Enter your file path: ");
      String path = sc.nextLine();
      insertFormFile(path);
    }
  }
}
