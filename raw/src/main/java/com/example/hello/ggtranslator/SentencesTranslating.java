package com.example.hello.ggtranslator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SentencesTranslating {
    private boolean enToVi = true;
    @FXML
    private Button voiceButton;

    @FXML
    private Button alterButton;

    @FXML
    private Button translateButton;

    @FXML
    private TextArea sinkText;

    @FXML
    private Label upButton;

    @FXML
    private TextArea sourceText;

    @FXML
    private Label downButton;

    public SentencesTranslating() {
    }

    @FXML
    private void initialize() {
        // prepareButtonIcon(Application.isLightMode());
    }

    /**
     * Translate the text from English to Vietnamese (or reverse, depends on current
     * state `enToVi`)
     * and output the content to the sinkText.
     */
    @FXML
    public void translateEnToVi() {
        String source = sourceText.getText();
        sinkText.setText(
                (enToVi
                        ? TranslatorApi.translateEnToVi(source)
                        : TranslatorApi.translateViToEn(source)));
    }

    /**
     * Play sound TTS the source text (English to Vietnamese or reverse depends on
     * current state
     * `enToVi`).
     */
    @FXML
    public void textToSpeech() {
        String source = sourceText.getText();
        if (enToVi) {
            SentenceToSpeech.playSoundGoogleTranslateEnToVi(source);
        } else {
            SentenceToSpeech.playSoundGoogleTranslateViToEn(source);
        }
    }

    /**
     * Change the current state `enToVi` to switch between English to Vietnamese or
     * Vietnamese to
     * English.
     */
    @FXML
    public void swapLanguage() {
        enToVi = !enToVi;
        if (enToVi) {
            upButton.setText("English");
            downButton.setText("Vietnamese");
        } else {
            upButton.setText("Vietnamese");
            downButton.setText("English");
        }
        if (sinkText.getText() != null) {
            String tmp = sinkText.getText();
            sinkText.setText(sourceText.getText());
            sourceText.setText(tmp);
        }
    }
}
