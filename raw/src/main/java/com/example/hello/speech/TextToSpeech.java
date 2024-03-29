package com.example.hello.speech;

import java.io.UnsupportedEncodingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextToSpeech {
    private String sourceWord;
    private List<String> sounds = new ArrayList<>();

    public TextToSpeech(String sourceWord) {
        this.sourceWord = sourceWord.toLowerCase();
        getSound();
    }

    private String makeURL() throws UnsupportedEncodingException {
        return "https://dictionary.cambridge.org/dictionary/english/" + sourceWord;
    }

    public void getSound() {
        try {
            String url = makeURL();
            // Connect to the webpage and parse it using JSoup
            Document doc = Jsoup.connect(url).get();

            // Find all audio elements on the page
            Elements audioElements = doc.select("audio");

            for (Element audioElement : audioElements) {
                // Extract the source (MP3) links from the audio elements
                Element sourceElement = audioElement.select("source[type=audio/mpeg]").first();
                if (sourceElement != null) {
                    String mp3Link = sourceElement.attr("src");
                    // System.out.println(mp3Link);
                    sounds.add(mp3Link);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUkSound() {
        // getSound();
        return "https://dictionary.cambridge.org/" + sounds.get(0);
    }

    public String getUsSound() {
        // getSound();
        String s = sounds.get(1);
        return "https://dictionary.cambridge.org/" + s;
    }

    public static void main(String[] args) {
    }
}
