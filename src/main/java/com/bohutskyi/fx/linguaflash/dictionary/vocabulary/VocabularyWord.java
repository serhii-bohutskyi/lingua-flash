package com.bohutskyi.fx.linguaflash.dictionary.vocabulary;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VocabularyWord {
    private String word;
    private String forms;
    private String shortDescription;
    private String longDescription;
}
