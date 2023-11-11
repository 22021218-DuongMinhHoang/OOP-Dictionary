package com.example.hello.data.SearchBar;

import java.util.Stack;

public class Word {
  private String word;
  private String description;
  private String html;
  private String pronounce;

  public Word(String word, String html, String description, String pronounce) {
    this.word = word;
    this.description = description;
    this.html = html;
    this.pronounce = pronounce;
  }

  public String getFullDescription() {
    String s = html;
    // String s = html;
    s = s.replace("<h1>", "BREAK");
    s = s.replace("</h1>", "BREAK");
    s = s.replace("<h3>", "BREAK");
    s = s.replace("</h3>", "BREAK");
    s = s.replace("<h2>", "BREAK");
    s = s.replace("</h2>", "BREAK");
    s = s.replace("<ul>", "BREAK");
    s = s.replace("</ul>", "BREAK");
    s = s.replace("<ul style=\"list-style-type:circle\">", "BREAK");
    s = s.replace("<li>", "BREAK");
    s = s.replace("</li>", "BREAK");
    s = s.replace("<i>", "BREAK");
    s = s.replace("</i>", "BREAK");
    String[] s1 = s.split("BREAK");
    s = "";
    for (int i = 0; i < s1.length; i++) {
      if (!s1[i].equals("")) {
        s += s1[i] + "\n";
      }
    }
    return s;
  }

  public String toString() {
    return word + "    " + description;
  }

  public static void main(String[] args) {

  }
}
