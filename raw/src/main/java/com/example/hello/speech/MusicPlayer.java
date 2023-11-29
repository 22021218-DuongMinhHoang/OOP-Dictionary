package com.example.hello.speech;

import java.io.File;
import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
  private static MusicPlayer instance;
  private Media media;
  private MediaPlayer mediaPlayer;

  private MusicPlayer() {
    media = new Media(new File("src\\main\\java\\com\\example\\hello\\speech\\music.mp3").toURI().toString());

    mediaPlayer = new MediaPlayer(media);
  }

  public static MusicPlayer getInstance() {
    if (instance == null) {
      instance = new MusicPlayer();
    }
    return instance;
  }

  public MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }
}
