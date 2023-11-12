package com.example.hello.data;

import javazoom.jl.player.Player;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SentenceToSpeech {
    /**
     * Convert English input {@code text} to voice and play it with Google Translator TTS API
     *
     * @param text The text to be converted to voice in English
     */
    public static void playSoundGoogleTranslateEnToVi(String text) {
        try {
            String api =
                    "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                            + "en"
                            + "&client=tw-ob&q="
                            + URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
            URL url = new URL(api);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream audio = con.getInputStream();
            new Player(audio).play();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in getting voices");
        }
    }

    /**
     * Convert Vietnamese input {@code text} to voice and play it with Google Translator TTS API
     *
     * @param text The text to be converted to voice in Vietnamese
     */
    public static void playSoundGoogleTranslateViToEn(String text) {
        try {
            String api =
                    "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                            + "vi"
                            + "&client=tw-ob&q="
                            + URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
            URL url = new URL(api);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream audio = con.getInputStream();
            new Player(audio).play();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in getting voices");
        }
    }
}
