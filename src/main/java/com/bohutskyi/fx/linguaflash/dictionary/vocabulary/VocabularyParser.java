package com.bohutskyi.fx.linguaflash.dictionary.vocabulary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VocabularyParser {

    private final String noForms;

    public VocabularyParser(@Value("${dictionary.vocabulary.noForms}") String noForms) {
        this.noForms = noForms;
    }

    public VocabularyWord parse(String html) {
        Document document = Jsoup.parse(html);

        String word = document.select("h1#hdr-word-area").get(0).text();
        String shortDescription = document.select("p.short").text();
        String longDescription = document.select("p.long").text();

        Elements formElements = document.select("p.word-forms");
        String forms = noForms;
        if (formElements.size() > 0) {
            forms = formElements.get(0).select("b").get(0).text();
        }

        return VocabularyWord.builder()
                             .word(word)
                             .shortDescription(shortDescription)
                             .longDescription(longDescription)
                             .forms(forms)
                             .build();
    }
}
