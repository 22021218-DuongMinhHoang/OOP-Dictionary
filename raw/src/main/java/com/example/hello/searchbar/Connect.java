package com.example.hello.searchbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.hello.data.Word;
import com.example.hello.management.WordFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class WrongCommandException extends Exception {

  public WrongCommandException(String message) {
    super(message);
  }
}

public class Connect {
  // private static int currentId = 108854;

  private static boolean checkCommand(String command) throws WrongCommandException {
    if (command.equals(VIETANH) || command.equals(ANHVIET)) {
      return true;
    } else {
      throw new WrongCommandException("Wrong command!! Must use Connect.VIETANH or Connect.ANHVIET!");
    }
  }

  public static final String VIETANH = "va";
  public static final String ANHVIET = "av";

  private static Connection connect() {
    Connection conn = null;
    try {
      // db parameters
      String url = "jdbc:sqlite:src\\main\\java\\com\\example\\hello\\searchbar\\dict_hh.db";
      // create a connection to the database
      conn = DriverManager.getConnection(url);

      // System.out.println("Connection to SQLite has been established.");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  private static int getMaxIdFromTable() {
    String sql = "SELECT MAX(id) FROM av";

    try (Connection conn = connect();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } catch (SQLException e) {
      System.out.println("Error executing SQL: " + e.getMessage());
    }
    return 0;
  }

  public static ObservableList<Word> findWords(String word, String command) {
    ObservableList<Word> fw = FXCollections.observableArrayList();
    try {
      if (checkCommand(command)) {
        String sql = "SELECT word, html, description, pronounce FROM av WHERE word LIKE '" + word
            + "%' AND isDelete = 0 LIMIT 10";
        try (Connection conn = connect();
            Statement stm = conn.createStatement();
            ResultSet rss = stm.executeQuery(sql)) {
          while (rss.next()) {
            Word w = new Word(rss.getString("word"), rss.getString("html"),
                rss.getString("description"), rss.getString("pronounce"));
            fw.add(w);
          }
          if (fw.size() < 10) {
            sql = "SELECT word, html, description, pronounce FROM av WHERE word LIKE '%" + word + "%' AND isDelete = 0 LIMIT "
                + (10 - fw.size());
            try (Connection conn2 = connect();
                Statement stm2 = conn2.createStatement();
                ResultSet rss2 = stm2.executeQuery(sql)) {
              while (rss2.next()) {
                Word w = new Word(rss2.getString("word"), rss2.getString("html"),
                    rss2.getString("description"), rss2.getString("pronounce"));
                fw.add(w);
              }
            } catch (SQLException e) {
              System.out.println(e.getMessage());
            }
          }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
      }
    } catch (WrongCommandException e) {
      System.out.println(e.getMessage());
    }
    return fw;
  }

  public static Word getWord(String word) {
    Word w = null;
    boolean isDelete = false;
    try {
      if (checkCommand("av")) {
        String sql = "SELECT word, html, description, pronounce, isDelete FROM av WHERE word = '" + word.toLowerCase()
            + "'";
        try (Connection conn = connect();
            Statement stm = conn.createStatement();
            ResultSet rss = stm.executeQuery(sql)) {
          while (rss.next()) {
            w = new Word(rss.getString("word"), rss.getString("html"),
                rss.getString("description"), rss.getString("pronounce"));
            isDelete = rss.getBoolean("isDelete");
          }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
      }
    } catch (WrongCommandException e) {
      System.out.println(e.getMessage());
    }
    if (isDelete)
      return null;
    return w;
  }

  public static void insertWord(Word newWord) {
    // if (!doesWordExist(newWord.getWord())) {
    try {
      if (checkCommand("av")) {
        int id = getMaxIdFromTable() + 1;
        String sql = "INSERT INTO av (id, word, html, description, pronounce,isDelete) VALUES (?, ?, ?, ?, ?,0)";

        try (Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
          preparedStatement.setInt(1, id);
          preparedStatement.setString(2, newWord.getWord());
          preparedStatement.setString(3, newWord.getHtml());
          preparedStatement.setString(4, newWord.getDescription());
          preparedStatement.setString(5, newWord.getPronunciation());
          preparedStatement.executeUpdate();
          System.out.println("sucessfully added to db");
        } catch (SQLException e) {
          System.out.println("Error executing SQL: " + e.getMessage());
        }
      }
    } catch (WrongCommandException e) {
      System.out.println("Wrong command: " + e.getMessage());
    }
    // } else {
    // System.out.println("Word already exists");
    // }
  }

  public static void updateWord(Word updatedWord) {
    try {
      if (checkCommand("av")) {
        String sql = "UPDATE av SET html = ?, description = ?, pronounce = ?, isDelete = 0 WHERE word = ?";

        try (Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

          preparedStatement.setString(1, updatedWord.getHtml());
          preparedStatement.setString(2, updatedWord.getDescription());
          preparedStatement.setString(3, updatedWord.getPronunciation());
          preparedStatement.setString(4, updatedWord.getWord());

          int rowsAffected = preparedStatement.executeUpdate();

          if (rowsAffected > 0) {
            System.out.println("Successfully updated in the database");
          } else {
            System.out.println("Word not found in the database");
          }

        } catch (SQLException e) {
          System.out.println("Error executing SQL: " + e.getMessage());
        }
      }
    } catch (WrongCommandException e) {
      System.out.println("Wrong command: " + e.getMessage());
    }
  }

  public static boolean doesWordExist(String word) {
    return getWord(word) != null;
  }

  public static String getHTML(int id) {
    String sql = "SELECT html FROM av WHERE id = " + id;
    try (Connection conn = connect();
        Statement stm = conn.createStatement();
        ResultSet rss = stm.executeQuery(sql)) {
      rss.next();
      return rss.getString("html");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return "";
  }

  public static void deleteWord(String wordToDelete) {
    wordToDelete = wordToDelete.toLowerCase();
    if (doesWordExist(wordToDelete)) {
      try {
        if (checkCommand("av")) {
          String sql = "UPDATE av SET isdelete = 1 WHERE word = ?";
          try (Connection conn = connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, wordToDelete);
            // Execute the update and check if one row was deleted
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
              System.out.println("Deletion successful!");
            } else {
              System.out.println("Word not found or deletion failed!");
            }
          } catch (SQLException e) {
            System.out.println("Error executing SQL: " + e.getMessage());
            // Handle the exception according to your needs
          }
        }
      } catch (WrongCommandException e) {
        System.out.println("Wrong command: " + e.getMessage());
        // Handle the exception according to your needs
      }
    } else {

    }
  }

  public static void restoreWordToDb(String wordToRestore) {
    wordToRestore = wordToRestore.toLowerCase();
    try {
      if (checkCommand("av")) {
        String sql = "UPDATE av SET isdelete = 0 WHERE word = ?";
        try (Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
          preparedStatement.setString(1, wordToRestore);
          int rowsAffected = preparedStatement.executeUpdate();

          if (rowsAffected > 0) {
            System.out.println("Restoration successful!");
          } else {
            System.out.println("Word not found or restoration failed!");
          }
        } catch (SQLException e) {
          // Log the exception or throw a custom exception for better error handling
          System.out.println("Error executing SQL: " + e.getMessage());
        }
      }
    } catch (WrongCommandException e) {
      // Log the exception or throw a custom exception for better error handling
      System.out.println("Wrong command: " + e.getMessage());
    }
    if (WordFile.getWordsFile().contains(wordToRestore)) {
      WordFile.getWordsFile().remove(wordToRestore);
    }
  }

  public static void restoreAllWordsToDb() {
    try {
      if (checkCommand("av")) {
        String sql = "UPDATE av SET isdelete = 0 WHERE isdelete = 1";
        try (Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

          int rowsAffected = preparedStatement.executeUpdate();

          if (rowsAffected > 0) {
            System.out.println("Restoration successful for " + rowsAffected + " words.");
          } else {
            System.out.println("No words found for restoration.");
          }
        } catch (SQLException e) {
          System.out.println("Error executing SQL: " + e.getMessage());
          // Handle the exception according to your needs
        }
      }
    } catch (WrongCommandException e) {
      System.out.println("Wrong command: " + e.getMessage());
      // Handle the exception according to your needs
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // System.out.println(getHTML(1));
    System.out.println(doesWordExist("abbreviation"));
    String word = "pneumonoultramicroscopicsilicovolcanoconiosis";
    String pronounce = "ˌnjuːmənoʊˌʌltrəˌmaɪkroʊˌskɒpɪksɪlɪkoʊˌvɒlkeɪnoʊkoʊˌniːoʊsɪs\n";
    String description = "It is a technical word and refers to a lung disease caused by\n"
        + "the inhalation of very fine silica particles, specifically from a volcano; medically,\n"
        + "it is considered a coined term";
    int type = Word.N;
    // insertWord(new Word(word, pronounce, description, type));
    // deleteWord(word);
    restoreWordToDb("Trieu Minh Nhat");
    restoreAllWordsToDb();  
  }
}

// thêm cái query này vào để tạo cột check xem từ đấy đã được xóa chưa nhé
// ALTER TABLE av
// ADD COLUMN isDelete BOOLEAN;
// UPDATE av
// set isDelete = 0;