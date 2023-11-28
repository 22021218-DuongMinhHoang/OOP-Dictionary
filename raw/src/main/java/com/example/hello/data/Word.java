package com.example.hello.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Word {
  public static int N = 1;
  public static int ADJ = 2;
  public static int ADV = 3;
  public static int PRE = 4;
  public static int V = 5;
  public static int OTHER = 6;

  private String word;
  private String description;
  private String html;
  private String pronounce;
  private String type;

  public Word(String word, String html, String description, String pronounce) {
    this.word = word;
    this.description = description;
    this.html = html;
    this.pronounce = pronounce;
  }

  public Word() {

  }

  public Word(String word, String pronounce, String description, int type) {
    this.word = word;
    this.pronounce = pronounce;
    this.description = description;
    this.type = getTypeString(type);
    this.html = word + "BREAK" + pronounce + "BREAK" + this.type + "BREAK" + description;
  }

  public Word(String word, String html){
    this.word = word;
    this.html = html;
    this.html = getFullDescription();
  }
  public String getTypeString(int t) {
    String type = null;
    switch (t) {
      case 1:
        type = "Danh từ";
        break;
      case 2:
        type = "Tính từ";
        break;
      case 3:
        type = "Trạng từ";
        break;
      case 4:
        type = "Giới từ";
        break;
      case 5:
        type = "Động từ";
        break;
      case 6:
        type = "Khác";
        break;
    }
    return type;
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
    s = s.replace("<ol>", "BREAK");
    s = s.replace("</ol>", "BREAK");
    String[] s1 = s.split("BREAK");
    s = "";
    for (int i = 0; i < s1.length; i++) {
      if (!s1[i].equals("")) {
        s += s1[i] + "\n";
      }
    }
    return s;
  }

  public List<String> getDescriptionList() {
    List<String> desList = new ArrayList<>();
    String s = html;
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
    s = s.replace("<ol>", "BREAK");
    s = s.replace("</ol>", "BREAK");
    s = s.replace("<br>", "BREAK");
    s = s.replace("<br/>", "BREAK");
    String[] s1 = s.split("BREAK");
    for (int i = 0; i < s1.length; i++) {
      if (!s1[i].equals("")) {
        desList.add(s1[i]);
      }
    }
    return desList;
  }

  public String toString() {
    return word + "    " + description;
  }

  public String getDescription() {
    return description;
  }

  public String getWord() {
    return this.word;
  }

  public String getPronunciation() {
    return this.pronounce;
  }

  public String getType() {

    return this.type;
  }

  public String getHtml() {

    return this.html;
  }

  // <h1>abasement break /ə'beismənt/breakdanh từ break sự làm hạ phẩm giá, sự làm
  // mất thể diện, sự làm nhục</li></ul>
  public void setDescription(String description) {
    this.description = description;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public void setPronunciation(String pronounce) {
    this.pronounce = pronounce;
  }

  public void setType(String type) {
    this.type = type;
  }

  public static void main(String[] args) {

  }
}
