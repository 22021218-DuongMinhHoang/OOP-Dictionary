package com.example.hello.game;

import java.util.Random;

import com.example.hello.data.Word;
import com.example.hello.searchbar.Connect;

public class Level {
  private Word[] options;
  private int rightAnswer;

  public Level() {
    options = new Word[4];
    for (int i = 0; i < 4; i++) {
      options[i] = Connect.getRandomWord();
    }
    rightAnswer = (new Random()).nextInt(4);
  }

  public String getQuestion() {
    return options[rightAnswer].getDescription();
  }

  public Word[] getOptions() {
    return options;
  }

  public int getRightAnswer() {
    return rightAnswer;
  }
}
