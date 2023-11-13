package com.bohutskyi.fx.linguaflash.controller;

import com.bohutskyi.fx.linguaflash.dictionary.DictionaryClient;
import com.bohutskyi.fx.linguaflash.translate.TranslateClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ExecutorService;

@Slf4j
@Controller
public class MainController {

    private String targetLang = "es"; //todo move to select box

    private final DictionaryClient dictionaryClient;

    private final TranslateClient translateClient;

    private final ExecutorService executorService;

    @FXML
    private TextField wordLabel;

    @FXML
    private TextArea formsTextArea;

    @FXML
    private TextArea shortTextArea;

    @FXML
    private TextArea longTextArea;

    @FXML
    private TextField translatedWordLabel;

    @FXML
    private TextArea translatedFormsTextArea;

    @FXML
    private TextArea translatedShortTextArea;

    @FXML
    private TextArea translatedLongTextArea;

    @FXML
    private Button nextButton;

    @FXML
    private Button translateButton;

    public MainController(
            DictionaryClient dictionaryClient, TranslateClient translateClient,
            ExecutorService executorService
    ) {
        this.dictionaryClient = dictionaryClient;
        this.translateClient = translateClient;
        this.executorService = executorService;
    }

    @FXML
    public void handleNextAction() {
        //to avoid UI freezing
        executorService.submit(() -> {
            disableButtons();
            dictionaryClient.randomWord().subscribe(
                    word -> {
                        Platform.runLater(() -> {
                            wordLabel.setText(word.getWord());
                            formsTextArea.setText(word.getForms());
                            shortTextArea.setText(word.getShortDescription());
                            longTextArea.setText(word.getLongDescription());
                        });
                    },
                    error -> {
                        Platform.runLater(this::enableButtons);
                        log.atError().log("Exception on next action", error);
                    },
                    this::enableButtons
            );
        });
    }

    @FXML
    public void handleTranslateAction() {
        //to avoid UI freezing
        executorService.submit(() -> {
            disableButtons();
            try {
                String word = wordLabel.getText();
                String forms = formsTextArea.getText();
                String shortDesc = shortTextArea.getText();
                String longDesc = longTextArea.getText();

                //TODO translate at one request
                String wordTranslated = translateClient.translate(word, targetLang);
                String formsTranslated = translateClient.translate(forms, targetLang);
                String shortDescTranslated = translateClient.translate(shortDesc, targetLang);
                String longDescTranslated = translateClient.translate(longDesc, targetLang);

                Platform.runLater(() -> {
                    translatedWordLabel.setText(wordTranslated);
                    translatedFormsTextArea.setText(formsTranslated);
                    translatedShortTextArea.setText(shortDescTranslated);
                    translatedLongTextArea.setText(longDescTranslated);
                });

            } catch (Exception e) {
                log.atError().log("Exception on translation action", e);
            } finally {
                enableButtons();
            }
        });
    }

    private void disableButtons() {
        Platform.runLater(() -> {
            nextButton.setDisable(true);
            translateButton.setDisable(true);
        });
    }

    private void enableButtons() {
        Platform.runLater(() -> {
            nextButton.setDisable(false);
            translateButton.setDisable(false);
        });
    }
}
