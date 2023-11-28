package com.example.hello.searchbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.example.hello.data.Word;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class WrongCommandException extends Exception {
  public WrongCommandException(String message) {
    super(message);
  }
}

public class Connect {
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

  public static ObservableList<Word> findWords(String word, String command) {
    ObservableList<Word> fw = FXCollections.observableArrayList();
    try {
      if (checkCommand(command)) {
        String sql = "SELECT word, html, description, pronounce FROM " + command + " WHERE word LIKE '" + word
            + "%' LIMIT 10";
        try (Connection conn = connect();
            Statement stm = conn.createStatement();
            ResultSet rss = stm.executeQuery(sql)) {
          while (rss.next()) {
            Word w = new Word(rss.getString("word"), rss.getString("html"),
                rss.getString("description"), rss.getString("pronounce"));
            fw.add(w);
          }
          if (fw.size() < 10) {
            sql = "SELECT word, html, description, pronounce FROM " + command + " WHERE word LIKE '%" + word
                + "%' AND NOT word LIKE '" + word + "' LIMIT "
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

  public static Word getRandomWord() {
    String sql = "SELECT word, html, description, pronounce FROM av ORDER BY RANDOM() LIMIT 1";
    Word w = null;
    try (Connection conn = connect();
        Statement stm = conn.createStatement();
        ResultSet rss = stm.executeQuery(sql)) {
      w = new Word(rss.getString("word"), rss.getString("html"),
          rss.getString("description"), rss.getString("pronounce"));
      System.out.println(w.getWord());
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return w;
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

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    getRandomWord();
  }
}