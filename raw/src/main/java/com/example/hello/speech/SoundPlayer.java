package com.example.hello.speech;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    String word;
    public static final int UK = 0;
    public static final int US = 1;

    public SoundPlayer(String word, int lang) {
        this.word = word.toLowerCase();
        playMP3(lang);
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
}
