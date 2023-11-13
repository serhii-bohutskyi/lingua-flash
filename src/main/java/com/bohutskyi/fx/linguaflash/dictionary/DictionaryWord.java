package com.bohutskyi.fx.linguaflash.dictionary;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DictionaryWord {
    private String word;
    private String forms;
    private String shortDescription;
    private String longDescription;
}
