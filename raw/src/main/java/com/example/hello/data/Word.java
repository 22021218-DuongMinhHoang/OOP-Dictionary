package com.example.hello.data;

import java.util.Stack;

public class Word {
  private String word;
  private String description;
  private String html;
  private String pronounce;
  private String type;
  public Word(String word, String html, String pronounce) {
    this.word = word;
    //this.description = description;
    this.html = html;
    this.pronounce = pronounce;
  }

  public Word (String word, String pronounce, String description, String type){
    this.word = word;
    this.pronounce = pronounce;
    this.description = description;
    this.type = type;
  }

  public Word(){
    
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

  public String getWord() {
    return this.word;
  }

  public String getPronunciation(){
    return this.pronounce;
  }
  public String getType(){

    return this.type;
  }
  public String getDescription(){
    return this.description;
  }
  public void setDescription(String description){
    this.description = description;
  }
  public void setWord(String word){
    this.word = word;
  }
  public void setPronunciation(String pronounce){
    this.pronounce = pronounce;
  }
  public void setType(String type){
    this.type = type;
  }
  public static void main(String[] args) {

  }
}
