package com.example.hello.speech;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.animation.PauseTransition;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundPlayer {
    String word;
    public static final int UK = 0;
    public static final int US = 1;
    public static String CORRECT = "src\\main\\java\\com\\example\\hello\\HangMan\\assets\\correct.wav";
    public static String INCORRECT = "src\\main\\java\\com\\example\\hello\\HangMan\\assets\\incorrect.wav";

    public SoundPlayer(String word, int lang) {
        this.word = word.toLowerCase();
        playMP3(lang);
    }

    public SoundPlayer(boolean correct) {
        playMp3(correct);
    }

    public static void main(String[] args) {
        // playMP3();
        new SoundPlayer("pneumonoultramicroscopicsilicovolcanoconiosis", UK);
    }

    public void playMP3(int lang) {
        try {
            TextToSpeech t = new TextToSpeech(word);
            URL url = null;
            if (lang == UK) {
                url = new URL(t.getUkSound());
            } else if (lang == US) {
                url = new URL(t.getUsSound());
            }

            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            AdvancedPlayer player = new AdvancedPlayer(bis);

            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    // Handle playback finished event if needed
                }
            });

            player.play();

        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean playMp3(boolean correct) {
        String filePath = correct ? CORRECT : INCORRECT;

        Media sound = new Media(new File(filePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            mediaPlayer.stop();
        });
        pause.play();
        return correct;
    }
}
